package tel_ran.security.aop;

import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;

import tel_ran.security.Accounter;
import tel_ran.security.Authenticater;

public class Authorizer {
	HashMap<String, Set<String>> rulesMethods;//key - role name, value - array of the permitted method names 
	Authenticater auth;
	Accounter acc;
	
	public Authorizer(HashMap<String, Set<String>> rulesMethods, Authenticater auth, Accounter acc) {
		super();
		this.rulesMethods = rulesMethods;
		this.auth = auth;
		this.acc = acc;
	}
	
	public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj = joinPoint.getTarget();
		String method = joinPoint.getSignature().getName();
		String role = auth.getRole(obj);
		if( role == null){
			acc.methodCallReject(method, false);
			throw new SecurityException("401");
		}
		Set<String> methods = rulesMethods.get(role);
		if(!methods.contains(method)){
			System.out.println(methods);
			acc.methodCallReject(method, false);
			throw new SecurityException("403");
		}
		acc.methodCallReject(method, true);
		return joinPoint.proceed();
	}
}
