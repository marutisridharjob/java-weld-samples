package persistence.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import model.ItemType;
import model.Order;
import model.OrderedItem;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class OrderItemRepo extends BaseEntityRepo<OrderedItem,Long>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8053374956100564650L;

	public OrderedItem newOrderItem(Order order, String itemName,ItemType type) {
		OrderedItem oi = order.newItem();
		oi.setItemName(itemName);
		oi.setItemType(type);
		return save(oi);
	}
	
}
