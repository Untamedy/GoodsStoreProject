<!DOCTYPE html>

<html lang="en">
    <head>       
        <title></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


        <style>  
            #header {
                position: absolute;
                top: 150px;

                left: 50px;
            }

            #logout {
                position: absolute;               
                top: 30px;
                right: 15px;
            }

            body {
                position: relative;
            }
            ul.nav-pills {
                top: 20px;
                position: fixed;
            }
            div.col-8 div {
                height: 500px;
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

            <div id="header">

               
                <div class="btn-group">
                    <button type="button" class="btn btn-info">Goods groups</button>
                    <button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Add group</a>
                        <a class="dropdown-item" href="#">Edit group</a>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-info">Goods</button>
                    <button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Add goods</a>
                        <a class="dropdown-item" href="#">Edit goods</a>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                        Reports
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Input report</a>
                        <a class="dropdown-item" href="#">Sale report</a>
                        <a class="dropdown-item" href="#">Fin report</a>
                    </div>
                </div>

                <button type="button" class="btn btn-info">Income goods</button>
                <button type="button" class="btn btn-info">Sale goods</button>
                <button type="button" class="btn btn-info">Customers</button>


            </div>

        </div>

    <body data-spy="scroll" data-target="#myScrollspy" data-offset="1">

        <div class="container-fluid">
            <div class="row">
                <nav class="col-sm-3 col-4" id="myScrollspy">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#section1">Candies</a>
                        </li> 
                         <li class="nav-item">
                            <a class="nav-link active" href="#section2">Fruits</a>
                        </li>
                    </ul>
                </nav>
                <div class="col-sm-9 col-8">
                    <div id="section1" class="bg-default">    
                        <h1>Candies</h1>
                        <p></p>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Code</th>
                                    <th>Unit</th>
                                    <th>Count</th>
                                    <th>Income price</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Candy</td>
                                    <td>2314</td>
                                    <td>kg</td>
                                    <td>10</td>
                                    <td>50</td>
                                    <td>100</td>
                                    <td><button type="button" class="btn btn-info">Add to order</button></td>
                                    <td><button type="button" class="btn btn-info">Delete</button></td>
                                    <td><button type="button" class="btn btn-info">Edit</button></td>                                    

                                </tr>
                               <tr>
                                    <td>Candy</td>
                                    <td>2314</td>
                                    <td>kg</td>
                                    <td>10</td>
                                    <td>50</td>
                                    <td>100</td>
                                    <td><button type="button" class="btn btn-info">Add to order</button></td>
                                    <td><button type="button" class="btn btn-info">Delete</button></td>
                                    <td><button type="button" class="btn btn-info">Edit</button></td>                                    

                                </tr>
                                <tr>
                                    <td>Candy</td>
                                    <td>2314</td>
                                    <td>kg</td>
                                    <td>10</td>
                                    <td>50</td>
                                    <td>100</td>
                                    <td><button type="button" class="btn btn-info">Add to order</button></td>
                                    <td><button type="button" class="btn btn-info">Delete</button></td>
                                    <td><button type="button" class="btn btn-info">Edit</button></td>                                    

                                </tr>
                                <tr>
                                    <td>Candy</td>
                                    <td>2314</td>
                                    <td>kg</td>
                                    <td>10</td>
                                    <td>50</td>
                                    <td>100</td>
                                    <td><button type="button" class="btn btn-info">Add to order</button></td>
                                    <td><button type="button" class="btn btn-info">Delete</button></td>
                                    <td><button type="button" class="btn btn-info">Edit</button></td>                                    

                                </tr>
                               <tr>
                                    <td>Candy</td>
                                    <td>2314</td>
                                    <td>kg</td>
                                    <td>10</td>
                                    <td>50</td>
                                    <td>100</td>
                                    <td><button type="button" class="btn btn-info">Add to order</button></td>
                                    <td><button type="button" class="btn btn-info">Delete</button></td>
                                    <td><button type="button" class="btn btn-info">Edit</button></td>                                    

                                </tr>
                              
                            </tbody>
                        </table>

                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </div>                  

                </div>
            </div>
        </div>





    </body>
</html>
