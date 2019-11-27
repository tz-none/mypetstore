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
                    <input type="text" name="username"">
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
                <td><img src="viewVercode"></td>
            </tr>
        </table>

        <%@ include file="IncludeAccountFields.jsp"%>

        <input type="submit" name="newAccount" value="Save Account Information">

    </form>
</div>

<script src="js/register.js"></script>

<%@ include file="../common/IncludeBottom.jsp"%>
