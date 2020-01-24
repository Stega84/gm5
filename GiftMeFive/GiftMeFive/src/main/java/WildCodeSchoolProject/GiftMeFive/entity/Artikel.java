package WildCodeSchoolProject.GiftMeFive.entity;

public class Artikel {

	Long id;
	String name;
	String beschreibung;
	String datum;
	String bildlink;
	String produktlink;
	String preis;
	Long wunschliste_id;
	String wunschliste_name;
	Boolean reserviert;
	String reservierung_name;

	public Artikel(Long id, String name, String beschreibung, String datum, String bildlink, String produktlink, String preis, Long wunschliste_id, String wunschliste_name, Boolean reserviert, String reservierung_name) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.datum = datum;
		this.bildlink = bildlink;
		this.produktlink = produktlink;
		this.preis = preis;
		this.wunschliste_id = wunschliste_id;
		this.wunschliste_name = wunschliste_name;
		this.reserviert = reserviert;
		this.reservierung_name = reservierung_name;
	}
	
	public Artikel(Long id, String name, String beschreibung, String bildlink, String produktlink, String preis) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.bildlink = bildlink;
		this.produktlink = produktlink;
		this.preis = preis;
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
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getBildlink() {
		return bildlink;
	}
	public void setBildlink(String bildlink) {
		this.bildlink = bildlink;
	}
	public String getProduktlink() {
		return produktlink;
	}
	public void setProduktlink(String produktlink) {
		this.produktlink = produktlink;
	}
	public String getPreis() {
		return preis;
	}
	public void setPreis(String preis) {
		this.preis = preis;
	}
	public Long getWunschliste_id() {
		return wunschliste_id;
	}	
	public void setWunschliste_id(Long wunschliste_id) {
		this.wunschliste_id = wunschliste_id;
	}
	public String getWunschliste_name() {
		return wunschliste_name;
	}
	public void setWunschliste_name(String wunschliste_name) {
		this.wunschliste_name = wunschliste_name;
	}
	public Boolean getReserviert() {
		return reserviert;
	}
	public void setReserviert(Boolean reserviert) {
		this.reserviert = reserviert;
	}
	public String getReservierung_name() {
		return reservierung_name;
	}
	public void setReservierung_name(String reservierung_name) {
		this.reservierung_name = reservierung_name;
	}

}
