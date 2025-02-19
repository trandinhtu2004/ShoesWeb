<%-- 
    Document   : index
    Created on : Feb 18, 2024, 6:20:44 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    //get user authentication
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth!=null) {
        request.setAttribute("auth", auth);
    }
    
    //get list of product in database()
    ProductDAO pd = new ProductDAO();
    List<Product> products = pd.getAllProducts();
    List<Product> products4 = pd.get4Products();
    
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
            request.setAttribute("cart_list", cart_list);
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shoes Shop Home</title>
        <%@include file="includes/head.jsp" %>
        <script type="text/javascript">
            function doImage(id) {
                window.location = "product-detail?id=" + id;
            }
            
        </script>
        <script>
    function scrollToFeaturedProducts() {
        // Tìm id là "featuredProducts"
        const featuredSection = document.getElementById("featuredProducts");
        if (featuredSection) {
            // Cuộn đến phần tử "featuredProducts"
            featuredSection.scrollIntoView({ behavior: "smooth", block: "start" });
        }
    }
</script>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        <div class="container">
            <div id="carouselExampleIndicators" class="carousel slide mt-3" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div id="img" class="carousel-item active">
                        <img class="d-block w-100" src="images/slider1.jpg" alt="First slide" style="height: 600px" onclick="scrollToFeaturedProducts()">
                        <div class="carousel-caption d-none d-md-block">
                            <h4 style="color: black">Elegant Stilettos</h4>
                            <h5 style="color: black">Step into the world of fashion with our elegant stilettos. Crafted with precision, they are perfect for a night out or a formal event.</h5>
                        </div>
                    </div>
                    <div id="img" class="carousel-item">
                        <img class="d-block w-100" src="images/slider4.png" alt="Second slide" style="height: 600px" onclick="scrollToFeaturedProducts()">
                        <div class="carousel-caption d-none d-md-block">
                            <h4>Comfortable Sneakers</h4>
                            <h5>Experience ultimate comfort and style with our range of sneakers. Perfect for a casual day out or for your daily run.</h5>
                        </div>
                    </div>
                    <div id="img" class="carousel-item">
                        <img class="d-block w-100" src="images/slider5.jpg" alt="Third slide" style="height: 600px" onclick="scrollToFeaturedProducts()">
                        <div class="carousel-caption d-none d-md-block">
                            <h4 style="color: blue">Classic Leather Boots</h4>
                            <h5 style="color: blue">Embrace the timeless appeal of our classic leather boots. Ideal for a stylish winter look or an adventurous outing.</h5>
                        </div>
                    </div>
                </div>

                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <br/>
            <h2 id="featuredProducts" class="text-center mb-5">Featured Products</h2>
            <div class="row">
                <c:forEach var="c" items="<%= products4%>">
                    <div class="col-md-3 col-6 mb-4">
                        <div class="card h-100">
                            <img src="images/${c.getImage()}" class="card-img-top" alt="${c.getName()}" onclick="doImage('${c.getId()}')">
                            <div class="card-body">
                                <h5 class="card-title">${c.getName()}</h5>
                                <p class="card-text">${c.getPrice()}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <br/>
            <h2 class="text-center mb-5">All Products</h2>

            <form action="search-name" method="post">
                <div class="form-group">
                    <label>Product name: </label>
                    <input type="text" class="form-control" name="name" placeholder="Enter product name" required/>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Search</button>
                    <button type="reset" class="btn btn-primary">Reset</button>
                </div>
            </form>
            <br/>
            <div class="row">
                <c:forEach items="<%= products%>" var="c">
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
        </div>

        <%@include file="includes/foot.jsp" %>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
