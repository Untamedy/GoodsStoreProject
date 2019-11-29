<%-- 
    Document   : error
    Created on : Nov 26, 2019, 7:55:43 PM
    Author     : YBolshakova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error page</title>
    </head>
    <body>
        <h1>Error</h1>
      <p>Application has encountered an error. Please contact support on ...</p>
      Exception= ${exception.message}<br><br>     
            
      <p>Try again ${url};</p>
           
     
    </body>
</html>
