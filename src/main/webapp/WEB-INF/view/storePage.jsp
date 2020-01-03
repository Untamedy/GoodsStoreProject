<%@page import="com.store.goodsstore.dto.GoodsDto"%>
<%@page import="com.store.goodsstore.dto.OrganizationDto"%>
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
        <title>Store</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
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
            #card-deck{
                position: fixed;
                top: 250px;
                left: 10px;
            }
            #back{
                position: relative;
                top: 500px;
                left: 10px;
            }
        </style>
    </head>      
    <body>   
        <%
            OrganizationDto dto = (OrganizationDto) session.getAttribute("orgdata");
            List<GoodsGroupDto> groups = (List<GoodsGroupDto>) request.getAttribute("groups");
        %>   

        <div class="jumbotron jumbotron-fluid">

            <div id="logout">
                <a href="logout" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-log-out"></span> Log out</a>
            </div>

            <h1>
                <img class="rounded-circle" src="https://cdn.pixabay.com/photo/2019/11/17/17/58/donuts-4633030_960_720.jpg" alt="dunat" width="72" height="72">

                <%
                    out.print("Store: " + dto.getStorename());
                %>
            </h1>

            <div id="header">
                <div class="btn-group">
                    <button type="button" class="btn btn-info">Goods groups</button>
                    <button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#addGroup" data-toggle="modal" data-target="#addGroup">Add group</a>
                        <a class="dropdown-item" href="#editGroup"data-toggle="modal" data-target="#editGroup">Edit group</a>
                        <a class="dropdown-item" href="#deleteGroup"data-toggle="modal" data-target="#deleteGroup">Remote group</a>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-info">Goods</button>
                    <button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#addGoods"data-toggle="modal" data-target="#addGoods">Add goods</a>                  
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
                <button type="button" class="btn btn-info">Customers</button>


            </div>

            <div class="container"> 
                <div class="card-columns" id="card-deck">                      
                    <%
                        for (GoodsGroupDto g : groups) {
                            out.print("<div class=\"card bg-light\">");
                            out.print("<div class=\"card-body text-center\">");
                            out.print("<a href=\"/GoodsStoreProject/goodslist/page/" + dto.getOrgCode() + "/" + g.getId() + "/1\" <i class=\"fa fa-shopping-cart\" aria-hidden=\"true\"></i>" + g.getName() + "</a>");
                            out.print("</div>");
                            out.print("</div>");

                        }
                    %>


                </div>

            </div>
        </div>





        <div name="addGroup" class="modal" id="addGroup">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Add new goods group</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="addGroupForm" role="form" method="POST" action="group/save">
                            <div class="form-group">
                                <label for="group">Name:</label>
                                <input type="text" class="form-control" id="groupName" name="groupName"required="true">
                                <%
                                    out.print("<input type=\"hidden\" name=\"storeCode\" value=\"" + dto.getStoreCode() + "\">");
                                %>
                            </div>
                            <button id="myFormSubmit" type="submit" onclick="addGroupFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div name="editGroup" class="modal" id="editGroup">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Edit group name</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="editGroupForm" role="form" method="Post" action="group/editGroup">
                            <div class="form-group">
                                <label for="group">Old name:</label>
                                <select id="inputState" name ="oldName" class="form-control" required="true">
                                    <%
                                        for (GoodsGroupDto g : groups) {
                                            out.print("<option selected name =\"oldName\"> " + g.getName() + "</option>");
                                        }
                                    %>
                                </select>
                                <label for="group">New name:</label>                               
                                <input type="text" class="form-control" id="groupName" name="newName" required="true">
                                <%
                                    out.print("<input type=\"hidden\" name=\"storeCode\" value=\"" + dto.getStoreCode() + "\">");
                                %>
                            </div>
                            <button id="editGroupForm" type="submit" onclick="editGroupFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div name="deleteGroup" class="modal" id="deleteGroup">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Edit group name</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="deleteGroupForm" role="form" method="Post" action="group/removedGroup">
                            <div class="form-group">
                                <label for="group">Group name:</label>
                                <select id="inputState" name ="name" class="form-control" required="true">
                                    <%
                                        for (GoodsGroupDto g : groups) {
                                            out.print("<option selected name =\"name\"> " + g.getName() + "</option>");
                                        }
                                    %>
                                </select>

                                <%
                                    out.print("<input type=\"hidden\" name=\"storeCode\" value=\"" + dto.getStoreCode() + "\">");
                                %>
                            </div>
                            <button id="deleteGroupForm" type="submit" onclick="deleteGroupFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div name="addGoods" class="modal" id="addGoods">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Add new goods</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="addGoodsForm" modelAtribut="goods" role="form" method="POST" action="saveGoods">
                            <label for="group">Group:</label>
                            <select name ="groupId" id="inputState" class="form-control" required="true">
                                <%
                                    for (GoodsGroupDto g : groups) {
                                        out.print("<option selected value=\"" + g.getId() + "\">" + g.getName()
                                                + "</option>");
                                    }
                                %>                                     
                            </select>
                            <div class="form-group">
                                <label for="group">Name:</label>
                                <input type="text" class="form-control" id="goodsname" name="name" required="true">
                            </div>
                            <div class="form-group">
                                <label for="group">Code</label>
                                <input type="text" class="form-control" id="goodscode" name="code" required="true">
                            </div>
                            <div class="form-group">
                                <label for="group">Unit</label>
                                <input type="text" class="form-control" id="goodsunit" name="unit" required="true">
                            </div>
                            <div class="form-group">
                                <label for="group">Quantity</label>
                                <input type="number" class="form-control" id="goodscount" name="quantity" default="0">
                            </div>
                            <div class="form-group">
                                <label for="group">Income price</label>
                                <input type="number" class="form-control" id="goodsincomePrice" name="incomePrice" default="0">
                            </div>
                            <div class="form-group">
                                <label for="group">Price</label>
                                <input type="number" class="form-control" id="goodsprice" name="price" default="0">
                            </div>                            
                            <button id="addGroupSubmit" type="submit" onclick="addGoodsFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
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
            function editGroupFunction() {
                document.getElementById("editGroupForm").submit();
            }

            function addGroupFunction() {
                document.getElementById("addGroupForm").submit();
            }

            function addGoodsFunction() {
                document.getElementById("addGoodsForm").submit();
            }
            function deleteGroupFunction() {
                document.getElementById("deleteGroupForm").submit();
            }

        </script>

        <a href="/GoodsStoreProject/startWork" id="back" class="btn btn-info mr-1" role="button">Back to start</a>

    </body>


</html>
