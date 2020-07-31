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
    <title>Edit</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-md-center pt-5">
        <h2>Change accident details</h2>
    </div>
    <div class="row justify-content-center pt-5">
        <div class="col-auto">
            <form action="<c:url value='/save-edit'/>" method="post">
                <c:set value="${accident}" var="accident"/>
                <input type="hidden" name="id" value="<c:out value="${accident.id}"/>"/>
                <div class="form-group">
                    <label for="name">Название:</label>
                    <input type="text" class="form-control" id="name" name="name"
                           value="<c:out value="${accident.name}"/>"/>
                </div>
                <div class="form-group">
                    <label for="text">Описание:</label>
                    <input type="text" class="form-control" id="text" name="text"
                           value="<c:out value="${accident.text}"/>"/>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" name="address"
                           value="<c:out value="${accident.address}"/>"/>
                </div>
                <input type="submit" name="submit" class="btn btn-success" value="Сохранить"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>