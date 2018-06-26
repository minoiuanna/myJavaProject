package mysql.example;

public class Departments{
	String name = null;
	int idDepart=0;
	
	Departments( String n) {
		
		this.name = n;
		
	}

	String getname() {
		return this.name;
	}
	int getID() {

		return this.idDepart;
	}
}
