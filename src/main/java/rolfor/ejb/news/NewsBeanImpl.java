package rolfor.ejb.news;


import rolfor.ejb.AbstractEntityBean;
import rolfor.model.news.News;
import rolfor.model.news.NewsRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Local(NewsBean.class)
@Stateless(name = "NewsBeanImpl")
public class NewsBeanImpl extends AbstractEntityBean<News, NewsRepo> implements NewsBean {
	@SuppressWarnings("unused")
	public NewsBeanImpl() {
		super(null);
	}
	
	@Inject
	public NewsBeanImpl(NewsRepo repo) {
		super(repo);
	}
}
