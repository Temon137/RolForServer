package rolfor.model.container;


import rolfor.model.Entity;


public interface Container extends Entity {
	Integer getParentId();
	
	String getTitle();
	
	Integer getAuthorId();
	
	String getImageName();
	
	String getDescription();
}
