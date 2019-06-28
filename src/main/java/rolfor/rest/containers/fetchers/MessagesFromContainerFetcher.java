package rolfor.rest.containers.fetchers;


import rolfor.model.AbstractFetcher;
import rolfor.model.container.Container;
import rolfor.model.container.ContainerRepo;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class MessagesFromContainerFetcher extends AbstractFetcher<Integer, Container> {
	private final Integer cont;
	
	public MessagesFromContainerFetcher(ContainerRepo repo, Integer cont) {
		super(repo);
		this.cont = cont;
	}
	
	@Override
	public CriteriaQuery<Integer> getCriteriaQuery() {
		CriteriaQuery<Integer>    cq    = cb.createQuery(Integer.class);
		Root<? extends Container> cRoot = cq.from(repo.getEntityClass());
		return cq.select(cb.max(cRoot.get("id"))).where(cb.lessThan(cRoot.get("id"), cont));
	}
}
