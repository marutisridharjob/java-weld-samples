package persistence.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import model.AuditTrail;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class AuditTrailRepo extends BaseEntityRepo<AuditTrail, Long> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1269120086132150686L;

	public void logAction(AuditTrail at) {
		save(at);
	}
	
	
}
