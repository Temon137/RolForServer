package rolfor.model.message;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "messages")
public class MessageImpl implements MutableMessage {
	private Integer   id;
	private Integer   containerId;
	private Integer   authorId;
	private Timestamp date;
	private String    text;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "container_id")
	public Integer getContainerId() {
		return containerId;
	}
	
	@Column(name = "user_id")
	public Integer getAuthorId() {
		return authorId;
	}
	
	@Column(name = "date")
	public Timestamp getDate() {
		return date;
	}
	
	@Column(name = "text")
	public String getText() {
		return text;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setContainerId(Integer containerId) {
		this.containerId = containerId;
	}
	
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		MessageImpl message = (MessageImpl) o;
		
		if (!id.equals(message.id)) return false;
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
}
