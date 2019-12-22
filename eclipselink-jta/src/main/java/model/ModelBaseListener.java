package model;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
public class ModelBaseListener {

	@Inject
	Principal principal;
	
	@PrePersist
	public void prePersist(ModelBase<?> entity) {
		preUpdate(entity);
	}
	
	@PreUpdate
	public void preUpdate(ModelBase<?> entity) {
		entity.modTime = LocalDateTime.now();
		entity.modUser = principal.getName(); 
	}
	
	
}
