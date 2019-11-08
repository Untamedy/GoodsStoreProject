<%-- 
    Document   : login
    Created on : Nov 6, 2019, 7:17:23 PM
    Author     : YBolshakova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    </head>
    <body>
        <form th:action="@{/}" method="get">
		<button class="btn btn-md btn-warming btn-block" type="Submit">Go to login page</button>
		</form>
		
		<div class="container">
		<div class="row>>
		<div class="col-md-offset-3">
		<form autocomplete="off" action="#" th:action="@{/registration}" th:object="${user}" method="post" class="form-horizontal" role="form">
		<h2>Registration form</h2>
		<div class="form-group">
		<div class="col-sm-9">
		<label th:if="${#fields.hasError('name')}" th:errors="*{name}" class="validation-message"></label>
		<input type="text" th:field="*{name}" placeholder="Name" class="form-control"/>		
		</div>
		</div>
		
		<div class="form-group">
		<div class="col-sm-9">
		<label th:if="${#fields.hasError('lastname)}" th:errors="*{lastname}" class="validation-message"></label>		
		<input type="text" th:field="*{lastname}" placeholder="Last name" class="form-control"/>
		</div>
		</div>
		
		<div class="form-group">
		<div class = "col-sm-9">
		<input type="text" th:field="*{email}" placeholder="Email" class=form-control"/>
		<label th:if="${#fields.hasError('email')} th:errors="*{email}" class="validation-message"></label>
		</div>
		</div>

        <div class="form-group">
		
<div class="col-sm-9">
<input type="password" th:field="*{password}" placeholder="Password" class="form-control"/>
<label th:if="${#fields.hasError('password')} th:errors="*{password}" class="validation-message"></label>
</div>
</div>		

<div class="form-group">
<div class="col-sm-9">
<button type="submit" class="btn btn-primary btn-block">Register User</button>
</div>
</div>

<span th:utex="${successMessage}"></span>

</form>	
</div>
</div>
</div>
			
    </body>
</html>
