$(document).ready(function (){
    if(loginCheck!=null){
        console.log("login");
        $('.nav.notLogin').hide();
        $('.nav.Login').show();
    }else{
        console.log("notLogin");
        $('.nav.notLogin').show();
        $('.nav.Login').hide();
    }
});
$(function(){
    $(".menuBtn>li").mouseover(function(){
        $(this).children(".miniMenu").stop().slideDown();
    });
    $(".menuBtn>li").mouseout(function(){
        $(this).children(".miniMenu").stop().slideUp();
    });
}) // last