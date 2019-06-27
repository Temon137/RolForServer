package rolfor.model.message;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "messages")
public class Message implements rolfor.model.Entity {
	private int       id;
	private Timestamp date;
	private String    text;
	
	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Message message = (Message) o;
		
		if (id != message.id) return false;
		if (!Objects.equals(date, message.date)) return false;
		if (!Objects.equals(text, message.text)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}
	
	@Id
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Basic
	@Column(name = "date")
	public Timestamp getDate() {
		return date;
	}
	
	@Basic
	@Column(name = "text")
	public String getText() {
		return text;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
