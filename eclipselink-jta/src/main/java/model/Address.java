package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
@SequenceGenerator(name = "ADDRESS_SEQ",allocationSize = 2,sequenceName = "ADDRESS_SEQ")
public class Address extends ModelBase<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ADDRESS_SEQ")
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name="TYPE",nullable=false)
	@Enumerated(EnumType.STRING)
	private AddressType type;
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@PrimaryKeyJoinColumn(name="PERSON_ID", referencedColumnName="ID")
	private Person theperson;
	
	@Column(name="STREET",nullable=false)
	private String street;
	
	@Column(name="STREET_NO",nullable=false)
	private String streetNo;
	
	@Column(name="POSTCODE",nullable=false)
	private String postcode;
	
	@Column(name="CITY",nullable=false)
	private String city; 
	
	
	@Override
	public Long getId() {
         return this.id;
	}

	public AddressType getType() {
		return type;
	}


	public void setType(AddressType type) {
		this.type = type;
	}


	public Person getTheperson() {
		return theperson;
	}


	public void setTheperson(Person theperson) {
		this.theperson = theperson;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getStreetNo() {
		return streetNo;
	}


	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
