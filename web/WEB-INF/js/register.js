var usernameInput = document.getElementsByName("username")[0];
console.log(usernameInput);
usernameInput.addEventListener("blur", function () {
});

var xmlHttpRequest;
function createXMLHttpRequest() {
    if(window.XMLHttpRequest) {
        xmlHttpRequest = new XMLHttpRequest();
    }else if(window.ActiveXObject) {
        xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
    }else {
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function usernameIsAvailable() {
    var username = document.registerForm.username.value;
    sendRequest("usernameIsAvailable?username="+username);
}

function sendRequest(url) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = processResponse;
    xmlHttpRequest.send(null);
}

function processResponse() {
    console.log(xmlHttpRequest.readyState);
    if(xmlHttpRequest.readyState == 4) {
        if(xmlHttpRequest.status == 200) {
            var responseInfo = xmlHttpRequest.responseXML.getElementsByTagName("msg")[0].firstChild.data;

            var div1 = document.getElementById("usernameMsg");

            if(responseInfo == "Available") {
                div1.innerHTML = "<font color='green'>avaliable</font>";
            }else {
                div1.innerHTML = "<font color='red'>not avaliable</font>";
            }
        }
    }
}