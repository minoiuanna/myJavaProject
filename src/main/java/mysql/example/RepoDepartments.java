package mysql.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepoDepartments {
	Connection connect = null;
	String mess = "Error in SQL syntax";
	Logger logger = LoggerFactory.getLogger(RepoEmployee.class);

	private void dbConnect() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		} catch (Exception ex) {
			logger.error("Cannot connect to server ", ex);

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
	int departmentId = 0;

	// Get data by Departments ID
	Departments getById(int id) throws SQLException {

		querystatement = "SELECT * FROM Departments where idDepartments=" + id;
		dbConnect();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			preparedStatement = connect.prepareStatement(querystatement);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				departmentId = resultSet.getInt("idDepartments");
				name = resultSet.getString("DepartmentsName");

			}

			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			logger.error(mess, e);

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
			if (resultSet != null)
				resultSet.close();
		}

		return new Departments(name);
	}

	public int create(Departments e) throws SQLException {
		dbConnect();
		String query = "insert into departments" + "(DepartmentsName) values" + "(?)";
		PreparedStatement st = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, e.getname());

		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		int result = 0;
		if (rs.next()) {
			result = rs.getInt(1);
		}
		return result;

	}

	int delete(int id) {
		dbConnect();
		int nr = 0;
		try {
			PreparedStatement st = connect.prepareStatement("delete from Departments where idDepartments= ?");
			st.setInt(1, id);

			nr = st.executeUpdate();

		}

		catch (Exception e) {
			logger.error(mess, e);
		}
		return nr;
	}

	int update(Departments d) {
		dbConnect();
		int status = 0;
		try {

			PreparedStatement ps = connect
					.prepareStatement("update employee set DepartmentsName=? where idDepartments=?");

			ps.setString(1, d.getname());

			ps.setInt(2, d.getID());

			status = ps.executeUpdate();

		} catch (Exception ex) {
			logger.error(mess, ex);
		}

		return status;
	}

	public void aquery(Connection c) throws SQLException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			preparedStatement = c.prepareStatement(querystatement);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				departmentId = resultSet.getInt("idDepartments");
				name = resultSet.getString("DepartmentsName");

			}

			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			logger.error(mess, e);

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
			if (resultSet != null)
				resultSet.close();
		}

	}

	public String toString(Departments a) {

		return (a.getname());

	}

}
