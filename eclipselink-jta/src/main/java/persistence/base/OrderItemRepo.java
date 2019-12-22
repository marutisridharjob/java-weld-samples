package persistence.base;

import javax.enterprise.context.ApplicationScoped;

import model.ItemType;
import model.Order;
import model.OrderedItem;

@ApplicationScoped
public class OrderItemRepo extends BaseEntityRepo<OrderedItem,Long> {

	public OrderedItem newOrderItem(Order order, String itemName,ItemType type) {
		OrderedItem oi = order.newItem();
		oi.setItemName(itemName);
		oi.setItemType(type);
		return save(oi);
	}
	
}
