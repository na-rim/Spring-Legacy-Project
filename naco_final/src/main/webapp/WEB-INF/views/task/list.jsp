<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Tasks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">

    <div class="header">
        <h1>Task Board</h1>

        <div class="nav">
            <c:choose>
                <c:when test="${not empty sessionScope.LOGIN_USER}">
                    <span>안녕하세요, <b>${sessionScope.LOGIN_USER.username}</b></span>

                    <c:if test="${sessionScope.LOGIN_USER.role == 'ADMIN'}">
                        <a class="btn ghost sm" href="${pageContext.request.contextPath}/admin/users">관리자</a>
                    </c:if>

                    <a class="btn ghost sm" href="${pageContext.request.contextPath}/logout">로그아웃</a>
                </c:when>
                <c:otherwise>
                    <a class="btn sm" href="${pageContext.request.contextPath}/login">로그인</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/tasks" method="post" class="filters">
            <input class="input" name="title" placeholder="새 할 일"/>
            <button class="btn" type="submit">추가</button>
            <a class="btn ghost" href="${pageContext.request.contextPath}/problems">개인 프로젝트 페이지로 이동</a>
        </form>

        <table class="table">
            <thead>
            <tr>
                <th style="width:80px;">상태</th>
                <th>제목</th>
                <th style="width:180px;">생성일</th>
                <th style="width:220px;">액션</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="t" items="${tasks}">
                <tr>
                    <td style="text-align:center;">
                        <c:if test="${t.done}">✅</c:if>
                        <c:if test="${not t.done}">⬜</c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${t.done}"><s>${t.title}</s></c:when>
                            <c:otherwise>${t.title}</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${t.createdAt}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/tasks/${t.id}/toggle" method="post" style="display:inline;">
                            <button class="btn ghost sm" type="submit">토글</button>
                        </form>

                        <a class="btn ghost sm" href="${pageContext.request.contextPath}/tasks/${t.id}/edit">수정</a>

                        <form action="${pageContext.request.contextPath}/tasks/${t.id}/delete" method="post" style="display:inline;">
                            <button class="btn danger sm" type="submit" onclick="return confirm('삭제할까요?');">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="actions">
            <a class="btn ghost" href="${pageContext.request.contextPath}/problems">개인 프로젝트 페이지로 이동</a>
        </div>
    </div>

</div>
</body>
</html>
