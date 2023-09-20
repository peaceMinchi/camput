
window.scrollTo({ left: 0, top: 0, behavior: "smooth" });
// 메뉴
$(function(){
    $(".menuBtn>li").mouseover(function(){
        $(this).children(".miniMenu").stop().slideDown();
    });
    $(".menuBtn>li").mouseout(function(){
        $(this).children(".miniMenu").stop().slideUp();
    });
}) // 메뉴

// 화면 슬라이딩 (예약,리뷰)
$(function(){
    // 각각 목록 내려오기
    $(".slideBtn>a").click(function(){
        $(this).children(".openBooking").stop().slideDown();
    });
    $(".slideBtn>a").click(function(){
        $(this).children(".openBooking").stop().slideUp();
    });
}); // 화면슬라이딩(예약,리뷰)


// 별점
const drawStar = (target) => {
    document.querySelector(`.star span`).style.width = `${target.value * 20}%`;
}// 별점


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
    // x 버튼 클릭하면 삭제됨.
    $('.modal_close').click(function() {
        $('#modal').hide();
    });
});

// 리뷰 쓰기 전송 폼
$("#re_postForm").submit(function (event) {
    event.preventDefault();
    var comment = $("#comment").val();
    var stars = $("#stars").val();
    var campName = $("#campName").val();
    console.log(comment,stars,campName);

    var commentDto = {
        comment: comment,
        stars: stars,
        campName: campName,
    };

    $.ajax({
        type: "POST",
        url: "/camput/reviews/new",
        // contentType: "application/json",
        async:false,
        data: $(".re_postForm").serialize(),
        success: function(data) {
            $('.member_Name').val(data.memberName);
            $('.re_star').val(data.stars);
            $('.re_txt').val(data.comment);
            console.log("성공했습니다.");
        },
        error: function(error) {
            console.log(error);
        }
    });
});
