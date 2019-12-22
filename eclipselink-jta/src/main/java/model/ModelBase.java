package model;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(ModelBaseListener.class)
public abstract class ModelBase<T> {

	public static final String pk = "ID";

	@Column(name = "MODIFIED_BY", length = 10)
	protected String modUser;
	
	@Column(name = "MODIFIED_AT")
	protected LocalDateTime modTime;

	public abstract T getId();

	@Override
	public String toString() {
		return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
	}

}
