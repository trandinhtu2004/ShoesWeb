<%-- 
    Document   : orderdetail
    Created on : Feb 14, 2024, 10:04:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    Order o = (Order) request.getAttribute("o");
    List<Order> orders = null;
    List<OrdersDetails> orderdetails = null;
    if (auth!=null) {
        request.setAttribute("auth", auth);
        OrderDAO odao = new OrderDAO();
        OrderDetailDAO oddao = new OrderDetailDAO();
        orders = odao.userOrders(auth.getId());
        
        orderdetails = oddao.listOrderDetail(o.getOid());
    } else {
        response.sendRedirect("login.jsp");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includes/head.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>

        <div class="container">
            <div class="card-header my-3">Order detail</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Product ID</th>
                        <th scope="col">Product Name</th>
                        <th scope="col">Image</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (orderdetails!=null) {
                    %>
                    <c:forEach items="<%= orderdetails %>" var="o">
                        <tr>
                            <td>${o.getOrder().getOid()}</td>
                            <td>${o.getProduct().getId()}</td>
                            <td><a href="product-detail?id=${o.getProduct().getId()}">${o.getProduct().getName()}</a></td>
                            <td><img src="images/${o.getProduct().getImage()}" alt="${o.getProduct().getName()}" style="height: 180px; width: 300px;"/></td>
                            <td><fmt:formatNumber pattern="##.##" value="${o.getPrice()}"/></td>
                            <td>${o.getQuantity()}</td>
                        </tr> 
                    </c:forEach>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
                
        <%@include file="includes/foot.jsp" %>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
