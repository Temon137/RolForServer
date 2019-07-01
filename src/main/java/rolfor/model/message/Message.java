package rolfor.model.message;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rolfor.model.Entity;

import java.sql.Timestamp;


@JsonDeserialize(as = MessageImpl.class)
public interface Message extends Entity {
	Integer getContainerId();
	
	Integer getAuthorId();
	
	Timestamp getDate();
	
	String getText();
}
