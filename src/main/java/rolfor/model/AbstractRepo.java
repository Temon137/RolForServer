package rolfor.model;


import rolfor.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@SuppressWarnings("WeakerAccess")
public abstract class AbstractRepo<T extends Entity, R extends T> implements Repo<T> {
	protected final EntityManager   em = HibernateUtil.getSessionFactory().createEntityManager();
	protected final CriteriaBuilder cb = em.getCriteriaBuilder();
	
	@Override
	public T find(int id) {
		return em.find(getEntityClass(), id);
	}
	
	@Override
	public void remove(T item) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(item);
		em.flush();
		transaction.commit();
	}
	
	@Override
	public T add(T item) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(item);
		em.flush();
		transaction.commit();
		return item;
	}
	
	@Override
	public T save(T item) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		R entity = em.find(getEntityClass(), item.getId());
		copy(item, entity);
		em.persist(entity);
		em.flush();
		transaction.commit();
		return item;
	}
	
	@Override
	public List<? extends T> findAll() {
		CriteriaQuery<R> cq        = cb.createQuery(getEntityClass());
		Root<R>          rootEntry = cq.from(getEntityClass());
		CriteriaQuery<R> all       = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get("id")));
		TypedQuery<R>    allQuery  = em.createQuery(all);
		return allQuery.getResultList();
	}
	
	@Override
	public CriteriaQuery<R> getSelectQuery() {
		CriteriaQuery<R> cq        = cb.createQuery(getEntityClass());
		Root<R>          rootEntry = cq.from(getEntityClass());
		return cq.select(rootEntry);
	}
	
	@Override
	public TypedQuery<? extends T> getPagedQuery(CriteriaQuery<? extends T> query, int pageNumber, int pageSize) {
		TypedQuery<? extends T> typedQuery = em.createQuery(query);
		typedQuery.setFirstResult((pageNumber - 1) * pageSize);
		typedQuery.setMaxResults(pageSize);
		return typedQuery;
	}
	
	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return cb;
	}
	
	@Override
	public <E> TypedQuery<E> getQuery(CriteriaQuery<E> query) {
		return em.createQuery(query);
	}
	
	@Override
	public Long getPagesCount(CriteriaQuery<? extends T> query, int pageSize) {
		em.createQuery(query); // I hate this shit
		
		final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		final Root<? extends Entity>             countRoot  = countQuery.from(query.getResultType());
		
		countQuery.select(cb.count(countRoot));
		countQuery.where(query.getRestriction());
		countRoot.alias(query.getRoots().iterator().next().getAlias());
		
		CriteriaQuery<Long> resultQuery = countQuery.distinct(query.isDistinct());
		Long                rowsCount   = em.createQuery(resultQuery).getSingleResult();
		return rowsCount / pageSize + (rowsCount % pageSize > 0 ? 1 : 0);
	}
	
	@Override
	public abstract Class<R> getEntityClass();
	
	protected abstract R copy(T from, R to);
}
