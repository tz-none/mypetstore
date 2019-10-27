window.onload = function () {
    for(var i=0; i<items.length; i++) {
        console.log(items[i]);
        var itemElements = document.getElementsByName(items[i]);
        itemElements[0].addEventListener("blur", function () {
            var quantity = itemElements[0].value;
            var price = itemElements[1].innerHTML;
            console.log(quantity);
            console.log(price);
            itemElements[2].innerHTML = (parseInt(quantity) * parseFloat(price)).toFixed(2).toString();
        })
    }
}