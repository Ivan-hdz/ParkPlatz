
window.onunload = sale;
var valor;
if(document.cookie){
	galleta = unescape(document.cookie);
	galleta = galleta.split(';');
	for(m=0; m<galleta.length; m++){
		if(galleta[m].split('=')[0] === "recarga"){
			valor = galleta[m].split('=')[1];
			break;
		}
	}
	if(valor === "sip"){
		document.cookie = "recarga=nop"; 
		window.onunload = function(){};
		document.location.reload();
	}
	else{
	window.onunload=sale;
	}
}
function sale(){
	document.cookie ="recarga=sip";
}

