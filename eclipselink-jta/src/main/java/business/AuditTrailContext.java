package business;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import model.Account;
import model.AuditTrail;
import model.Order;
import persistence.base.AuditTrailRepo;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class AuditTrailContext  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4013706320746262622L;
	
	@Inject
	AuditTrailRepo auditTrailRepo;
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public AuditTrail logOrderAction(Order o) {
		AuditTrail at = new AuditTrail();
		at.setAudit_object("order_id "+o.getId());
		auditTrailRepo.logAction(at);
		return at;
	}
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public AuditTrail logAccountAction(Account o)  {
		AuditTrail at = new AuditTrail();
		at.setAudit_object("new_account "+o.getId());
		auditTrailRepo.logAction(at);
		return at;
	}
	
}
