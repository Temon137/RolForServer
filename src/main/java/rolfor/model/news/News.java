package rolfor.model.news;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
public class News {
	private int id;
	private String title;
	private Timestamp date;
	private int authorId;
	private String text;
	
	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		News news = (News) o;
		
		if (id != news.id) return false;
		if (authorId != news.authorId) return false;
		if (!Objects.equals(title, news.title)) return false;
		if (!Objects.equals(date, news.date)) return false;
		if (!Objects.equals(text, news.text)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + authorId;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}
	
	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	@Basic
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	@Basic
	@Column(name = "date")
	public Timestamp getDate() {
		return date;
	}
	
	@Basic
	@Column(name = "author_id")
	public int getAuthorId() {
		return authorId;
	}
	
	@Basic
	@Column(name = "text")
	public String getText() {
		return text;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
