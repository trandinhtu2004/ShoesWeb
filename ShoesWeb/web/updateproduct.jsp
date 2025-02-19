<%-- 
    Document   : updateproducts
    Created on : Feb 19, 2024, 3:48:58 PM
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
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <div class="container container-fluid">
            <div class="card-header my-3 d-flex align-items-center justify-content-between">
                <a href="#"><h5>Dashboard</h5></a>
                <a class="nav-link" href="logout">Log out</a>
            </div>
            <div class="row">
                <%if (auth!=null && auth.getRoleid() == 1) {%>
                <%@include file="includes/asideadmin.jsp" %>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="card mx-auto">
                        <div class="card-header text-center">Update Product</div>
                        <div class="card-body">
                            <h2 style="color: red">${requestScope.msg}</h2>
                            <form action="update-product">
                                <div class="form-group">
                                    <label>Product ID: </label>
                                    <input type="text" class="form-control" name="id" placeholder="Enter id" required/>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                </div>
                            </form>
                        </div>
                        <div class="card-body">
                            <h2 style="color: red">${requestScope.error}</h2>
                            <c:set var="c" value="${requestScope.product}"/>
                            <form action="update-product" method="post">
                                <div class="form-group">
                                    <label>Product ID: </label>
                                    <input type="number" class="form-control" name="id" value="${c.id}" readonly/>
                                </div>
                                <div class="form-group">
                                    <label>Product Name: </label>
                                    <input type="text" class="form-control" name="name" value="${c.name}" required/>
                                </div>
                                <div class="form-group">
                                    <label>Description: </label>
                                    <input type="text" class="form-control" name="description" value="${c.description}" required/>
                                </div>
                                <div class="form-group">
                                    <label>Category: </label>
                                    <input type="text" class="form-control" name="category" value="${c.category}" required/>
                                </div>
                                <div class="form-group">
                                    <label>Price: </label>
                                    <input type="number" class="form-control" name="price" value="${c.price}" required/>
                                </div>
                                <div class="form-group">
                                    <label>Stock: </label>
                                    <input type="number" class="form-control" name="stock" value="${c.stock}" required/>
                                </div>
                                <div class="form-group">
                                    <label>Image: </label>
                                    <input type="file" class="form-control" name="image" value="${c.image}" required/>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </main>
            </div>
        </div>
                                <%}%>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
