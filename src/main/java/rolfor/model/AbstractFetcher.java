package rolfor.model;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;


public abstract class AbstractFetcher<T, E extends Entity> implements Fetcher<T> {
	protected final Repo<E>         repo;
	protected final CriteriaBuilder cb;
	
	public AbstractFetcher(Repo<E> repo) {
		this.repo = repo;
		this.cb = repo.getCriteriaBuilder();
	}
	
	@Override
	public TypedQuery<? extends T> getTypedQuery() {
		return repo.getQuery(getCriteriaQuery());
	}
}
