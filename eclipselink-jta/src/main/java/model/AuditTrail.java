package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AUDIT")
@SequenceGenerator(name = "AUDIT_SEQ",allocationSize = 1,sequenceName = "AUDIT_SEQ")
public class AuditTrail extends ModelBase<Long>{

	@Id
	@Column(name="ID",nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "AUDIT_SEQ")
	private Long id;
	
	@Column(name = "AUDIT_OBJ",  length = 50,nullable = false)
	private String audit_object;

	@Override
	public Long getId() {
		return this.id; 
	}

	public void setAudit_object(String audit_object) {
		this.audit_object = audit_object;
	}
	
	
}
