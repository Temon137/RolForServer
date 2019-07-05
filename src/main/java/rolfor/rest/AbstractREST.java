package rolfor.rest;


import rolfor.ejb.EntityBean;
import rolfor.model.Entity;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;


public class AbstractREST<E extends Entity, B extends EntityBean<E>> {
	protected final B bean;
	
	public AbstractREST(B bean) {
		this.bean = bean;
	}
	
	@GET
	@Produces("application/json")
	public Response getAll() {
		var all = bean.getAll();
		return Response.ok(all).build();
	}
	
	@GET
	@Path(value = "/paged")
	@Produces("application/json")
	public Response getAllPaged(@Valid @BeanParam PaginationParams pageParams) {
		var allPage = bean.getAllPaged(pageParams);
		return Response.ok(allPage).build();
	}
	
	@GET
	@Path(value = "/paged/count")
	@Produces("application/json")
	public Response getAllPagesCount(@Valid @BeanParam PaginationParams pageParams) {
		var pagesCount = bean.getAllPagesCount(pageParams);
		return Response.ok(Map.of("pagesCount", pagesCount)).build();
	}
	
	@GET
	@Path(value = "/{id}")
	@Produces("application/json")
	public Response get(@PathParam(value = "id") Integer id) {
		E entity = bean.get(id);
		return Response.ok(entity).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(E entity) {
		E newEntity = bean.create(entity);
		return Response.ok(newEntity).status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Path(value = "/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam(value = "id") Integer id, E entity) {
		E updatedEntity = bean.update(id, entity);
		return Response.ok(updatedEntity).status(Response.Status.CREATED).build();
	}
	
	@DELETE
	@Path(value = "/{id}")
	@Produces("application/json")
	public Response delete(@PathParam(value = "id") Integer id) {
		bean.delete(id);
		return Response.ok().build();
	}
}
