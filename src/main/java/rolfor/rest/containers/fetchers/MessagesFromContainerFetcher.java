package rolfor.rest.containers.fetchers;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.container.ContainerImpl;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class MessagesFromContainerFetcher extends AbstractFetcher<Integer, ContainerImpl> {
	private final Integer cont;
	
	public MessagesFromContainerFetcher(Repo<ContainerImpl> repo, Integer cont) {
		super(repo);
		this.cont = cont;
	}

	@Override
	public CriteriaQuery<Integer> getCriteriaQuery() {
		CriteriaQuery<Integer> cq    = cb.createQuery(Integer.class);
		Root<ContainerImpl>    cRoot = cq.from(ContainerImpl.class);
		return cq.select(cb.max(cRoot.get("id"))).where(cb.lessThan(cRoot.get("id"), cont));
	}
}
