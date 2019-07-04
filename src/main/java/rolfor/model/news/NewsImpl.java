package rolfor.model.news;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "news")
public class NewsImpl implements MutableNews {
	private Integer   id;
	private String    title;
	private Timestamp date;
	private Integer   authorId;
	private String    text;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	@Column(name = "date")
	public Timestamp getDate() {
		return date;
	}
	
	@Column(name = "author_id")
	public Integer getAuthorId() {
		return authorId;
	}
	
	@Column(name = "text")
	public String getText() {
		return text;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		NewsImpl news = (NewsImpl) o;
		
		if (!id.equals(news.id)) return false;
		if (!authorId.equals(news.authorId)) return false;
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
}
