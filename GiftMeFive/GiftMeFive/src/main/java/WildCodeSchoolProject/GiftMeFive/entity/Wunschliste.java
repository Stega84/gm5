package WildCodeSchoolProject.GiftMeFive.entity;

public class Wunschliste {
	
	Long id;
	String name;
	String enddatum;
	
	public Wunschliste(Long id, String name, String enddatum) {
		super();
		this.id = id;
		this.name = name;
		this.enddatum = enddatum;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnddatum() {
		return enddatum;
	}
	public void setEnddatum(String enddatum) {
		this.enddatum = enddatum;
	}

}
