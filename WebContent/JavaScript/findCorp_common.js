/**
 * 
 */

// 기업 찾기 각 페이지마다 적용되는 리셋버튼 함수
function resetKeyword() {
	var form = document.findCorp;
	var noKeyword = "";

	form.keyword.value = noKeyword;
	form.action = "FindCorp.do";
	form.method = "get";
	form.submit();
}