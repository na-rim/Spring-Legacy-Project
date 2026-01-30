<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Edit Task</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">

    <div class="header">
        <h1>Task 수정</h1>
        <div class="nav">
            <a class="btn ghost sm" href="${pageContext.request.contextPath}/tasks">목록</a>
            <a class="btn ghost sm" href="${pageContext.request.contextPath}/problems">개인 프로젝트 페이지로 이동</a>
        </div>
    </div>

    <div class="card">
        <form action="${pageContext.request.contextPath}/tasks/${task.id}/edit" method="post">
            <label class="label">제목</label>
            <input class="input" name="title" value="${task.title}" />

            <div style="margin-top:12px;">
                <label style="display:flex; align-items:center; gap:8px;">
                    <input type="checkbox" name="done" value="true" <c:if test="${task.done}">checked</c:if> />
                    완료
                </label>
            </div>

            <div class="actions">
                <button class="btn" type="submit">저장</button>
                <a class="btn ghost" href="${pageContext.request.contextPath}/tasks">취소</a>
            </div>
        </form>
    </div>

</div>
</body>
</html>
