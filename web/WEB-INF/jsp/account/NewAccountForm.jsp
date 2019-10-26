<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/1
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="newAccount" method="post" name="registerForm">

        <h3>User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td>
                    <input type="text" name="username" onblur="usernameIsAvailable();">
                    <div id="usernameMsg"></div>
                </td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword"></td>
            </tr>
            <tr>
                <td>Verification:</td>
                <td><input type="text" name="code"></td>
                <td><img src="/viewVercode"></td>
            </tr>
        </table>

        <%@ include file="IncludeAccountFields.jsp"%>

        <input type="submit" name="newAccount" value="Save Account Information">

    </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

<script>
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
</script>