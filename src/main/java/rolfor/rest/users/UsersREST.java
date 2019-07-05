package rolfor.rest.users;


import rolfor.ejb.user.UserBean;
import rolfor.model.user.User;
import rolfor.rest.AbstractREST;

import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(value = "/users")
public class UsersREST extends AbstractREST<User, UserBean> {
	@SuppressWarnings("unused")
	public UsersREST() {
		super(null);
	}
	
	@Inject
	public UsersREST(UserBean bean) {
		super(bean);
	}
}
