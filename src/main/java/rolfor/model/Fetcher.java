package rolfor.model;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;


public interface Fetcher<T> {
	CriteriaQuery<? extends T> getCriteriaQuery();
	
	TypedQuery<? extends T> getTypedQuery();
}
