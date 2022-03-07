// 일정 등록 시 유효한지 확인
function validCheck(){
	let title = document.getElementById('title');
	let startDate = document.getElementById('startDate');
	let endDate = document.getElementById('endDate');
	
	// 필수요소 빈칸 확인
	if(title.value.length == 0){
		alert('제목을 입력해주세요');
		title.focus();
		return false;
	} 
	else if(startDate.value.length == 0){
		alert('시작 날짜를 입력해주세요');
		startDate.focus();
		return false;
	} 
	else if(endDate.value.length == 0){
		alert('종료 날짜를 입력해주세요');
		endDate.focus();
		return false;
	}
	
	// 일정 등록 시 날짜 비교
	if(startDate.value.replaceAll("-", "") > endDate.value.replaceAll("-", "")){
		alert('시작 날짜와 종료 날짜를 확인해주세요');
		return false;
	}
	
	alert('일정등록을 완료하였습니다');
	return true;
}