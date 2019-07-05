package rolfor.ejb;


import rolfor.model.Entity;
import rolfor.model.Repo;
import rolfor.rest.PaginationParams;

import java.util.List;


public abstract class AbstractEntityBean<E extends Entity, R extends Repo<E, ?>> implements EntityBean<E> {
	protected final R repo;
	
	public AbstractEntityBean(R repo) {
		this.repo = repo;
	}
	
	@Override
	public List<? extends E> getAll() {
		var allQuery = repo.getAllQuery();
		return repo.buildQuery(allQuery).getResultList();
	}
	
	@Override
	public List<? extends E> getAllPaged(PaginationParams pageParams) {
		var allQuery   = repo.getAllQuery();
		var pagedQuery = repo.getPagedQuery(allQuery, pageParams.getPageNumber(), pageParams.getPageSize());
		return pagedQuery.getResultList();
	}
	
	@Override
	public Long getAllPagesCount(PaginationParams pageParams) {
		var allQuery = repo.getAllQuery();
		return repo.getPagesCount(allQuery, pageParams.getPageSize());
	}
	
	@Override
	public E get(Integer id) {
		return repo.find(id);
	}
	
	@Override
	public E create(E entity) {
		return repo.add(entity);
	}
	
	@Override
	public E update(Integer id, E entity) {
		return repo.save(id, entity);
	}
	
	@Override
	public void delete(Integer id) {
		repo.remove(repo.find(id));
	}
}
