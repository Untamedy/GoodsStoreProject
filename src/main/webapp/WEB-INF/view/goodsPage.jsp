<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

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
            
        </style>
    </head>
    <body>
        
         <div id="header">
             <h1> Goods </h1>  
                <a href="" class="btn btn-info mr-1" role="button">Income goods</a>
               <a href="allcustomer" class="btn btn-info mr-1" role="button">Sale goods</a>
                <a href="allcustomer" class="btn btn-info mr-1" role="button">Add goods</a>
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
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    List<GoodsDto> goods = (List<GoodsDto>) request.getAttribute("goodsList");
                                    for (GoodsDto g : goods) {
                                        out.print("<tr>");
                                        out.print("<td>" + g.getName() + "</td>");
                                        out.print("<td>" + g.getCode() + "</td>");
                                        out.print("<td>" + g.getUnit() + "</td>");
                                        out.print("<td>" + g.getQuantity() + "</td>");
                                        out.print("<td>" + g.getIncomePrice() + "</td>");
                                        out.print("<td>" + g.getPrice() + "</td>");
                                        out.print("<td> <a href=\"allcustomer\" class=\"btn btn-info mr-1\" role=\"button\">Add to order</a></td>");
                                        out.print("<td> <a href=\"/GoodsStoreProject/removeGoods/"+(int)request.getAttribute("group")+"/"+g.getCode()+"\" class=\"btn btn-info mr-1\" role=\"button\">Delete</a></td>");
                                        out.print("<td> <a href=\"GoodsStoreProject/editGoods\" class=\"btn btn-info mr-1\" role=\"button\">edit</a></td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>
                            <%
                                List<Integer> pages = (List<Integer>) request.getAttribute("pageNumber");
                                int id = (int) request.getAttribute("group");

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


    </body>
</html>
