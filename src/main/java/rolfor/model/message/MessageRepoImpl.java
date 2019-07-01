package rolfor.model.message;


import rolfor.model.AbstractRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(MessageRepo.class)
@Stateless(name = "MessageRepoImpl")
public class MessageRepoImpl extends AbstractRepo<Message, MessageImpl> implements MessageRepo {
	@Override
	public Class<MessageImpl> getEntityClass() {
		return MessageImpl.class;
	}
	
	@Override
	protected MessageImpl copy(Message from, MessageImpl to) {
		to.setText(from.getText());
		to.setDate(from.getDate());
		return to;
	}
}
