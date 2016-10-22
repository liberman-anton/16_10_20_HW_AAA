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
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ctx.close();
	}

	@Test
	public void testAdmin() {
		boolean flag = true;
		apCl = (AplicationClass)ctx.getBean("appl_class");
		apCl.login("admin", "12345");
		try {
			apCl.get1();
			apCl.set1();
			apCl.set2();
		} catch (Exception e) {
			flag = false;
		}
		assertTrue(flag);
		System.out.println();
	}
	
	@Test
	public void testUser() {
		apCl = (AplicationClass)ctx.getBean("appl_class");
		String message = null;
		apCl.login("user", "123");
		boolean flag = true;
		try {
			apCl.get1();
		} catch (Exception e1) {
			flag = false;
		}
		assertTrue(flag);
		try {
			apCl.set1();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("403", message);
		try {
			apCl.set2();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("403", message);
		System.out.println();
	}
	
	@Test
	public void testBoss() {
		apCl = (AplicationClass)ctx.getBean("appl_class");
		String message = null;
		apCl.login("admin", "777");
		try {
			apCl.get1();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("401", message);
		try {
			apCl.set1();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("401", message);
		try {
			apCl.set2();
		} catch (SecurityException e) {
			message = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertEquals("401", message);
		System.out.println();
	}
}
