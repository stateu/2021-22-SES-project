/**
 * 회원 관련 폼 제어하는 스크립트
 */
 
 function checkBlank() {
 	// 폼 객체들 가져오기
 	let userId = document.getElementById('userId');
 	let userPw = document.getElementById('userPw');
 	let userPwCheck = document.getElementById('userPwCheck');
 	let userName = document.getElementById('name');
 	let userPhone = document.getElementById('phoneNumber');
 	let birthDay = document.getElementById('birth');
 	
 	// 객체들에 들어있는 문자열의 길이 확인하기
 	if(userId.value.length == 0) { // id가 빈칸
 		alert('아이디는 필수입니다');
 		userId.focus();
 		return false;
 	} else if(userPw.value.length == 0) {	// 비밀번호가 빈칸
 		alert('비밀번호는 필수입니다');
 		userPw.focus();
 		return false;
 	} else if(userPwCheck.value.length == 0) {	// 비밀번호 확인이 빈칸
 		alert('비밀번호를 다시 한 번 입력하세요');
 		userPwCheck.focus();
 		return false;
 	} else if(userName.value.length == 0){
 		userName.focus();
 		alert('이름을 입력해주세요');
 		return false;
 	} else if(userPhone.value.length == 0){
 		userPhone.focus();
 		alert('전화번호를 입력해주세요');
 		return false;
 	} else if(birthDay.value.length == 0){
 		birthDay.focus();
 		alert('생일을 입력해주세요');
 		return false;
 	}
 	
 	// 생일 확인
 	let today = new Date();
 	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2);
	var dateString = year + '-' + month  + '-' + day;
	
	dateString = dateString.replaceAll("-", "");
	
	var birthString = birthDay.value;
	birthString = birthString.replaceAll("-", "");
	
	if(birthString > dateString){
		alert('생일을 확인해주세요');
		return false;
	}
 	
 	// 비밀번호와 비밀번호 확인에 들어있는 값이 같은가?
 	if(userPw.value !== userPwCheck.value) {	// 비밀번호와 그 확인 값이 서로 다를때
 		alert('비밀번호가 일치하지 않습니다');
 		return false;
 	}
 	
 	alert('회원가입을 완료했습니다');
 	
 	return true;
 }
 
 function checkBlankFind(){
 	userId = document.getElementById('userId');
 	phone = document.getElementById('phoneNumber');
 	birth = document.getElementById('birth');
 	userPw = document.getElementById('userPw');
 	userPwCheck = document.getElementById('userPwCheck');
 	
 	if(userId.value.length == 0){
 		alert('아이디를 입력해주세요');
 		userId.focus();
 		return false;
 	} 
 	else if(phone.value.length == 0){
 		alert('전화번호를 입력해주세요');
 		phone.focus();
 		return false;
 	}
 	else if(birth.value.length == 0){
 		alert('생일을 입력해주세요');
 		birth.focus();
 		return false;
 	 } 
 	 else if(userPw.value.length == 0) {	// 비밀번호가 빈칸
 		alert('비밀번호를 입력해주세요');
 		userPw.focus();
 		return false;
 	} 
 	else if(userPwCheck.value.length == 0) {	// 비밀번호 확인이 빈칸
 		alert('비밀번호를 다시 한 번 입력하세요');
 		userPwCheck.focus();
 		return false;
 	}
 	
 	if(userPw.value !== userPwCheck.value) {	// 비밀번호와 그 확인 값이 서로 다를때
		alert('비밀번호가 일치하지 않습니다');
		return false;
 	}
 	
 	alert('비밀번호를 변경하였습니다');
 	return true;
 }