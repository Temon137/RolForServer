package rolfor.model.message;


import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;


public interface Message extends rolfor.model.Entity {
	@Basic
	@Column(name = "date")
	Timestamp getDate();
	
	@Basic
	@Column(name = "text")
	String getText();
}
