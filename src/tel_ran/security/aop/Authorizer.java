package tel_ran.security.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import tel_ran.security.Accounter;
import tel_ran.security.Authenticater;
import tel_ran.security.aop.annotations.Authorized;

public class Authorizer {
	Authenticater auth;
	Accounter acc;
	
	public Authorizer(Authenticater auth, Accounter acc) {
		super();
		this.auth = auth;
		this.acc = acc;
	}
	
	public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj = joinPoint.getTarget();
		String methodName = joinPoint.getSignature().getName();
		String role = auth.getRole(obj);
		
		checkAuthenticated(role,methodName);
		
		Method method = obj.getClass().getMethod(methodName);
		if(method.isAnnotationPresent(Authorized.class)){
			checkAuthorized(method,methodName,role);
		}
		
		acc.methodCallReject(methodName, true);
		return joinPoint.proceed();
	}

	private void checkAuthenticated(String role, String methodName) {
		if( role == null){
			acc.methodCallReject(methodName, false);
			throw new SecurityException("401");
		}
	}

	private void checkAuthorized(Method method, String methodName, String role) {
		String[] roles = method.getAnnotation(Authorized.class).roles();
		boolean flag = true;
		for(int i = 0; i < roles.length; i++){
			if(roles[i].equals(role))
				flag = false;
		}
		if(flag){
			acc.methodCallReject(methodName, false);
			throw new SecurityException("403");
		}		
	}
}
