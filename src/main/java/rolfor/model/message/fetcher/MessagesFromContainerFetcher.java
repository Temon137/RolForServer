package rolfor.model.message.fetcher;


import rolfor.model.AbstractFetcher;
import rolfor.model.message.Message;
import rolfor.model.message.MessageRepo;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class MessagesFromContainerFetcher extends AbstractFetcher<Message, Message> {
	public MessagesFromContainerFetcher(MessageRepo repo) {
		super(repo);
	}
	
	@Override
	public CriteriaQuery<? extends Message> getCriteriaQuery() {
		var     selectQuery = repo.getSelectQuery();
		Root<?> root        = selectQuery.getRoots().iterator().next();
		return selectQuery.orderBy(cb.asc(root.get("id")));
	}
}
