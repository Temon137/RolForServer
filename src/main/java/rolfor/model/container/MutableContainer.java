package rolfor.model.container;


public interface MutableContainer extends Container {
	void setParentId(Integer parentId);
	
	void setTitle(String title);
	
	void setAuthorId(Integer authorId);
	
	void setImageName(String imageName);
	
	void setDescription(String description);
}
