<%-- 
    Document   : showsearch
    Created on : Jul 30, 2016, 7:43:41 PM
    Author     : anhnh
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order list</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <h1>You haven't logged in yet!!! Go to <a href="CheckCookie">LOGIN PAGE</a> </h1>
            </c:when>
            <c:otherwise>
                <c:url var="Logout" value="ProcessServlet?btnAction=Logout"/>
                <h4 style="color: red">Hello ${sessionScope.USER.customerName} <a href="${Logout}">(Log out)</a> </h4>
                <p style="font-weight: bold">Order list</p>
                <pre style="font-family: sans-serif; font-weight: bold">From: <%= request.getParameter("txtFrom")%>       To: <%= request.getParameter("txtTo")%> </pre>

                <c:choose>
                    <c:when test="${empty requestScope.SEARCH}">
                        <h3> Empty list </h3>
                    </c:when>
                    <c:otherwise>
                        <table border="1" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>OrderID</th>
                                    <th>Date</th>
                                    <th>Customer</th>
                                    <th>Total</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${requestScope.SEARCH}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${order.orderID}</td>
                                        <td>${order.orderDate}</td>
                                        <td>${sessionScope.USER.customerName}</td>
                                        <td>${order.total}</td>
                                        <c:url var="detail" value="ProcessServlet?btnAction=Detail">
                                            <c:param name="OrderID" value="${order.orderID}"/>
                                            <c:param name="total" value="${order.total}"/>
                                            <c:param name="date" value="${order.orderDate}"/>
                                            <c:param name="name" value="${sessionScope.USER.customerName}"/>
                                            <c:param name="phone" value="${order.phone}"/>
                                            <c:param name="address" value="${order.address}"/>
                                            <c:param name="txtFrom" value="${param.txtFrom}"/>
                                            <c:param name="txtTo" value="${param.txtTo}"/>
                                        </c:url>
                                        <td> <a href="${detail}">Details</a> </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose> <%--end check result existed--%>
                <a href="search.jsp">Back to search page</a>
            </c:otherwise> 
        </c:choose> <%--end check user are logged in --%>
    </body>
</html>
