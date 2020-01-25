<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

<%@page import="com.store.goodsstore.dto.CustomerDto"%>
<%@page import="com.store.goodsstore.entities.Customer"%>
<%@page import="com.store.goodsstore.dto.OrganizationDto"%>
<%@page import="com.store.goodsstore.dto.GoodsDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                top: 170px;
                left: 20px;
            }
        </style>
    </head>
    <body>
        <%
            OrganizationDto dto = (OrganizationDto) session.getAttribute("orgdata");
        %>
        <div id="header">
            <h1> Select customer </h1>  
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#addCustomer">Add customer</button> 
            <button type="button" class="btn btn-info mr-1" data-toggle="modal" data-target="#editCustomer">Edit goods</button>  
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
                                    <th>Phone</th>
                                    <th></th>                                    

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    List<CustomerDto> customers = (List<CustomerDto>) request.getAttribute("customerList");
                                    for (CustomerDto c : customers) {
                                        out.print("<tr>");
                                        out.print("<td>" + c.getName() + "</td>");
                                        out.print("<td>" + c.getPhone() + "</td>");
                                        out.print("<td><a href=\"/incomeCreateDoc/" + c.getPhone() + "/" + c.getOrgCode() + "\" class=\"btn btn-default\">Select</a></td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>
                            <%
                                List<Integer> pages = (List<Integer>) request.getAttribute("pageNumber");

                            %>
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

        
       
        <a href="/startWork" id="back" class="btn btn-info mr-1" role="button">Back to start</a>                  

                  
    </body>

</html>
