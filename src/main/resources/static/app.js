var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#bidList").html("");
}
function connect() {
    var socket = new SockJS('/ws-stomp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        //경매 호가 불러오기

        //구독 시작, 구독자에게 구독 내용 보여 주는 func
        stompClient.subscribe('/auctionRoom/'+auctionId, function (auction) {
            showGreeting(JSON.parse(auction.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendPrice() {
    stompClient.send("/auction/bid", {}, JSON.stringify({'price': $("#price").val()}));
}

function showGreeting(price) {
    $("#bidList").append("<tr><td>" + price + "</td></tr>");
}

function showPost(){
    let url = '/api/v1/post'
    fetch(url).then(res=> console.log("response : ", res))
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendPrice(); });
    $( "#getpost" ).click(function() { showPost(); });
});