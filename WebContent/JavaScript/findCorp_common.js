/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

window.onload = function() {
	if (document.title == "CorpCollector : 상세 기업 정보") {
		var corpType = $('#corpType').val();
		
		$('#' + corpType + '_anchor').css({
			"color" : "#e1bf27",
			"font-weight" : "bold"
		});
	}
}

// 기업 찾기 각 페이지마다 적용되는 리셋버튼 함수
function resetKeyword() {
	var form = document.findCorp;
	var noKeyword = "";

	form.keyword.value = noKeyword;
	form.action = "FindCorp.do";
	form.method = "get";
	form.submit();
}

// 관심 기업 등록
function addFavoriteCorp(button) {
	
	var serial_number = button.value;
	var corpType = $('#corpType').val();
	var corpName = $('#corpName'+serial_number).text();
	
	addFavAjax(serial_number, corpType, corpName, button, '★', '☆');
}

// 통합 검색 페이지에서 관심 기업을 등록할 경우
function addFavoriteCorp_main(button) {
	var serial_number = button.value;
	var corpType = $('#corpType').val();
	var corpName = $('#corpName'+serial_number).text();
	
	addFavAjaxInMain(serial_number, corpName, button, '★', '☆');
}

// 상세 기업 페이지에서 관심기업을 등록한 경우
function addFavoriteCorp_detail(button) {
	var serial_number = $('#serial_num').val();
	var corpName = $('#corpName').val();
	var corpType = $('#corpType').val();
	var btn_text_add = "관심기업 삭제"; // 관심 기업을 등록한 이후 버튼의 텍스트
	var btn_text_rm = "관심기업 등록" // 관심 기업을 삭제한 이후 버튼의 텍스트
	
	if (corpType == 'interCorp') {
		addFavAjaxInMain(serial_number, corpName, button, btn_text_add, btn_text_rm);
	}
	else {
		addFavAjax(serial_number, corpType, corpName, button, btn_text_add, btn_text_rm);
	}

}

function addFavAjax(serial_number, corpType, corpName, button, bnt_text_add, btn_text_rm) {
	$.ajax({
        type:'post',
        async:false,
        url:'AddFavoriteCorp.do',
        dataType:'text',
        data:{"serial_number":serial_number, "corpType":corpType, "corpName":corpName},
        success: function(data, textStatus) {
            if(data === 'not-login') {
                alert("로그인해주세요.");     
            } else if(data === 'addFavoriteCorp'){
                $(button).text(bnt_text_add);
            } else {
            	$(button).text(btn_text_rm);
            }
        },
        error:function (data, textStatus) {
            console.log('error');
        }
    });   //ajax
}

function addFavAjaxInMain(serial_number, corpName, button, bnt_text_add, btn_text_rm) {
	var location = $('#location'+serial_number).val();
	var sector = $('#sector'+serial_number).val();
	var tableName = $('#tableName'+serial_number).val();
	
	$.ajax({
        type:'post',
        async:false,
        url:'AddFavoriteCorp_InMain.do',
        dataType:'text',
        data:{"corpName":corpName, "location":location, "sector":sector, "tableName":tableName},
        success: function(data, textStatus) {
            if(data === 'not-login') {
                alert("로그인해주세요.");     
            } else if(data === 'addFavoriteCorp'){
                $(button).text(bnt_text_add);
            } else {
            	$(button).text(btn_text_rm);
            }
        },
        error:function (data, textStatus) {
            console.log('error');
        }
    });   //ajax
}

/*function backSpace() {
	location.href = document.referrer;
}*/