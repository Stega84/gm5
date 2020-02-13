package WildCodeSchoolProject.GiftMeFive.entity;

public class User {
	
	String name;
	String datum;
	
	public User() {
		
	}
	
	public User(String name, String datum) {
		this.name = name;
		this.datum = datum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	
	
}
