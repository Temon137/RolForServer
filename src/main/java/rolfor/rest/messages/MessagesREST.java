package rolfor.rest.messages;


import rolfor.model.message.Message;
import rolfor.model.message.MessageRepo;
import rolfor.rest.AbstractREST;
import rolfor.rest.PaginationParams;
import rolfor.rest.messages.fetchers.MessagesFromContainerFetcher;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path(value = "/messages")
public class MessagesREST extends AbstractREST<Message, MessageRepo> {
	@SuppressWarnings("unused")
	public MessagesREST() {
		super(null);
	}
	
	@Inject
	public MessagesREST(MessageRepo repo) {
		super(repo);
	}
	
	@GET
	@Path("/from/{containerId}")
	public Response getMessagesFromContainer(@PathParam(value = "containerId") Integer containerId) {
		var messages = new MessagesFromContainerFetcher(repo).getTypedQuery().getResultList();
		return Response.ok(messages).build();
	}
	
	@GET
	@Path("/from/{containerId}/paged")
	public Response getMessagesFromContainerPaged(@PathParam(value = "containerId") Integer containerId,
	                                              @Valid @BeanParam PaginationParams pageParams) {
		var criteriaQuery = new MessagesFromContainerFetcher(repo).getCriteriaQuery();
		var typedQuery    = repo.getPagedQuery(criteriaQuery, pageParams.getPageNumber(), pageParams.getPageSize());
		var messages      = typedQuery.getResultList();
		return Response.ok(messages).build();
	}
}
