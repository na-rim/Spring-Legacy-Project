<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">

    <div class="header">
        <h1>회원가입</h1>
        <div class="nav">
            <a class="btn ghost sm" href="${pageContext.request.contextPath}/tasks">Task Board</a>
            <a class="btn ghost sm" href="${pageContext.request.contextPath}/problems">개인 프로젝트 페이지로 이동</a>
        </div>
    </div>

    <div class="card">
        <c:if test="${not empty error}">
            <div style="margin-bottom:12px; color: var(--danger);">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <label class="label">아이디</label>
            <input class="input" name="username" autocomplete="username"/>

            <label class="label">비밀번호</label>
            <input class="input" name="password" type="password" autocomplete="new-password"/>
            <div class="muted" style="margin-top:6px;">* 8자 이상</div>

            <label class="label">비밀번호 확인</label>
            <input class="input" name="passwordConfirm" type="password" autocomplete="new-password"/>

            <div class="actions">
                <button class="btn" type="submit">가입</button>
                <a class="btn ghost" href="${pageContext.request.contextPath}/login">로그인</a>
            </div>
        </form>
    </div>

</div>
</body>
</html>
