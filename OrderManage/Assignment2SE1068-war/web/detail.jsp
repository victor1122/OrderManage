<%-- 
    Document   : detail
    Created on : Jul 30, 2016, 8:31:57 PM
    Author     : anhnh
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <h1>You haven't logged in yet!!! Go to <a href="CheckCookie">LOGIN PAGE</a> </h1>
            </c:when>
            <c:otherwise>
                <c:url var="Logout" value="ProcessServlet?btnAction=Logout"/>
                <h4 style="color: red">Hello ${sessionScope.USER.customerName} <a href="${Logout}">(Log out)</a> </h4>
                <p style="font-weight: bold">Order Details</p>
                <c:choose>
                    <c:when test="${empty requestScope.DETAIL}">
                        <h3> Empty list </h3>
                    </c:when>
                    <c:otherwise>
                        <c:set var="OrderDetail" value="${requestScope.DETAIL}"/>
                        <table border="0" style="width: 54%">
                            <tbody>
                                <tr>
                                    <td>OrderId:__${param.OrderID}__</td>
                                    <td>Date:__${param.date}__</td>
                                </tr>
                                <tr>
                                    <td>Customer:__${param.name}__</td>
                                    <td>Phone:__${param.phone}__</td>
                                </tr>
                                <tr>
                                    <td colspan="2">Address:__${param.address}__</td>
                                </tr>
                            </tbody>
                        </table>

                        <p>Details</p>
                        <table border="1" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Product</th>
                                    <th>Unit</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detail" items="${OrderDetail}" varStatus="counter">
                                <form action="ProcessServlet">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${detail.key}
                                        </td>
                                        <td>
                                            ${detail.value.unitItem}
                                        </td>
                                        <td>
                                            <input type="text" name="txtQuantity" value="${detail.value.quantity}" 
                                                   style="width: 97%" required pattern="\d+" title="Positive number"/> 
                                        </td>
                                        <td>
                                            <input type="hidden" name="txtUnitPrice" value="${detail.value.unitPrice}"/>
                                            ${detail.value.unitPrice}
                                        </td>
                                        <td>
                                            ${detail.value.total}
                                        </td>
                                        <td>
                                            <input type="submit" value="Update" name="btnAction" style="width: 100%"/>
                                        </td>
                                        <td style="text-align: center">
                                            <c:url var="delete" value="ProcessServlet?btnAction=Delete">
                                                <c:param name="OrderID" value="${param.OrderID}"/>
                                                <c:param name="date" value="${param.date}"/>
                                                <c:param name="name" value="${sessionScope.USER.customerName}"/>
                                                <c:param name="phone" value="${param.phone}"/>
                                                <c:param name="address" value="${param.address}"/>
                                                <c:param name="txtFrom" value="${param.txtFrom}"/>
                                                <c:param name="txtTo" value="${param.txtTo}"/>
                                                <c:param name="productid" value="${detail.value.productID}"/>
                                                <c:param name="id" value="${detail.value.id}"/>
                                            </c:url>
                                            <a href="${delete}">x</a>
                                        </td>
                                    </tr>
                                    <input type="hidden" name="id" value="${detail.value.id}" />
                                    <input type="hidden" name="OrderID" value="${param.OrderID}" />
                                    <input type="hidden" name="date" value="${param.date}" />
                                    <input type="hidden" name="name" value="${sessionScope.USER.customerName}" />
                                    <input type="hidden" name="phone" value="${param.phone}" />
                                    <input type="hidden" name="address" value="${param.address}" />
                                    <input type="hidden" name="productid" value="${detail.value.productID}" />
                                    <input type="hidden" name="productName" value="${detail.key}" />
                                    <input type="hidden" name="txtFrom" value="${param.txtFrom}" />
                                    <input type="hidden" name="txtTo" value="${param.txtTo}" />
                                </form>
                            </c:forEach>
                            <tr>
                                <td colspan="4" style="border: none"></td>
                                <td colspan="4" style="border: none">Total: ${requestScope.TOTAL}</td>
                            </tr>
                        </tbody>
                    </table>
                    <c:if test="${not empty requestScope.ERR}">
                        <c:if test="${not empty requestScope.ERR.wrongQuantity}">
                            <p style="color: red">${requestScope.ERR.wrongQuantity}</p>
                        </c:if>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <c:url var="Back" value="ProcessServlet?btnAction=Search">
                <c:param name="txtFrom" value="${param.txtFrom}"/>
                <c:param name="txtTo" value="${param.txtTo}"/>
            </c:url>
            <a href="${Back}">Back to orders page</a>                    
        </c:otherwise>
    </c:choose>


</body>
</html>
