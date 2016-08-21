<%-- 
    Document   : index
    Created on : Jul 25, 2016, 9:24:42 PM
    Author     : anhnh
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>

    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <h1>You haven't logged in yet!!! Go to <a href="CheckCookie">LOGIN PAGE</a> </h1>
            </c:when>
            <c:otherwise>
                <c:url var="Logout" value="ProcessServlet?btnAction=Logout"/>
                <h4 style="color: red">Hello ${sessionScope.USER.customerName} <a href="${Logout}">(Log out)</a> </h4>
                Search Order<br/><br/>
                <form action="ProcessServlet">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td style="text-align: left; width: 30%">
                                    From Date
                                </td>
                                <td style="text-align: right">
                                    <input id="from" type="text" name="txtFrom" value="" placeholder="mm/dd/yyyy" required
                                           pattern="([0-9]|0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/]\d\d\d\d"
                                           title="Please input right format mm/dd/yyyy" style="width: 300px"
                                           onblur="checkfromdate()"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p style="color: red" id="fromtest"></p>
                                </td> 
                            </tr>
                            <tr>
                                <td>
                                    To Date
                                </td>
                                <td>
                                    <input id="to" type="text" name="txtTo" value="" placeholder="mm/dd/yyyy" required
                                           pattern="(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/]\d\d\d\d"
                                           title="Please input right format mm/dd/yyyy" style="width: 300px"
                                           onblur="multy()"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p style="color: red" id="totest"></p>
                                </td> 
                            </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Search" name="btnAction"/>
                    <input type="reset" value="Reset" />
                </form>

                <p style="color: red" id="test"></p>
            </c:otherwise>
        </c:choose>
        <script type="text/javascript">
            function checkfromdate() {
                var x = document.getElementById("from");
                var regex = new RegExp("(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d");
                if (!regex.test(x.value)) {
                    document.getElementById("from").value = "";
                    document.getElementById("fromtest").innerHTML = "The right format mm/dd/yyyy";
                } else {
                    var element = x.value.split("/");
                    var month = parseInt(element[0]);
                    var day = parseInt(element[1]);
                    var year = parseInt(element[2]);
                    if ((year % 4) === 0) {
                        if (month === 2 && day > 29) {
                            document.getElementById("from").value = "";
                            document.getElementById("fromtest").innerHTML = "February on leap year only have 29 days";
                        } else {
                            document.getElementById("fromtest").innerHTML = "";
                        }
                    } else {
                        if (month === 2 && day > 28) {
                            document.getElementById("from").value = "";
                            document.getElementById("fromtest").innerHTML = "February only have 28 days";
                        }
                    }
                    if (month === 4 || month === 6 || month === 9 || month === 11) {
                        if (day > 30) {
                            document.getElementById("from").value = "";
                            document.getElementById("fromtest").innerHTML = 'The month you input only has 30 days';
                        } else {
                            document.getElementById("fromtest").innerHTML = "";
                        }
                    }
                }// end check regex
            }

            function checktodate() {
                var x = document.getElementById("to");
                var regex = new RegExp("(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d");
                if (!regex.test(x.value)) {
                    document.getElementById("to").value = "";
                    document.getElementById("totest").innerHTML = "The right format mm/dd/yyyy"
                } else {
                    var element = x.value.split("/");
                    var month = parseInt(element[0]);
                    var day = parseInt(element[1]);
                    var year = parseInt(element[2]);
                    if ((year % 4) === 0) {
                        if (month === 2 && day > 29) {
                            document.getElementById("to").value = "";
                            document.getElementById("totest").innerHTML = "February on leap year only have 29 days";
                        } else {
                            document.getElementById("totest").innerHTML = "";
                        }
                    } else {
                        if (month === 2 && day > 28) {
                            document.getElementById("to").value = "";
                            document.getElementById("totest").innerHTML = "February only have 28 days";
                        } else {
                            document.getElementById("totest").innerHTML = "";
                        }
                    }
                    if (month === 4 || month === 6 || month === 9 || month === 11) {
                        if (day > 30) {
                            document.getElementById("to").value = "";
                            document.getElementById("totest").innerHTML = 'The month you input only has 30 days';
                        } else {
                            document.getElementById("totest").innerHTML = "";
                        }
                    }
                }//end check regex
            }

            function checkgreater() {
                var x = document.getElementById("to");
                var y = document.getElementById("from");

                if (x !== "" || y !== "") {

                    var elementt = x.value.split("/");
                    var montht = parseInt(elementt[0]);
                    var dayt = parseInt(elementt[1]);
                    var yeart = parseInt(elementt[2]);

                    var elementf = y.value.split("/");
                    var monthf = parseInt(elementf[0]);
                    var dayf = parseInt(elementf[1]);
                    var yearf = parseInt(elementf[2]);

                    if (yearf > yeart) {
                        document.getElementById("to").value = "";
                        document.getElementById("from").value = "";
                        document.getElementById("test").innerHTML = "From date must less than To date";
                    } else {
                        if (monthf > montht) {
                            document.getElementById("to").value = "";
                            document.getElementById("from").value = "";
                            document.getElementById("test").innerHTML = "From date must less than To date";
                        } else {
                            if (dayf > dayt) {
                                document.getElementById("to").value = "";
                                document.getElementById("from").value = "";
                                document.getElementById("test").innerHTML = "From date must less than To date";
                            } else {
                                document.getElementById("test").innerHTML = "";
                            }//end check day
                        } //end check month
                    } //end check year
                }//end check if not empty string

            }

            function multy() {
                checktodate();
                checkgreater();
            }

        </script>


    </body>
</html>
