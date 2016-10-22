package tel_ran.application.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.application.AplicationClass;

public class TestAuth {
	static AbstractApplicationContext ctx;
	static AplicationClass apCl;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new FileSystemXmlApplicationContext("beansAOP.xml");
		apCl = (AplicationClass)ctx.getBean("appl_class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ctx.close();
	}

	@Test
	public void testAdmin() {
		apCl.login("admin", "12345");
		apCl.get1();
		apCl.set1();
		apCl.set2();
		System.out.println();
	}
	
	@Test
	public void testUser() {
		String message = null;
		apCl.login("user", "123");
		apCl.get1();
		try {
			apCl.set1();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("403", message);
		System.out.println();
	}
	
//	@Test
	public void testBoss() {
		String message = null;
		apCl.login("boss", "777");
		try {
			apCl.set1();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("401", message);
		System.out.println();
	}
}
