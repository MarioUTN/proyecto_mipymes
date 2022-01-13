function habilitar(){
a=document.getElementById("a").value;
b=document.getElementById("b").value;
c=document.getElementById("c").value;
val=0;
if(a=""){
	val++;
}

if(b=""){
	val++;
}

if(c=""){
	val++;
}

if(val==0){
	document.getElementById("btn-editar").disable=false;
} else {
	document.getElementById("btn-editar").disable=true;
}
}

document.getElementById("a").addEventListener("keyup",habilitar);
document.getElementById("b").addEventListener("keyup",habilitar);
document.getElementById("c").addEventListener("keyup",habilitar);

document.getElementById("btn-editar").addEventListener("click",() => { alert("Hacer el Llenado")});
