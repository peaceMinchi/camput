
function idCheck(memberLoginId) {
  var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
  saveJoin.idbtncheck.value = "idCheck";

  if (memberLoginId == "" || memberLoginId == null) {
    idMsg.innerHTML = "아이디를 입력하세요";
    saveJoin.memberLoginId.focus();
    return;
  }
  if (!regExp.test(memberLoginId)) {
    idMsg.innerHTML = "영문자(소) 또는 숫자 6 ~ 20자";
    saveJoin.memberLoginId.focus();
    return;
  }

  $.ajax({
    // 요청방식: post, url(맴핑): "/member/join2", 데이터: 멤버로그인아이디
    type: "post",
    url: "/camput/member/join2",
    data: {
      memberLoginId: memberLoginId,
    },
    success: function (res) {
      if (res == "ok") {
        console.log("사용가능한 아이디");
        idMsg.innerHTML = "사용가능한 아이디 입니다.";
      } else {
        console.log("사용불가한 아이디");
        idMsg.innerHTML = "이미 사용중인 아이디 입니다.";
        saveJoin.idbtncheck.value = "idUncheck2";
        saveJoin.memberLoginId.focus();
        return;
      }
    },
    error: function (err) {
      console.log("에러발생", err);
      return;
    },
  });
}

// 아이디창에 입력을 하면 idUncheck로 변경.
function inputIdChk() {
  saveJoin.idbtncheck.value = "idUncheck";
}

// 회원가입 버튼 누르면 확인후 넘어감.
function submitButton() {

  if (saveJoin.memberLoginId.value == "" || saveJoin.memberLoginId.value == null) {
    idMsg.innerHTML = "아이디를 입력하세요";
    saveJoin.memberLoginId.focus();
    return;
  }

  if (saveJoin.idbtncheck.value == "idUncheck") {
    idMsg.innerHTML = "아이디 중복체크 해주세요";
    saveJoin.memberLoginId.focus();
    return;
  }

  if (saveJoin.idbtncheck.value == "idUncheck2") {
    idMsg.innerHTML = "이미 사용중인 아이디 입니다.";
    saveJoin.memberLoginId.focus();
    return;
  }

  if (saveJoin.memberPassword.value == "") {
    error_txt_pwd.innerHTML = "비밀번호를 입력하세요";
    saveJoin.memberPassword.focus();
    return;
  }

  if (saveJoin.memberPassword2.value == "") {
    error_txt_pwd2.innerHTML = "비밀번호 확인을 입력하세요";
    saveJoin.memberPassword2.focus();
    return;
  }

  //비밀번호 - 정규식 8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합
  var password = $("#memberPassword").val();
  var regExp = /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

  if (!regExp.test(password)) {
    error_txt_pwd.innerHTML = "8 ~ 16자 영문, 숫자, 특수문자 최소 하나씩 입력";
    saveJoin.memberPassword.focus();
    return false;
  }

  if (saveJoin.memberPassword.value != saveJoin.memberPassword2.value) {
    error_txt_pwd2.innerHTML = "비밀번호가 일치하지 않습니다";
    saveJoin.memberPassword2.focus();
    return;
  }

  if (saveJoin.memberName.value == "") {
    error_txt_name.innerHTML = "이름을 입력하세요";
    saveJoin.memberName.focus();
    return;
  }

  if (saveJoin.nickName.value == "") {
    error_txt_nickName.innerHTML = "별명을 입력하세요";
    saveJoin.nickName.focus();
    return;
  }

  if (saveJoin.memberEmail.value == "") {
    error_txt_memberEmail.innerHTML = "이메일을 입력하세요";
    saveJoin.memberEmail.focus();
    return;
  }
  if (saveJoin.phoneNumber.value == "") {
    error_txt_phone.innerHTML = "전화번호 입력하세요";
    saveJoin.phoneNumber.focus();
    return;
  }

  if (saveJoin.memberPostNum.value == "" || saveJoin.streetAddress.value == "" || saveJoin.extraAddress.value == "" || saveJoin.detailAddress.value == "") {
    error_txt_address.innerHTML = "정확한 주소를 입력 하세요";
    saveJoin.detailAddress.focus();
    return false;
  }
  if (saveJoin.gender.value == "") {
    error_txt_gender.innerHTML = "성별을 골라주세요";
    saveJoin.gender.focus();
    return false;
  }

  if (saveJoin.birthday.value == "") {
    //error_txt_birthday.innerHTML = "생년월일 8자리를 입력하세요";
    $("#error_txt_birthday").text("생년월일 8자리를 입력하세요");
    saveJoin.birthday.focus();
    return false;
  }

  //이름 - 한글만 2글자 ~ 10글자
  var name = $("input#memberName").val();
  var regExp1 = /^[가-힣]{2,10}$/;
  if (!regExp1.test(name)) {
    error_txt_name.innerHTML = "한글 2글자 ~ 10글자";
    saveJoin.memberName.focus();
    return false;
  }

  //닉네임 - 한글, 영어, 숫자 가능, 2글자 ~ 10글자.
  //var regExpNick = /^[가-힣a-zA-Z]{2,10}+$/;
  //var regExpNick = /^[가-힣]{2,10}$/;
  //var regExpNick = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}+$/;
  //var regExpNick = /^[0-9a-zA-Zㄱ-ㅎ가-힣 ]{2,10}*$/;
  var nickName = $("input#nickName").val();
  var regExpNick = /^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9_]{2,10}$/;
  if (!regExpNick.test(nickName)) {
    console.log(!regExpNick.test(nickName));
    error_txt_nickName.innerHTML = "2글자 ~ 10글자 (한글 영어 숫자 사용가능)";
    saveJoin.nickName.focus();
    return;
  }

  // 이메일 - 숫자, 영문 대문자, 소문자, 하이픈, 언더바 가능, @가 나오고, . 나오고 2글자 혹은 3글자.
  var emailVal = $("input#memberEmail").val();
  var regExp2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  if (!regExp2.test(emailVal)) {
    error_txt_memberEmail.innerHTML = "올바른 이메일 형식으로 작성하세요";
    saveJoin.memberEmail.focus();
    return false;
  }

  // 전화번호 - 핸드폰 형식의 숫자만
  var phoneNumber = $("input#phoneNumber").val();
  var regExpNum = /(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
  if (!regExpNum.test(phoneNumber)) {
    error_txt_phone.innerHTML = "핸드폰 번호를 입력해 주세요 11자.";
    saveJoin.phoneNumber.focus();
    return;
  }

  // 생년월일 8글자 숫자 ex.19940527.
  var birthday = $("input#birthday").val();
  var regExpBrith = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
  if (!regExpBrith.test(birthday)) {
    error_txt_birthday.innerHTML = "8글자 숫자 입력";
    saveJoin.birthday.focus();
    return;
  }
  saveJoin.submit();
}
