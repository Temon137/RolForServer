package rolfor.model;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public interface Repo<T extends Entity> {
	T find(int id);
	
	void remove(T item);
	
	T add(T item);
	
	T save(Integer id, T item);
	
	List<? extends T> findAll();
	
	<R> TypedQuery<R> getQuery(CriteriaQuery<R> query);
	
	
	CriteriaQuery<? extends T> getSelectQuery();
	
	TypedQuery<? extends T> getPagedQuery(CriteriaQuery<? extends T> query, int pageNumber, int pageSize);
	
	Long getPagesCount(CriteriaQuery<? extends T> query, int pageSize);
	
	
	Class<? extends T> getEntityClass();
	
	CriteriaBuilder getCriteriaBuilder();
}
