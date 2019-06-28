package rolfor.rest.containers.fetchers;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.container.Container;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ChildContainersFetcher extends AbstractFetcher<Container, Container> {
	private final Integer parentId;
	
	public ChildContainersFetcher(Repo<Container> repo, Integer parentId) {
		super(repo);
		this.parentId = parentId;
	}
	
	@Override
	public CriteriaQuery<? extends Container> getCriteriaQuery() {
		var     selectQuery = repo.getSelectQuery();
		Root<?> root        = selectQuery.getRoots().iterator().next();
		return selectQuery.where(cb.equal(root.get("parentId"), parentId));
	}
}
