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
                top: 260px;
                left: 10px;
            }
        </style>
    </head>
    <body>
        <%
            OrganizationDto dto = (OrganizationDto) session.getAttribute("orgdata");          
        %>



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

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    List<CustomerDto> customers = (List<CustomerDto>) request.getAttribute("customerList");
                                    for (CustomerDto c : customers) {
                                        out.print("<tr>");
                                        out.print("<td>" + c.getName() + "</td>");
                                        out.print("<td>" + c.getPhone() + "</td>");
                                        out.print("<td><a href=\"customer/delete/" + c.getPhone() + "/" + c.getOrgCode() + "class=\"btn btn-default\">Delete</a></td>");
                                        out.print("<tr>");
                                    }
                                %>

                            </tbody>
                            <%
                                List<Integer> pages = (List<Integer>) request.getAttribute("pageNumber");

                            %>
                        </table>

                        <ul class="pagination">

                            <%      for (Integer i : pages) {
                                    out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + i + "\">" + i + "</a></li>");
                                }
                            %>

                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <div name="addCustomer" class="modal" id="addCustomer">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Add new customer</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="addCustmForm" role="form" method="POST" action="customer/save">
                            <div class="form-group">
                                <label for="customer">Name:</label>
                                <input type="text" class="form-control" id="customerName" name="name">
                                <%
                                    out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                                %>
                            </div>
                            <button id="myFormSubmit" type="submit" onclick="addCustomerFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div name="editCustomer" class="modal" id="editCustomer">
            <div class="modal-dialog">
                <div class="modal-content">               
                    <div class="modal-header">                        
                        <h4 class="modal-title">Edit customer</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div> 
                    <div class="modal-body">
                        <form id="editCustmForm" role="form" method="POST" action="customer/save">
                            <div class="form-group">
                                <label for="goods">Select customer to edit</label>
                                <select name ="phoneNum" id="inputState" class="form-control">
                                    <%
                                        for (CustomerDto c : customers) {
                                            out.print("<option selected value=\"" + c.getPhone() + "\">" + c.getName() + "</option>");
                                        }
                                    %>
                                </select>                                 
                                <label for="customreName">New name:</label>
                                <input type="text" class="form-control" id="name" name="name">                                
                                <%
                                    out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                                %>
                            </div>
                            <button id="myFormSubmit" type="submit" onclick="editCustomerFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
                            <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <a href="/GoodsStoreProject/startWork" id="back" class="btn btn-info mr-1" role="button">Back to start</a>                  

        <script>
            function addCustomerFunction() {
                document.getElementById("addCustmForm").submit();
            }
            function editCustomerFunction() {
                document.getElementById("editCustmForm").submit();
            }
        </script>               
    </body>

</html>
