<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/1
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="signon" method="post">

        <p>Please enter your username and password.</p>
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" value="j2ee"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" value="j2ee"></td>
            </tr>
            <tr>
                <td>Verification:</td>
                <td><input type="text" name="code"></td>
                <td><a href=""><img src="/viewVercode"></a></td>
            </tr>
        </table>
        <p>
            <font color="red">${requestScope.msg}</font>
        </p>
        <input type="submit" name="signon" value="login">

    </form>Need a user name and password?
    <a href="newAccountForm">Register Now!</a>
</div>


<%@ include file="../common/IncludeBottom.jsp"%>


