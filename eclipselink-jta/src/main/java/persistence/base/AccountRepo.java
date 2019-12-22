package persistence.base;


import javax.enterprise.context.ApplicationScoped;

import model.Account;
import model.Person;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class AccountRepo extends BaseEntityRepo<Account,String>{

	public Account getAccountByEmail(String email) {
		return createNamedQuery(Account.findByEmail, Account.class).
				setParameter(Account.paramEmail,email).getSingleResult();
	}

	public Account createNewAccount(String name,String email,Person person) {
		Account newAcc = new Account();
		newAcc.setAccName(name);newAcc.setEmail(email);
		newAcc.setPerson(person);
		return save(newAcc);
	}
	
}
