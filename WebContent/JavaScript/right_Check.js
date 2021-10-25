/**
 권한 체크 js 파일 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function writing_Check(){
	
	$.ajax({
        type:'post',
        async:false,
        url:'WriteRightCheck.do',
        dataType:'text',
        data:{},
        success: function(data, textStatus) {
            if(data === 'accessible') {   
				location.href = "notice_Write.jsp";
            } else {
				alert("접근 권한이 없습니다.");
            }
        },
        error:function (data, textStatus) {
            console.log('error');
        }
    });   //ajax
}

function updating_Check(button) {
	var board_number = button.value;
	var boardName = $('#boardName').val();
	
	$.ajax({
        type:'post',
        async:false,
        url:'UpdateRightCheck.do',
        dataType:'text',
        data:{"board_number":board_number, "boardName":boardName},
        success: function(data, textStatus) {
            if(data === 'accessible') {   
				location.href = "LoadPost.do?board_number="+board_number+"&boardName="+boardName;
            } else {
				alert("접근 권한이 없습니다.");
            }
        },
        error:function (data, textStatus) {
            console.log('error');
        }
    });   //ajax
}

function writePost() {
	var form = $('#postForm');
	form.attr("action", "WritePost.do");
	form.submit();
}

function updatePost() {
	var form = $('#postForm');
	form.attr("action", "UpdatePost.do");
	form.submit();
}

function goListPage(button) {
	var nextPage = button.value;
	location.href = nextPage;
}

function cancelWriting(button) {
	var nextPage = button.value;
	
	if(confirm('작성을 취소하시겠습니까?') == true) {
		location.href = nextPage;
	}
}