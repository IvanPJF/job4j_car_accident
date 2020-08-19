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
    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-md-end pt-2">
        <div class="col-3">
            Login as: <b>${user.username}</b>
        </div>
    </div>
    <div class="row justify-content-md-end pt-2">
        <div class="col-3">
            <a href="<c:url value='/logout'/>">Logout</a>
        </div>
    </div>
    <div class="row justify-content-md-center pt-3">
        <h2>List of all accidents</h2>
    </div>
    <div class="row justify-content-md-end">
        <div class="col-3">
            <a href="<c:url value='/create'/>">Добавить инцидент</a>
        </div>
    </div>
    <div class="row justify-content-md-center pt-5">
        <div class="col-md-auto">
            <form id="form" action="<c:url value='/edit'/>" method="get">
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Text</th>
                        <th scope="col">Address</th>
                        <th scope="col">Тип</th>
                        <th scope="col">Статьи</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accidents}" var="accident">
                        <tr>
                            <td><c:out value="${accident.name}"/></td>
                            <td><c:out value="${accident.text}"/></td>
                            <td><c:out value="${accident.address}"/></td>
                            <td><c:out value="${accident.type.name}"/></td>
                            <td>
                                <c:forEach items="${accident.rules}" var="rule">
                                    <c:out value="${rule.name}"/><br>
                                </c:forEach>
                            </td>
                            <td>
                                <button form="form" class="btn btn-warning"
                                        type="submit" name="id" value="<c:out value="${accident.id}"/>">edit
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
