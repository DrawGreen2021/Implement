/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

window.onload=function(){
	const pw = $('#pw').val();
	const pw_chk = $('#pw_chk').val();
	
	$('#updatePWBtn').click(function() {
		if (pw == pw_chk) {
			$("form").attr("action", "UpdatePw.do");
		}
		else {
			alert("비밀번호가 다릅니다.");
			return false;
		}
	});
}