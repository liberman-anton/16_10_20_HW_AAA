package tel_ran.application;

import tel_ran.security.Authenticater;

public class AplicationClass {
	private Authenticater auth;

	public AplicationClass(Authenticater auth) {
		super();
		this.auth = auth;
	}
	public boolean login(String role, String password){
		return auth.authenticate(role, password, this);	
	}
	public void set1(){
		System.out.println("set1");
	}
	public void set2(){
		System.out.println("set2");
	}
	public void get1(){
		System.out.println("get1");
	}
}
