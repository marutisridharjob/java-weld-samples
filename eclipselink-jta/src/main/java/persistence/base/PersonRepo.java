package persistence.base;

import javax.enterprise.context.ApplicationScoped;

import model.Address;
import model.AddressType;
import model.Person;

@ApplicationScoped
public class PersonRepo extends BaseEntityRepo<Person,Long> {

	public Person createNewPerson(String firstname, String lastname) {
		Person p = new Person();
		p.setFirstname(firstname);
		p.setLastname(lastname);
		return save(p);
	}
	
	private void populate(Address addr,String street, String streetNo, String city, String postcode) {
		addr.setStreet(street);
		addr.setStreetNo(streetNo);
		addr.setCity(city);
		addr.setPostcode(postcode);
	}
	
	public Address addInvoiceAddress(Person p,String street,String streetNo,String city,String postcode) {
		Address addr = new Address();
		addr.setType(AddressType.INVOICE_ADDR);
		populate(addr,street,streetNo,city,postcode);
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
		save(p);
		return addr;
	}
	
	
}
