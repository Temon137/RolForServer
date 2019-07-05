package rolfor.ejb.authentication;


import rolfor.model.token.Token;
import rolfor.model.user.User;

import javax.security.enterprise.AuthenticationException;


public interface AuthenticationBean {
	User authenticate(String login, String password) throws AuthenticationException;
	
	Token createToken(User user, Boolean isRemembered);
	
	Token getTokenByString(String tokenString) throws AuthenticationException;
	
	Token refreshToken(Token token);
	
	void logout(Token token);
}
