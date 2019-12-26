package persistence.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import model.Order;
import model.OrderStatus;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */

@ApplicationScoped
public class OrderRepo extends BaseEntityRepo<Order,Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3679689478746148675L;

	public Order saveOrder(Order o) {
		return save(o);
	}
	
	public void updateOrder(Order o,OrderStatus status) {
		o.setStatus(status);
		save(o);
	}
	
}
