/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

var doubleSubmitFlag = false;

document.getElementById('keyword').value = saveValue('keyword');


function fncSubmit(){
    if(doubleSubmitFlag){
        return false;
    }else {
    	$('#findGreenCorp').submit();
        doubleSubmitFlag = true;
    }
}

window.onload=function(){
	function getParameterByName(name) { 
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]"); 
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), 
			results = regex.exec(location.search); 
		return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " ")); 
	}

	var keyword = getParameterByName('keyword');
	$('#keyword').val(keyword);
	console.log(keyword);
	
	
}

function saveValue(e){
    var id = e.id;  // get the sender's id to save it . 
    var val = e.value; // get the value. 
    localStorage.setItem(id, val);// Every time user writing something, the localStorage's value will override . 
}

function getSavedValue(v){
    if (!localStorage.getItem(v)) {
        return "";// You can change this to your defualt value. 
    }
    return localStorage.getItem(v);
}