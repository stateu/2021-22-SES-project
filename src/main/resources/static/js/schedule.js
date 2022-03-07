// 달력 클릭 시 버튼
function getDateData(dateValue){
	let val = dateValue;
	console.log(dateValue);
	console.log(typeof(dateValue));
	
	$.ajax({
		url: "/ScheduleManagement/searchSchedule",
		type: "POST",
		data: {"selectedDate" : val},
		success: function(fragment){ 
			$("#sele").replaceWith(fragment);
		}
	});
}