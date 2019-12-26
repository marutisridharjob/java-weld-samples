package persistence.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.ModelBase;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
public abstract class BaseEntityRepo<E extends ModelBase<T>,T> {

	@Inject
	EntityManager entityManager;
	
	protected final Class<E> clazz;
	
	@SuppressWarnings({"unchecked"})
	public BaseEntityRepo() {
	   Type type = getClass().getGenericSuperclass();
	
	   while(!(type instanceof ParameterizedType)||((ParameterizedType)type).getRawType() != BaseEntityRepo.class ) {
		   if(type instanceof ParameterizedType) {
			   type=((Class<?>) ((ParameterizedType)type).getRawType()).getGenericSuperclass();
		   }else {
			   type= ((Class<?>)type).getGenericSuperclass();
		   }
	   }
	   this.clazz = (Class<E>) ((ParameterizedType)type).getActualTypeArguments()[0];
	}
	
	private void persist(E entity) {
		this.entityManager.persist(entity);
	}
	
	private E merge(E entity) {
	 return this.entityManager.merge(entity);
	}
	
	public E find(T id) {
		return this.entityManager.find(clazz, id);
	}
	
	public void remove(E entity) {
		this.entityManager.remove(entity);
	}
	
	public static <X> X getSingleResultOrNull(List<X> list) {
       return list == null || list.isEmpty() ? null : list.get(0); 
	}
	
	public <Y> TypedQuery<Y> createNamedQuery(String name, Class<Y> resultClass){
		return this.entityManager.createNamedQuery(name, resultClass);
	}
	
	public E save(E entity) {
		if(entity.getId() == null) {
			persist(entity);
			return entity;
		}else {
			if(!this.entityManager.contains(entity)){
			  return merge(entity);
			}
			return entity;
		}
		
	}
	
	
}
