package rolfor.ejb.container;


import rolfor.ejb.AbstractEntityBean;
import rolfor.model.container.Container;
import rolfor.model.container.ContainerRepo;
import rolfor.rest.PaginationParams;
import rolfor.rest.containers.fetcher.ChildContainersFetcher;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Local(ContainerBean.class)
@Stateless(name = "ContainerBeanImpl")
public class ContainerBeanImpl extends AbstractEntityBean<Container, ContainerRepo> implements ContainerBean {
	@SuppressWarnings("unused")
	public ContainerBeanImpl() {
		super(null);
	}
	
	@Inject
	public ContainerBeanImpl(ContainerRepo repo) {
		super(repo);
	}
	
	@Override
	public List<? extends Container> getChildren(Integer id) {
		return repo.buildQuery(new ChildContainersFetcher(repo, id).getCriteriaQuery()).getResultList();
	}
	
	@Override
	public List<? extends Container> getChildrenPaged(Integer id, PaginationParams pageParams) {
		return repo.getPagedQuery(new ChildContainersFetcher(repo, id).getCriteriaQuery(),
		                          pageParams.getPageNumber(),
		                          pageParams.getPageSize()).getResultList();
	}
	
	@Override
	public Long getChildrenPagesCount(Integer id, PaginationParams pageParams) {
		var fetcher = new ChildContainersFetcher(repo, id);
		return repo.getPagesCount(fetcher.getCriteriaQuery(), pageParams.getPageSize());
	}
}
