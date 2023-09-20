async function cancel(){

    var bookCancel=await fetch("/camput/rservation/cancel/deleteReaervation",{
        method:'POST',
        body:JSON.stringify({
            "bookedDay":bookedDay,
            "startReservationDay": startReservationDay,
            "endReservationDay":endReservationDay,
            "campName":campName,
            "price":price
        })
    }).then(re=>re.text()).catch(err=>console.log('err'));
    console.log(bookCancel);
    if(bookCancel!==null){
        $('.successCancel').show();
        $('.goToHome').setAttribute("action","/camput/mypage/bookInfo")
    }
}