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
    sendRequest("completeSearch?keyword="+keyword);
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
            for(var i=0; i<products.length; i++) {
                var product = products[i].firstChild.data;
                datalist.options.add(new Option(product, product));
            }
        }
    }
}