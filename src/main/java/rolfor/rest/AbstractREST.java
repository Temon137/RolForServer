package rolfor.rest;


import rolfor.model.Entity;
import rolfor.model.Repo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


public class AbstractREST<E extends Entity, R extends Repo<E>> {
	protected final R repo;
	
	public AbstractREST(R repo) {
		this.repo = repo;
	}
	
	@GET
	@Path(value = "/{id}")
	@Produces("application/json")
	public E get(@PathParam(value = "id") Integer id) {
		return repo.find(id);
	}
	
	@DELETE
	@Path(value = "/{id}")
	@Produces("application/json")
	public Response delete(@PathParam(value = "id") Integer id) {
		repo.remove(repo.find(id));
		return Response.status(201).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public E update(E container) {
		return repo.save(container);
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public E create(E container) {
		return repo.add(container);
	}
	
	@GET
	@Produces("application/json")
	public List<? extends E> getAll() {
		return repo.findAll();
	}
}
