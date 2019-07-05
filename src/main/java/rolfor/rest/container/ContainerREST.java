package rolfor.rest.container;


import rolfor.ejb.container.ContainerBean;
import rolfor.model.container.Container;
import rolfor.rest.AbstractREST;
import rolfor.rest.PaginationParams;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;


@Path(value = "/containers")
public class ContainerREST extends AbstractREST<Container, ContainerBean> {
	@SuppressWarnings("unused")
	public ContainerREST() {
		super(null);
	}
	
	@Inject
	public ContainerREST(ContainerBean bean) {
		super(bean);
	}
	
	
	@GET
	@Path(value = "/{id}/children/")
	@Produces("application/json")
	public Response getChildren(@PathParam(value = "id") Integer id) {
		var list = bean.getChildren(id);
		return Response.ok(list).build();
	}
	
	@GET
	@Path(value = "/{id}/children/paged")
	@Produces("application/json")
	public Response getChildrenPaged(@PathParam(value = "id") Integer id,
	                                 @Valid @BeanParam PaginationParams pageParams) {
		var list = bean.getChildrenPaged(id, pageParams);
		return Response.ok(list).build();
	}
	
	@GET
	@Path(value = "/{id}/children/paged/count")
	@Produces("application/json")
	public Response getChildrenPagesCount(@PathParam(value = "id") Integer id,
	                                      @Valid @BeanParam PaginationParams pageParams) {
		Long pagesCount = bean.getChildrenPagesCount(id, pageParams);
		return Response.ok(Map.of("pagesCount", pagesCount)).build();
	}
}
