<!doctype html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../../../favicon.ico">

        <title>Signin</title>        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">      

        <style>
            body {
                height: 100%;
            }

            body {
                display: -ms-flexbox;
                display: -webkit-box;
                display: flex;
                -ms-flex-align: center;
                -ms-flex-pack: center;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: center;
                justify-content: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                width: 50%;
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
            }

            .form-signin .form-control {
                position: relative;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                font-size: 16px;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
            #signUp {
                position: absolute;               
                top: 30px;
                right: 15px;
            }

           
        </style>

    </head>

    <body class="text-center">
        <header>
            <a href="signup" id="signUp" class="btn btn-default btn-lg">SignUp</a>
        </header>
        <form class="form-signin" method="POST" action="dologin">
            <img class="mb-4" src="https://cdn.pixabay.com/photo/2019/11/17/17/58/donuts-4633030_960_720.jpg" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            <label for="username" class="sr-only">Email address</label>
            <input type="email"  name="username" class="form-control" placeholder="Email address" required autofocus>
            <label for="password" class="sr-only">Password</label>
            <input type="password"  name="password" class="form-control" placeholder="Password" required>

            <button class="btn btn-lg btn-default btn-block" type="submit">Sign in</button>  
             <a href="sendEmail" class="btn btn-link">Forgot password?</a> 
        </form>  
        
        
           
       </body>
</html>
