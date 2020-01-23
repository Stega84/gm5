package WildCodeSchoolProject.GiftMeFive.entity;

public class Reservierung {

	Long id;
	String name;
	Boolean reserviert;

	public Reservierung(Long id, String name, Boolean reserviert) { 
		this.id = id;
		this.name = name;
		this.reserviert = reserviert;
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

	public Boolean getReserviert() {
		return reserviert;
	}

	public void setReserviert(Boolean reserviert) {
		this.reserviert = reserviert;
	}
	

}
