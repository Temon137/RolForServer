package rolfor.ejb.user;


import rolfor.ejb.AbstractEntityBean;
import rolfor.model.user.User;
import rolfor.model.user.UserRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Local(UserBean.class)
@Stateless(name = "UserBeanImpl")
public class UserBeanImpl extends AbstractEntityBean<User, UserRepo> implements UserBean {
	@SuppressWarnings("unused")
	public UserBeanImpl() {
		super(null);
	}
	
	@Inject
	public UserBeanImpl(UserRepo repo) {
		super(repo);
	}
}
