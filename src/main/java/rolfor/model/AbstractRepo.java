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
public abstract class AbstractRepo<T extends Entity> implements Repo<T> {
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
		T entity = find(item.getId());
		copy(item, entity);
		em.persist(entity);
		em.flush();
		transaction.commit();
		return item;
	}
	
	@Override
	public List<T> findAll() {
		CriteriaQuery<T> cq        = cb.createQuery(getEntityClass());
		Root<T>          rootEntry = cq.from(getEntityClass());
		CriteriaQuery<T> all       = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get("id")));
		TypedQuery<T>    allQuery  = em.createQuery(all);
		return allQuery.getResultList();
	}
	
	@Override
	public List<T> findFromPage(CriteriaQuery<T> query, int pageNumber, int pageSize) {
		TypedQuery<T> typedQuery = em.createQuery(query);
		typedQuery.setFirstResult((pageNumber - 1) * pageSize);
		typedQuery.setMaxResults(pageSize);
		return typedQuery.getResultList();
	}
	
	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return cb;
	}
	
	@Override
	public <R> TypedQuery<R> getQuery(CriteriaQuery<R> query) {
		return em.createQuery(query);
	}
	
	@Override
	public <R extends Entity> Long getPagesCount(CriteriaQuery<R> query, int pageSize) {
		em.createQuery(query); // I hate this shit
		
		final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		final Root<R>             countRoot  = countQuery.from(query.getResultType());
		
		countQuery.select(cb.count(countRoot));
		countQuery.where(query.getRestriction());
		countRoot.alias(query.getRoots().iterator().next().getAlias());
		
		CriteriaQuery<Long> resultQuery = countQuery.distinct(query.isDistinct());
		Long                rowsCount   = em.createQuery(resultQuery).getSingleResult();
		return rowsCount / pageSize + (rowsCount % pageSize > 0 ? 1 : 0);
	}
	
	protected abstract T copy(T from, T dest);
}
