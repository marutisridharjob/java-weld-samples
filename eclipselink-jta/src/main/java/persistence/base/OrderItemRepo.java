package persistence.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import model.ItemType;
import model.Order;
import model.OrderedItem;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */

public class OrderItemRepo extends BaseEntityRepo<OrderedItem,Long>  implements Serializable{

	public OrderedItem newOrderItem(Order order, String itemName,ItemType type) {
		OrderedItem oi = order.newItem();
		oi.setItemName(itemName);
		oi.setItemType(type);
		return save(oi);
	}
	
}
