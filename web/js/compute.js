for(var i=0; i<items.length; i++) {
    console.log(items[i]);
    var itemElements = document.getElementsByName(items[i]);
    console.log(itemElements[0]);
    itemElements[0].addEventListener("blur", function () {
        for(var i=0; i<items.length; i++) {
        var elements = document.getElementsByName(items[i]);
        var quantity = elements[0].value;
        var price = elements[1].innerHTML;
        elements[2].innerHTML = (parseInt(quantity) * parseFloat(price)).toFixed(2).toString();
        }
    });
}