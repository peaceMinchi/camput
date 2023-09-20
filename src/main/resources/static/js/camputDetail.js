
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
    $("#stars").click(function (event) {
        document.querySelector(`.star span`).style.width = `${event.target.value * 20}%`;
    })
}); // 화면슬라이딩(예약,리뷰)

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

$(function(){
// 리뷰 쓰기 전송 폼
$(".sub_btn").click(function () {
    console.log("submit")
    $.ajax({
        type: "POST",
        url: "/camput/reviews/insert",
        // contentType: "application/json",
        data: $("#re_postForm").serialize(),
        success: function(data) {
            console.log("성공했습니다.");
            location.reload();
        },
        error: function(error) {
            console.log(error);
        }
    });
});
})

var checkStartYear="";
var checkStartMonth="";
var checkStartDay="";
var checkEndYear="";
var checkEndMonth="";
var checkEndDay="";

var startColor="";
var endColor="";

var startDayCheck=true;
var endDayCheck=true;

$(document).ready(function(){
    initCalendar1();
    initCalendar2();
});

function initCalendar1() {
    // 한국 날짜 정보 가져오기
    var date = new Date();
    var utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000);
    var kstGap = 9 * 60 * 60 * 1000;
    var today = new Date(utc + kstGap);

    var thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    const fixedCurrentMonth=thisMonth.getMonth()+1;
    const fixedCurrentYear=thisMonth.getFullYear();;
    // 달력에서 표기하는 연,월,일
    var currentYear = thisMonth.getFullYear();
    var currentMonth = thisMonth.getMonth();
    var currentDate = thisMonth.getDate();

    renderCalender(thisMonth);
    function renderCalender(thisMonth) {

        currentYear = thisMonth.getFullYear();
        currentMonth = thisMonth.getMonth();
        currentDate = thisMonth.getDate();
        // 이전 달의 마지막 날 날짜와 요일 구하기
        var startDay = new Date(currentYear, currentMonth, 0);
        var prevDate = startDay.getDate();
        var prevDay = startDay.getDay();
        // 이번 달의 마지막날 날짜와 요일 구하기
        var endDay = new Date(currentYear, currentMonth + 1, 0);
        var nextDate = endDay.getDate();
        var nextDay = endDay.getDay();

        // 현재 월 표기
        $('.year-month').text(currentYear + '.' + (currentMonth + 1));
        calendar = document.querySelector('.one')
        calendar.innerHTML = '';
        // 지난달
       for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
            var prevDays = document.createElement('div');
            prevDays.className = "day prev disable";
            prevDays.setAttribute('id', i);
            prevDays.innerHTML = i;
            calendar.appendChild(prevDays);
        }
        if (fixedCurrentMonth === currentMonth + 1 && fixedCurrentYear === currentYear) {
            $("#prev1").hide();
        } else {
            $("#prev1").show();
        }
        // 이번달
        for (var idx = 1; idx <= nextDate; idx++) {
            var currentDay = document.createElement('div');
            currentDay.className = 'day current';
            currentDay.setAttribute('id', "start" + (currentMonth + 1) + "month" + idx);
            currentDay.innerHTML = idx;
            var bookedDate = idx >= 10 ? idx : '0' + idx;
            var bookedMonth = (currentMonth + 1) >= 10 ? (currentMonth + 1) : '0' + (currentMonth + 1);
            var checkFullReservation = currentYear + '-' + bookedMonth + '-' + bookedDate;
            var block=false;

            if((today.getDate() > idx && fixedCurrentMonth === (currentMonth + 1))){
                currentDay.classList.add("disable");
            }

            if (fullBookedDays !== null) {
                fullBookedDays.forEach((check) => {
                    if (checkFullReservation === String(check) && (today.getDate() < idx || fixedCurrentMonth < (currentMonth + 1))) {
                        console.log('start');
                        block=true;
                    }
                }) };
                if (!block&&(today.getDate() < idx || fixedCurrentMonth < (currentMonth + 1))) {
                    currentDay.setAttribute('type', 'button');
                    currentDay.classList.add('startDayButton');
                    currentDay.dataset.idx = idx;
                }
                calendar.appendChild(currentDay);
                }

            $(".startDayButton").click(function () {
                var idx = $(this).data("idx");
                var warring = $('.startWarring')

                if (checkStartYear === "" && endDayCheck && startDayCheck && checkEndYear === "") {
                    startReservationDay(this);
                    startDayCheck = false;
                    console.log("첫입력");
                } else if (endDayCheck && !startDayCheck) {
                    var start = document.querySelector(".startReservationDay");
                    start.innerHTML = clickedYMD;
                    start.setAttribute('value',clickedYMD);
                    var deldeteColor = document.getElementById(startColor);
                    deldeteColor.setAttribute('style', "background-color: white")
                    startReservationDay(this);
                    startDayCheck = false;
                    console.log("엔드안입력 시작일 수정");
                } else if (!endDayCheck && !startDayCheck && checkEndDay > idx
                ) {
                    console.log("엔드입력 시작일 수정 이번달");
                    var start = document.querySelector(".startReservationDay");
                    start.innerHTML = clickedYMD;
                    start.setAttribute('value',clickedYMD);
                    var deldeteColor = document.getElementById(startColor);
                    startReservationDay(this);
                    if (startColor != "" && deldeteColor != null) {
                        deldeteColor.setAttribute('style', "background-color: white")
                        startReservationDay(this);
                        startDayCheck = false;
                    }
                } else if (checkEndYear != "" && (checkEndYear > currentYear || checkEndMonth > (currentMonth + 1))) {
                    console.log("다른 달 마지막날 입력하고 시작날 바꾸기");
                    var start = document.querySelector(".startReservationDay");
                    start.innerHTML = clickedYMD;
                    start.setAttribute('value',clickedYMD);

                    var deldeteColor = document.getElementById(startColor);
                    if (startColor != "" && deldeteColor != null) {
                        deldeteColor.setAttribute('style', "background-color: white")
                        startReservationDay(this);
                        startDayCheck = false;
                    } else if (!endDayCheck && !startDayCheck) {
                        var start = document.querySelector(".startReservationDay");
                        start.innerHTML = clickedYMD;
                        start.setAttribute('value',clickedYMD);
                        var deldeteColor = document.getElementById(startColor);
                        if (startColor != "" && deldeteColor != null) {
                            deldeteColor.setAttribute('style', "background-color: white")
                            startReservationDay(this);
                        }
                    } else {
                        console.log("예외");
                        warring.text = "마지막날 보다 늦은 날자로 입력하세요"
                        warring.show();
                    }
                }
            })
            function startReservationDay(_this) {
                clickedYear = today.getFullYear();
                clickedMonth = (1 + currentMonth);
                clickedDate = $(_this).data("idx");
                checkStartYear = clickedYear;
                checkStartMonth = clickedMonth;
                checkStartDay = clickedDate;
                clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
                clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
                clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;
                var selected=document.querySelector(".startReservationDay.selected");
                var start=document.querySelector(".startReservationDay");
                selected.innerHTML=clickedYMD;
                start.setAttribute('value',clickedYMD);
                var dayId = "start" + (currentMonth + 1) + "month" + $(_this).data("idx")
                startColor = dayId;
                var clickEndDay = document.getElementById(dayId);
                clickEndDay.setAttribute('style', "background-color: white")
                return startDay;
            };
            // 다음달
            for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
                var nextDays = document.createElement('div');
                nextDays.className = "day next disable";
                nextDays.setAttribute("id", i);
                nextDays.innerHTML = i;
                calendar.appendChild(nextDays);
            };
            // 오늘 날짜 표기
             if (today.getMonth() == currentMonth) {
                todayDate = today.getDate();
                var currentMonthDate = document.querySelectorAll('.dates .current');
                 currentMonthDate[todayDate - 1].classList.add('disable');
                currentMonthDate[todayDate - 1].classList.add('today');
             };
            }
            $('.go-prev').on('click', function() {
                thisMonth = new Date(currentYear, currentMonth - 1, 1);
                renderCalender(thisMonth);
            });
            //다음달로 이동
            $('.go-next').on('click', function() {
                thisMonth = new Date(currentYear, currentMonth + 1, 1);
                renderCalender(thisMonth);
            });
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function initCalendar2() {
    // 날짜 정보 가져오기
    var date = new Date();
    var utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000);
    var kstGap = 9 * 60 * 60 * 1000;
    var today = new Date(utc + kstGap);

    var thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    const fixedCurrentMonth=thisMonth.getMonth()+1;
    const fixedCurrentYear=thisMonth.getFullYear();;

    var currentYear = thisMonth.getFullYear();
    var currentMonth = thisMonth.getMonth();
    var currentDate = thisMonth.getDate();
    renderCalender(thisMonth);

    function renderCalender(thisMonth) {

        currentYear = thisMonth.getFullYear();
        currentMonth = thisMonth.getMonth();
        currentDate = thisMonth.getDate();

        var startDay = new Date(currentYear, currentMonth, 0);
        var prevDate = startDay.getDate();
        var prevDay = startDay.getDay();

        var endDay = new Date(currentYear, currentMonth + 1, 0);
        var nextDate = endDay.getDate();
        var nextDay = endDay.getDay();
        // 현재 월 표기

        $('.year-month-end').text(currentYear + '.' + (currentMonth + 1));
        calendar = document.querySelector('.two')

        calendar.innerHTML = '';

        // 지난달
        for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
            var prevDays = document.createElement('div');
            prevDays.className = "day prev disable";
            prevDays.setAttribute('id', i);
            prevDays.innerHTML = i;
            calendar.appendChild(prevDays);
        }

        if(fixedCurrentMonth===currentMonth+1&&fixedCurrentYear===currentYear){
            $("#prev2").hide();
        }else{
            $("#prev2").show();
        }

        for (var idx = 1; idx <= nextDate; idx++) {
            var currentDay= document.createElement('div');
            currentDay.className='day current';
            currentDay.setAttribute('id',"end"+(currentMonth + 1)+"month"+idx);
            currentDay.innerHTML=idx;
            var currentDayButton =document.createElement('div');
            var bookedDate = idx >= 10 ? idx : '0' + idx;
            var bookedMonth = (currentMonth + 1) >= 10 ? (currentMonth + 1) : '0' + (currentMonth + 1);
            var checkFullReservation = currentYear + '-' + bookedMonth + '-' + bookedDate;
            var block=false;

            if((today.getDate() > idx && fixedCurrentMonth === (currentMonth + 1))){
                currentDay.classList.add("disable");
            }

            if (fullBookedDays !== null) {
                fullBookedDays.forEach((check) => {
                    if (checkFullReservation === String(check) && (today.getDate() < idx || fixedCurrentMonth < (currentMonth + 1))) {
                        console.log('start');
                        block=true;
                    }
                }) };
            if (!block&&(today.getDate() < idx || fixedCurrentMonth < (currentMonth + 1))) {
                currentDay.setAttribute('type', 'button');
                currentDay.classList.add('endDayButton');
                currentDay.dataset.idx = idx;
            }
            calendar.appendChild(currentDay);
        }

        $(".endDayButton").click(function(){
            var warring= $('.endWarring')
            var idx= $(this).data("idx");

            if(checkStartYear!=""&&checkEndYear===""&&idx>checkStartDay){
                console.log("end first")
                warring.hide();
                endReservationDay(this);
                endDayCheck=false;
            }else if(checkStartYear!=""&&
                (checkStartYear<today.getFullYear|| checkStartMonth<(currentMonth+1)||checkStartDay<idx)
            ){
                var end=document.querySelector(".endReservationDay");
                end.innerHTML=clickedYMD;
                end.setAttribute('value',clickedYMD)
                var deldeteColor= document.getElementById(endColor);
                if(endColor!=""&&deldeteColor!=null){
                    deldeteColor.setAttribute('style',"background-color: white")}
                endReservationDay(this);
                startDayCheck=false;
            }else if(checkStartYear!=""&&(checkStartYear<currentYear || checkStartMonth<(currentMonth+1))){
                var end=document.querySelector(".endReservationDay");
                end.innerHTML=clickedYMD;
                end.setAttribute('value',clickedYMD)

                var deldeteColor= document.getElementById(endColor);
                if(endColor!=""&&deldeteColor!=null){
                    deldeteColor.setAttribute('style',"background-color: white")}
                endReservationDay(this);
                startDayCheck=false;
            }
            else{
                warring.innerHTML ="올바른 시작일이 필요합니다"
                warring.show();
            }
        })
        function endReservationDay(_this){
            clickedYear = today.getFullYear();
            clickedMonth = (1+currentMonth);
            clickedDate = $(_this).data("idx");

            checkEndYear = clickedYear;
            checkEndMonth = clickedMonth;
            checkEndDay = clickedDate;
            clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
            clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
            clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;
            var selected=document.querySelector(".endReservationDay.selected");
            var end=document.querySelector(".endReservationDay");
            selected.innerHTML=clickedYMD;
            end.setAttribute('value',clickedYMD);
            var dayId="end"+(currentMonth + 1)+"month"+$(_this).data("idx")
            endColor=dayId;
            document.getElementById(dayId).setAttribute('style',"background-color:white")
            return startDay;
        };
        // 다음달
        for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
            var nextDays = document.createElement('div');
            nextDays.className = "day next disable";
            nextDays.setAttribute("id", i);
            nextDays.innerHTML = i;
            calendar.appendChild(nextDays);
        };
        // 오늘 날짜 표기
        if (today.getMonth() == currentMonth) {
            todayDate = today.getDate();
            var currentMonthDate = document.querySelectorAll('.dates .current');
            currentMonthDate[todayDate - 1].classList.add('disable');
            currentMonthDate[todayDate - 1].classList.add('today');
        };
    }
    // 이전달로 이동
    $('.go-prevend').on('click', function() {
        thisMonth = new Date(currentYear, currentMonth - 1, 1);
        renderCalender(thisMonth);
    });
    // 다음달로 이동
    $('.go-nextend').on('click', function() {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth);
    });
}
function reset(){
    console.log("reset")
    var start = document.querySelector(".startReservationDay");
    var end=document.querySelector(".endReservationDay");
    var startText = document.querySelector(".startReservationDay.selected");
    var endText=document.querySelector(".endReservationDay.selected");
    if(start!==""&&end!==""){
        startText.innerHTML = "";
        start.setAttribute('value',"");
        endText.innerHTML="";
        end.setAttribute('value',"");
    }
    var startDldete = document.getElementById(startColor);
    var endDeldete = document.getElementById(endColor);
    if(startDldete!=null&&endDeldete!=null){
        startDldete.setAttribute('style',"background-color: white")
        endDeldete.setAttribute('style',"background-color: white")
    }
}
$(document).ready(function (){
    if(loginCheck===null){
        $('.likeAction').hide()
    }else{
        $('.likeAction').show()
    }
})
async function like(){
    var likeCheck=document.querySelector('.likeButton>i')
    var totalLikes =document.querySelector('.totalLike')
    var result=await fetch("/camput/detail/like",{
        method:'POST',
        body: JSON.stringify({
            "campName":campName
        })
    }).then(re=>re.json()).catch(err=>console.log('err'));

   if(result.like==0){
       likeCheck.setAttribute("style","color: white");
       totalLikes.innerHTML = result.totalLike;
    }else if(result.like==1){
       likeCheck.setAttribute("style","color: red");
       totalLikes.innerHTML =result.totalLike;
    }
}
$(document).ready(function (){
    console.log(likeCheck);
    var check = document.querySelector('.likeButton>i');
    if(likeCheck==1){
        check.setAttribute("style","color: red");
    }
})

function nullCheck(){
    if( $('.price').val()===""|| $('.startReservationDay').val()===""||$('.endReservationDay').val()===""){
        console.log( $('.price').val())
        console.log( $('.startReservationDay').val())
        console.log( $('.endReservationDay').val())
        $('.warring').show();
        return;
    }
    main_title.submit();
}
function checkOnlyOne(_this){
var checkBox=document.getElementsByName('price');
var checkBox1=document.querySelector('.price50000');
var checkBox2=document.querySelector('.price100000');

if(((!checkBox2.checked&&!checkBox1.checked))&&_this.value===checkBox1.value){
    checkBox.forEach((a)=>{
        a.checked = false;
    })
} else if(((!checkBox2.checked&&!checkBox1.checked))&&_this.value===checkBox2.value){
        checkBox.forEach((a)=>{
        a.checked = false;
    })
}else{
    checkBox.forEach((a)=>{
        a.checked = false;
    });
    _this.checked =true;
}
}

