package rolfor.ejb.message;


import rolfor.ejb.EntityBean;
import rolfor.model.message.Message;
import rolfor.rest.PaginationParams;

import java.util.List;


public interface MessageBean extends EntityBean<Message> {
	List<? extends Message> getMessagesFromContainer(Integer containerId);
	
	List<? extends Message> getMessagesFromContainerPaged(Integer containerId, PaginationParams pageParams);
}
