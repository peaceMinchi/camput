

// 모달 창 나오게 만들기 var.4
$( document ).ready(function() {
    $('#open_modal').click(function() {
        $('#modal').show();
    });
    // 모달 창 외의 다른 곳 클릭하면 다시 없어짐.
    $(window).on('click', function() {
        if (event.target == $('#modal').get(0)) {
            $('#modal').hide();
            }
    });
});

// /* 아이디 찾기 */ 
// //아이디 & 스토어 값 저장하기 위한 변수
// 	// 아이디 값 받고 출력하는 ajax
function findId_click(){
    var nickName=$('#nickName').val()
    console.log(nickName);
    var memberPhone=$('#memberPhone').val()
    console.log(memberPhone);

    $.ajax({
        url:"/camput/findId/modal",
        type:"POST",
        async:false,
        data: $(".findId").serialize(), //  보내는 값이 두개 이상일 때 직렬화 처리해서 한번에 처리
        success:function(data){
            if(data.memberIdCheck === "아이디가 없습니다."){
                $('#modal_body').text(data.memberIdCheck);
                $('#nickName').val('');
                $('#memberPhone').val('');
            } else {
                $('#modal_body').text(data.memberLoginId);
                $('#nickName').val('');
                $('#memberPhone').val('');
                
            }
        },
            error:function(){
                alert("에러입니다");
            }
    });
};
