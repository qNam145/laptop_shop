<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Table Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="col-md-6 col-12 mx-auto">
        <div style="display: flex; justify-content: space-between">
            <h3>Table Users</h3>
            <a href="/admin/user/create" class="btn btn-success">Create new user</a>
        </div>
        <hr>
        <table class="table table-striped align-middle" style="text-align: center">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Email</th>
                <th scope="col">Full Name</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.email}</td>
                    <td>${user.fullName}</td>
                    <td style="justify-content: space-between;">
                        <a href="/admin/user/detail-user=${user.id}" class="btn btn-primary">View</a>
                        <a href="/admin/user/update-user=${user.id}" class="btn btn-outline-success">Update</a>
                        <a href="/admin/user/delete-user=${user.id}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>