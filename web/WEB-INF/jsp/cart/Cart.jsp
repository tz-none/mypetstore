<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/9/30
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">
        <script>var items = new Array();</script>

        <h2>Shopping Cart</h2>
            <form action="updateCart" method="post" name="cartForm">
                <table>
                    <tr>
                        <th><b>Item ID</b></th>
                        <th><b>Product ID</b></th>
                        <th><b>Description</b></th>
                        <th><b>In Stock?</b></th>
                        <th><b>Quantity</b></th>
                        <th><b>List Price</b></th>
                        <th><b>Total Cost</b></th>
                        <th>&nbsp;</th>
                    </tr>

                    <c:if test="${sessionScope.cart.numberOfItems == 0}">
                        <tr>
                            <td colspan="8"><b>Your cart is empty.</b></td>
                        </tr>
                    </c:if>

                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                        <tr>
                            <td>
                                <a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                            </td>
                            <td>${cartItem.item.product.productId}</td>
                            <td>
                                ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}
                            </td>
                            <td>${cartItem.inStock}</td>
                            <td>
                                <input type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
                                <script>
                                    var item = "${cartItem.item.itemId}";
                                    items.push(item);
                                </script>
                            </td>
                            <td>
                                <div name="${cartItem.item.itemId}">${cartItem.item.listPrice}</div>
                            </td>
                            <td>
                                <div name="${cartItem.item.itemId}">${cartItem.total}</div>
                            </td>
                            <td>
                                <a class="Button" href="removeItemFromCart?workingItemId=${cartItem.item.itemId}">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7">
                            Sub Total: <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                            <input type="submit" value="Update Cart">
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </form>

        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <a class="Button" href="newOrderForm">Proceed to Checkout</a>
        </c:if>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

<script>
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
</script>
