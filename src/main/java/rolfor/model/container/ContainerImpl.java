package rolfor.model.container;


import rolfor.model.AbstractEntity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "containers")
public class ContainerImpl extends AbstractEntity implements Container {
	private Integer parentId;
	private String  title;
	private Integer authorId;
	private String  imageName;
	private String  description;
	
	public ContainerImpl() {
	}
	
	public ContainerImpl(Integer id,
	                     Integer parentId,
	                     String title,
	                     Integer authorId,
	                     String imageName,
	                     String description) {
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.authorId = authorId;
		this.imageName = imageName;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Basic
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	@Basic
	@Column(name = "image_name")
	public String getImageName() {
		return imageName;
	}
	
	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	@Basic
	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}
	
	@Basic
	@Column(name = "author_id")
	public Integer getAuthorId() {
		return authorId;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		ContainerImpl that = (ContainerImpl) o;
		
		if (!id.equals(that.id)) return false;
		if (!authorId.equals(that.authorId)) return false;
		if (!Objects.equals(parentId, that.parentId)) return false;
		if (!Objects.equals(title, that.title)) return false;
		if (!Objects.equals(imageName, that.imageName)) return false;
		if (!Objects.equals(description, that.description)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + authorId;
		result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
