<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Screen</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<form action="customerList" method="get">
<br>
	<div class="container col-md-4">
    	<div class="card">
        	<div class="card-body">
        		<caption>
                	<h3 class="ml-3">
                	<c:if test="${customer == null}">Login Page</c:if>
                	</h3>
                	<br>
                </caption>
                <div class="form col">
						<fieldset class="form-group row-md-6">
                            <label>Login Id</label>
                            <input type="text" class="form-control" name="loginid" required="required">
                         </fieldset>
						<fieldset class="form-group row-md-6">
                            <label>Password</label>
                            <input type="password" class="form-control" name="password" required="required">
                         </fieldset>                            
                </div>
                <button class="btn btn-primary ml-3" type="submit">Submit</button>
        	</div>
        </div>
    </div>
</body>
</html>