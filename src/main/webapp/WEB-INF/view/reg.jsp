<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center pt-3">
        <div class="col-auto">
            <form name='reg' action="<c:url value='/reg'/>" method='POST'>
                <div class="form-group row">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username"/>
                </div>
                <div class="form-group row">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password"/>
                </div>
                <div class="row pt-3">
                    <input type="submit" name="submit" class="btn btn-success btn-block" value="save"/><br>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>