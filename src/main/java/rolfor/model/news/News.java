package rolfor.model.news;


import rolfor.model.Entity;

import java.sql.Timestamp;


public interface News extends Entity {
	String getTitle();
	
	Timestamp getDate();
	
	Integer getAuthorId();
	
	String getText();
}
