package persistence.base;


import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
public class EntityManagerProducer implements Serializable {


  /**
	 * 
	 */
	private static final long serialVersionUID = -3357459309592123852L;
	
  @Inject
  private EntityManagerFactory emf;
    
  @Produces 
  @RequestScoped
  public EntityManager newEntityManager(){
    return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
  }

  public void closeEntityManager(@Disposes EntityManager em){
    if (em.isOpen()) {
	  em.close();
    }
    
  }
}
