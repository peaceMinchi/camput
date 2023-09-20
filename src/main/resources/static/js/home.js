let menuEvent =$(".campSideInfo");

menuEvent.hover(function(){
    var content= menuEvent.children("ul");
    content.show();
},function(){
    var content= menuEvent.children("ul");
    content.hide();
})

$(document).ready(function(){
    var nowNum= 0;
    var slide=$('.campSideInfo')
    var maxLength = slide.length;
    slide.eq(nowNum).fadeIn();

    $('.next').bind('click',function(){
        rollingFn('right');
    });
    $('.pre').bind('click',function(){
        rollingFn('left');
    });

    function rollingFn(direction){
        if(direction=="right"){
            nowNum = nowNum+1;
            if(nowNum>maxLength-1){
                nowNum = 0;
            }
        }else{
            nowNum =nowNum-1;
            if(nowNum<0){
                nowNum = maxLength-1;
            }
        }
        slide.hide().eq(nowNum).fadeIn();
    }
});
//Top5 vieweffect//
function checkVisible1( element, check = 'above' ) {
    let viewportHeight = $(window).height();
    let scrolltop = $(window).scrollTop();
    let y = $(element).offset().top;
    let elementHeight = $(element).height();

    document.querySelector('.toparea').classList.remove('animate__animated','animate__slideInUp');
    // document.querySelector('.campIcon').classList.remove('animate__animated','animate__slideInUp');
    // document.querySelector('.campTopSeven').classList.remove('animate__animated','animate__slideInUp');

    if (check == "visible"){
        return ((y < (viewportHeight + scrolltop)) && (y > (scrolltop - elementHeight)));
    }
    if (check == "above"){
        return ((y < (viewportHeight + scrolltop)));
    }

}
let func1 = function () {
    if (checkVisible1('.campTopSevenContent') ) {
        document.querySelector('.campTopSevenContent').classList.add('animate__animated','animate__slideInUp');
        document.querySelector('.campTopSevenContent').style.setProperty('--animate-duration', '3s');
    }
}
// window.addEventListener ('scroll',function(){
//     $(".campTopSevenContent").fadeIn(1000).animate({top:"100"},500);
// });
window.addEventListener('scroll', func1);
function checkVisible2( element, check = 'above' ) {
    let viewportHeight = $(window).height();
    let scrolltop = $(window).scrollTop();
    let y = $(element).offset().top;
    let elementHeight = $(element).height();

    if (check == "visible") {
        if (check == "above")
            return ((y < (viewportHeight + scrolltop)));
    }
    return ((y < (viewportHeight + scrolltop)) && (y > (scrolltop - elementHeight)));
}

let func2 = function () {
    if (checkVisible2('.map') ) {
        document.querySelector('header').classList.add('animate__animated','animate__fadeOutUp');
        document.querySelector('header').classList.remove('.fixed');
    }else{
        document.querySelector('header').classList.remove('animate__animated','animate__fadeOutUp');
        document.querySelector('header').classList.add('animate__animated','animate__fadeInDown');
        document.querySelector('header').classList.add('.fixed');
    }
}

window.addEventListener('scroll', func2);

let check = true;
//map과 category분리
function filter(){
    if(check){
        $('.filterBox').show();
        document.querySelector('.mapImage').classList.add('zIndex');
        check=false;
    }else{
        $('.filterBox').hide();
        document.querySelector('.mapImage').classList.remove('zIndex');
        check=true;
    }
}

//mpa//
var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng( 37.604394620976244, 127.05306776321997),
        level: 10
    };
var map =new kakao.maps.Map(mapContainer,mapOption);
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
var imageSize = new kakao.maps.Size(24, 35);
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

var markers=[];
var clust = new kakao.maps.MarkerClusterer({
    map:map,
    averageCenter:true,
    minLevel:6
});
///현재 위치로 이동//
function currentLocation(){
    if( navigator.geolocation){
        navigator.geolocation.getCurrentPosition(function(position){
            const presentX= position.coords.latitude,
                presentY = position.coords.longitude;
            var newCenter = new kakao.maps.LatLng(presentX,presentY);
            map.panTo(newCenter);
        });
    }else{
        var newCenter = new kakao.maps.LatLng(37.4812845080678,126.952713197762);
        currentLatLon['presentX'] = 33.450701
        currentLatLon['presentY'] = 126.570667
        map.panTo(newCenter);
    }
    return true;
}
currentLocation();
///marker생성///
campMap.forEach(function(m){
    marker=new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(Number(m.mapX),String(m.mapY)),
        title: m.name,
        image : markerImage
    });
    markers.push(marker);
    makeOverLay(m,marker);
});
clust.addMarkers(markers);
//마커 오버레이 생성///
function makeOverLay(m,marker) {
    var overlay = new kakao.maps.CustomOverlay({
        yAnchor: 3,
        position: marker.getPosition()
    });

    var content = document.createElement('div');
    content.className = 'overayWrap'

    var info = document.createElement('div');
    info.className = 'info'
    content.style

    var title = document.createElement('div');
    title.className = 'title';
    title.innerHTML = m.name;

    var closeBtn = document.createElement('button');
    closeBtn.className = 'closeBtn';
    closeBtn.innerHTML = 'X';
    closeBtn.onclick = function () {
        overlay.setMap(null);
    };
    var content = document.createElement('div');
    content.className = 'overayWrap'

    var body = document.createElement('div');
    body.className = 'overlayContent';

    var imageC = document.createElement('div');
    imageC.className = 'image';
    if (m.imageUrl == "") {
        imageC.style.cssText = 'background-image: url("../img/camput.png")';
    } else {
        imageC.style.cssText = 'background-image: url( ' + m.imageUrl + ')';
    }
    var lineInfo = document.createElement('div');
    lineInfo.className = 'lineInfo';
    if (m.lineIntro == "") {
        lineInfo.textContent = '놀러오세요!'
    } else {
        lineInfo.textContent = m.lineIntro;
    }
    var address = document.createElement('div');
    address.className = 'address';
    address.textContent = m.simpleAddress;
    var animalCheck = document.createElement('div');
    animalCheck.className = 'animalCheck';
    animalCheck.setAttribute('value', m.animalCheck);
    animalCheck.textContent = m.animalCheck;
    var carvan = document.createElement('div')
    carvan.className = 'carvan';
    carvan.setAttribute('value', m.carvan);
    var gramping = document.createElement('div')
    gramping.className = 'gramping';
    gramping.setAttribute('value', m.gramping);
    var doNum = document.createElement('div')
    doNum.className = 'doNum';
    doNum.setAttribute('value', m.doNum);
    var totalStars = document.createElement('div')
    totalStars.className = 'totalStars';
    totalStars.setAttribute('value', m.totalStars);

    var CampHome = document.createElement('div');
    CampHome.className = 'CampHome';
    var homeUrl;
    if(m.homepageUrl!=""){
        homeUrl  = document.createElement('a');
        homeUrl.setAttribute("href", m.homepageUrl);
        homeUrl.textContent = '홈페이지'
        homeUrl.className = 'campHomePage';
    }else{
        homeUrl = document.createElement('div');
        homeUrl.className = 'campHomePage';
    }

    CampHome.appendChild(homeUrl)

    var detailPage =
        document.createElement('form');
    detailPage.setAttribute("action","/camput/detail/"+m.name);
    detailPage.setAttribute("method","get")
    detailPage.setAttribute("value",m.name)
    var detailCampName= document.createElement('div');
    detailCampName.className='detailCampName';

    var moveToDetail = document.createElement('input');
    moveToDetail.setAttribute("type","submit")
    moveToDetail.className = 'moveToDetail';
    moveToDetail.setAttribute("value",m.name);
    detailPage.appendChild(detailCampName)
    detailPage.appendChild(moveToDetail)

    var desc = document.createElement('div');
    desc.className = 'desc'

    desc.appendChild(lineInfo);
    desc.appendChild(address);
    desc.appendChild(carvan);
    desc.appendChild(gramping);
    desc.appendChild(doNum);
    desc.appendChild(totalStars);
    desc.appendChild(CampHome);
    desc.appendChild(detailPage);

    body.appendChild(imageC);
    body.appendChild(desc);

    info.appendChild(title);
    info.appendChild(body);

    title.appendChild(closeBtn)
    content.appendChild(info);
    overlay.setContent(content);
    kakao.maps.event.addListener(marker, 'click', function () {
        var move = new kakao.maps.LatLng(Number(m.mapX),String(m.mapY))
        overlay.setMap(map);
        map.panTo(move);
    });
}
///마커 초기화////
function deleteMarker() {
    clust.removeMarkers(markers)
    markers=[]
}
///마커,오버레이생성///
function makeMarkersAndOveray(data) {
    $.each(data, function (index, m) {
        var newMarker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(Number(m.mapX), String(m.mapY)),
            title: m.name,
            image: markerImage
        });
        markers.push(newMarker);
        makeOverLay(m, newMarker);
    });
    clust.addMarkers(markers);
}
///큰맵 변환////
function BigMap() {
    map.setLevel(13);
    var moveLatLng = new kakao.maps.LatLng(37.004463879516784, 125.41500850102987);
    map.panTo(moveLatLng);
}

async function reset(){
    $.ajax({
        url:"home/reset",
        type:"GET",
        async: false,
        success:function (data){
            BigMap();
            deleteMarker();
            makeMarkersAndOveray(data);
        },error:function (){
            console.log("check error");
        }
    })
}

//카페고리//
async function categoryCamps(){

    $.ajax({
        url:"home/category",
        type:"GET",
        data: $(".categoryInput").serialize(),
        async:false,
        success:function(data){
            BigMap();
            deleteMarker();
            makeMarkersAndOveray(data);
        },
        error: function (){
            console.log("check error");
        }
    });
}

async function searchCamps(){

    var searchInputText=$('.searchInputText').val();

    $.ajax({
        url:"home/search",
        type: "GET",
        data: {
            "searchInputText":searchInputText
        },
        async: false,
        success:function (data){
            console.log(data)
            if(data==""){
                $(".warring").show();
            }
            else if(data.size==1) {
                $.each(data,function(index,m){
                    var movetosearchCamp = new kakao.maps.LatLng(m.mapX,m.mapY);
                    map.panTo(movetosearchCamp);
                });
            }else{
                BigMap();
                deleteMarker();
                makeMarkersAndOveray(data);
            }
        },error: function (){
            console.log("check error");
        }
    });
}

//redio Button하나만 체크//
$(document).on("click","input[name='star']",function (){
    thisRadio = $(this);
    Nocheck=$('#noChoice');
    if(thisRadio.hasClass("thisChecked")){
        thisRadio.removeClass("thisChecked");
        thisRadio.prop('checked',false);
        Nocheck.prop('checked',true);
        Nocheck.addClass("thisChecked")
    }else{
        Nocheck.removeClass("thisChecked");
        Nocheck.prop('checked',false);
        thisRadio.prop('checked',true);
        thisRadio.addClass("thisChecked")

    }
});

$(document).on("click","input[name='animalCheck']",function (){
    thisRadio = $(this);
    Nocheck=$('#NoCheck');
    if(thisRadio.hasClass("thisAnimalChecked")){
        thisRadio.removeClass("thisAnimalChecked");
        thisRadio.prop('checked',false);
        Nocheck.prop('checked',true);
        Nocheck.addClass("thisChecked")
    }else{
        Nocheck.removeClass("thisChecked");
        Nocheck.prop('checked',false);
        thisRadio.prop('checked',true);
        thisRadio.addClass("thisAnimalChecked")
    }
});
function initPlaceholder(){
    $(".warring").hide();
}

