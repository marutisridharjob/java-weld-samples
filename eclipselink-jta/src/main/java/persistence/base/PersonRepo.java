package persistence.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import model.Address;
import model.AddressType;
import model.Person;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class PersonRepo extends BaseEntityRepo<Person,Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6384841350043899403L;

	public Person createNewPerson(String firstname, String lastname) {
		Person p = new Person(); 
		p.setFirstname(firstname);
		p.setLastname(lastname);
		return save(p);
	}
		
	public Address addInvoiceAddress(Person p) {
		Address addr = new Address();
		addr.setType(AddressType.INVOICE_ADDR);
		p.addAddress(addr);
		save(p);
		return addr;
	}
	
	public Address addPrivateAddress(Person p) {
		Address addr = new Address();
		addr.setType(AddressType.PRIVATE_ADDR);
		p.addAddress(addr);
		save(p);
		return addr;
	}
	
	public Address addShippingAddress(Person p) {
		Address addr = new Address();
		addr.setType(AddressType.SHIPPING_ADDR);
		p.addAddress(addr);
		return addr;
	}
	
	
}
