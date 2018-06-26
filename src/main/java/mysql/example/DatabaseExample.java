package mysql.example;

import java.sql.SQLException;

import org.slf4j.*;

public class DatabaseExample {

	public static void main(String[] args) throws SQLException {
		Logger logger = LoggerFactory.getLogger(DatabaseExample.class);
		RepoEmployee tab1 = new RepoEmployee();
		RepoDepartments tab2 = new RepoDepartments();
		Employee e1 = new Employee("Ion", 14.5, 2);

		String s = tab1.toString(tab1.getById(3));
		logger.info("Employee: {}", s);

		s = tab2.toString(tab2.getById(4));
		logger.info("Department: {}", s);

		int id = tab1.create(e1);
		logger.info("The id of employee added: {} ", id);

		int nr = tab1.delete(65);
		logger.info("The number of rows deleted: {}", nr);

		nr = tab1.update(e1, 152.2);
		logger.info("The number of rows updated: {}", nr);
		tab1.getallEmployees();
	}

}
