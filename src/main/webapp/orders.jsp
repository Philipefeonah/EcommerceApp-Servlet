<%@ page import="com.maxiflexy.model.Customer" %>
<%@ page import="com.maxiflexy.model.Cart" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 12/03/2024
  Time: 10:17 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Customer auth = (Customer) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null){
        request.setAttribute("cart_list",cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>order</title>
   <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/navbar.jsp"%>
    <%@include file="includes/footer.jsp"%>
    <h2>Thank You,</h2>
    <h4>Order is currently processed for payment, Kindly wait for confirmation</h4>
</body>
</html>
