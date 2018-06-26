package mysql.example;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRunner {

	@Test
	public void MyProject_test() throws SQLException {
		Logger logger = LoggerFactory.getLogger(TestRunner.class);
		RepoEmployee d = new RepoEmployee();
		Employee e = new Employee("ex", 15.2, 2);
		int is = d.create(e);
		
		assertTrue(is > 0);
		logger.info("Was created the new record");
		
		int del = 0;
		del = d.delete(2);
		if(del==0) {
		assertFalse( del != 0);
		logger.info("Was not deleted the record");}
		
		else {
		assertTrue(del != 0);
		logger.info("Was deleted the record");}
		
		int up=0;
		up=d.update(e,158.2);
		assertTrue(up != 0);
		logger.info("Was updated the record");
		
		

	}
}