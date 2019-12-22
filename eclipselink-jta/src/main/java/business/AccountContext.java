package business;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Account;
import model.Address;
import model.Person;
import persistence.base.PersonRepo;


@SessionScoped
public class AccountContext extends AbstractContext implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 4871931197680581995L;

	@Inject
	PersonRepo personRepo;
	
	private Account acc;
	
	@Transactional
	public void createNewAccount(String name,String email,String firstname,String lastname) {
		Person p = personRepo.createNewPerson(firstname, lastname);
		this.acc = this.accountRepo.createNewAccount(name, email,p);	
	}
	
	public void readAccount(String email) {
		this.acc = this.accountRepo.getAccountByEmail(email);
	}
	
	@Transactional
	public Address newShippingAddress() {
		return personRepo.addShippingAddress(this.acc.getPerson());		
	}
	
	@Transactional
	public Address updateAddress(Address addr) {
		throw new RuntimeException("not implemented");
	}
	
	@Transactional
	public Address deleteAddress(Address addr) {
		throw new RuntimeException("not implemented");
	}

	protected Account getAcc() {
		return acc;
	}
	
	
	
}
