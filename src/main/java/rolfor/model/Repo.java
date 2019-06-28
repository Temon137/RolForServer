package rolfor.model;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public interface Repo<T extends Entity> {
	T find(int id);
	
	void remove(T item);
	
	T add(T item);
	
	T save(T item);
	
	List<? extends T> findAll();
	
	<R> TypedQuery<R> getQuery(CriteriaQuery<R> query);
	
	
	<R extends Entity> Long getPagesCount(CriteriaQuery<R> query, int pageSize);
	
	CriteriaQuery<? extends T> getSelectQuery();
	
	List<? extends T> findFromPage(CriteriaQuery<? extends T> query, int pageNumber, int pageSize);
	
	
	Class<? extends T> getEntityClass();
	
	CriteriaBuilder getCriteriaBuilder();
}
