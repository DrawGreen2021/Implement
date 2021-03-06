/**
 * 비밀번호 찾기에 필요한 JS파일
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

window.onload=function(){
	
	// 사용자 이메일로 인증번호 전송
	$('#emailSendBtn').click(function () {
		var irregular = validate();
		
		if(!irregular) {
			const email = $('#findInfo_email').val();
			
			$.ajax({
	            type:'post',
	            async:false,
	            url:'EmailSend.do',
	            dataType:'text',
	            data:{"email":email},
	            success: function(data, textStatus) {
	                if(data === 'connectable') {
	                	alert("이메일 주소 인증 메일이 전송되었습니다. 인증번호를 확인해주세요.");           
	                } else {
	                	alert("유효하지 않은 이메일입니다.");
	                }
	            },
	            error:function (data, textStatus) {
	                console.log('error');
	            }
	        });   //ajax
	        
		}
		return false;
	});
	
	// 인증코드 체크 후에 아이디 찾기 수행
	$('#findPwBtn').click(function() {
		var irregular = validate();
		
		if(!irregular) {
			const email_auth_code = $('#email_auth_code').val();
			
			if(email_auth_code.length === 0 || email_auth_code === null) {
				alert("인증번호를 입력하세요");
				return false;
			}
			
			$.ajax({
	            type:'post',
	            async:false,
	            url:'EmailCheck.do',
	            dataType:'text',
	            data:{"email_auth_code":email_auth_code},
	            success: function(data, textStatus) {
	                if(data === 'authenticated') {
	                	$("form").attr("action", "FindPw.do");
	                	$("form").submit();
	                } else {
	                    alert("인증번호가 다릅니다. 다시 확인해주세요.");
	                }
	            },
	            error:function (data, textStatus) {
	                console.log('error');
	            }
	        });   //ajax
		}
	
        return false;
	});
}

function validate(){
	var irregular = false;
	var findInfo_id = document.getElementById("findInfo_id").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	//아이디 체크
	if(findInfo_id.length == 0){
		alert("아이디를 입력하세요");
		irregular = true;
	}
	else if(findInfo_id.search(/\s/) != -1){
		alert("아이디에 공백은 들어갈 수 없습니다");
		irregular = true;
	}
	else if(pattern_chk1.test(findInfo_id)){
		alert("아이디에 특수문자는 들어갈 수 없습니다");
		irregular = true;
	}
	
	//이메일 체크
	else if(findInfo_email.length === 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		irregular = true;
	}
	
	return irregular;
}