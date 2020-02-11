function imageFunction() {

	var imageCategory = [ "/getimage/1", "/getimage/2", "/getimage/3",
			"/getimage/4", "/getimage/5", "/getimage/6", "/getimage/7",
			"/getimage/8", "/getimage/9", "/getimage/10", "/getimage/11",
			"/getimage/12", "/getimage/13", "/getimage/14", "/getimage/15",
			"/getimage/16", "/getimage/17", "/getimage/18", "/getimage/19",
			"/getimage/20", "/getimage/21", "/getimage/22", "/getimage/23" ];

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

function dataFunction(){
	document.getElementById("articlenameFormOne").value = document.getElementById("articlenameFormTwo").value;
	document.getElementById("descriptionFormOne").value = document.getElementById("descriptionFormTwo").value;
}