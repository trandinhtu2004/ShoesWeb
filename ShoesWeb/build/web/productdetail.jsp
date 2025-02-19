<%-- 
    Document   : productdetail
    Created on : Feb 13, 2024, 9:58:07 PM
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
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <%@include file="includes/head.jsp" %>
        <script type="text/javascript">
            function doImage(id) {
                window.location = "product-detail?id=" + id;
            }

        </script>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>



        <div class="container">

            <div class="card-header my-3">Product Detail</div>

            <c:set value="${requestScope.product}" var="p"/>
            <div class="row gx-5">
                <aside class="col-lg-6">
                    <div class="border rounded-4 mb-3 d-flex justify-content-center">
                        <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image" href="images/${p.image}">
                            <img style="max-width: 100%; max-height: 100vh; margin: auto; height: 300px; width: auto;" class="rounded-4 fit" src="images/${p.image}" />
                        </a>
                    </div>
                </aside>
                <main class="col-lg-6">
                    <div class="ps-lg-3">
                        <h4 class="title text-dark">
                            ${p.name}
                        </h4>
                        <div class="mb-3">
                            <span class="h5"><fmt:parseNumber pattern="##.##" value="${p.getPrice()}"/>$/box</span>
                        </div>

                        <p>
                            ${p.description}
                        </p>

                        <div class="row">
                            <dt class="col-3">Type:</dt>
                            <dd class="col-9"><a href="search-category?category=${p.getCategory()}">${p.getCategory()}</a></dd>
                        </div>
                        
                        <div class="row">
                            <dt class="col-3">Stock:</dt>
                            <dd class="col-9">${p.getStock()}</dd>
                        </div>

                        <hr />
                        <div class="mt-3 d-flex justify-content-between">
                            <a href="add-to-cart?id=${p.id}" class="btn btn-dark">Add to cart</a>
                            <a href="order-now?quantity=1&id=${p.id}" class="btn btn-primary">Buy now</a>
                        </div>
                    </div>
                </main>
            </div>

            <h4 style="text-align: center; padding: 20px 0;">Related Products</h4>

            <div class="row">
                <c:forEach var="c" items="${requestScope.list}">
                    <div class="col-md-4 pb-3">
                        <div class="card w-100" style="width: 18rem; position: relative;">
                            <image class="card-img-top" src="images/${c.getImage()}" alt="${c.getImage()}" style="height: 240px; width: auto;" onclick="doImage('${c.getId()}')"/>
                            <div class="card-body">
                                <h5 class="card-title">${c.getName()}</h5>
                                <div class="price-box" style="display: flex; align-items: center; justify-content: space-between;">
                                    <p style="font-weight: bold;">Price:</p> 
                                    <p>$<fmt:formatNumber value="${c.getPrice()}" type="number" maxFractionDigits="2"/></p>
                                </div>
                                <div class="description">${c.getDescription()}</div>
                                <h6 class="category">Category: <a href="#">${c.getCategory()}</a></h6>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="add-to-cart?id=${c.getId()}" class="btn btn-dark">Add to cart</a>
                                    <a href="order-now?quantity=1&id=${c.getId()}" class="btn btn-primary">Buy now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>



            </div>

        </div>

        <%@include file="includes/foot.jsp" %>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
