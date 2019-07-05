package rolfor.model.token.fetcher;


import rolfor.model.AbstractFetcher;
import rolfor.model.Repo;
import rolfor.model.token.Token;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class TokenByStringFetcher extends AbstractFetcher<Token, Token> {
	private final String tokenString;
	
	public TokenByStringFetcher(Repo<Token, ? extends Token> repo, String tokenString) {
		super(repo);
		this.tokenString = tokenString;
	}
	
	@Override
	public CriteriaQuery<? extends Token> getCriteriaQuery() {
		var     selectQuery = repo.getSelectQuery();
		Root<?> root        = selectQuery.getRoots().iterator().next();
		return selectQuery.where(cb.equal(root.get("tokenString"), tokenString));
	}
}
