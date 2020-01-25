<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.store.goodsstore.entities.Customer"%>
<%@page import="com.store.goodsstore.dto.OrganizationDto"%>
<%@page import="com.store.goodsstore.dto.GoodsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Goods</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">      
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>



        <style>
            #header {
                position: absolute;
                top: 10px;
                left: 50px;
            }

            #logout {
                position: absolute;
                top: 30px;
                right: 15px;
            }
            #list{
                position: relative;
                top: 100px;  
            }

            body {
                position: relative;
                top: 10px;                
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
                top: 290px;
                left: 10px;
            }

        </style>
    </head>
    <body>

        <%
            OrganizationDto dto = (OrganizationDto) session.getAttribute("orgdata");
            List<GoodsDto> goods = new ArrayList<>();
            List<Customer> customers = new ArrayList<>();
            List<Integer> pages = new ArrayList<>();
            if (request.getAttribute("goodsList") != null) {
                goods = (List<GoodsDto>) request.getAttribute("goodsList");
            }
            if (request.getAttribute("customers") != null) {
                customers = (List<Customer>) request.getAttribute("customers");
            }
            if (request.getAttribute("pageNumber") != null) {
                pages = (List<Integer>) request.getAttribute("pageNumber");
            }

            int id = (int) request.getAttribute("group");
        %>

        <div id="header">
            <h1> Goods </h1>  
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#addGoods">Add goods</button> 
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#editGoods">Edit goods</button> 
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#createIncome">Income goods</button>
            
            <a href="/show" class="btn btn-info mr-1" role="button">Show order</a>
            
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#saleGoods">Sale goods</button>             

        </div>
        <div class="container-fluid" id="list">
            <div class="row">
                <div class="col-sm-12 col-8">
                    <div id="section1" class="bg-default">                                                
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

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    for (GoodsDto g : goods) {
                                        out.print("<tr>");
                                        out.print("<td>" + g.getName() + "</td>");
                                        out.print("<td>" + g.getCode() + "</td>");
                                        out.print("<td>" + g.getUnit() + "</td>");
                                        out.print("<td>" + g.getQuantity() + "</td>");
                                        out.print("<td>" + g.getIncomePrice() + "</td>");
                                        out.print("<td>" + g.getPrice() + "</td>");
                                        out.print("<td> <a href=\"/addToOrder/" + dto.getOrgCode() + "/" + g.getCode() + "\"class=\"btn btn-info mr-1\" role=\"button\">Add to order</a></td>");
                                        out.print("<td> <a href=\"/removeGoods/" + dto.getOrgCode() + "/" + (int) request.getAttribute("group") + "/" + g.getCode() + "\" class=\"btn btn-info mr-1\" role=\"button\">Delete</a></td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>

                        </table>

                        <ul class="pagination justify-content-center">                           

                            <%
                                if (!pages.isEmpty()) {
                                    for (Integer i : pages) {
                                        out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + i + "\">" + i + "</a></li>");
                                    }
                                }

                            %>

                        </ul>  
                    </div>
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
                        <form id="addGoodsForm" modelAtribut="goods" role="form" method="POST" action="/saveGoods">
                            <%                                int groupId = (int) request.getAttribute("group");
                                out.print("<input type=\"hidden\" name=\"groupId\" value=\"" + groupId + "\">");
                                out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                            %>                                    

                            <div class="form-group">
                                <label for="group">Name:</label>
                                <input type="text" class="form-control" id="goodsname" name="name" required="true">
                            </div>
                            <div class="form-group">
                                <label for="group">Code</label>
                                <input type="text" class="form-control" id="goodsunit" name="code" required="true">
                            </div>

                            <div class="form-group">
                                <label for="group">Unit</label>
                                <input type="text" class="form-control" id="goodsunit" name="unit" required="true">
                            </div>
                            <div class="form-group">
                                <label for="group">Quantity</label>
                                <input type="number" class="form-control" id="goodscount" name="quantity">
                            </div>
                            <div class="form-group">
                                <label for="group">Income price</label>
                                <input type="number" class="form-control" id="goodsincomePrice" name="incomePrice">
                            </div>
                            <div class="form-group">
                                <label for="group">Price</label>
                                <input type="number" class="form-control" id="goodsprice" name="price">
                            </div>                            
                            <button id="addgoodsSubmit" type="submit" onclick="addGoodsFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div> 

        <div name="editGoods" class="modal" id="editGoods">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Edit goods</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="editGoodsForm" role="form" method="POST" action="/editGoods">
                            <%
                                out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                            %>
                            <label for="goods">Select goods to edit</label>
                            <select name ="code" id="inputState" class="form-control">

                                <%
                                    for (GoodsDto g : goods) {
                                        out.print("<option selected value=\"" + g.getCode() + "\">" + g.getName() + "</option>");
                                    }
                                %>
                            </select> 
                            <%
                                out.print("<input type=\"hidden\" name=\"groupId\" value=\"" + groupId + "\">");
                            %>

                            <div class="form-group">
                                <label for="group">New name:</label>
                                <input type="text" class="form-control" id="goodsname" name="name" required="true">
                            </div>                          

                            <div class="form-group">
                                <label for="group">New price</label>
                                <input type="number" class="form-control" id="goodsprice" name="price" required="true">
                            </div>                            
                            <button id="addgoodsSubmit" type="submit" onclick="editGoodsFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div> 

        <div name="createIncomeDoc" class="modal" id="createIncome">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Income goods</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="createIncomeForm" role="form" modelAttribute="income" method="POST" action="/input">
                            <
                            <div class="form-group">
                                <%
                                    out.print("<input type=\"hidden\" name=\"groupId\" value=\"" + id + "\">");
                                    out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                                %>  
                            </div>  

                            <div class="form-group">
                                <label for="input">Customer</label>                                
                                <select name ="customer" id="inputState" class="form-control">
                                    <%
                                        for (Customer c : customers) {
                                            out.print("<option selected value=\"" + c.getPhoneNum() + "\">" + c.getName() + "</option>");
                                        }
                                    %>
                                </select> 
                            </div>
                            <div class="form-group">    
                                <select name ="goodsCode" id="inputState" class="form-control">

                                    <%
                                        for (GoodsDto g : goods) {
                                            out.print("<option selected value=\"" + g.getCode() + "\">" + g.getName() + "</option>");
                                        }
                                    %>
                                </select> 
                            </div>   
                            <div class="form-group">
                                <label for="group">Quantity</label>
                                <input type="number" class="form-control" id="quantity" name="quantity">
                            </div> 
                            <div class="form-group">
                                <label for="group">Income price</label>
                                <input type="number" class="form-control" id="incomePrice" name="incomePrice">
                            </div>
                            <button id="createIncome" type="submit" onclick="createIncomeFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div> 

        <div name="saleGoodsDoc" class="modal" id="saleGoods">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Sale goods</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="saleGoodsForm" role="form" modelAttribute="income" method="POST" action="/sale">

                            <div class="form-group">
                                <%
                                    out.print("<input type=\"hidden\" name=\"groupId\" value=\"" + id + "\">");
                                    out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                                %>  
                            </div>  
                            <div class="form-group">
                                <label for="input">Customer</label>                                
                                <select name ="customer" id="inputState" class="form-control">
                                    <%
                                        for (Customer c : customers) {
                                            out.print("<option selected value=\"" + c.getPhoneNum() + "\">" + c.getName() + "</option>");
                                        }
                                    %>
                                </select> 
                            </div>                            

                            <button id="saleGoodsSubmit" type="submit" onclick="saleGoodsFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div> 

        <%
            out.print(" <a href=\"/gostore/" + dto.getStoreCode() + "\"id=\"back\" class=\"btn btn-info mr-1\" role=\"button\">Back to groups</a>");
        %>



        <script>
            function editGoodsFunction() {
                document.getElementById("editGoodsForm").submit();
            }
            function addGoodsFunction() {
                document.getElementById("addGoodsForm").submit();
            }
            function deleteGoodsFunction() {
                document.getElementById("deleteGoodsForm").submit();
            }
            function createIncomeFunction() {
                document.getElementById("createIncomeForm").submit();
            }
            function saleGoodsFunction() {
                document.getElementById("saleGoodsForm").submit();
            }


        </script>

    </body>
</html>
