function imageFunction() {

	var imageCategory = [ "/imageCategory/default.jpg" ,"/imageCategory/baby.webp",
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
			"/imageCategory/sport.webp", "/imageCategory/uhr.webp"];

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