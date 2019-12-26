package transactionaltests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import com.arjuna.ats.arjuna.coordinator.TransactionReaper;

import business.AccountContext;
import mikra.jta.persistence.H2DataSourceWrapper;
import model.Account;
import model.Address;
import model.Person;


/**
 * main test to demonstrate JTA-Testing with SimpleJNDI,EclipseLink and H2 (in-memory)
 * @author Michael Krauter
 *
 */
// @ActivateScopes({SessionScoped.class,RequestScoped.class}) only for autoextension
@ExtendWith(ExampleTestExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExampleTest {

	@Inject
	BeanManager beanManager;
			
	@Inject
	H2DataSourceWrapper datasource;
	
		
	@AfterAll
	private void shutdownTest(){
		TransactionReaper.terminate(true);
	}
	
	
	@Test
	@Order(value = 0)
	void testScopeActive(BeanManager beanManager) {
	    assertTrue(beanManager.getContext(SessionScoped.class).isActive());
	  }
	
	@Inject
	UserTransaction ut;
	
	@Inject
	EntityManager em;
	
	@Inject
	EntityManager em2;
	
	@Test
	@Order(value = 1)
	public void testCreateAccount(AccountContext ctx) throws SystemException,javax.transaction.NotSupportedException,
	HeuristicRollbackException,HeuristicMixedException,javax.transaction.RollbackException,Exception{
	    // manual transaction handling by injecting the user-transaction
		ut.begin();
		em.joinTransaction();  	    		
		Person p = new Person();
		p.setFirstname("x1");
		p.setLastname("testname");
		Account newAcc = new Account();
		newAcc.setAccName("testaccount");newAcc.setEmail("x1@test.net");
		newAcc.setPerson(p);
		em.persist(newAcc);
	    ut.commit();
		ut.begin();
		em2.joinTransaction();
  	    Person p2 = new Person();
		p2.setFirstname("x12");
		p2.setLastname("testname2");
		Account newAcc2 = new Account();
		newAcc2.setAccName("testaccount2");newAcc2.setEmail("x12@test.net");
		newAcc2.setPerson(p2);
		em2.persist(newAcc2);
        ut.commit();
	
	}
	
    @Test
    @Order(value = 2)
	public void testCreateAccount2(AccountContext ctx){
		ctx.createNewAccount("testname3","test4@test.net"," basic ", "test ");	
        ctx.createNewAccount("testname5","test5@test.net","testfirstname","testlastname");
	}
	
    
    @Test
    @Order(value = 3)
	public void testAddAddresses(AccountContext ctx) {
         Account acc = ctx.readAccount("x1@test.net");
         assertTrue(acc != null);
         String aid = acc.getId();
         Address addr = ctx.newShippingAddress(acc);
         addr.setCity("testcity");
         addr.setPostcode("234a");
         addr.setStreet("teststreet");
         addr.setStreetNo("22a");
         Address addr2 = ctx.newInvoiceAddress(acc);
         addr2.setCity("testcity");
         addr2.setPostcode("234b");
         addr2.setStreet("teststreet_inv");
         addr2.setStreetNo("22b");
         acc = ctx.updateAccount(acc);
         assertTrue(acc.getId().equals(aid));
    }
    
    @Test
    @Order(value = 4)
	public void testReadAddress(AccountContext ctx) {
        Account acc = ctx.readAccount("x1@test.net");
        List<Address> addrs = acc.getPerson().allAddresses();
        assertTrue(addrs.size()==2);
    }
    
    
    @Test
    @Order(value = 5)
    public void checkOutcome() throws IOException,SQLException {
		this.datasource.scriptDatabase("test_outcome.sql");
		// optional script your test-results to file
    	 try (LineNumberReader reader = new LineNumberReader(new FileReader("test_outcome.sql"))) {
    	        reader.skip(Integer.MAX_VALUE);
    	        int noOfLines = reader.getLineNumber();
    	        assertTrue(noOfLines == 89);
    	 }
    }

}
