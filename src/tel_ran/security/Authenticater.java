package tel_ran.security;

import java.util.HashMap;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.AccountsMongoDB;

public class Authenticater {
	
	private HashMap<Object, String> authObjects;//key - reference to object, value - role
	
	public Authenticater() {
		super();
		this.authObjects = new HashMap<>();
	}
	
	public boolean authenticate(String username,String password, Object object){
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		AccountsMongoDB accountsMongo = ctx.getBean(AccountsMongoDB.class);
		Account account = accountsMongo.getAccount(username);
		if(account != null && account.getPassword().equals(password)){
			authObjects.put(object, account.getRole());
			return true;
		}
		return false;
	}
	public String getRole(Object obj){
		//returns role for a given object or null
		return authObjects.get(obj);
	}

}
