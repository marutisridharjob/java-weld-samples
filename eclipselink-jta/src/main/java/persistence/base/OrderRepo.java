package persistence.base;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;

import model.Order;
import model.OrderStatus;
import model.Person;

@ApplicationScoped
public class OrderRepo extends BaseEntityRepo<Order,Long> {

	public Order newOrder(Person p,LocalDateTime orderDate,OrderStatus status) {
		Order o = p.newOrder();
		o.setStatus(status);
		o.setOrderDate(orderDate);
		return save(o);
	}
	
	public void updateOrder(Order o,OrderStatus status) {
		o.setStatus(status);
		save(o);
	}
	
}
