var stompClient = null;

function sendMessage() {
  let jsonOb = {
    name: localStorage.getItem("name"),
    messageContent: $("#message-value").val(),
  };

  stompClient.send("/app/message", {}, JSON.stringify(jsonOb));
}

function connect() {
  let socket = new SockJS("/server1");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("Connected : " + frame);
    $("#name-form").addClass("d-none");
    $("#chat-room").removeClass("d-none");

    // subscribe
    stompClient.subscribe("/topic/return-to", function (response) {
      showMessage(JSON.parse(response.body));
    });
  });
}

function showMessage(message) {
  $("#message-container-table").prepend(
    `<tr><td><b> ${message.name} :</b> ${message.messageContent} </td></tr>`
  );
}

$(document).ready((e) => {
  $("#login").click(() => {
    let name = $("#name-value").val();
    localStorage.setItem("name", name);
    connect();
  });

  $("#send-btn").click(() => {
    sendMessage();
  });
});

// var stompClient = null;
// var notificationCount = 0;

// $(document).ready(function () {
//   console.log("Index page is ready");
//   connect();

//   $("#send").click(function () {
//     sendMessage();
//   });

//   $("#send-private").click(function () {
//     sendPrivateMessage();
//   });

//   $("#notifications").click(function () {
//     resetNotificationCount();
//   });
// });

// function connect() {
//   var socket = new SockJS("/our-websocket");
//   stompClient = Stomp.over(socket);
//   stompClient.connect({}, function (frame) {
//     console.log("Connected: " + frame);
//     updateNotificationDisplay();
//     stompClient.subscribe("/topic/messages", function (message) {
//       var parsedMessage = JSON.parse(message.body); // Parse the JSON string
//       var content = parsedMessage.content;
//       showMessage(content);
//     });

//     stompClient.subscribe("/user/topic/private-messages", function (message) {
//       showMessage(JSON.parse(message.body).content);
//     });

//     stompClient.subscribe("/topic/global-notifications", function (message) {
//       notificationCount = notificationCount + 1;
//       updateNotificationDisplay();
//     });

//     stompClient.subscribe(
//       "/user/topic/private-notifications",
//       function (message) {
//         notificationCount = notificationCount + 1;
//         updateNotificationDisplay();
//       }
//     );
//   });
// }

// function showMessage(message) {
//   console.log(message);
//   $("#messages").append("<tr><td>" + message + "</td></tr>");
// }

// function sendMessage() {
//   console.log("sending message");
//   stompClient.send(
//     "/ws/message",
//     {},
//     JSON.stringify({ messageContent: $("#message").val() })
//   );
// }

// function sendPrivateMessage() {
//   console.log("sending private message");
//   stompClient.send(
//     "/ws/private-message",
//     {},
//     JSON.stringify({ messageContent: $("#private-message").val() })
//   );
// }

// function updateNotificationDisplay() {
//   if (notificationCount == 0) {
//     $("#notifications").hide();
//   } else {
//     $("#notifications").show();
//     $("#notifications").text(notificationCount);
//   }
// }

// function resetNotificationCount() {
//   notificationCount = 0;
//   updateNotificationDisplay();
// }
