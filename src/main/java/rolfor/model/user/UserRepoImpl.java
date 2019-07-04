package rolfor.model.user;


import rolfor.model.AbstractRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(UserRepo.class)
@Stateless(name = "UserRepoImpl")
public class UserRepoImpl extends AbstractRepo<User, MutableUser, UserImpl> implements UserRepo {
	@Override
	public Class<UserImpl> getEntityClass() {
		return UserImpl.class;
	}
	
	@Override
	protected void copy(User from, MutableUser to) {
		to.setAvatarImageName(from.getAvatarImageName());
		to.setDescription(from.getDescription());
		to.setLogin(from.getLogin());
		to.setName(from.getName());
		to.setPassword(from.getPassword());
		to.setRole(from.getRole());
	}
}
