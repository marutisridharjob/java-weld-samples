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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@Entity
@Table(name="ORDERED_ITEM")
@SequenceGenerator(name = "ORDERED_ITEM_SEQ",allocationSize = 1,sequenceName = "ORDERED_ITEM_SEQ")
public class OrderedItem extends ModelBase<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ORDERED_ITEM_SEQ")
	@Column(name = "ID",nullable = false)
	private Long id;
	
	@Column(name="ITEMNAME",nullable=false)
	private String itemName;
	
	@Column(name="ITEMTYPE",nullable=false)
	@Enumerated(EnumType.STRING)
	private ItemType itemType;
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="ORDER_ID", nullable = false, referencedColumnName="ID")
	protected Order theorder;
	
	@Override
	public Long getId() {
         return this.id;
	}
	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public Order getTheorder() {
		return theorder;
	}

	public void setTheorder(Order theorder) {
		this.theorder = theorder;
	}
	
}
