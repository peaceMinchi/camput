$(document).ready(function (){
    if(loginCheck!=null){
        console.log("login");
        $('.nav.notLogin').hide();
        $('.menu.Login').show();
    }else{
        console.log("notLogin");
        $('.nav.notLogin').show();
        $('.menu.Login').hide();
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