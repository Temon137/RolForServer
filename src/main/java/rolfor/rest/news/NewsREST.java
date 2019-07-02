package rolfor.rest.news;


import rolfor.model.news.News;
import rolfor.model.news.NewsRepo;
import rolfor.rest.AbstractREST;

import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(value = "/news")
public class NewsREST extends AbstractREST<News, NewsRepo> {
	@SuppressWarnings("unused")
	public NewsREST() {
		super(null);
	}
	
	@Inject
	public NewsREST(NewsRepo repo) {
		super(repo);
	}
}
