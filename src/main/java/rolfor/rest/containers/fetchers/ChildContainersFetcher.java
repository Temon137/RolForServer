package rolfor.rest.containers.fetchers;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.container.ContainerImpl;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ChildContainersFetcher extends AbstractFetcher<ContainerImpl, ContainerImpl> {
	private final Integer parentId;
	
	public ChildContainersFetcher(Repo<ContainerImpl> repo, Integer parentId) {
		super(repo);
		this.parentId = parentId;
	}
	
	@Override
	public CriteriaQuery<ContainerImpl> getCriteriaQuery() {
		Class<ContainerImpl>         clazz = ContainerImpl.class;
		CriteriaQuery<ContainerImpl> cq    = cb.createQuery(clazz);
		Root<ContainerImpl>          root  = cq.from(clazz);
		return cq.select(root).where(cb.equal(root.get("parentId"), parentId));
	}
}
