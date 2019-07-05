package rolfor.rest.users;


import rolfor.ejb.user.UserBean;
import rolfor.model.user.User;
import rolfor.rest.AbstractREST;

import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(value = "/users")
public class UserREST extends AbstractREST<User, UserBean> {
	@SuppressWarnings("unused")
	public UserREST() {
		super(null);
	}
	
	@Inject
	public UserREST(UserBean bean) {
		super(bean);
	}
}
