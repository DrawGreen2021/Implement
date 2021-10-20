/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


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
                $(button).text('★');
            } else {
            	$(button).text('☆');
            }
        },
        error:function (data, textStatus) {
            console.log('error');
        }
    });   //ajax
}

// 통합 검색 페이지에서 관심 기업을 등록할 경우
function addFavoriteCorp_main(button) {
	var serial_number = button.value;
	var corpType = $('#corpType').val();
	var corpName = $('#corpName'+serial_number).text();
	
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
                $(button).text('★');
            } else {
            	$(button).text('☆');
            }
        },
        error:function (data, textStatus) {
            console.log('error');
        }
    });   //ajax
}