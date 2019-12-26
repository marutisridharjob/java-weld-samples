package persistence.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import model.Order;
import model.OrderStatus;
import model.Person;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */

public class OrderRepo extends BaseEntityRepo<Order,Long> implements Serializable {

	public Order saveOrder(Order o) {
		return save(o);
	}
	
	public void updateOrder(Order o,OrderStatus status) {
		o.setStatus(status);
		save(o);
	}
	
}
