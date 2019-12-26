package persistence.base;


import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import model.Account;
import model.Person;

/**
 * demo for the JTA feature
 * @author Michael Krauter
 *
 */
@ApplicationScoped
public class AccountRepo extends BaseEntityRepo<Account,String> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912006270166494146L;

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
