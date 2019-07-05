package rolfor.ejb.message;


import rolfor.ejb.AbstractEntityBean;
import rolfor.model.message.Message;
import rolfor.model.message.MessageRepo;
import rolfor.rest.PaginationParams;
import rolfor.ejb.message.fetcher.MessagesFromContainerFetcher;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Local(MessageBean.class)
@Stateless(name = "MessagesBeanImpl")
public class MessageBeanImpl extends AbstractEntityBean<Message, MessageRepo> implements MessageBean {
	@SuppressWarnings("unused")
	public MessageBeanImpl() {
		super(null);
	}
	
	@Inject
	public MessageBeanImpl(MessageRepo repo) {
		super(repo);
	}
	
	@Override
	public List<? extends Message> getMessagesFromContainer(Integer containerId) {
		return new MessagesFromContainerFetcher(repo).getTypedQuery().getResultList();
	}
	
	@Override
	public List<? extends Message> getMessagesFromContainerPaged(Integer containerId, PaginationParams pageParams) {
		var criteriaQuery = new MessagesFromContainerFetcher(repo).getCriteriaQuery();
		var typedQuery    = repo.getPagedQuery(criteriaQuery, pageParams.getPageNumber(), pageParams.getPageSize());
		return typedQuery.getResultList();
	}
}
