<%--
    Document   : EditGoodsPage
    Created on : Nov 5, 2019, 1:39:45 PM
    Author     : YBolshakova
--%>

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
        </style>
    </head>
    <body>
        <%
            OrganizationDto dto = (OrganizationDto) session.getAttribute("orgData");
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
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
                                    for (Customer c : customers) {
                                        out.print("<tr>");
                                        out.print("<td>" + c.getName() + "</td>");
                                        out.print("<td>" + c.getPhoneNum() + "</td>");
                                        out.print("<td><a href=\"customer/delete/" + c.getPhoneNum() + "/" + c.getOrg().getCode() + "class=\"btn btn-default\">Go to Google</a></td>");
                                        out.print("<td><a href=\"customer/delete/" + c.getPhoneNum() + "/" + c.getOrg().getCode() + "class=\"btn btn-default\">Go to Google</a></td>");
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
                                        out.print("<a class=\"page-link\" href=\"" + i + "\">" + i + "</a>");
                                    }
                                %>
                            </li>
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
                                <label for="group">Name:</label>
                                <input type="text" class="form-control" id="groupName" name="name">
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

        <script>
            function addCustomerFunction() {
                document.getElementById("addCustmForm").submit();
            }
        </script>               
    </body>

</html>
