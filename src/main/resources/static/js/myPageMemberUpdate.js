function submitButton1(){

  //비밀번호 - 정규식 8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합
  if (!memberUpdate.memberPassword.value == "") {
  var password = $("#memberPassword").val();
  var regExp = /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    if (!regExp.test(password)) {
        error_txt_pwd.innerHTML = "8 ~ 16자 영문, 숫자, 특수문자 최소 하나씩 입력";
        memberUpdate.memberPassword.focus();
    return;
    }
    if (memberUpdate.memberPassword2.value == "") {
      error_txt_pwd2.innerHTML = "비밀번호 확인을 입력하세요";
      memberUpdate.memberPassword2.focus();
      return;
    }
    if (memberUpdate.memberPassword.value != memberPassword2.value) {
      error_txt_pwd2.innerHTML = "비밀번호가 일치하지 않습니다";
      memberUpdate.memberPassword2.focus();
      return;
    }

    if (memberUpdate.memberPassword.value == memberPasswordHidden.value){
       error_txt_pwd.innerHTML = '기존의 비밀번호와 동일합니다';
       memberUpdate.memberPassword.focus();
       return;
    }
  }

  if (memberUpdate.memberName.value == "") {
    error_txt_name.innerHTML = "변경하실 이름을 입력하세요";
    memberUpdate.memberName.focus();
    return;
  }

  if (memberUpdate.nickName.value == "") {
    error_txt_nickName.innerHTML = "변경하실 별명을 입력하세요";
    memberUpdate.nickName.focus();
    return;
  }

  if (memberUpdate.memberEmail.value == "") {
    error_txt_memberEmail.innerHTML = "변경하실 이메일을 입력하세요";
    memberUpdate.memberEmail.focus();
    return;
  }
  if (memberUpdate.phoneNumber.value == "") {
    error_txt_phone.innerHTML = "변경하실 전화번호 입력하세요";
    memberUpdate.phoneNumber.focus();
    return;
  }

  if (memberUpdate.memberPostNum.value == "" || memberUpdate.streetAddress.value == "" || memberUpdate.extraAddress.value == "" || memberUpdate.detailAddress.value == "") {
    error_txt_address.innerHTML = "변경하실 정확한 주소를 입력 하세요";
    memberUpdate.detailAddress.focus();
    return false;
  }

  if (memberUpdate.gender.value == "") {
    error_txt_gender.innerHTML = "변경하실 성별을 골라주세요";
    memberUpdate.gender.focus();
    return false;
  }

  if (memberUpdate.birthday.value == "") {
    //error_txt_birthday.innerHTML = "생년월일 8자리를 입력하세요";
    $("#error_txt_birthday").text("변경하실 생년월일 8자리를 입력하세요");
    memberUpdate.birthday.focus();
    return false;
  }

  //이름 - 한글만 2글자 ~ 10글자
  var name = $("input#memberName").val();
  var regExp1 = /^[가-힣]{2,10}$/;
  if (!regExp1.test(name)) {
    error_txt_name.innerHTML = "한글 2글자 ~ 10글자";
    memberUpdate.memberName.focus();
    return false;
  }

  //닉네임 - 한글, 영어, 숫자 가능, 2글자 ~ 10글자.
  var nickName = $("input#nickName").val();
  var regExpNick = /^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9_]{2,10}$/;
  if (!regExpNick.test(nickName)) {
    console.log(!regExpNick.test(nickName));
    error_txt_nickName.innerHTML = "2글자 ~ 10글자 (한글 영어 숫자 사용가능)";
    memberUpdate.nickName.focus();
    return;
  }

  // 이메일 - 숫자, 영문 대문자, 소문자, 하이픈, 언더바 가능, @가 나오고, . 나오고 2글자 혹은 3글자.
  var emailVal = $("input#memberEmail").val();
  var regExp2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  if (!regExp2.test(emailVal)) {
    error_txt_memberEmail.innerHTML = "올바른 이메일 형식으로 작성하세요";
    memberUpdate.memberEmail.focus();
    return false;
  }

  // 전화번호 - 핸드폰 형식의 숫자만
  var phoneNumber = $("input#phoneNumber").val();
  var regExpNum = /(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
  if (!regExpNum.test(phoneNumber)) {
    error_txt_phone.innerHTML = "핸드폰 번호를 입력해 주세요 11자.";
    memberUpdate.phoneNumber.focus();
    return;
  }

  // 생년월일 8글자 숫자 ex.19940527.
  var birthday = $("input#birthday").val();
  var regExpBrith = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
  if (!regExpBrith.test(birthday)) {
    error_txt_birthday.innerHTML = "8글자 생년월일 입력";
    memberUpdate.birthday.focus();
    return;
  }
  memberUpdate.submit();
}