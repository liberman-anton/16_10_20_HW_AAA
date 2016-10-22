package tel_ran.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationController {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		AplicationClass apCl =  (AplicationClass)ctx.getBean("appl_class");
		try {
			apCl.login("admin8", "123485");
			apCl.get1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			apCl.set1();
			apCl.set2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		ctx.close();
	}

}
