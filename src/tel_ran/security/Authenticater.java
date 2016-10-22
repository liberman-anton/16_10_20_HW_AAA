package tel_ran.security;

import java.util.HashMap;

public class Authenticater {
	private HashMap<String, String> rulesPasswords;//key - role, value-password
	private HashMap<Object, String> authObjects;//key - reference to object, value - role
	
	public Authenticater(HashMap<String, String> rulesPasswords) {
		super();
		this.rulesPasswords = rulesPasswords;
		this.authObjects = new HashMap<>();
	}
	//method authenticate puts to authObjects entry,containing Object and role for successful authentication
	
	public boolean authenticate(String role,String password, Object object){
		//returns true if password matches role
		if(password == null)
			return false;
		if(password.equals(rulesPasswords.get(role))){
			authObjects.put(object, role);
			return true;
		}
		return false;
	}
	public String getRole(Object obj){
		//returns role for a given object or null
		return authObjects.get(obj);
	}

}
