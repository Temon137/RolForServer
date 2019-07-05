package rolfor.ejb.container;


import rolfor.ejb.EntityBean;
import rolfor.model.container.Container;
import rolfor.rest.PaginationParams;

import java.util.List;


public interface ContainerBean extends EntityBean<Container> {
	List<? extends Container> getChildren(Integer id);
	
	List<? extends Container> getChildrenPaged(Integer id, PaginationParams pageParams);
	
	Long getChildrenPagesCount(Integer id, PaginationParams pageParams);
}
