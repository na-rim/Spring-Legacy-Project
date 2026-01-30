<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>관리자 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">
    <h2>User Management</h2>

    <p>
        이 페이지는 <b>관리자 전용</b>입니다.
    </p>

    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>사용자명</th>
            <th>권한</th>
            <th>생성일시</th>
            <th>상태</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.role}</td>
                <td>${u.createdAt}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/users/${u.id}/role">
                        <select name="role">
                            <option value="USER" <c:if test="${u.role == 'USER'}">selected</c:if>>USER</option>
                            <option value="ADMIN" <c:if test="${u.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
                        </select>
                        <button type="submit">Change</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p><a href="${pageContext.request.contextPath}/tasks">Back to Tasks</a></p>

</div>
</body>
</html>
