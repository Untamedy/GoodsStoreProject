<%-- 
    Document   : successPage
    Created on : Nov 5, 2019, 1:42:10 PM
    Author     : YBolshakova
--%>

<%@page import="com.store.goodsstore.dto.OrganizationDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>

    </head>
    <body>
        <!DOCTYPE html>
    <html lang="en">
        <head>
            <!-- Theme Made By www.w3schools.com - No Copyright -->
            <title>Start page</title>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

            <style>
                .bg-1 { 
                    background-color: #1abc9c; /* Green */
                    color: #ffffff;
                }
                .bg-2 { 
                    background-color: #474e5d; /* Dark Blue */

                    color: #ffffff;
                }
                .bg-3 { 
                    background-color: #fff; /* White */
                    color: #555555;
                }
            </style>
        </head>
        <body>
        <security:authorize access="isAuthenticated()">
            <security:authentication property="principal.username" /> 
        </security:authorize>

        <div class="container-fluid bg-1 text-center">
            <h1>Portable Store</h1>  
            <img src="https://cdn.pixabay.com/photo/2019/11/17/17/58/donuts-4633030_960_720.jpg" class="img-circle" alt="Dunat" width="350" height="350">
            <h3>This app will help you gain control of your small business</h3>
        </div>

        <div class="container-fluid bg-2 text-center">             
            <p><h1>Hello! <security:authentication property="principal.username" /> </h1> </p>
        <a href="startWork" class="btn btn-default btn-lg">Go work</a>

        <p> </p>
    </div>

</body>
</html>



