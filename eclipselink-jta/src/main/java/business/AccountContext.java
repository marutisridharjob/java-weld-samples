package business;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Account;
import model.Address;
import model.Person;
import persistence.base.AccountRepo;
import persistence.base.PersonRepo;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class AccountContext  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4871931197680581995L;

	@Inject
	PersonRepo personRepo;
	
	@Inject
	AccountRepo accountRepo;
	
	@Inject
	protected AuditTrailContext auditTrailContext;
	

    @Transactional
	public Account createNewAccount(String name,String email,String firstname,String lastname) {
		Person p = personRepo.createNewPerson(firstname, lastname);
		Account acc = this.accountRepo.createNewAccount(name, email,p);
		this.auditTrailContext.logAccountAction(acc);
		return acc;
	}
	
	public Account readAccount(String email) {
		return this.accountRepo.getAccountByEmail(email);
	}
	
	public Address newShippingAddress(Account acc) {
		return personRepo.addShippingAddress(acc.getPerson());		
	}

	public Address newInvoiceAddress(Account acc) {
		return personRepo.addInvoiceAddress(acc.getPerson());		
	}

	
	@Transactional
	public Account updateAccount(Account acc) {
		return this.accountRepo.save(acc);
	}

	public Address updateAddress(Address addr) {
		throw new RuntimeException("not implemented");
	}
	

	public Address deleteAddress(Address addr) {
		throw new RuntimeException("not implemented");
	}
	
	
}
