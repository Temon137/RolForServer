package rolfor.model.user;


import rolfor.model.AbstractRepo;


public class UserRepoImpl extends AbstractRepo<User, UserImpl> implements UserRepo {
	@Override
	public Class<UserImpl> getEntityClass() {
		return UserImpl.class;
	}
	
	@Override
	protected UserImpl copy(User from, UserImpl to) {
		to.setId(from.getId());
		to.setAvatarImageName(from.getAvatarImageName());
		to.setDescription(from.getDescription());
		to.setLogin(from.getLogin());
		to.setName(from.getName());
		to.setPassword(from.getPassword());
		to.setRole(from.getRole());
		return to;
	}
}
