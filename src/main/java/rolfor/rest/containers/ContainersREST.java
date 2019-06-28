package rolfor.rest.containers;


import rolfor.model.Repo;
import rolfor.model.container.Container;
import rolfor.model.container.ContainerImpl;
import rolfor.model.container.ContainerRepo;
import rolfor.rest.containers.fetchers.ChildContainersFetcher;
import rolfor.rest.containers.fetchers.MessagesFromContainerFetcher;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path(value = "/containers")
public class ContainersREST {
	private final Repo<Container> containerRepo;
	
	public ContainersREST() {
		containerRepo = new ContainerRepo();
	}
	
	@GET
	@Path(value = "/{id}")
	@Produces("application/json")
	public Container getContainer(@PathParam(value = "id") Integer id) {
		return containerRepo.find(id);
	}
	
	@DELETE
	@Path(value = "/{id}")
	@Produces("application/json")
	public Response deleteContainer(@PathParam(value = "id") Integer id) {
		containerRepo.remove(containerRepo.find(id));
		return Response.status(201).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Container updateContainer(ContainerImpl container) {
		return containerRepo.save(container);
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Container createContainer(ContainerImpl container) {
		return containerRepo.add(container);
	}
	
	@GET
	@Path(value = "/{parentId}/children/")
	@Produces("application/json")
	public List<? extends Container> getChildrenContainers(@PathParam(value = "parentId") Integer parentId) {
		return getChildrenContainersPage(parentId, 1);
	}
	
	@GET
	@Path(value = "/{parentId}/children/page{pageNumber}")
	@Produces("application/json")
	public List<? extends Container> getChildrenContainersPage(@PathParam(value = "parentId") Integer parentId,
	                                                           @PathParam(value = "pageNumber") Integer pageNumber) {
		return containerRepo.findFromPage(new ChildContainersFetcher(containerRepo, parentId).getCriteriaQuery(),
		                                  pageNumber,
		                                  5);
	}
	
	@GET
	@Path(value = "/{parentId}/children/pages")
	@Produces("application/json")
	public Long getChildrenContainersPagesCount(@PathParam(value = "parentId") Integer parentId) {
		ChildContainersFetcher fetcher = new ChildContainersFetcher(containerRepo, parentId);
		return containerRepo.getPagesCount(fetcher.getCriteriaQuery(), 5);
	}
	
	@GET
	@Path(value = "/{parentId}/test2")
	@Produces("application/json")
	public Integer test(@PathParam(value = "parentId") Integer parentId) {
		MessagesFromContainerFetcher fetcher = new MessagesFromContainerFetcher(containerRepo, parentId);
		return containerRepo.getQuery(fetcher.getCriteriaQuery()).getSingleResult();
	}
	
	@GET
	@Produces("application/json")
	public List<? extends Container> getAllContainers() {
		return containerRepo.findAll();
	}
}
