package rolfor.model.token;


import rolfor.model.AbstractRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(TokenRepo.class)
@Stateless(name = "TokenRepoImpl")
public class TokenRepoImpl extends AbstractRepo<Token, MutableToken, TokenImpl> implements TokenRepo {
	@Override
	public Class<TokenImpl> getEntityClass() {
		return TokenImpl.class;
	}
	
	@Override
	public MutableToken createEmpty() {
		return new TokenImpl();
	}
	
	@Override
	protected void copy(Token from, MutableToken to) {
		to.setUserId(from.getUserId());
		to.setTokenString(from.getTokenString());
		to.setExpirationDate(from.getExpirationDate());
		to.setRemembered(from.getRemembered());
	}
}
