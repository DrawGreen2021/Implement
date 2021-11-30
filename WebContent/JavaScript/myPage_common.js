var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function selectAll(selectAll) {
	const checkboxes = document.querySelectorAll('input[type="checkbox"]');
 
	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked
	})
}

function deleteFeedback() {
	var myfeedback_select_length = $('input:checkbox[name="myfeedback_select"]:checked').length;
	
	if (myfeedback_select_length == 0) {
		alert("삭제할 고객 후기가 없습니다.");
	} else {
		$('#deleteForm').submit();
	}
}

function deleteFavCorp() {
	var favCorp_select_talent_length
		= $('input:checkbox[name="favCorp_select_talent"]:checked').length;
	var favCorp_select_green_length
		= $('input:checkbox[name="favCorp_select_green"]:checked').length;
	var favCorp_select_social_length
		= $('input:checkbox[name="favCorp_select_social"]:checked').length;
	var favCorp_select_family_length
		= $('input:checkbox[name="favCorp_select_family"]:checked').length;
	var favCorp_select_youth_length
		= $('input:checkbox[name="favCorp_select_youth"]:checked').length;
	
	if (favCorp_select_family_length == 0 &&
		favCorp_select_green_length == 0 &&
		favCorp_select_social_length == 0 &&
		favCorp_select_talent_length == 0 &&
		favCorp_select_youth_length == 0) {
		alert("삭제할 관심 기업이 없습니다.");
	} else {
		$('#deleteForm').submit();
	}
}

function resetList() {
	if (confirm("관심 기업 목록이 사라집니다. 정말로 초기화하시겠습니까?") == true) {
		$.ajax({
            type:'post',
            async:false,
            url:'ResetFavoriteCorpList.do',
            dataType:'text',
            success: function(data, textStatus) {
            	if(data === 'resetOk') {    
					alert("관심 기업 목록이 초기화되었습니다.");
                } else {
					alert("초기화 할 관심 기업 목록이 없습니다.");
                }
            },
            error:function (data, textStatus) {
                console.log('error');
            },
            complete: function() {
            	location.href = "FavoriteCorpView.do?page=1";
            }
        });   //ajax
	}
}

function resignMembership() {
	
	if (confirm("회원 탈퇴를 진행하면 회원님의 모든 정보가 삭제됩니다. 그래도 진행하시겠습니까?")==true) {
		$.ajax({
            type:'post',
            async:false,
            url:'ResignMembership.do',
            dataType:'text',
            success: function(data, textStatus) {
            	if(data === 'resignOk') {    
					alert("회원 탈퇴가 완료되었습니다.");
					location.href = "../index.jsp";
                } else {
					alert("회원 탈퇴에 문제가 생겼습니다. 관리자 메일로 연락주십시오.");
					location.href = "../service/service_Summary.jsp";
                }
            },
            error:function (data, textStatus) {
                console.log('error');
            }
        });   //ajax
	}
}