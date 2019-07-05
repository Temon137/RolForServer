package rolfor.model.token;


import rolfor.model.Entity;

import java.sql.Timestamp;


public interface Token extends Entity {
	Integer getUserId();
	
	String getTokenString();
	
	Timestamp getExpirationDate();
	
	Boolean getRemembered();
}
