window.onload = function () {
    for(var i=0; i<items.length; i++) {
        var itemId = items[i];
        var itemElements = document.getElementsByName(itemId);

        itemElements[0].addEventListener("blur", function () {
            var url = "computeCartItem?";
            for(var i=0; i<items.length; i++) {
                var item_id = items[i];
                var item_elements = document.getElementsByName(item_id);

                url += item_id + "=" + item_elements[0].value;
                if(i != items.length-1) {
                    url += "&";
                }
            }
            sendRequestCompute(url);
        })
    }
}
function sendRequestCompute(url) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = processResponseCompute;
    xmlHttpRequest.send(null);
}

function processResponseCompute() {
    if(xmlHttpRequest.readyState == 4) {
        if(xmlHttpRequest.status == 200) {
            var json = xmlHttpRequest.responseText;
            var obj = JSON.parse(json);
            for(var i=0; i<obj.items.length; i++) {
                var price = obj.items[i].price;
                var quantity = obj.items[i].quantity;
                document.getElementsByName(obj.items[i].itemId)[2].innerHTML = (parseInt(quantity) * parseFloat(price)).toFixed(2).toString();
            }
            document.getElementById("total").innerHTML = obj.subTotal;
        }
    }
}