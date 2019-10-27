window.onload = function () {
    var usernameInput = document.getElementsByName("username")[0];
    usernameInput.addEventListener("blur", usernameIsAvailable);
}

function usernameIsAvailable() {
    var username = document.registerForm.username.value;
    console.log(username);
    sendRequestRegister("usernameIsAvailable?username="+username);
}

function sendRequestRegister(url) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = processResponseRegister;
    xmlHttpRequest.send(null);
}

function processResponseRegister() {
    if(xmlHttpRequest.readyState == 4) {
        if(xmlHttpRequest.status == 200) {
            var responseInfo = xmlHttpRequest.responseXML.getElementsByTagName("msg")[0].firstChild.data;

            var div1 = document.getElementById("usernameMsg");
            console.log(responseInfo);

            if(responseInfo == "Available") {
                div1.innerHTML = "<font color='green'>avaliable</font>";
            }else {
                div1.innerHTML = "<font color='red'>not avaliable</font>";
            }
        }
    }
}