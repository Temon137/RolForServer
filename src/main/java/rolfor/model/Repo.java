package rolfor.model;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


public interface Repo<T extends Entity, M extends T> {
	T find(Integer id);
	
	M findMutable(Integer id);
	
	void remove(T item);
	
	T add(T item);
	
	T save(Integer id, T item);
	
	CriteriaQuery<? extends T> getAllQuery();
	
	<R> TypedQuery<R> buildQuery(CriteriaQuery<R> query);
	
	
	CriteriaQuery<? extends T> getSelectQuery();
	
	TypedQuery<? extends T> getPagedQuery(CriteriaQuery<? extends T> query, int pageNumber, int pageSize);
	
	Long getPagesCount(CriteriaQuery<? extends T> query, int pageSize);
	
	
	Class<? extends T> getEntityClass();
	
	CriteriaBuilder getCriteriaBuilder();
}
