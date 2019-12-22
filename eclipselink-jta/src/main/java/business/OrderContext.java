package business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Account;
import model.Order;
import model.OrderStatus;
import model.OrderedItem;
import persistence.base.OrderItemRepo;
import persistence.base.OrderRepo;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@SessionScoped
public class OrderContext extends AbstractContext implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9146565685786248901L;

	@Inject
	private OrderRepo orderRepo;
	
	@Inject
	private OrderItemRepo itemRepo;
	
	private Order tmpOrder;

	private List<OrderedItem> tmpItems;
		
	@PostConstruct
	private void init() {
         this.tmpItems = new ArrayList<OrderedItem>();		
	}
	
	private List<Order> filterOrderByStatus(List<Order> order,OrderStatus status){
		return order.stream()               
                .filter(o -> o.getStatus() == status)    
                .collect(Collectors.toList());
	}
	
	public List<Order> getPendingOrder(Account acc){
		return filterOrderByStatus(acc.getPerson().getAllOrders(),OrderStatus.PENDING);
		// not a real world example - filtering is db's job
	}
	
	public List<Order> getShippedOrder(Account acc){
		return filterOrderByStatus(acc.getPerson().getAllOrders(),OrderStatus.SHIPPED);
	}
	
	@Transactional
	public void newOrder(AccountContext ctx) {
		this.tmpOrder = orderRepo.newOrder(ctx.getAcc().getPerson(),LocalDateTime.now(),OrderStatus.NEW);
		this.tmpItems.clear();
	}
	
	
}
