package rolfor.rest;


import rolfor.model.Entity;
import rolfor.model.Repo;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;


public class AbstractREST<E extends Entity, R extends Repo<E>> {
	protected final R repo;
	
	public AbstractREST(R repo) {
		this.repo = repo;
	}
	
	@GET
	@Produces("application/json")
	public Response getAll() {
		var allQuery = repo.getAllQuery();
		var all = repo.buildQuery(allQuery).getResultList();
		return Response.ok(all).build();
	}
	
	@GET
	@Path(value = "/paged")
	@Produces("application/json")
	public Response getAllPaged(@Valid @BeanParam PaginationParams pageParams) {
		var allQuery   = repo.getAllQuery();
		var pagedQuery = repo.getPagedQuery(allQuery, pageParams.getPageNumber(), pageParams.getPageSize());
		var allPage    = pagedQuery.getResultList();
		return Response.ok(allPage).build();
	}
	
	@GET
	@Path(value = "/paged/count")
	@Produces("application/json")
	public Response getAllPagesCount(@Valid @BeanParam PaginationParams pageParams) {
		var allQuery   = repo.getAllQuery();
		var pagesCount = repo.getPagesCount(allQuery, pageParams.getPageSize());
		return Response.ok(Map.of("pagesCount", pagesCount)).build();
	}
	
	@GET
	@Path(value = "/{id}")
	@Produces("application/json")
	public Response get(@PathParam(value = "id") Integer id) {
		E entity = repo.find(id);
		return Response.ok(entity).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(E entity) {
		E newEntity = repo.add(entity);
		return Response.ok(newEntity).status(201).build();
	}
	
	@PUT
	@Path(value = "/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam(value = "id") Integer id, E entity) {
		E updatedEntity = repo.save(id, entity);
		return Response.ok(updatedEntity).status(201).build();
	}
	
	@DELETE
	@Path(value = "/{id}")
	@Produces("application/json")
	public Response delete(@PathParam(value = "id") Integer id) {
		repo.remove(repo.find(id));
		return Response.ok().build();
	}
}
