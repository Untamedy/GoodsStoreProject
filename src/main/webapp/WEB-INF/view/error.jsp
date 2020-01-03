<%-- 
    Document   : error
    Created on : Nov 26, 2019, 7:55:43 PM
    Author     : YBolshakova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error page</title>
    </head>
    <body>
        <h1>Error</h1>
        <p>Application has encountered an error.</p>
        <%
            Exception ex =(Exception) request.getAttribute("exception");
            String message = ex.getMessage();
            if(message!=null){
                 out.print("<p>Error couse " + message +"</p>");
            }
            else{
                out.print("Oops...Somethink was wrong. Ask to program developer about our problem");
                
            }
                        
        %>
        
        <button type="button" name="back" onclick="history.back()">Go back</button>

          

    </body>
</html>
