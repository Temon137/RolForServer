package rolfor.model.message;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rolfor.model.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;


@JsonDeserialize(as = MessageImpl.class)
public interface Message extends Entity {
	@Basic
	@Column(name = "date")
	Timestamp getDate();
	
	@Basic
	@Column(name = "text")
	String getText();
}
