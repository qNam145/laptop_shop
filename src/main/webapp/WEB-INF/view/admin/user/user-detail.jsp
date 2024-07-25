<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Detail</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 col-12 mx-auto">
            <h3 style="text-align: center">User detail</h3>
            <hr />
            <div class="card" style="width: 20rem;">
                <div class="card-header" style="text-align: center">
                    ID: ${id}
                </div>
                <ul class="list-group list-group-flush" style="text-align: center">
                    <li class="list-group-item">
                        ${user.fullName}
                    </li>
                    <li class="list-group-item">
                        ${user.email}
                    </li>
                    <li class="list-group-item">
                        ${user.password}
                    </li>
                    <li class="list-group-item">
                        ${user.address}
                    </li>
                    <li class="list-group-item">
                        ${user.phone}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>