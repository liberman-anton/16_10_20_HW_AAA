package tel_ran.application;

import tel_ran.security.Authenticater;
import tel_ran.security.aop.annotations.Authorized;

public class AplicationClass {
//	add annotation for methods @Autorized(rolls = {})
//	from beans.xml delete map from Authorizer
//	class Authenticator field: userName, password, role
//	map{String userName, String password, String role}
	
	private Authenticater auth;

	public void setAuth(Authenticater auth) {
		this.auth = auth;
	}
	public AplicationClass(Authenticater auth) {
		super();
		this.auth = auth;
	}
	public boolean login(String role, String password){
		return auth.authenticate(role, password, this);	
	}
	@Authorized(roles = {"admin"})
	public void set1(){
		System.out.println("set1");
	}
	@Authorized(roles = {"admin"})
	public void set2(){
		System.out.println("set2");
	}
	@Authorized(roles = {"admin","user"})
	public void get1(){
		System.out.println("get1");
	}
}
