<%@page import="com.store.goodsstore.dto.OrganizationDto"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Store</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script type="text/javascript" 
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

        <style>
             #header {
                position: absolute;
                top: 270px;

                left: 55px;
            }

            .active-cyan-3 input[type=text] {
                border: 1px solid #4dd0e1;
                box-shadow: 0 0 0 1px #4dd0e1;
            }

            #logout {
                position: absolute;               
                top: 30px;
                right: 15px;
            }
            * {
                box-sizing: border-box;
            }

            form.example input[type=text] {
                padding: 10px;
                font-size: 17px;
                border: 1px solid grey;
                float: left;
                width: 80%;
                background: #f1f1f1;
            }

            form.example button {
                float: left;
                width: 20%;
                padding: 10px;
                background: #2196F3;
                color: white;
                font-size: 17px;
                border: 1px solid grey;
                border-left: none;
                cursor: pointer;
            }

            form.example button:hover {
                background: #0b7dda;
            }

            form.example::after {
                content: "";
                clear: both;
                display: table;
            }
        </style>


    </head>
    <body>

        <div class="jumbotron jumbotron-fluid">

            <div id="logout">
                <a href="logout" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-log-out"></span> Log out</a>
            </div>

            <h1>
                <img class="rounded-circle" src="https://cdn.pixabay.com/photo/2019/11/17/17/58/donuts-4633030_960_720.jpg" alt="dunat" width="72" height="72">
                Store page </h1>

            <div class="container">
                <h2>Search store by name</h2>
                <div class="active-cyan-3 active-cyan-4 mb-4">
                    <form class="example" action="#">
                        <input type="text" placeholder="Search.." name="search">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>

            <div id="header">
                <div class="btn-group">                    
                    <a href="gostore" class="btn btn-info mr-1" role="button">Go store</a>             
                    <a href="allcustomer" class="btn btn-info mr-1" role="button">Go to customer list</a>
                    <a href="allreports" class="btn btn-info mr-1" role="button">Show reports</a>
                </div>
            </div>


    </body>
</html>
