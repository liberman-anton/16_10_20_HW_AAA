package tel_ran.persons.model.dao;

import org.springframework.beans.factory.annotation.Autowired;

import tel_ran.security.Account;
import tel_ran.security.dao.repo.AccountsRepository;

public class AccountsMongoDB {
	@Autowired
	private AccountsRepository accounts;

	public Iterable<Account> getAllAcconts(){
		return accounts.findAll();
	}
	public Account getAccount(String username){
		return accounts.findOne(username);
	}
	
}
