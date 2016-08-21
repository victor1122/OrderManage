<%-- 
    Document   : register
    Created on : Jul 25, 2016, 10:01:25 PM
    Author     : anhnh
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <style>
            pre{
                font-family: sans-serif;
            }
        </style>
    </head>
    <body>
        <h1>Create account</h1>
        <form action="ProcessServlet" method="POST">
            <p style="vertical-align: central">
            <pre>Username:        <input type="text" name="txtUsername" value="" required 
                pattern="([a-zA-Z._0-9]){1,10}" title="Username can only contains a-zA-z._0-9, max length is 10 characters"/></pre>

            <%--Print error--%>
            <c:if test="${not empty requestScope.ERROR}">
                <c:if test="${not empty requestScope.ERROR.dupUsername}">
                    <p style="color: red">${requestScope.ERROR.dupUsername}</p>
                </c:if>
            </c:if>

            <pre>Password:         <input type="password" name="txtPassword" value="" required pattern="{1,20}" title="Max length is 20"/></pre>
            <pre>Confirm password: <input type="password" name="txtConfirm" value="" required pattern="{1,20}" title="Max length is 20"/></pre> 

            <%--Print error--%>
            <c:if test="${not empty requestScope.ERROR}">
                <c:if test="${not empty requestScope.ERROR.wrongConfirm}">
                    <p style="color: red">${requestScope.ERROR.wrongConfirm}</p>
                </c:if>
            </c:if>

            <pre>Customer name:   <input type="text" name="txtName" value="" required pattern="{1,50}" title="Max length is 50"/></pre>
            <pre>Email:           <input type="text" name="txtEmail" value="" required pattern="{1,50}" title="Max length is 50"/></pre>

            <%--Print error--%>
            <c:if test="${not empty requestScope.ERROR}">
                <c:if test="${not empty requestScope.ERROR.dupEmail}">
                    <p style="color: red">${requestScope.ERROR.dupEmail}</p>
                </c:if>
            </c:if>
            
        </p>
        <input type="submit" value="Regist" name="btnAction" />
        <input type="reset" value="Reset" />
    </form>
</body>
</html>
