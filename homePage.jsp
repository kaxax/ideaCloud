<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Quartz iStorage</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<script>
var avatarCanvas;
var avatarImage;
var WallCanvas;
var TextField;
var postButton;
var posts = [];
function createCanvas(canvasName, cWidth, cHeight, cX, cY){
	Canvas = document.createElement('canvas');
	Canvas.id = canvasName;
	Canvas.style.zIndex = 8;
	Canvas.style.position = "absolute";
	Canvas.style.border = "1px solid";
	Canvas.style.left = cX+"%";
	Canvas.style.width = cWidth+"%";
	Canvas.style.height = cHeight+"%";
	Canvas.style.top = cY+"%";
	return Canvas;
}
function updatePosts(postText){
	h = window.innerHeight;
	//15 konstantaa
	postLen = (h*10)/100;
	wlHeight = WallCanvas.height/h;
	WallCanvas.style.height = (wlHeight)*100 +10 +"%";
	WallCanvas.height = (h * ((wlHeight)*100 + 10))/100;	
	var body = document.getElementsByTagName("body")[0];
	var ctx=WallCanvas.getContext("2d");
	posts.push(postText);
	ctx.font="20px Georgia";
	for (i=0;i<posts.length;i++){
		if(i == 0){
			ctx.fillText(posts[i],WallCanvas.width/10,10);		
		}else{
			ctx.fillText(posts[i],WallCanvas.width/10,i*postLen);		
		}
			
	}
	ctx.font="30px Verdana";
	body.removeChild(WallCanvas);
	body.appendChild(WallCanvas);
}
function CreateButton(fieldName,cWidth, cHeight, cX, cY, buttonName){
    x = document.createElement("INPUT");
    x.id = fieldName;
    x.setAttribute("type", "BUTTON");
	x.style.position = "absolute";
	x.style.border = "1px solid";
	x.style.left = cX+"%";
	x.style.top = cY+"%";
	x.style.width = cWidth+"%";
	x.style.height = cHeight+"%";
	x.value = buttonName;
	x.onclick = function() { 	
		if(TextField.value != ""){
			updatePosts(TextField.value);
			TextField.value="";	
		}
	};
	var foo = document.getElementById("fooBar");  
    return x;
}

function CreateTextField(fieldName,cWidth, cHeight, cX, cY) {
    x = document.createElement("INPUT");
    x.id = fieldName;
    x.setAttribute("type", "text");
	x.style.position = "absolute";
	x.style.border = "1px solid";
	x.style.left = cX+"%";
	x.style.top = cY+"%";
	x.style.width = cWidth+"%";
	x.style.height = cHeight+"%";
    return x;
}
window.onload = function() {
	WallCanvasX =20 ;
	WallCanvasWitdh =60;
	WallCanvasHeight =10;
	textfieldX=WallCanvasX;
	textfieldY=20;
	textfieldHeight=10;
	postButtonHeight = 4;
	WallCanvasY =textfieldY + textfieldHeight + postButtonHeight + textfieldHeight;
	texfieldWidth = WallCanvasWitdh*0.6;
	postButtonWidth = 6;
	postButtonX= textfieldX + texfieldWidth - postButtonWidth;
	postButtonY = textfieldY+ textfieldHeight+ textfieldHeight/10;
	var body = document.getElementsByTagName("body")[0];
	avatarCanvas = createCanvas("avatarCnv", 10, 10,"2","4");
	TextField = CreateTextField("txtField", texfieldWidth, textfieldHeight, textfieldX, textfieldY);
	WallCanvas = createCanvas("wallCnv", WallCanvasWitdh, WallCanvasHeight,WallCanvasX,WallCanvasY);
	postButton = CreateButton("postBtn",postButtonWidth, postButtonHeight, postButtonX, postButtonY,"post");
	body.appendChild(avatarCanvas);
	body.appendChild(WallCanvas);
	body.appendChild(TextField);
	body.appendChild(postButton);
    this.ctx = avatarCanvas.getContext("2d");
    ctx.moveTo(10,10);
    avatarImage = new Image();
    avatarImage.src = "max.jpg";
    ctx.drawImage(avatarImage, 10, 10, 150, 180);
    
}
</script>
</body>
</html>
