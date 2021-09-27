/**
 * 
 */


window.onload=function() {
	
	// 이메일 직접입력 및 뒷주소 선택
	document.getElementById('email').onchange = function() {
		var email = document.getElementById('email');
		var email2 = document.getElementById('email2');
		
		if(email[email.selectedIndex].value == '0'){
	 		email2.disabled = true;
			email2.value = "";
		}

		if(email[email.selectedIndex].value == '9'){
			email2.disabled = false;
			email2.value = "";
			email2.focus();
		} else{
			email2.disabled = true;
	 		email2.value = email[email.selectedIndex].value;
	 	}
	}
}

