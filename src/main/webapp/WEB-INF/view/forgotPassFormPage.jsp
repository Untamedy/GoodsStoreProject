<!DOCTYPE html>
<html lang="en">
    <head>

        <title>SignUp form</title>
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
            String email = (String) request.getAttribute("email");
        %>

        <div class="container">

            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                    <form  method="Post" action="registration" modelAtribut = "regForm">
                        <h2 class="text-center">Restore password</h2>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">                               

                                <div class="form-group">
                                    <%
                                        out.print("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
                                    %>  
                                </div>
                                <div class="form-group">
                                    <input type="password" name="newPass" id="newPass" class="form-control input-lg" placeholder="New password" required>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12 col-md-6"><input type="submit" value="Registration" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                                    <div class="col-xs-12 col-md-6"><a href="login" class="btn btn-success btn-block btn-lg">Sign In</a></div>
                                </div>

                            </div>
                        </div>          
                    </form>
                </div>
            </div>
        </div>
    </body>  



</html>