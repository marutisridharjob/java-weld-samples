package mikra.jta.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class H2EntityManagerFactoryProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6103386914428711458L;

	public static final String jndi_SetupDbScriptName = "java:/h2SetupScript";
	public static final String jndi_PersistentContextName = "java:/persistentCtxName";
	
	@Inject
	private BeanManager beanManager;

	@Resource(lookup = H2EntityManagerProducer.jndi_SetupDbScriptName)
	private String scriptpathAndName;
	
	@Resource(lookup = H2EntityManagerProducer.jndi_PersistentContextName)
	private String persistentCtxName;

	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory produceEntityManagerFactory() {
	    Map<String, Object> m = new HashMap<>();
	    m.put("javax.persistence.bean.manager", beanManager);
		//m.put("javax.persistence.jdbc.driver","org.h2.Driver");
	    m.put("eclipselink.target-database","org.eclipse.persistence.platform.database.H2Platform");
		m.put("eclipselink.target-server","mikra.jta.persistence.SeServerPlatform");
		m.put("javax.persistence.schema-generation.database.action","drop-and-create");
		m.put("javax.persistence.sql-load-script-source",this.scriptpathAndName);
		m.put("eclipselink.deploy-on-startup","true");
		m.put("eclipselink.cache.shared.default","true");
		m.put("eclipselink.validation-only","false");
        // override settings for testing		
	    return Persistence.createEntityManagerFactory(this.persistentCtxName, m);
	}
		

	public void close(@Disposes EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }
	
}
