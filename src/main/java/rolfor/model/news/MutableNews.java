package rolfor.model.news;


import java.sql.Timestamp;


public interface MutableNews extends News {
	void setAuthorId(Integer authorId);
	
	void setDate(Timestamp date);
	
	void setTitle(String title);
	
	void setText(String text);
}
