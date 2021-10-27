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