package rolfor.model.user.fetcher;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.user.User;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class UserForAuthFetcher extends AbstractFetcher<User, User> {
	private final String login;
	private final String password;
	
	public UserForAuthFetcher(Repo<User, ? extends User> repo, String login, String password) {
		super(repo);
		this.login = login;
		this.password = password;
	}
	
	@Override
	public CriteriaQuery<? extends User> getCriteriaQuery() {
		var     selectQuery = repo.getSelectQuery();
		Root<?> root        = selectQuery.getRoots().iterator().next();
		return selectQuery.where(cb.and(cb.equal(root.get("login"), login),
		                                cb.equal(root.get("password"), password)));
	}
}
