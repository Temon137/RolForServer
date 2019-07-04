package rolfor.model.message;


import java.sql.Timestamp;


public interface MutableMessage extends Message {
	void setContainerId(Integer containerId);
	
	void setAuthorId(Integer authorId);
	
	void setDate(Timestamp date);
	
	void setText(String text);
}
