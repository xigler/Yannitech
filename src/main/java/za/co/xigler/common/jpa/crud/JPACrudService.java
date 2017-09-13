package za.co.xigler.common.jpa.crud;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class JPACrudService {

	private EntityManager em;

	public JPACrudService() {}

	@Inject
	public JPACrudService(EntityManager em) {
		this.em = em;
	}

	public <T> T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> type,Object id) {
		return (T) this.em.find(type, id);
	}

	public void delete(Object t) {
		Object ref = this.em.getReference(t.getClass(), t);
		this.em.remove(ref);
	}

	public <T> T update(T t) {
		return (T)this.em.merge(t);
	}

	public List findWithNamedQuery(String namedQueryName){
		return this.em.createNamedQuery(namedQueryName).getResultList();
	}

	public List findWithNamedQuery(String namedQueryName, Map<String,Object> parameters){
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	public List findWithNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).
				setMaxResults(resultLimit).
				getResultList();    
	}

	public List findByNativeQuery(String sql, Class type) {
		return this.em.createNativeQuery(sql, type).getResultList();
	}

	public List findWithNamedQuery(String namedQueryName, Map<String,Object> parameters,int resultLimit){
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		if(resultLimit > 0)
			query.setMaxResults(resultLimit);
		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
	
}