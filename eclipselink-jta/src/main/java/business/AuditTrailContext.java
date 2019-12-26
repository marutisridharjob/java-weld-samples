package business;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Account;
import model.AuditTrail;
import model.Order;
import persistence.base.AuditTrailRepo;

@ApplicationScoped
public class AuditTrailContext  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4013706320746262622L;
	
	@Inject
	AuditTrailRepo auditTrailRepo;
	
	@Transactional
	public AuditTrail logOrderAction(Order o) {
		AuditTrail at = new AuditTrail();
		at.setAudit_object("order_id "+o.getId());
		auditTrailRepo.logAction(at);
		return at;
	}
	
	@Transactional
	public AuditTrail logAccountAction(Account o)  {
		AuditTrail at = new AuditTrail();
		at.setAudit_object("new_account "+o.getId());
		auditTrailRepo.logAction(at);
		return at;
	}
	
}
