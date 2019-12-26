package business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Account;
import model.Order;
import model.OrderStatus;
import persistence.base.OrderRepo;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */

@ApplicationScoped
public class OrderContext  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9146565685786248901L;

	@Inject
	private OrderRepo orderRepo;
		
		
	private List<Order> filterOrderByStatus(List<Order> order,OrderStatus status){
		return order.stream()               
                .filter(o -> o.getStatus() == status)    
                .collect(Collectors.toList());
	}
	
	public List<Order> getPendingOrders(Account acc){
		return filterOrderByStatus(acc.getPerson().getAllOrders(),OrderStatus.PENDING);
		// not a real world example - filtering is db's job
	}
	
	public List<Order> getShippedOrders(Account acc){
		return filterOrderByStatus(acc.getPerson().getAllOrders(),OrderStatus.SHIPPED);
	}
	
	@Transactional
	public Order newOrder(Account acc) {
		Order o = acc.getPerson().newOrder();
		o.setOrderDate(LocalDateTime.now());
		o.setStatus(OrderStatus.NEW);
		return orderRepo.saveOrder(o);
	}
	
	
}
