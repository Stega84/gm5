package WildCodeSchoolProject.GiftMeFive.entity;

public class Article {

	Long id;
	String name;
	String description;
	String creationdate;
	String imagelink;
	String productlink;
	Long wishlistId;
	String wishlistname;
	Boolean reserved;
	String reservationname;
	
	public Article(Long id, String name, String description, String creationdate, String imagelink, String productlink,
			Long wishlistId, String wishlistname, Boolean reserved, String reservationname) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationdate = creationdate;
		this.imagelink = imagelink;
		this.productlink = productlink;
		this.wishlistId = wishlistId;
		this.wishlistname = wishlistname;
		this.reserved = reserved;
		this.reservationname = reservationname;
	}

	public Article(String name, String description, String imagelink, String productlink, Long wishlistId) {
		super();
		this.name = name;
		this.description = description;
		this.imagelink = imagelink;
		this.productlink = productlink;
		this.wishlistId = wishlistId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getImagelink() {
		return imagelink;
	}

	public void setImagelink(String imagelink) {
		this.imagelink = imagelink;
	}

	public String getProductlink() {
		return productlink;
	}

	public void setProductlink(String productlink) {
		this.productlink = productlink;
	}

	public Long getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Long wishlistId) {
		this.wishlistId = wishlistId;
	}

	public String getWishlistname() {
		return wishlistname;
	}

	public void setWishlistname(String wishlistname) {
		this.wishlistname = wishlistname;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public String getReservationname() {
		return reservationname;
	}

	public void setReservationname(String reservationname) {
		this.reservationname = reservationname;
	}
	
	
}	


