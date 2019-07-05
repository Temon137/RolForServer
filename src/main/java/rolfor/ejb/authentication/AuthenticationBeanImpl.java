package rolfor.ejb.authentication;


import rolfor.model.token.MutableToken;
import rolfor.model.token.Token;
import rolfor.model.token.TokenRepo;
import rolfor.model.token.fetcher.TokenByStringFetcher;
import rolfor.model.user.User;
import rolfor.model.user.UserRepo;
import rolfor.model.user.fetcher.UserForAuthFetcher;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Local(AuthenticationBean.class)
@Stateless(name = "TokenBeanImpl")
public class AuthenticationBeanImpl implements AuthenticationBean {
	private static final int EXPIRATION_HOURS            = 1;
	private static final int EXPIRATION_HOURS_REMEMBERED = 12;
	
	@Inject
	private UserRepo  userRepo;
	@Inject
	private TokenRepo tokenRepo;
	
	@Override
	public User authenticate(String login, String password) throws AuthenticationException {
		var result = userRepo.buildQuery(new UserForAuthFetcher(userRepo, login, password).getCriteriaQuery())
		                     .getResultList();
		if (result.isEmpty()) {
			throw new AuthenticationException("Пользователя с указанными данными не существует.");
		}
		if (result.size() > 1) {
			throw new RuntimeException("Одновременно существуют несолько пользователей с одинаковой сигнатурой.");
		}
		
		return result.get(0);
	}
	
	@Override
	public Token createToken(User user, Boolean isRemembered) {
		Token existingToken = tokenRepo.find(user.getId());
		if (existingToken != null) {
			tokenRepo.remove(existingToken);
		}
		
		MutableToken token = tokenRepo.createEmpty();
		token.setUserId(user.getId());
		token.setTokenString(generateString(20));
		token.setRemembered(isRemembered);
		int addedHours = isRemembered ? EXPIRATION_HOURS_REMEMBERED : EXPIRATION_HOURS;
		token.setExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusHours(addedHours)));
		
		return tokenRepo.add(token);
	}
	
	@Override
	public Token getTokenByString(String tokenString) throws AuthenticationException {
		var result = tokenRepo.buildQuery(new TokenByStringFetcher(tokenRepo, tokenString).getCriteriaQuery())
		                      .getResultList();
		if (result.isEmpty()) {
			throw new AuthenticationException("Указан невалидный токен авторизации.");
		}
		if (result.size() > 1) {
			throw new RuntimeException("Одновременно существуют несколько одинаковых токенов.");
		}
		Token token = result.get(0);
		if (token.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
			tokenRepo.remove(token);
			throw new AuthenticationException("Срок действия указанного токена авторизации истёк.");
		}
		return token;
	}
	
	@Override
	public Token refreshToken(Token token) {
		MutableToken  mutableToken      = tokenRepo.findMutable(token.getId());
		int           addedHours        = token.getRemembered() ? EXPIRATION_HOURS_REMEMBERED : EXPIRATION_HOURS;
		LocalDateTime newExpirationDate = LocalDateTime.now().plusHours(addedHours);
		mutableToken.setExpirationDate(Timestamp.valueOf(newExpirationDate));
		return tokenRepo.save(mutableToken.getId(), mutableToken);
	}
	
	@Override
	public void logout(Token token) {
		tokenRepo.remove(token);
	}
	
	private String generateString(int length) {
		String symbols = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd     = new Random();
		return IntStream.generate(() -> rnd.nextInt(symbols.length()))
		                .limit(length)
		                .mapToObj(symbols::charAt)
		                .map(String::valueOf)
		                .collect(Collectors.joining());
	}
}
