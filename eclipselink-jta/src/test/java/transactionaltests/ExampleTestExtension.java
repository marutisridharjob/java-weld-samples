package transactionaltests;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.WeldInitiator;
import org.junit.jupiter.api.extension.ExtensionContext;

import business.AccountContext;
import mikra.jta.junit5.WeldJunit5NarayanaExtension;
import mikra.jta.persistence.H2DataSourceWrapper;
import mikra.jta.persistence.H2EntityManagerProducer;
import model.Account;
import model.Person;
import persistence.base.AccountRepo;
import persistence.base.PersonRepo;

public class ExampleTestExtension extends WeldJunit5NarayanaExtension {

	@Override
	protected void weldInit(Object testInstance,ExtensionContext context,Weld weld,WeldInitiator.Builder weldInitiatorBuilder) {
		// we cant add services while doing @WeldSetup so init is placed here
        weld.addBeanClasses(
        		AccountContext.class,AccountRepo.class,
        		PersonRepo.class,Account.class,Person.class,
        		TestPrincipal.class
        		);
        weldInitiatorBuilder.activate(RequestScoped.class,SessionScoped.class);
		weldInitiatorBuilder.bindResource(H2EntityManagerProducer.jndi_PersistentContextName,"example_pu");
		weldInitiatorBuilder.bindResource(H2EntityManagerProducer.jndi_SetupDbScriptName,"dbtest/dbinit.sql");
		weldInitiatorBuilder.bindResource(H2DataSourceWrapper.jndi_DataSourceName,"jdbc/example_ds");		
	    super.weldInit(testInstance, context, weld, weldInitiatorBuilder);
	}	
}
