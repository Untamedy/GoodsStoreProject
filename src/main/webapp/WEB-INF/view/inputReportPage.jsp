<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

<%@page import="com.store.goodsstore.dto.IncomeDocDto"%>
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
        <title>Income documents</title>
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
            List<IncomeDocDto> incomeDtos = (List<IncomeDocDto>) request.getAttribute("list");

        %>

        <div id="header">
            <%                out.print("<h1> Incom documents</h1>");
            %>                    

        </div>
        <div class="container-fluid" id="list">
            <div class="row">
                <div class="col-sm-12 col-8">
                    <div id="section1" class="bg-default">                                                
                        <p></p>                       

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Number</th>
                                    <th>Date</th>
                                    <th>Customer</th>
                                    <th>Goods</th>                                    
                                    <th>Quantity</th>   
                                    <th>Sum</th>

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    for (IncomeDocDto d : incomeDtos) {
                                        out.print("<tr>");
                                        out.print("<td>" + d.getNum() + "</td>");
                                        out.print("<td>" + d.getDate() + "</td>");
                                        out.print("<td>" + d.getCustomerName() + "</td>");
                                        out.print("<td>"+d.getGoodsName()+"</td>");
                                        out.print("<td>" + d.getQuantity() + "</td>");
                                        out.print("<td>" + d.getSum() + "</td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>

                        </table>


                    </div>
                </div>
            </div>

        </div>

        <a href="/startWork" id="back" class="btn btn-info mr-1" role="button">Back to groups</a>

    </body>
</html>
