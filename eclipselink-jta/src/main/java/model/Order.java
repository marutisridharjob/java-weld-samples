package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
@Table(name="ORDER")
@SequenceGenerator(name = "ORDER_SEQ",allocationSize = 2,sequenceName = "ORDER_SEQ")
public class Order extends ModelBase<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ORDER_SEQ")
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name="ORDERSTATUS",nullable=false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(name="ORDERDATE",nullable=false)
	private LocalDateTime orderDate;

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy =  "theorder")
	private List<OrderedItem> orderContent = new ArrayList<OrderedItem>();
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@PrimaryKeyJoinColumn(name="PERSON_ID", referencedColumnName="ID")	
	protected Person theperson;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderedItem> getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(List<OrderedItem> orderContent) {
		this.orderContent = orderContent;
	}

	public Person getTheperson() {
		return theperson;
	}

	public OrderedItem newItem() {
		OrderedItem i = new OrderedItem();
		i.theorder = this;
		this.orderContent.add(i);
		return i;
	}
	
	
}
