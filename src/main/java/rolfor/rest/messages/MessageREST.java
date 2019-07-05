package rolfor.rest.messages;


import rolfor.ejb.message.MessageBean;
import rolfor.model.message.Message;
import rolfor.rest.AbstractREST;
import rolfor.rest.PaginationParams;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path(value = "/messages")
public class MessageREST extends AbstractREST<Message, MessageBean> {
	@SuppressWarnings("unused")
	public MessageREST() {
		super(null);
	}
	
	@Inject
	public MessageREST(MessageBean bean) {
		super(bean);
	}
	
	
	@GET
	@Path("/from/{containerId}")
	public Response getMessagesFromContainer(@PathParam(value = "containerId") Integer containerId) {
		var messages = bean.getMessagesFromContainer(containerId);
		return Response.ok(messages).build();
	}
	
	@GET
	@Path("/from/{containerId}/paged")
	public Response getMessagesFromContainerPaged(@PathParam(value = "containerId") Integer containerId,
	                                              @Valid @BeanParam PaginationParams pageParams) {
		var messages = bean.getMessagesFromContainerPaged(containerId, pageParams);
		return Response.ok(messages).build();
	}
}
