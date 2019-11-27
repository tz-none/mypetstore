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
    console.log(keyword);
    sendRequestSearch("completeSearch?keyword="+keyword);
}

function sendRequestSearch(url) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = processResponseSearch;
    xmlHttpRequest.send(null);
}

function processResponseSearch() {
    if(xmlHttpRequest.readyState == 4) {
        if(xmlHttpRequest.status == 200) {
            var products = xmlHttpRequest.responseXML.getElementsByTagName("product");
            var datalist = document.getElementById("complete");
            var length = datalist.options.length;
            console.log(datalist.options[0]);
            for(var i=0; i<length; i++) {
                datalist.options.remove(0);
            }
            for(var i=0; i<products.length; i++) {
                var product = products[i].firstChild.data;
                datalist.appendChild(new Option(product, product));
            }
        }
    }
}