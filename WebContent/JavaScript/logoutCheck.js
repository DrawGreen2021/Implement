/**
 * a태그 클릭 시 post방식으로 값 전달
 */

function logoutDo() {
	var form = document.passBeforeUrl;

    // form 태그의 하위 태그 값 매개 변수로 대입
	form.beforeUrl.value = document.location.href;

    // input태그의 값들을 전송하는 주소
	form.action = "Logout.do";

    // 전송 방식 : post
	form.method = "post";
	form.submit();
}