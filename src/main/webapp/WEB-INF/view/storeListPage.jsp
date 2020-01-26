<%@page import="com.store.goodsstore.entities.Users"%>
<%@page import="java.security.Principal"%>
<%@page import="com.store.goodsstore.dto.OrganizationDto"%>



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
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />



        <style>
            #header {
                position: absolute;
                top: 270px;

                left: 55px;
            }

            .active-cyan-3 input[type=text] {
                border: 1px solid #4dd0e1;
                box-shadow: 0 0 0 1px #4dd0e1;
            }

            #logout {
                position: absolute;               
                top: 30px;
                right: 15px;
            }
            * {
                box-sizing: border-box;
            }

            form.example input[type=text] {
                padding: 10px;
                font-size: 17px;
                border: 1px solid grey;
                float: left;
                width: 80%;
                background: #f1f1f1;
            }

            form.example button {
                float: left;
                width: 20%;
                padding: 10px;
                background: #2196F3;
                color: white;
                font-size: 17px;
                border: 1px solid grey;
                border-left: none;
                cursor: pointer;
            }

            form.example button:hover {
                background: #0b7dda;
            }

            form.example::after {
                content: "";
                clear: both;
                display: table;
            }
        </style>


    </head>
    <body>
        <%
            OrganizationDto dto = (OrganizationDto) request.getAttribute("orgdata");
            session.setAttribute("orgdata", dto);
        %>
        <div class="jumbotron jumbotron-fluid">

            <div id="logout">
                <a href="/logout" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-log-out"></span> Log out</a>
            </div>

            <h1>
                <img class="rounded-circle" src="https://cdn.pixabay.com/photo/2019/11/17/17/58/donuts-4633030_960_720.jpg" alt="dunat" width="72" height="72">
                Store page </h1>

            <div class="container">
                <h2>Search orders by customer</h2>
                <div class="active-cyan-3 active-cyan-4 mb-4">                    
                    <%
                        out.print(" <form class=\"example\" action=\"search/" + dto.getOrgCode() + "\">");
                    %>
                    <input type="text" placeholder="Customer phone" name="customer">
                    <button type="submit"><i class="fa fa-search"></i></button>
                    
                </div>
            </div>

            <div id="header">
                <div class="btn-group">  
                    <%
                        out.print("<a href=\"/gostore/" + dto.getStoreCode() + "\"class=\"btn btn-info mr-1\" role=\"button\">Go store</a>");
                        out.print("<a href=\"/allCustomer/page/" + dto.getOrgCode() +"/1" +"\"class=\"btn btn-info mr-1\" role=\"button\">Go to customer list</a>");

                    %>
                    <a class="btn btn-info mr-1" role="button" href="#fin"data-toggle="modal" data-target="#fin">Show fin report</a>

                </div>
            </div>
            <div name="fin" class="modal" id="fin">
                <div class="modal-dialog">
                    <div class="modal-content">               
                        <div class="modal-header">                        
                            <h4 class="modal-title">Fin report by period</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div> 
                        <div class="modal-body">
                            <form id="finDocForm" role="form" method="Post" action="finReport">
                                <%                                     
                                    out.print("<input type=\"hidden\" name=\"orgCode\" value=\"" + dto.getOrgCode() + "\">");
                                %>
                                <div class="form-group">
                                    <label for="group">Date from:</label>
                                    <input id="datepicker1" name="dateFrom" width="276" />

                                </div>
                                <div class="form-group">
                                    <label for="group">Date to</label>
                                    <input id="datepicker2" name="dateTo" width="276" />

                                </div>

                                <button id="finReportForm" type="submit" onclick="finDocFunction()" class="btn btn-success" data-dismiss="modal">Submit</button>
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
                function finDocFunction() {
                    document.getElementById("finDocForm").submit();
                }

                $('#datepicker1').datepicker({
                    uiLibrary: 'bootstrap4'
                });
                $('#datepicker2').datepicker({
                    uiLibrary: 'bootstrap4'
                });
            </script>

    </body>
</html>
