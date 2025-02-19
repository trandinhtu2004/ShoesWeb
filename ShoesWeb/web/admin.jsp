<%-- 
    Document   : admin
    Created on : Feb 18, 2024, 10:34:39 PM
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
    if (auth!=null && auth.getRoleid() == 1) {
        request.setAttribute("auth", auth);
    }else{
    
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
                <%if (auth!=null && auth.getRoleid() == 1) {%>
                <a href="#"><h5>Dashboard</h5></a>
                <%}%>  
                <a class="nav-link" href="logout">Log out</a>
            </div>
            <div class="row">
                <%if (auth!=null && auth.getRoleid() == 1) {%>
                <%@include file="includes/asideadmin.jsp" %>
                <%} else{ %>
                <h1 style="color: red;">Access denied, please log out!!!</h1>
        <%}%>   
        <%if (auth!=null && auth.getRoleid() == 1) {%>
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    Number of products:
                                    
                                    ${totalProducts}
                                    
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    Number of users:
                                    <%if (auth!=null && auth.getRoleid() == 1) {%>
                                    ${totalUsers}
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                                <%}%>
            </div>
        </div>

        <%@include file="includes/footer.jsp" %>
    </body>
</body>
</html>
