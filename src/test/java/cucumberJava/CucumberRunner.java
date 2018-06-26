package cucumberJava;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mysql.example.*;

public class CucumberRunner {
	RepoEmployee r = new RepoEmployee();
	Employee e = new Employee();

	@Given("^For the employee with id (\\d+) the manager said that will increase the salary$")
	public void for_the_employee_with_id_the_manager_said_that_will_increase_the_salary(int id) throws Throwable {
		e = r.getById(id);
	}

	@When("^The manager want to increse the salary with (\\d+)%$")
	public void the_manager_want_to_increse_the_Ion_salary_with(int perc) throws Exception {
		double salary = e.getsalary();
		double salaryInc = 0.0;
		salaryInc = salary + (salary * ((double) perc / 100.0));
		salaryInc = Math.round(salaryInc * 100.0) / 100.0;
		e.setSalary(salaryInc);
	}

	@Then("^In the final,the employee with id (\\d+) should has as salary  (\\-?\\d+\\.\\d+)$")
	public void in_the_final_the_employee_with_id_should_has_as_salary(int id, double salary) throws Throwable {
		RepoEmployee re = new RepoEmployee();
		re.getPayroll(id);
		salary = Math.round(salary * 100.0) / 100.0;
		assertEquals(salary, e.getsalary(), 2);
	}

}
