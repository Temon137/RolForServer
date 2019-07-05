package rolfor.model.news;


import rolfor.model.AbstractRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(NewsRepo.class)
@Stateless(name = "NewsRepoImpl")
public class NewsRepoImpl extends AbstractRepo<News, MutableNews, NewsImpl> implements NewsRepo {
	@Override
	public Class<NewsImpl> getEntityClass() {
		return NewsImpl.class;
	}
	
	@Override
	public MutableNews createEmpty() {
		return new NewsImpl();
	}
	
	@Override
	protected void copy(News from, MutableNews to) {
		to.setAuthorId(from.getAuthorId());
		to.setDate(from.getDate());
		to.setTitle(from.getTitle());
		to.setText(from.getText());
	}
}
