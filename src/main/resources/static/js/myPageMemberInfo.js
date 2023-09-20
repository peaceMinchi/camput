

async function checkMember(){
    var memberPw=$('.pwCheck').val();
    var result=await fetch("/camput/myPage/information/checkMember?memberPw="+memberPw).then(re=>re.text()).catch(err=>console.log("error"));
    if(result===""){
        $('.warring').show();
    }else{
        window.location.href='/member/update';
    }
}
function openWindow(){
    $('.modal').show();
}
function hideWarring(){
    console.log('hide')
    $('.warring').hide();
}
function hideWindow(){
    console.log('windowhide')
    $('.modal').hide();
}

function showCheckWindow(){
    $('.modal').show();
}
