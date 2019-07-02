package rolfor.rest.containers;


import rolfor.model.container.Container;
import rolfor.model.container.ContainerRepo;
import rolfor.rest.AbstractREST;
import rolfor.rest.PaginationParams;
import rolfor.rest.containers.fetchers.ChildContainersFetcher;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;


@Path(value = "/containers")
public class ContainersREST extends AbstractREST<Container, ContainerRepo> {
	@SuppressWarnings("unused")
	public ContainersREST() {
		super(null);
	}
	
	@Inject
	public ContainersREST(ContainerRepo repo) {
		super(repo);
	}
	
	
	@GET
	@Path(value = "/{id}/children/")
	@Produces("application/json")
	public Response getChildren(@PathParam(value = "id") Integer id) {
		var list = repo.buildQuery(new ChildContainersFetcher(repo, id).getCriteriaQuery()).getResultList();
		return Response.ok(list).build();
	}
	
	@GET
	@Path(value = "/{id}/children/paged")
	@Produces("application/json")
	public Response getChildrenPaged(@PathParam(value = "id") Integer id,
	                                 @Valid @BeanParam PaginationParams pageParams) {
		var list = repo.getPagedQuery(new ChildContainersFetcher(repo, id).getCriteriaQuery(),
		                              pageParams.getPageNumber(),
		                              pageParams.getPageSize()).getResultList();
		return Response.ok(list).build();
	}
	
	@GET
	@Path(value = "/{id}/children/paged/count")
	@Produces("application/json")
	public Response getChildrenPagesCount(@PathParam(value = "id") Integer id,
	                                      @Valid @BeanParam PaginationParams pageParams) {
		var  fetcher    = new ChildContainersFetcher(repo, id);
		Long pagesCount = repo.getPagesCount(fetcher.getCriteriaQuery(), pageParams.getPageSize());
		return Response.ok(Map.of("pagesCount", pagesCount)).build();
	}
}
