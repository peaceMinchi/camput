

$(document).ready(function (){
    console.log('start!')
    if(noPoint==null){
        $('.errorPage').show();
    }
})
async function booking(){
    var reservation=$('.finalReservation').val();

    console.log(reservation)
    var check =await fetch("/camput/reservationPage/init", {
        method:'POST',
        body:JSON.stringify({
            "startReservationDay": startReservationDay,
            "endReservationDay":endReservationDay,
            "campName":campName,
            "price":price
        })
    }).then(re=>re.text()).catch(err=>console.log('err'));
    if(check=='complete'){
        alert("예약이 완료되었습니다!"+"\n"+"홈페이지로 이동합니다.");
        window.location.href="/camput/home"
    }else{
        alert("예약이 꽉찾습니다!"+"\n"+"이전페이지로 이동합니다.");
        window.location.href="'/camput/detail/"+campName;
    }
}