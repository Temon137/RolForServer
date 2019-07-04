package rolfor.model.user;


public interface MutableUser extends User {
	void setAvatarImageName(String avatarImageName);
	
	void setDescription(String description);
	
	void setLogin(String login);
	
	void setName(String name);
	
	void setPassword(String password);
	
	void setRole(Integer role);
}
