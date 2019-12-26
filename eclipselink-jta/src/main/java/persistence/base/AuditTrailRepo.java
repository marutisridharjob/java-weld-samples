package persistence.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import model.AuditTrail;


public class AuditTrailRepo extends BaseEntityRepo<AuditTrail, Long> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1269120086132150686L;

	public void logAction(AuditTrail at) {
		save(at);
	}
	
	
}
