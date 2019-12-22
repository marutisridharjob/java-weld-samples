package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
@SequenceGenerator(name = "PERSON_SEQ",allocationSize = 2,sequenceName = "PERSON_SEQ")
public class Person extends ModelBase<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PERSON_SEQ")
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name="FIRSTNAME",length=50,nullable=false)
	private String firstname;
	
	@Column(name="LASTNAME",length=50,nullable=false)
	private String lastname;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "theperson")
	private List<Address>  addresses = new ArrayList<Address>();
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy =  "theperson")
	private List<Order> allOrders = new ArrayList<Order>();
	
	public List<Order> getAllOrders() {
		return allOrders;
	}

	@Override
	public Long getId() {
         return this.id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void addAddress(Address addr) {
		addr.setTheperson(this);
		this.addresses.add(addr);
	}
	
	public Order newOrder() {
		Order o = new Order();
		o.theperson = this;
		this.allOrders.add(o);
		return o;
	}
	
}
