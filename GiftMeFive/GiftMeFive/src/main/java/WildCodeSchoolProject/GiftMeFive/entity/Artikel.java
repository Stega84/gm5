package WildCodeSchoolProject.GiftMeFive.entity;

public class Artikel {

	String name;
	String beschreibung;
	String datum;
	String bildlink;
	String produktlink;
	String preis;

	public Artikel(String name, String beschreibung, String datum, String bildlink, String produktlink, String preis) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.datum = datum;
		this.bildlink = bildlink;
		this.produktlink = produktlink;
		this.preis = preis;
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

}
