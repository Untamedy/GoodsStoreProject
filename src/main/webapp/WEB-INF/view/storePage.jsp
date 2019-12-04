<%@page import="com.store.goodsstore.entities.GoodsGroup"%>
<%@page import="java.util.List"%>
<%@page import="com.store.goodsstore.dto.GoodsGroupDto"%>
<%@page import="com.store.goodsstore.entities.Store"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
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
                        <a class="dropdown-item" href="#addGroup" data-toggle="modal" data-target="#myModal">Add group</a>
                        <a class="dropdown-item" href="editGroup"data-toggle="modal" data-target="#editModal">Edit group</a>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-info">Goods</button>
                    <button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Add goods</a>                       
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
        <div class="container">
            <ul class="nav">
                <c:forEach items="${groups}" var="group"> 
                    <li class="nav-item"><a class="nav-link active"href="#section1"><c:out value="${group.name}"/></a></li>
                    </c:forEach>                  
            </ul>
        </div>
    <body data-spy="scroll" data-target="#myScrollspy" data-offset="1">

        <div class="container-fluid">     


            <div class="row">                    

                <div class="col-sm-12 col-8">               

                    <div id="section1" class="bg-default"> 
                        <c:forEach items="${groups}" var="group">    
                            <h1> <c:out value="${group.name}"/></h1>
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
                                        <c:forEach items="${groups.goods}" var="goods">   
                                            <td><c:out value="${goods.name}"/></td>
                                            <td><c:out value="${goods.code}"/></td>
                                            <td><c:out value="${goods.unit}"/></td>
                                            <td><c:out value="${goods.quantity}"/></td>
                                            <td><c:out value="${goods.incomePrice}"/></td>
                                            <td><c:out value="${group.price}"/></td>
                                            <td><button type="button" class="btn btn-info">Add to order</button></td>
                                            <td><button type="button" class="btn btn-info">Delete</button></td>
                                            <td><button type="button" class="btn btn-info">Edit</button></td>                                    
                                        </c:forEach>
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
                        </c:forEach>    
                    </div>                  

                </div>
            </div>

        </div>


        <div name="addGroup" class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Add new goods group</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="myForm" role="form" method="Get" action="group/save">
                            <div class="form-group">
                                <label for="group">Name:</label>
                                <input type="text" class="form-control" id="groupName" name="groupName">
                            </div>
                            <button id="myFormSubmit" type="submit" onclick="myFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div name="editGroup" class="modal" id="editModal">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Edit group name</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="myForm" role="form" method="Get" action="group/save">
                            <div class="form-group">
                                <label for="group">Old name:</label>
                                <%

                                %>
                                <input type="text" class="form-control" id="groupName" name="groupName">
                            </div>
                            <button id="myFormSubmit" type="submit" onclick="myFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>


        <script>
            function myFunction() {
                document.getElementById("myForm").submit();
            }
        </script>



    </body>


</html>
