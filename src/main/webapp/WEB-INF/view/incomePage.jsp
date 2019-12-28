<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

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
            List<GoodsDto> goods = (List<GoodsDto>) request.getAttribute("goodsList");
            List<Customer> customers = (List<Customer>) request.getAttribute("customer");
            List<Integer> pages = (List<Integer>) request.getAttribute("pageNumber");
            int id = (int) request.getAttribute("group");
        %>

        <div id="header">
            <h1> Goods </h1>  
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#createIncome">Income goods</button> 
                                
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
                                        out.print("<td> <a href=\"allcustomer\" class=\"btn btn-info mr-1\" role=\"button\">Add to order</a></td>");
                                        out.print("<td> <a href=\"/GoodsStoreProject/removeGoods/" + (int) request.getAttribute("group") + "/" + g.getCode() + "\" class=\"btn btn-info mr-1\" role=\"button\">Delete</a></td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>

                        </table>

                        <ul class="pagination justify-content-center">                           

                            <%      for (Integer i : pages) {
                                    out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + i + "\">" + i + "</a></li>");
                                }
                            %>

                        </ul>  
                    </div>
                </div>
            </div>


        </div>

        <div name="createIncome" class="modal" id="addGoods">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Add new goods</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="createIncome" role="form" method="POST" action="/GoodsStoreProject/input">
                            <%
                                out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                            %>                                    

                            <div class="form-group">
                                <label for="input">Customer</label>                                
                                <select name ="code" id="inputState" class="form-control">

                                    <%
                                        for (Customer c : customers) {
                                            out.print("<option selected value=\"" + c.getPhoneNum() + "\">" + c.getName() + "</option>");
                                        }

                                        out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                                    %>
                                </select> 
                            </div>
                                

                            <button id="addgoodsSubmit" type="submit" onclick="createIncome()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div> 

        <a href="/GoodsStoreProject/startWork" id="back" class="btn btn-info mr-1" role="button">Back to groups</a>
        <script>
            function createIncome() {
                document.getElementById("createIncome").submit();
            }


        </script>

    </body>
</html>
