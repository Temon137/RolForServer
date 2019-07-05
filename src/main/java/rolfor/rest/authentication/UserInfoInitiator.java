package rolfor.rest.authentication;


import rolfor.model.token.Token;
import rolfor.model.user.User;


public interface UserInfoInitiator {
	void init(User user, Token token);
}
