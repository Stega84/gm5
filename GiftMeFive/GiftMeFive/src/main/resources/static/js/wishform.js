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

function save() {
	var t1 = document.getElementById('wishlistId');
    var t2 = document.getElementById('titlename');
    var t3 = document.getElementById('text_area');
    var fname = document.getElementById('filename');

      /* var data = '[wishlistId ' + 1.value + ' wishlistName ' + t2.value + '] csvData>> ' + t3.value; */
      var data = t3.value;
      var filename = fname.value
      var textToBLOB = new Blob([data], { type: 'text/plain' });

		var sFileName = filename +'.csv';   // The file name.                 	

      var newLink = document.createElement("a");
      newLink.download = sFileName;

      if (window.webkitURL != null) {
          newLink.href = window.webkitURL.createObjectURL(textToBLOB);
      }
      else {
          newLink.href = window.URL.createObjectURL(textToBLOB);
          newLink.style.display = "none";
          document.body.appendChild(newLink);
      }
      newLink.click();
 }

function saveRes() {
	var t1 = document.getElementById('wishlistId');
    var t2 = document.getElementById('reservationname');
    var t3 = document.getElementById('text_area');
    var fname = document.getElementById('filename');

      /* var data = '[wishlistId ' + 1.value + ' wishlistName ' + t2.value + '] csvData>> ' + t3.value; */
      var data = t3.value;
      var filename = fname.value
      var textToBLOB = new Blob([data], { type: 'text/plain' });

		var sFileName = filename +'.csv';   // The file name.                 	

      var newLink = document.createElement("a");
      newLink.download = sFileName;

      if (window.webkitURL != null) {
          newLink.href = window.webkitURL.createObjectURL(textToBLOB);
      }
      else {
          newLink.href = window.URL.createObjectURL(textToBLOB);
          newLink.style.display = "none";
          document.body.appendChild(newLink);
      }
      newLink.click();
 }
