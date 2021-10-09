/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

var doubleSubmitFlag = false;


function fncSubmit(){
    if(doubleSubmitFlag){
        return false;
    }else {
    	$('#findGreenCorp').submit();
        doubleSubmitFlag = true;
    }
}

function resetKeyword() {
	var form = document.findGreenCorp;
	var noKeyword = "";
	
	form.keyword.value = noKeyword;
	form.actiond = "FindGreenCorp.do";
	form.method = "get";
	form.submit();
}