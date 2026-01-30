<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Problem Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>
            <c:choose>
                <c:when test="${empty problem.id}">새 문제</c:when>
                <c:otherwise>문제 수정</c:otherwise>
            </c:choose>
        </h1>
        <a class="btn ghost" href="${pageContext.request.contextPath}/problems">목록</a>
    </div>

    <c:choose>
        <c:when test="${empty problem.id}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/problems"/>
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/problems/${problem.id}"/>
        </c:otherwise>
    </c:choose>

    <form method="post" action="${actionUrl}" class="card">
        <c:if test="${not empty problem.id}">
            <input type="hidden" name="id" value="${problem.id}"/>
        </c:if>

        <label class="label">제목</label>
        <input class="input" name="title" value="${problem.title}" required />

        <div class="grid2">
            <div>
                <label class="label">플랫폼</label>
                <input class="input" name="platform" value="${problem.platform}" placeholder="BOJ / Programmers" required />
            </div>
            <div>
                <label class="label">난이도</label>
                <select class="select" name="difficulty" required>
                    <option value="EASY" <c:if test="${problem.difficulty == 'EASY'}">selected</c:if>>EASY</option>
                    <option value="MEDIUM" <c:if test="${problem.difficulty == 'MEDIUM'}">selected</c:if>>MEDIUM</option>
                    <option value="HARD" <c:if test="${problem.difficulty == 'HARD'}">selected</c:if>>HARD</option>
                </select>
            </div>
        </div>

        <label class="label">링크</label>
        <input class="input" name="link" value="${problem.link}" placeholder="https://..." />

        <div class="grid2">
            <div>
                <label class="label">토픽</label>
                <input class="input" name="topic" value="${problem.topic}" placeholder="DP / Graph / Greedy" />
            </div>
            <div>
                <label class="label">상태</label>
                <select class="select" name="status">
                    <option value="TODO" <c:if test="${empty problem.status || problem.status == 'TODO'}">selected</c:if>>TODO</option>
                    <option value="SOLVED" <c:if test="${problem.status == 'SOLVED'}">selected</c:if>>SOLVED</option>
                    <option value="REVIEW" <c:if test="${problem.status == 'REVIEW'}">selected</c:if>>REVIEW</option>
                </select>
            </div>
        </div>

        <label class="label">메모(풀이 요약/실수 포인트)</label>
        <textarea class="textarea" name="memo" rows="6">${problem.memo}</textarea>

        <div class="actions">
            <button class="btn" type="submit">저장</button>
            <a class="btn ghost" href="${pageContext.request.contextPath}/problems">취소</a>
        </div>
    </form>
</div>
</body>
</html>
