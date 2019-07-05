package rolfor.ejb;


import rolfor.model.Entity;
import rolfor.rest.PaginationParams;

import java.util.List;


public interface EntityBean<E extends Entity> {
	List<? extends E> getAll();
	
	List<? extends E> getAllPaged(PaginationParams pageParams);
	
	Long getAllPagesCount(PaginationParams pageParams);
	
	E get(Integer id);
	
	E create(E entity);
	
	E update(Integer id, E entity);
	
	void delete(Integer id);
}
