
// 데이터 주고 받기
function findPw_check(){

    var memberLoginId=$('#memberLoginId').val()
    console.log(memberLoginId);

    $.ajax({
        url:"/camput/findPw/id", // 어디서
        type:"POST",    // 어떤 메소드 타입의 애가
        async:false,
        data: {"memberLoginId":memberLoginId} ,
        success:function(response){
            console.log(response);
            if(response === true){
                        $('#pwUpdate').show();
                        $('#checkBox').hide();
                    // });
                // });
            } else {
                $('.checkError').text("아이디가 없습니다.");
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
};

function passwordBtn(){

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
    memberUpdate.submit();
}