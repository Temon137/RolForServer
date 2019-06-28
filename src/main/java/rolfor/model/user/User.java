package rolfor.model.user;


import rolfor.model.Entity;


public interface User extends Entity {
	String getDescription();
	
	String getAvatarImageName();
	
	Integer getRole();
	
	String getPassword();
	
	String getLogin();
	
	String getName();
}
