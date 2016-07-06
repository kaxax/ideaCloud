var name = " ";
var chater = " ";
var ChatEngine=function(){
     
     var msg="";
     var chatZone=document.getElementById("chatZone");
     var oldata ="";
     var sevr=" ";
     var xhr=" ";
     var xhhr = " ";
     
     this.init=function(){
          if(EventSource){
          this.setName();
          } else{
          alert("Use latest Chrome or FireFox");
        }
     };
     this.setName=function(){
          name = document.getElementById("host").innerHTML;
          chater = document.getElementById("second").innerHTML;
          
          if (!name || name ==="") {
             name = "Chater";  
          }
          
     };
     this.sendMsg=function(){ 
    	 if(name.length !=0 && chater.length!=0){
          msg=document.getElementById("msg").value;
          chatZone.innerHTML+='<div class="chatmsg"><b>'+name+'</b>: '+msg+'<br/></div>';
          oldata='<div class="chatmsg"><b>'+name+'</b>: '+msg+'<br/></div>'; 
          
          this.ajaxSent();  
    	 }
          return false;
     };
     this.ajaxSent=function(){
          try{
               xhr=new XMLHttpRequest();
          }
          catch(err){
               alert(err);
          }
          xhr.open("GET","ChatServer?msg="+msg+"&name="+name+"&second="+chater,false);
          xhr.onreadystatechange = function(){
               if(xhr.readyState == 4) {
                    if(xhr.status == 200) {
                         msg.value="";
                    }
               }     
          };
          xhr.send();
          document.getElementById("msg").value="";
     };
     
};
show =function(){
	
	var x = num;
	num = 0;
	document.getElementById("chatZone").innerHTML = "";
	for (; num < jsArray.length-1 && num<= x+10; num+=2) {
		document.getElementById("chatZone").innerHTML='<div class="chatmsg"><b>'+jsArray[num]+'</b>: '+jsArray[num+1]+'<br/></div>' + document.getElementById("chatZone").innerHTML ;
		}
	
	};
peer = function(id ){
	
	if(chater !=document.getElementById(id).value ){
		document.getElementById("chatZone").innerHTML = "";
	}
	chater = document.getElementById(id).value;
	document.getElementById("second").innerHTML = chater;
	num = 0;
	try{
		 xhhr =new XMLHttpRequest();
	 	}
  catch(err){
       alert(err);
  	}
  	xhhr .onreadystatechange = function() {
			    if (xhhr .readyState == 4 && xhhr .status == 200) {	
			    	
			    	strk = xhhr.responseText;
			    	jsArray = strk.split(",");
			    
			    	
			    	}
			 
		};
		xhhr .open("POST","notificationServer?name="+name+"&second="+chater,false);
		xhhr .send(); 
	
};

var chat= new ChatEngine();
chat.init();
setInterval(function(){ 
	if(name.length !=0 && chater.length!=0){
	try{
		 xhhr =new XMLHttpRequest();
	 	}
   catch(err){
        alert(err);
   	}
   xhhr .onreadystatechange = function() {
			    if (xhhr .readyState == 4 && xhhr .status == 200) {	
			    	strk = xhhr .responseText;
			    	chatZone.innerHTML+=strk;        
			 }
		};
		xhhr .open("POST","ChatServer?sender="+chater+"&reciever="+name,false);
		xhhr .send(); 
	}
}, 1000);
setInterval(function(){ 
	try{
		 xhhr =new XMLHttpRequest();
	 	}
   catch(err){
        alert(err);
   	}
   xhhr .onreadystatechange = function() {
			    if (xhhr .readyState == 4 && xhhr .status == 200) {	
			    	var arr;
			    	strk = xhhr.responseText;
			    	if(strk.length>0){
			    		arr = strk.split(",");
			    	
			    	document.getElementById("peers").innerHTML= "";
			    	
			    	
			    	for(var i=0; i<arr.length; i++){
			    		document.getElementById("peers").innerHTML+= '<input type="submit" class="myButton" value=' + arr[i] +' id= '+ arr[i] + ' onclick = "peer(this.id)"/> ' ;
			    		}
			    	}
			 }
		};
		xhhr .open("GET","notificationServer?name="+name,false);
		xhhr .send(); 
	
}, 1000);


