package business;

import javax.inject.Inject;

import persistence.base.AccountRepo;

public abstract class AbstractContext {

	@Inject
	protected AccountRepo accountRepo;
	
	
}
