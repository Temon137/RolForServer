package rolfor.model.container;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rolfor.model.Entity;


@JsonDeserialize(as = ContainerImpl.class)
public interface Container extends Entity {
	Integer getParentId();
	
	String getTitle();
	
	Integer getAuthorId();
	
	String getImageName();
	
	String getDescription();
}
