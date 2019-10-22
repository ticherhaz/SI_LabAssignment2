<%-- 
    Document   : index
    Created on : Sep 26, 2019, 7:02:14 AM
    Author     : Hazman
--%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="net.ticherhaz.model.Common"%>
<%@page import="net.ticherhaz.database.DBConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="net.ticherhaz.model.Bank"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SI Lab Assignment 2</title>
    </head>
    <body>
        <h1>Loan Calculator Hazman's Website</h1>
        <form action="LoanServlet" method="POST" id="loan">
            <p>
                <%! DBConnectionManager dBConnectionManager = new DBConnectionManager();%>
                <%-- Display text error of loan amount and number of year --%>
                <strong style="tomato">
                    <%
                        if (request.getAttribute(Common.ERROR_MESSAGE_LOAN_AMOUNT_NUMBER_OF_YEAR) != null) {
                            out.println("** " + request.getAttribute(Common.ERROR_MESSAGE_LOAN_AMOUNT_NUMBER_OF_YEAR).toString());
                            out.println("<br>");
                        }
                    %>
                </strong>
                <strong style="tomato">
                    <%-- Display text error of number format --%>
                    <%
                        if (request.getAttribute(Common.ERROR_MESSAGE_NUMBER_FORMAT) != null) {
                            out.println("** " + request.getAttribute(Common.ERROR_MESSAGE_NUMBER_FORMAT).toString());
                            out.println("<br>");
                        }
                    %>
                </strong>
            </p>
            <p>
                Loan Amount: <input type="text" 
                                    name="loanAmount" 
                                    value="<% if (request.getAttribute(Common.LOAN_AMOUNT) != null) {
                                            out.println(request.getAttribute(Common.LOAN_AMOUNT).toString());
                                        }%>"/>
                <%-- Display message error loan amount --%>
                <strong style="tomato">
                    <%
                        if (request.getAttribute(Common.ERROR_MESSAGE_LOAN_AMOUNT) != null) {
                            out.println("** " + request.getAttribute(Common.ERROR_MESSAGE_LOAN_AMOUNT).toString());
                            out.println("<br>");
                        }
                    %>
                </strong>
            </p>
            <p>
                No. of Year: <input type="text" 
                                    name="numberOfYear" 
                                    value="<% if (request.getAttribute(Common.NUMBER_OF_YEAR) != null) {
                                            out.println(request.getAttribute(Common.NUMBER_OF_YEAR).toString());
                                        }%>"/>
                <%-- Display message error number of year --%>
                <strong style="tomato">
                    <%
                        if (request.getAttribute(Common.ERROR_MESSAGE_NUMBER_OF_YEAR) != null) {
                            out.println("** " + request.getAttribute(Common.ERROR_MESSAGE_NUMBER_OF_YEAR).toString());
                            out.println("<br>");
                        }
                    %>
                </strong>
            </p>
            <p>
                <%
                    //Make connection with MySQL
                    Connection connection = DBConnectionManager.setDBConnection();
                    Statement st = null;
                    ResultSet rs = null;
                    st = connection.createStatement();
                    rs = st.executeQuery("SELECT bankName FROM bank"); %>

                Select Bank  : <select  id="bankName" name="bankName"  onchange="displayInterestRate(this.value)">
                    <%while (rs.next()) {%>
                    <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
                    <%}
                        rs.close();
                        st.close();
                        connection.close();%>           
                </select> 
            </p>

            <p>
                Interest Rate: <span class="interestRate">2.7</span>%  
            </p>
            <br>
            <input type="submit" name="submit" value="Submit"/>
        </form>
        <script>
            //Making function/method for the triggered onchange of selected select
            function displayInterestRate(value) {
                var interest = 0;
                var $interestRate = document.querySelector('.interestRate');
                //Get the value of bankName and check
                if (value === "CLIMB") {
                    interest = 2.7;
                } else if (value === "CIMP") {
                    interest = 3.2;
                } else if (value === "CIMB") {
                    interest = 4.1;
                } else if (value === "MBB") {
                    interest = 2.6;
                }
                $interestRate.innerHTML = interest;
            }
        </script>
    </body>
</html>
