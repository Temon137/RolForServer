package rolfor.model.news;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rolfor.model.Entity;

import java.sql.Timestamp;


@JsonDeserialize(as = NewsImpl.class)
public interface News extends Entity {
	String getTitle();
	
	Timestamp getDate();
	
	Integer getAuthorId();
	
	String getText();
}
