<%-- 
    Document   : product
    Created on : Feb 18, 2024, 6:57:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth!=null) {
        request.setAttribute("auth", auth);
    }
    
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
            request.setAttribute("cart_list", cart_list);
    }
    
ProductDAO pd = new ProductDAO();
    List<String> categories = pd.getCategories();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shoes Shop Products</title>
        <%@include file="includes/head.jsp" %>
        <style>
            .card-img-top:hover {
                cursor: pointer;
            }
        </style>
        <script type="text/javascript">
            function doImage(id) {
                window.location = "product-detail?id=" + id;
            }

        </script>
        
        <%
            String message = (String) request.getSession().getAttribute("message");
            if (message!=null && message!="") {
        %>

        <script type="text/javascript">
            window.onload = function () {
                var message = '<%=message%>';
                if (message !== null && message !== '') {
                    alert(message, 3000);
                }
            };
        </script>
        <script src="includes/alert.js"></script>

        <%
            }
            request.getSession().removeAttribute("message");
        %>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>

        <div class="container">
            <div class="card-header my-3">All Products</div>
            <div class="row">
                <div class="col-md-3 card w-50 mx-auto">
                    <div class="card-body">
                        <form action="search-category">
                            <label><h5>Search by Category:</h5></label>
                            <c:forEach var="c" items="<%= categories%>">
                                <input type="submit" name="category" value="${c}"/>
                            </c:forEach>
                        </form>
                        <br/>
                        <label><h5>Sort products by price: </h5></label>
                        <button><a href="sort-price">Sort</a></button>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <c:forEach items="${list}" var="c">
                            <div class="col-md-4 pb-3">
                                <div class="card w-100" style="width: 18rem; position: relative;">
                                    <image class="card-img-top" src="images/${c.getImage()}" alt="${c.getImage()}" style="height: 180px; width: auto;" onclick="doImage('${c.getId()}')"/>
                                    <div class="card-body">
                                        <h5 class="card-title">${c.getName()}</h5>
                                        <div class="price-box" style="display: flex; align-items: center; justify-content: space-between;">
                                            <p style="font-weight: bold;">Price:</p> 
                                            <p>$<fmt:formatNumber value="${c.getPrice()}" type="number" maxFractionDigits="2"/></p>
                                        </div>
                                        <div class="description">${c.getDescription()}</div>
                                        <h6 class="category">Category: <a href="search-category?category=${c.getCategory()}">${c.getCategory()}</a></h6>
                                        <div class="mt-3 d-flex justify-content-between">
                                            <a href="add-to-cart?id=${c.getId()}" class="btn btn-dark">Add to cart</a>
                                            <a href="order-now?quantity=1&id=${c.getId()}" class="btn btn-primary">Buy now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div style="text-align: center;" class="clearfix">
                        <div class="hint-text">Showing <b>${requestScope.tag}</b> out of <b>${requestScope.endP}</b> entries</div>
                        <ul class="pagination" style="display: flex; align-items: center; justify-content: center;">
                            <c:if test="${requestScope.tag>1}">
                                <li class="page-item">
                                    <a href="pagination?index=${requestScope.tag-1}" class="page-link" aria-label="Go to previous page">Previous</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.endP}" var="i">
                                <li class="page-item ${requestScope.tag == i?"active":""}">
                                    <a href="pagination?index=${i}" class="page-link">${i}</a>
                                </li> 
                            </c:forEach>
                            <c:if test="${requestScope.tag < requestScope.endP}">
                                <li class="page-item">
                                    <a href="pagination?index=${requestScope.tag+1}" class="page-link" aria-label="Go to next page">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="includes/foot.jsp" %>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
