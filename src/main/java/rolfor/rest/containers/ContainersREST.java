package rolfor.rest.containers;


import rolfor.model.container.Container;
import rolfor.model.container.ContainerRepo;
import rolfor.rest.AbstractREST;
import rolfor.rest.PaginationParams;
import rolfor.rest.containers.fetchers.ChildContainersFetcher;
import rolfor.rest.containers.fetchers.MessagesFromContainerFetcher;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;


@Path(value = "/containers")
public class ContainersREST extends AbstractREST<Container, ContainerRepo> {
	@SuppressWarnings("unused")
	public ContainersREST() {
		super(null);
	}
	
	@Inject
	public ContainersREST(ContainerRepo containerRepo) {
		super(containerRepo);
	}
	
	@GET
	@Path(value = "/{parentId}/children/")
	@Produces("application/json")
	public List<? extends Container> getChildrenContainers(@PathParam(value = "parentId") Integer parentId) {
		return getChildrenContainersPage(parentId, new PaginationParams());
	}
	
	@GET
	@Path(value = "/{parentId}/children/")
	@Produces("application/json")
	public List<? extends Container> getChildrenContainersPage(@PathParam(value = "parentId") Integer parentId,
	                                                           @Valid @BeanParam PaginationParams pageParams) {
		return repo.getPagedQuery(new ChildContainersFetcher(repo, parentId).getCriteriaQuery(),
		                          pageParams.getPageNumber(),
		                          pageParams.getPageSize()).getResultList();
	}
	
	@GET
	@Path(value = "/{parentId}/children/pages")
	@Produces("application/json")
	public Long getChildrenContainersPagesCount(@PathParam(value = "parentId") Integer parentId,
	                                            @Valid @BeanParam PaginationParams pageParams) {
		ChildContainersFetcher fetcher = new ChildContainersFetcher(repo, parentId);
		return repo.getPagesCount(fetcher.getCriteriaQuery(), pageParams.getPageSize());
	}
	
	@GET
	@Path(value = "/{parentId}/test2")
	@Produces("application/json")
	public Integer test(@PathParam(value = "parentId") Integer parentId) {
		MessagesFromContainerFetcher fetcher = new MessagesFromContainerFetcher(repo, parentId);
		return repo.getQuery(fetcher.getCriteriaQuery()).getSingleResult();
	}
}
