package mikra.jta.junit5;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

import mikra.jta.cdi.CDITransactionServices;
import mikra.jta.cdi.JTEMInterceptor;
import mikra.jta.jndihelper.SeMemoryContext;
import mikra.jta.jndihelper.SeMemoryContextFactory;
import mikra.jta.persistence.H2TransactionalDataSourceDelegate;
import mikra.jta.persistence.H2EntityManagerFactoryProducer;
import mikra.jta.persistence.H2DataSourceWrapper;


/**
 * identical setup to the WeldJunit5NarayanaAutoExtension.class
 * @author Michael Krauter
 *
 */
public class WeldJunit5NarayanaExtension extends WeldJunit5Extension {

		@Override
		protected void weldInit(Object testInstance,ExtensionContext context,Weld weld,WeldInitiator.Builder weldInitiatorBuilder) {
			SeMemoryContextFactory.initSystemProperties();
			weld.addBeanClasses(
    		SeMemoryContext.class,H2EntityManagerFactoryProducer.class,
    		H2DataSourceWrapper.class,H2TransactionalDataSourceDelegate.class,
    		JTEMInterceptor.class
    		);
			
			weld.addExtension(new com.arjuna.ats.jta.cdi.TransactionExtension());
			weld.addServices(new CDITransactionServices());
			weld.addInterceptor(JTEMInterceptor.class);
		    super.weldInit(testInstance, context, weld, weldInitiatorBuilder);
		}

}
