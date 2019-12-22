package persistence.base;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer {

	@Produces
	@PersistenceContext(unitName = "example_pu")
	private EntityManager em;
	
	  public void closeEntityManager(@Disposes EntityManager em) {
	        em.close();
	    }
	  	
}
