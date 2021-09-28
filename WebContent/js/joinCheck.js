/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


window.onload=function() {
	 
	$("#join").click(function () {
	       $("form").attr("action", "Join.do");
	});

	
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
	
	 $('#idCheckBtn').on("click", (e) => { 
		
		const id = $('#id').val();
        console.log('btn click');
        if(id.length === 0 || id === null) return alert("아이디를 입력하세요");

        $.ajax({
            type:'post',
            async:false,
            url:'http://localhost:8080/CorpCollector/IdCheck.do',
            dataType:'text',
            data:{id:id},
            success: function(data, textStatus) {
                if(data === 'usable') {
                    $('#message').text('사용할 수 있는 ID입니다.')      
                    $('#idCheckBtn').prop('disabled', true) // 사용할 수 있는 ID면 버튼을 비활성화 시킨다.
                } else {
                    $('#message').text('이미 사용 중인 아이디입니다.')
                }
            },
            error:function (data, textStatus) {
                console.log('error');
            }
        })    //ajax
        return false;
	})
	
}

