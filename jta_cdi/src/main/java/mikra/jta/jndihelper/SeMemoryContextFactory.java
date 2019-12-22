package mikra.jta.jndihelper;

import java.util.Hashtable;
import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.naming.Context;
import javax.naming.NamingException;

import org.osjava.sj.memory.MemoryContextFactory;

/**
 * returns the initial context with help of the container
 * @author Michael Krauter
 *
 */
public class SeMemoryContextFactory extends MemoryContextFactory {

	public static void initSystemProperties() {
		System.setProperty(javax.naming.Context.INITIAL_CONTEXT_FACTORY,"mikra.jta.jndihelper.SeMemoryContextFactory");
		System.setProperty("org.osjava.sj.jndi.shared","true");
	}
	
	@Override
	public Context getInitialContext(Hashtable env) throws NamingException{
		// we ignore the env and always return the context from the bm
		// -> needs to be initialised within the test for instance
		BeanManager bm = CDI.current().getBeanManager();
		Bean<?> bean = bm.resolve(bm.getBeans(SeMemoryContext.class));
	    
		if(bean == null || !bm.isNormalScope(bean.getScope())) {
			throw new NamingException("no jndi-context found - managedBean not initialised!");
		}
		
		return (Context)SeMemoryContext.class.cast(
				bm.getReference(bean,SeMemoryContext.class,bm.createCreationalContext(null))
				);
	}
	
	
}
