/**
 * 회원가입에 필요한 JS파일
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


window.onload=function(){
	 
	$("#signUpBtn").click(function () {
		var id = $('#id').val();
		var authID = $('#authID').val();
		var pw = $('#pw').val();
		var pw_chk = $('#pw_chk').val();
		var name = $('#name').val();
		var year = $('#year').val();
		var month = $('#month').val();
		var day = $('#day').val();
		var gender = $('#gender').val();
		var email = $('#email').val();
		var authEmail = $('#authEmail').val();
		
		var pattern_chk_specialChar1 = /[`~!@#$%^&*|\\\'\";:\/?]/gi; //아이디 특수문자 체크
		var pattern_chk_number = /[0-9]/;
		var pattern_chk_char = /[a-zA-Z]/;
		var pattern_chk_specialChar2 = /[~!@#$%^&*()_+:{}]/;
		
		// 아이디 입력 검사
		if(id.length === 0 || id === null) {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		// 아이디 공백 검사
		else if(id.search(/\s/) != -1) {
			alert("아이디에 공백은 들어갈 수 없습니다.");
			return false;
		}
			
		// 아이디 특수문자 검사
		else if(pattern_chk_specialChar1.test(id)) {
			alert("아이디에 특수문자는 들어갈 수 없습니다.");
			return false;
		}	
		
		// 아이디 인증 검사
		else if(authID == "false") {
			alert("아이디를 인증해주세요.");
			return false;
		}
		
		// 비밀번호 입력 검사
		else if(pw.length == 0 || pw == null) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		// 비밀번호 유효성 검사: 8자리 이상 문자, 숫자, 특수문자로 구성
		else if(!pattern_chk_number.test(pw) || !pattern_chk_char.test(pw) 
				|| !pattern_chk_specialChar2.test(pw) || pw.length < 8) {
			alert("비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다.");
			return false;
		}
		
		// 비밀번호 재확인 검사
		else if(pw != pw_chk) {
			alert("입력하신 비밀번호가 다릅니다.");
			return false;
		}
		
		// 이름 입력 검사
		else if(name.length === 0 || name === null) {
			alert("이름을 입력하세요.");
			return false;
		}
		
		// 이름 공백 검사
		else if(name.search(/\s/) != -1) {
			alert("이름에 공백은 들어갈 수 없습니다.");
			return false;
		}
		
		// 이름 특수문자 검사
		else if(pattern_chk_specialChar1.test(name)) {
			alert("이름에 특수문자는 들어갈 수 없습니다.");
			return false;
		}
		
		// 이름 숫자 검사
		else if(pattern_chk_number.test(name)) {
			alert("이름에 숫자는 들어갈 수 없습니다.");
			return false;
		}
		
		// 생년월일 검사
		else if(year.length != 4) {
			alert("생년은 4자리 숫자여야 합니다.");
			return false;
		}
		
		else if(month.length != 2) {
			alert("생월은 2자리 숫자여야 합니다.");
			return false;
		}
		
		else if(day.length != 2) {
			alert("생일은 2자리 숫자여야 합니다.");
			return false;
		}
		
		// 성별 선택 검사
		else if(gender.length == 0) {
			alert("성별을 선택하세요.");
			return false;
		}
		
		// 이메일 입력 검사
		else if(email.length === 0 || email === null) {
			alert("이메일을 입력하세요.");
			return false;
		} 
		
		// 이메일 인증 검사
		else if(authEmail == "false") {
			alert("이메일을 인증해주세요.");
			return false;
		}
		
		else {
			if(confirm("입력하신 정보로 가입하시겠습니까?") == true){
				$("form").attr("action", "SignUp.do");
			} else {
				return false;
			}
		}
		
	});

	// 아이디 중복 체크
	 $('#idCheckBtn').click(function () { 
		
		const id = $('#id').val();

        if(id.length === 0 || id === null) {
        	alert("아이디를 입력하세요.");
        	return false;
        }

        $.ajax({
            type:'post',
            async:false,
            url:'IdCheck.do',
            dataType:'text',
            data:{"id":id},
            success: function(data, textStatus) {
                if(data === 'usable') {
                   // $('#idCheckMessage').text('사용할 수 있는 ID입니다.')    
					$('#authID').val("true");
					alert("사용할 수 있는 ID입니다."); 
                } else {
                    //$('#idCheckMessage').text('이미 사용 중인 ID입니다.')
					alert("이미 사용 중인 ID입니다.");
                }
            },
            error:function (data, textStatus) {
                console.log('error');
            }
        });   //ajax
        return false;
	});
	

	// 사용자 이메일로 인증번호 전송
	$('#emailSendBtn').click(function () {
		
		const email = $('#email').val();
		var signUp = true;

		if(email.length === 0 || email === null) {
			alert("이메일을 입력하세요.");
			return false;
		}
		
		$.ajax({
            type:'post',
            url:'EmailSend.do',
            dataType:'text',
            async:false,
            data:{"email":email, "signUp":signUp},
            success: function(data, textStatus) {
            	
            	if(data === 'duplicated') {
            		alert("이미 누가 사용하고 있는 이메일입니다.");
            	}
            	else if(data === 'connectable') { 
					alert("이메일 주소 인증 메일이 전송되었습니다. 인증번호를 확인해주세요.");     
                } else {
					alert("유효하지 않은 이메일입니다.");
                }
            },
            error:function (data, textStatus) {
                console.log('error');
            }
        });   //ajax
        return false;
	});
	 
	// 인증번호 체크
	$('#emailCheckBtn').click(function () { 
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
                    $('#authEmail').val("true");
					alert("이메일 인증이 완료되었습니다.");
                } else {
					alert("인증번호가 다릅니다.");
                }
            },
            error:function (data, textStatus) {
                console.log('error');
            }
        });   //ajax
        return false;
	});

	
}

