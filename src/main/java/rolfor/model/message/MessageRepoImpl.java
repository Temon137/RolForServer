package rolfor.model.message;


import rolfor.model.AbstractRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(MessageRepo.class)
@Stateless(name = "MessageRepoImpl")
public class MessageRepoImpl extends AbstractRepo<Message, MutableMessage, MessageImpl> implements MessageRepo {
	@Override
	public Class<MessageImpl> getEntityClass() {
		return MessageImpl.class;
	}
	
	@Override
	protected MutableMessage copy(Message from, MutableMessage to) {
		to.setContainerId(from.getContainerId());
		to.setAuthorId(from.getAuthorId());
		to.setDate(from.getDate());
		to.setText(from.getText());
		return to;
	}
}
