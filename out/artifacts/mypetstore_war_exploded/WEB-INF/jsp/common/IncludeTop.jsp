<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/9/28
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
          media="screen" />

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore Demo</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="../images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="viewCart"><img align="middle" name="img_cart"
                                    src="../images/cart.gif" />
            </a>
            <img align="middle" src="../images/separator.gif" />
            <c:if test="${sessionScope.account == null}">
                <a href="signonForm">Sign In</a>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <a href="signoff">Sign Out</a>
            </c:if>
            <img align="middle" src="../images/separator.gif" />
            <c:if test="${sessionScope.account != null}">
                <a href="editAccountForm">My Account</a>
            </c:if>
            <img align="middle" src="../images/separator.gif" />
            <a href="../../../help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="search" method="post" name="searchForm">
                <input type="text" name="keyword" size="14" list="complete" autocomplete="false" oninput="autoComplete()" /> <input type="submit" name="searchProducts" value="Search" />
                <datalist id="complete"></datalist>
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="viewCategory?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
        <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
    </div>

</div>

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

    function autoComplete() {
        var keyword = document.searchForm.keyword.value;
        if(keyword != "") {
            sendRequest("completeSearch?keyword="+keyword);
        }
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
                var products = xmlHttpRequest.responseXML.getElementsByTagName("product");
                var datalist = document.getElementById("complete");
                datalist.innerHTML="";
                for(var i=0; i<products.length; i++) {
                    var option = document.createElement("option");
                    var product = products[i].firstChild.data;
                    option.text = product
                    datalist.appendChild(option);
                    console.log(product);
                }
            }
        }
    }
</script>

<div id="Content">
