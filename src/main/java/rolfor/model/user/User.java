package rolfor.model.user;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rolfor.model.Entity;


@JsonDeserialize(as = UserImpl.class)
public interface User extends Entity {
	String getDescription();
	
	String getAvatarImageName();
	
	Integer getRole();
	
	String getPassword();
	
	String getLogin();
	
	String getName();
}
