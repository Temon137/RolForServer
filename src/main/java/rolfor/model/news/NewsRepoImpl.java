package rolfor.model.news;


import rolfor.model.AbstractRepo;


public class NewsRepoImpl extends AbstractRepo<News, NewsImpl> implements NewsRepo {
	@Override
	public Class<NewsImpl> getEntityClass() {
		return NewsImpl.class;
	}
	
	@Override
	protected NewsImpl copy(News from, NewsImpl to) {
		to.setId(from.getId());
		to.setAuthorId(from.getAuthorId());
		to.setDate(from.getDate());
		to.setTitle(from.getTitle());
		to.setText(from.getText());
		return to;
	}
}
