package mysql.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepoEmployee {
	public RepoEmployee() {
	}

	String mess = "Error in SQL syntax";
	Logger logger = LoggerFactory.getLogger(RepoEmployee.class);
	Connection connect = null;

	// Connect to database
	private void dbConnect() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		} catch (Exception ex) {
			logger.error("Cannot connect to database server ", ex);

		}

		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/mydb?autoReconnect=true&useSSL=false",
					"AMinoiu", "password18");

		} catch (SQLException ex) {
			logger.error("Cannot connect to database ", ex);

		}

	}

	String querystatement = null;
	String name = null;
	float salary = 0;
	int employeeId = 0;
	int departmentId = 0;

	// Get data by Employ ID
	public Employee getById(int id) throws SQLException {
		dbConnect();
		querystatement = "SELECT * FROM Employee where idEmploy=" + id;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			preparedStatement = connect.prepareStatement(querystatement);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				name = resultSet.getString("EmployName");
				salary = resultSet.getFloat("EmploySalary");
				employeeId = resultSet.getInt("idEmploy");
				departmentId = resultSet.getInt("idDepartament");

			}

		} catch (Exception e) {
			logger.error(mess, e);

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
			if (resultSet != null)
				resultSet.close();
		}

		return new Employee(name, salary, departmentId);
	}

	// create a new row in database
	int create(Employee e) throws SQLException {
		dbConnect();
		Statement st1 = connect.createStatement();
		ResultSet resultSet = st1.executeQuery("SELECT MAX(idEmploy) FROM employee");
		int i = 0;
		while (resultSet.next()) {
			i = resultSet.getInt("MAX(idEmploy)");
		}

		PreparedStatement st = connect.prepareStatement("ALTER TABLE employee AUTO_INCREMENT = ?");
		st.setInt(1, i);

		String query = "insert into employee" + "(EmployName,EmploySalary,idDepartament) values" + "(?,?,?)";
		st = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, e.getname());
		st.setDouble(2, e.getsalary());
		st.setInt(3, e.getIdDepart());
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		int result = 0;
		if (rs.next()) {
			result = rs.getInt(1);
		}
		e.setID(result);
		return result;

	}

	// delete rows from database
	int delete(int id) {
		dbConnect();
		int nr = 0;
		try {
			PreparedStatement st = connect.prepareStatement("delete from employee where idEmploy= ?");
			st.setInt(1, id);

			nr = st.executeUpdate();

		}

		catch (Exception e) {
			logger.error(mess, e);
		}
		return nr;
	}

	// update record from database
	int update(Employee e, double salary) {
		dbConnect();
		int status = 0;
		try {

			PreparedStatement ps = connect.prepareStatement("update employee set EmploySalary=? where idEmploy=?");

			ps.setDouble(1, salary);

			ps.setInt(2, e.getID());

			status = ps.executeUpdate();

		} catch (Exception ex) {
			logger.error(mess, ex);

		}

		return status;
	}

	// convert Employee object to String
	public String toString(Employee a) {

		return (a.name + " " + a.salary + " " + a.iddepart);

	}

	HashMap<Integer, Employee> listofEmployees = new HashMap<Integer, Employee>();

	public Map<Integer, Employee> getallEmployees() {

		Statement st = null;
		ResultSet rs = null;

		Employee e;

		try {
			st = connect.createStatement();
			rs = st.executeQuery("SELECT * FROM employee");
			while (rs.next()) {
				Integer id = rs.getInt("idEmploy");
				String ename = rs.getString("EmployName");
				Double esalary = rs.getDouble("EmploySalary");
				int idDepart = rs.getInt("idDepartament");
				e = new Employee(ename, esalary, idDepart);
				listofEmployees.put(id, e);

			}

			for (Integer i : listofEmployees.keySet()) {
				Employee us = listofEmployees.get(i);

				String emp = i + " " + us.getname() + " " + us.getsalary() + " " + us.getIdDepart();
				logger.info(emp);
			}
		} catch (Exception ex) {
			logger.error(mess, ex);
		}
		return listofEmployees;
	}

	public Employee getPayroll(final int id) {
		return listofEmployees.get(id);
	}

}
