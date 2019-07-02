package rolfor.rest.users;


import rolfor.model.user.User;
import rolfor.model.user.UserRepo;
import rolfor.rest.AbstractREST;

import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(value = "/users")
public class UsersREST extends AbstractREST<User, UserRepo> {
	@SuppressWarnings("unused")
	public UsersREST() {
		super(null);
	}
	
	@Inject
	public UsersREST(UserRepo repo) {
		super(repo);
	}
}
