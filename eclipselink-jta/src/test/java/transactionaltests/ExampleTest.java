package transactionaltests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import com.arjuna.ats.arjuna.coordinator.TransactionReaper;
import com.arjuna.ats.jta.utils.JNDIManager;

import business.AccountContext;

/**
 * main test to demonstrate JTA-Testing with SimpleJNDI,EclipseLink and H2 (in-memory)
 * @author Michael Krauter
 *
 */
// @ActivateScopes({SessionScoped.class,RequestScoped.class}) only autoextension
@ExtendWith(ExampleTestExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExampleTest {

//	@WeldSetup
//	WeldInitiator initiator = WeldInitiator.from(
//			H2EntityManagerProducer.class,AccountContext.class,BootupRepo.class,
//    		PersonRepo.class,Account.class,Person.class,H2DataSourceWrapper.class,
//    		SeMemoryContext.class,TestPrincipal.class).activate(RequestScoped.class,SessionScoped.class,ApplicationScoped.class).
//	bindResource(H2EntityManagerProducer.jndi_PersistentContextName,"example_pu").
//	bindResource(H2EntityManagerProducer.jndi_SetupDbScriptName,"dbtest/dbinit.sql").
//	bindResource(H2DataSourceWrapper.jndi_DataSourceName,"jdbc/example_ds").build();
// unable to bind services here (see ExampleTestExtension.java)
	
	@Inject
	BeanManager beanManager;
		
	@Inject
	mikra.jta.persistence.H2DataSourceWrapper dsWrapper;
			
	@BeforeAll
	private void initTest() {	
		try {
		InitialContext ctx = new InitialContext();
		ctx.bind("jdbc/example_ds",dsWrapper);
		JNDIManager.bindJTAImplementation();
		
		}catch(NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@AfterAll
	private void shutdownTest() throws SQLException {
		// this.dsWrapper.scriptDatabase("testscript");
		// optional script your test-results to file
		TransactionReaper.terminate(true);
	}
	
	
	@Test
	void testScopeActive(BeanManager beanManager) {
	    assertTrue(beanManager.getContext(SessionScoped.class).isActive());
	  }
	
	@Inject
	AccountContext ctx;
	
	@Test
	public void testCreateAccount() throws SystemException,javax.transaction.NotSupportedException,
	HeuristicRollbackException,HeuristicMixedException,javax.transaction.RollbackException{
		ctx.createNewAccount("testname","test@test.net"," basic ", "test ");	
	}
	
	
}
