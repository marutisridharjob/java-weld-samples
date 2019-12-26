package transactionaltests;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.WeldInitiator;
import org.junit.jupiter.api.extension.ExtensionContext;

import business.AccountContext;
import business.AuditTrailContext;
import business.OrderContext;
import mikra.jta.junit5.WeldJunit5NarayanaExtension;
import mikra.jta.persistence.H2DataSourceWrapper;
import mikra.jta.persistence.H2EntityManagerFactoryProducer;
import persistence.base.AccountRepo;
import persistence.base.AuditTrailRepo;
import persistence.base.EntityManagerProducer;
import persistence.base.OrderItemRepo;
import persistence.base.OrderRepo;
import persistence.base.PersonRepo;

/**
 * extension for setting up the container
 * @author Michael Krauter
 *
 */
public class ExampleTestExtension extends WeldJunit5NarayanaExtension {

	@Override
	protected void weldInit(Object testInstance,ExtensionContext context,Weld weld,WeldInitiator.Builder weldInitiatorBuilder) {
		// we cant add services while doing @WeldSetup so init is placed here
        weld.addBeanClasses(
        		AccountContext.class,AccountRepo.class,EntityManagerProducer.class,
        		PersonRepo.class, //Account.class,Person.class,Order.class,OrderedItem.class
        		TestPrincipal.class,AuditTrailRepo.class,AuditTrailContext.class,
        		OrderRepo.class,OrderContext.class,
        		OrderItemRepo.class
        		);
		weldInitiatorBuilder.bindResource(H2DataSourceWrapper.jndi_H2Url,"jdbc:h2:mem:test_db;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false");
        // following values according to persistence.xml
        weldInitiatorBuilder.activate(RequestScoped.class,SessionScoped.class);
		weldInitiatorBuilder.bindResource(H2EntityManagerFactoryProducer.jndi_PersistentContextName,"example_pu");
		weldInitiatorBuilder.bindResource(H2EntityManagerFactoryProducer.jndi_SetupDbScriptName,"dbtest/dbinit.sql");
		weldInitiatorBuilder.bindResource(H2EntityManagerFactoryProducer.jndi_NON_JTADataSourceName,"java:read_ds");
		weldInitiatorBuilder.bindResource(H2EntityManagerFactoryProducer.jndi_JTADataSourceName,"java:example_ds");
		// these values must correspond with your persistence.xml
		  
		super.weldInit(testInstance, context, weld, weldInitiatorBuilder);
	}	
}
