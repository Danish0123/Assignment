<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Add a new customer</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
            <br>

            <div class="container col-md-6">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${customer != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${customer == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h3>
                                <c:if test="${customer != null}">
                                    Edit Employee
                                </c:if>
                                <c:if test="${customer == null}">
                                    Customer Details
                                </c:if>
                            </h3>
                            <br>
                        </caption>

                        <c:if test="${customer != null}">
                            <input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
                        </c:if>
                        
						<div class="form row">
						<fieldset class="form-group col-md-6">
                            <label>First name</label>
                            <input type="text" value="<c:out value='${customer.firstname}' />" class="form-control" name="firstname" placeholder="First name" required="required">
                         </fieldset>
						<fieldset class="form-group col-md-6">
                            <label>Last name</label>
                            <input type="text" value="<c:out value='${customer.lastname}' />" class="form-control" name="lastname" placeholder="Last name" required="required">
                         </fieldset>                            
                        </div>
                        
                        <div class="form row">
						<fieldset class="form-group col-md-6">
                            <label>Street</label>
                            <input type="text" value="<c:out value='${customer.street}' />" class="form-control" name="street" placeholder="Street" required="required">
                         </fieldset>
						<fieldset class="form-group col-md-6">
                            <label>Address</label>
                            <input type="text" value="<c:out value='${customer.address}' />" class="form-control" name="address" placeholder="Address" required="required">
                         </fieldset>
                         </div>
                         
                         <div class="form row">
						<fieldset class="form-group col-md-6">
                            <label>City</label>
                            <input type="text" value="<c:out value='${customer.city}' />" class="form-control" name="city" placeholder="City" required="required">
                         </fieldset>
						<fieldset class="form-group col-md-6">
                            <label>Sate</label>
                            <input type="text" value="<c:out value='${customer.state}' />" class="form-control" name="state" placeholder="State" required="required">
                         </fieldset>
                         </div>

                        <div class="form row">
						<fieldset class="form-group col-md-6">
                            <label>Email</label>
                            <input type="text" value="<c:out value='${customer.email}' />" class="form-control" name="email" placeholder="Email" required="required">
                         </fieldset>
						<fieldset class="form-group col-md-6">
                            <label>Phone</label>
                            <input type="text" value="<c:out value='${customer.phone}' />" class="form-control" name="phone" placeholder="Phone" required="required">
                         </fieldset>
                         </div>
                         
                         <div class="d-grid gap-2 d-md-flex justify-content-md-end">
  						<button class="btn btn-primary" type="submit">Submit</button>
						</div>
                        </form>
                    </div>
                </div>
            </div>
            </form>
        </body>

     </html>