<%@page import="com.store.goodsstore.dto.OrganizationDto"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <title>Edit registration data</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>

        </style>
    </head>

    <body>
        <%
            OrganizationDto dto = (OrganizationDto) session.getAttribute("orgData");
        %>

        <div class="container">

            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                    <form method="Post" action="registration" modelAtribut="regForm">
                        <h2 class="text-center">Sign Up</h2>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <%
                                        out.print("<input type=\"text\" name=\"organizationName\" id=\"organizationName\" class=\"form-control input-lg\" placeholder=\"" + dto.getOrganizationName() + "\" tabindex=\"1\" required=\"true\">");
                                    %>                                    
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <%
                                            out.print("<input type=\"text\" name=\"organizationEmail\" id=\"organizationEmail\" class=\"form-control input-lg\" placeholder=\"" + dto.getOrganizationEmail() + "\" tabindex=\"2\" required=\"true\">");
                                        %>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <%
                                    out.print("<input type=\"text\" name=\"storeName\" id=\"storeName\" class=\"form-control input-lg\" placeholder=\"" + dto.getStorename() + "\" tabindex=\"3\" required=\"true\">");
                                %>                            
                            </div>
                            <div class="form-group">
                                <%
                                    out.print("<input type=\"text\" name=\"userName\" id=\"userName\" class=\"form-control input-lg\" placeholder=\"" + dto.getUserName() + "\" tabindex=\"4\" required=\"true\">");
                                %>                            
                            </div>                                               

                            <div class="row">
                                <div class="col-xs-12 col-md-6"><input type="submit" value="Edit data" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                                <div class="col-xs-12 col-md-6"><a href="gostore" class="btn btn-success btn-block btn-lg">Cancel</a></div>
                            </div>
                    </form>
                </div>
            </div>          

        </div>
    </div>
</body>  



</html>