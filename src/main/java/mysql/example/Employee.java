package mysql.example;

public class Employee {
	String name = null;
	double salary = 0;
	int id = 0;
	int iddepart = 0;

	public Employee() {
	}

	Employee(String n, double s, int idd) {

		this.name = n;
		this.salary = s;
		this.iddepart = idd;
	}

	String getname() {
		return this.name;
	}

	public double getsalary() {
		return this.salary;
	}

	int getID() {

		return this.id;
	}

	int getIdDepart() {
		return this.iddepart;
	}

	void setID(int id) {

		this.id = id;
	}

	void setName(String name) {

		this.name = name;
	}

	void setDepartID(int id) {

		this.iddepart = id;
	}

	public void setSalary(double sal) {
		this.salary = sal;
	}

	

}