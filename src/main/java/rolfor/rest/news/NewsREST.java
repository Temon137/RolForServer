package rolfor.rest.news;


import rolfor.ejb.news.NewsBean;
import rolfor.model.news.News;
import rolfor.rest.AbstractREST;

import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(value = "/news")
public class NewsREST extends AbstractREST<News, NewsBean> {
	@SuppressWarnings("unused")
	public NewsREST() {
		super(null);
	}
	
	@Inject
	public NewsREST(NewsBean bean) {
		super(bean);
	}
}
