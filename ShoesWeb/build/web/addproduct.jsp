<%-- 
    Document   : addproduct
    Created on : Feb 19, 2024, 3:48:33 PM
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
    //kiem tra dang nhap
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
                        
                        <div class="card-header text-center">Add Product</div>
                        <!--"Add Product" o giua -->

                        <div class="card-body">
                          

                            <h2 style="color: red">${requestScope.error}</h2>
                            

                            <form action="add-product" method="post">
                                

                                <div class="form-group">
                                    
                                    <label>Product name: </label>
                                    
                                    <input type="text" class="form-control" name="name" placeholder="Enter product name" required/>
                                
                                </div>

                                <div class="form-group">
                                    
                                    <label>Description: </label>
                                   
                                    <input type="text" class="form-control" name="description" placeholder="Enter description" required/>
                                    
                                </div>

                                <div class="form-group">
                              
                                    <label>Category: </label>
                                    <!-- Nhãn cho ô nhập liệu -->
                                    <input type="text" class="form-control" name="category" placeholder="Enter category" required/>
                                 
                                </div>

                                <div class="form-group">
                             
                                    <label>Price: </label>
                               
                                    <input type="number" class="form-control" name="price" placeholder="Enter role" required/>
                                  
                                </div>

                                <div class="form-group">
                                  
                                    <label>Stock: </label>
                                 
                                    <input type="number" class="form-control" name="stock" value="${c.stock}" required/>
              
                                </div>

                                <div class="form-group">
                                    <label>Image: </label>
                                    <input type="file" class="form-control" name="image" required/>
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Add</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                </main>
                                    <%}%>
            </div>
        </div>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
