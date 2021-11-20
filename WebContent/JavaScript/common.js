
/* ----- 기업 유형 지정 ----- */
var corpType = document.getElementById("corpType");

function setType(button) {
	corpType.value = button.value;
	document.getElementById("findCorpForm").submit();
}

