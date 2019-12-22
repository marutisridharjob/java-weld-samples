package mikra.jta.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.SynchronizationType;

@ApplicationScoped
public class H2EntityManagerProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1209703107311878536L;
	public static final String jndi_SetupDbScriptName = "java:/h2SetupScript";
	public static final String jndi_PersistentContextName = "java:/persistentCtxName";
	
	@Inject
	private EntityManagerFactory emf;
	
	@Produces
	@ApplicationScoped
	public EntityManager create() {
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()){
         entityManager.close();
		}
    }
	

}
