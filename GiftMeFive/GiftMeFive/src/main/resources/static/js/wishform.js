//function newImageFunction() {
//
//	var imageCategory = [ "/imageCategory/baby.webp",
//			"/imageCategory/beauty.webp", "/imageCategory/bekleidung.webp",
//			"/imageCategory/buecherZeitschriften.webp",
//			"/imageCategory/buero.webp", "/imageCategory/computer.webp",
//			"/imageCategory/dvd.webp", "/imageCategory/drogerie.webp",
//			"/imageCategory/foto.webp", "/imageCategory/games.webp",
//			"/imageCategory/garten.jpg", "/imageCategory/gutschein.webp",
//			"/imageCategory/haustier.webp", "/imageCategory/foto.webp",
//			"/imageCategory/haushalt.webp", "/imageCategory/cd.webp",
//			"/imageCategory/schmuck.webp", "/imageCategory/schuhe.webp",
//			"/imageCategory/software.webp", "/imageCategory/spielzeug.webp",
//			"/imageCategory/sport.webp", "/imageCategory/uhr.webp",
//			"/imageCategory/default.jpg" ];
//
//	var x = document.getElementById("modcategory").selectedIndex;
//	var y = document.getElementById("modcategory").options;
//
//	document.getElementById("modcategory_wish_image").src = imageCategory[y[x].index];
//	document.getElementById("modcategoryImage").value = imageCategory[y[x].index];
//
//}

//Zweite, geklonte Funktion für Kategoriebild, zur Unterscheidung der IDs für Modal und HTML-Body (wishform_list.html) 
function imageFunction() {

	var imageCategory = [ "/imageCategory/baby.webp",
			"/imageCategory/beauty.webp", "/imageCategory/bekleidung.webp",
			"/imageCategory/buecherZeitschriften.webp",
			"/imageCategory/buero.webp", "/imageCategory/computer.webp",
			"/imageCategory/dvd.webp", "/imageCategory/drogerie.webp",
			"/imageCategory/foto.webp", "/imageCategory/games.webp",
			"/imageCategory/garten.jpg", "/imageCategory/gutschein.webp",
			"/imageCategory/haustier.webp", "/imageCategory/foto.webp",
			"/imageCategory/haushalt.webp", "/imageCategory/cd.webp",
			"/imageCategory/schmuck.webp", "/imageCategory/schuhe.webp",
			"/imageCategory/software.webp", "/imageCategory/spielzeug.webp",
			"/imageCategory/sport.webp", "/imageCategory/uhr.webp",
			"/imageCategory/default.jpg" ];

	var x = document.getElementById("Category").selectedIndex;
	var y = document.getElementById("Category").options;

	document.getElementById("Category_wish_image").src = imageCategory[y[x].index];
	document.getElementById("CategoryImage").value = imageCategory[y[x].index];

}

function imgResetFunction() {
	document.getElementById("bildlink").value = '';
	document.getElementById("bildlink").label.addClass("selected").html(
			'default-title');
}

//function fillEditwish () {
//	var listArticleId = document.getElementById("listArticleId}").value;
//	var listArticlename = document.getElementById("listArticlename").value;
//	var listDescription = document.getElementById("listDescription").value;
//	var listProductlink = document.getElementById("listProductlink").value;
//	var listImagelink = document.getElementById("listImagelink").value;
//	var listWishlistId = document.getElementById("listWishlistId").value;
//	    console.log("editing wish " + listArticleId + listArticlename);
//	    document.getElementById("articleId").value = listArticleId;
//	    document.getElementById("articlename").value = listArticlename;
//	    document.getElementById("description").value = listDescription;
//	    document.getElementById("productlink").value = listProductlink;
//	    document.getElementById("userimage").value = listImagelink;
//	    document.getElementById("wishlistId").value = listWishlistId;
//	    document.getElementById("CategoryImage").value = listImagelink;
//}