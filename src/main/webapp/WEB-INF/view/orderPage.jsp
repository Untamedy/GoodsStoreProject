<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

<%@page import="com.store.goodsstore.dto.OrderDto"%>
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
            OrderDto orderDto = (OrderDto) request.getAttribute("order");
            
        %>

        <div id="header">
            <h1> Order </h1>            

        </div>
        <div class="container-fluid" id="list">
            <div class="row">
                <div class="col-sm-12 col-8">
                    <div id="section1" class="bg-default">                                                
                        <p></p>
                        <%
                        out.print("<h3>Organization: "+ orderDto.getOrgName()+"</h3>");
                        out.print("<h3>Customer: "+ orderDto.getCustomerName()+" "+ orderDto.getCustomerPhone()+"</h3>");
                        out.print("<h3>Customer:"+ orderDto.getCustomerName()+"</h3>");
                        %>
                        
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Code</th>
                                    <th>Unit</th>
                                    <th>Count</th>                                    
                                    <th>Price</th>                                                                        

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    for (GoodsDto g : orderDto.getGoods()) {
                                        out.print("<tr>");
                                        out.print("<td>" + g.getName() + "</td>");
                                        out.print("<td>" + g.getCode() + "</td>");
                                        out.print("<td>" + g.getUnit() + "</td>");
                                        out.print("<td>1</td>");
                                        out.print("<td>" + g.getPrice() + "</td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>

                        </table>


                    </div>
                </div>
            </div>


        </div>





        <a href="/GoodsStoreProject/startWork" id="back" class="btn btn-info mr-1" role="button">Back to groups</a>



    </body>
</html>
