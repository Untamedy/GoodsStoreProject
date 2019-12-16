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
        </style>
    </head>
    <body>
        
        
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12 col-8">
                    <div id="section1" class="bg-default">
                        <h1> Customers:
                        </h1>
                        <p></p>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Phone</th>
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
                                            out.print("<td>" + g.getCode()+ "</td>");  
                                            out.print("<td><button type=\"button\" class=\"btn btn-info\">Delete</button></td>");
                                            out.print("<td><button type=\"button\" class=\"btn btn-info\">Edit</button></td>");
                                            out.print("<tr>");
                                        }
                                    %>
                                
                            </tbody>
                            <%
                                List<Integer> pages = (List<Integer>) request.getAttribute("pageNumber");
                                int id = (int) request.getAttribute("group");

                            %>
                        </table>

                        <ul class="pagination">
                            <li class="page-item">
                                <%      for (Integer i : pages) {
                                        out.print("<a class=\"page-link\" href=\""+ i + "\">"+i+ "</a>");
                                    }
                                %>

                            </li>

                        </ul>

                    </div>

                </div>
            </div>

        </div>
                                
                                div name="addGroup" class="modal" id="addGroup">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Add new goods group</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="addCustmForm" role="form" method="POST" action="customer/save">
                            <div class="form-group">
                                <label for="group">Name:</label>
                                <input type="text" class="form-control" id="groupName" name="groupName">
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
    </body>
</html>
