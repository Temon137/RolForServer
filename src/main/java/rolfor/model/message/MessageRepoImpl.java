package rolfor.model.message;


import rolfor.model.AbstractRepo;


public class MessageRepoImpl extends AbstractRepo<Message, MessageImpl> implements MessageRepo {
	@Override
	public Class<MessageImpl> getEntityClass() {
		return MessageImpl.class;
	}
	
	@Override
	protected MessageImpl copy(Message from, MessageImpl to) {
		to.setId(from.getId());
		to.setText(from.getText());
		to.setDate(from.getDate());
		return to;
	}
}
