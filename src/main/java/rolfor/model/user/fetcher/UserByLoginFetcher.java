package rolfor.model.user.fetcher;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.user.User;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class UserByLoginFetcher extends AbstractFetcher<User, User> {
	private final String login;
	
	public UserByLoginFetcher(Repo<User, ? extends User> repo, String login) {
		super(repo);
		this.login = login;
	}
	
	@Override
	public CriteriaQuery<? extends User> getCriteriaQuery() {
		var     selectQuery = repo.getSelectQuery();
		Root<?> root        = selectQuery.getRoots().iterator().next();
		return selectQuery.where(cb.equal(root.get("login"), login));
	}
}
