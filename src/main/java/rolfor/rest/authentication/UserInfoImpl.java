package rolfor.rest.authentication;


import rolfor.model.token.Token;
import rolfor.model.user.User;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;


@Local({UserInfo.class, UserInfoInitiator.class})
@RequestScoped
public class UserInfoImpl implements UserInfo, UserInfoInitiator {
	private boolean isInit = false;
	private User    user;
	private Token   token;
	
	@Override
	public User getUser() {
		if (!isInit) {
			throw new RuntimeException("Текущий пользователь не был проинициализирован.");
		}
		return user;
	}
	
	@Override
	public Token getToken() {
		if (!isInit) {
			throw new RuntimeException("Текущий пользователь не был проинициализирован.");
		}
		return token;
	}
	
	@Override
	public void init(User user, Token token) {
		this.user = user;
		this.token = token;
		isInit = true;
	}
}
