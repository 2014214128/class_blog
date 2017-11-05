var curr =1;
function changePicture(){
	var image = document.getElementById("image");
	if (curr < 5){
		curr ++;
	}else{
		curr = 1;
	}

	image.src = "/blog/src/images/photo/"+curr+".jpg";

	setTimeout(changePicture,2000);
}

window.onload = function(){
	changePicture();
	for (var i = 1; i < 6; i++) {
		document.getElementById("image"+i+"").addEventListener("click",function(){
			transfer(this.src);
			curr = 0;
		},false);
	}
}
function transfer(str){
	var image = document.getElementById("image");
	image.src = str;
}

