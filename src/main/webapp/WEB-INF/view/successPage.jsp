<%-- 
    Document   : successPage
    Created on : Nov 5, 2019, 1:42:10 PM
    Author     : YBolshakova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>

    </head>
    <body>

        <div class="container">
            <div class="row text-center">
                <div class="col-sm-6 col-sm-offset-3">
                    <br><br> <h2 style="color:#0fad00">Success</h2>
                    <img src="https://cdn.pixabay.com/photo/2019/11/17/17/58/donuts-4633030_960_720.jpg">
                    <%
                        String name = (String) request.getAttribute("userName");
                        out.print("<h1> Dear, " + name + "<h1>");
                        %>
                   
                    <p style="font-size:20px;color:#5C5C5C;">Thank you for registration!
                        Please go to login.</p>
                    <a href="login" class="btn btn-success"> Sign in </a>
                    <br><br>
                </div>

            </div>
        </div>

    </body>
</html>
