package rolfor.rest.containers.fetchers;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.container.Container;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class MessagesFromContainerFetcher extends AbstractFetcher<Integer, Container> {
	private final Integer cont;
	
	public MessagesFromContainerFetcher(Repo<Container> repo, Integer cont) {
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
