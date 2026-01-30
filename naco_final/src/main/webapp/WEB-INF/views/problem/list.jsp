<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Problems</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>organize practice problems, track progress, and measure solving time</h1>
        <a class="btn" href="${pageContext.request.contextPath}/problems/new">+ 새 문제</a>
    </div>

    <form class="filters" method="get" action="${pageContext.request.contextPath}/problems">
        <input class="input" type="text" name="q" placeholder="검색(제목/메모)" value="${q}"/>

        <select class="select" name="status">
            <option value="">상태(전체)</option>
            <option value="TODO"  ${status=='TODO'?'selected':''}>TODO</option>
            <option value="SOLVED"${status=='SOLVED'?'selected':''}>SOLVED</option>
            <option value="REVIEW"${status=='REVIEW'?'selected':''}>REVIEW</option>
        </select>

        <select class="select" name="difficulty">
            <option value="">난이도(전체)</option>
            <option value="EASY" ${difficulty=='EASY'?'selected':''}>EASY</option>
            <option value="MEDIUM" ${difficulty=='MEDIUM'?'selected':''}>MEDIUM</option>
            <option value="HARD" ${difficulty=='HARD'?'selected':''}>HARD</option>
        </select>

        <input class="input" type="text" name="topic" placeholder="토픽(예: DP, Graph)" value="${topic}"/>
        <button class="btn" type="submit">필터</button>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th>제목</th>
            <th>플랫폼</th>
            <th>난이도</th>
            <th>토픽</th>
            <th>상태</th>
            <th>시간</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${items}">
            <tr>
                <td>
                    <div class="title-row">
                        <div class="title">${p.title}</div>
                        <c:if test="${not empty p.link}">
                            <a class="muted" target="_blank" href="${p.link}">link</a>
                        </c:if>
                    </div>
                    <c:if test="${not empty p.memo}">
                        <div class="muted clamp">${p.memo}</div>
                    </c:if>
                </td>
                <td>${p.platform}</td>
                <td><span class="pill">${p.difficulty}</span></td>
                <td>${p.topic}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/problems/${p.id}/status" class="inline">
                        <select class="select" name="status" onchange="this.form.submit()">
                            <option value="TODO" ${p.status=='TODO'?'selected':''}>TODO</option>
                            <option value="SOLVED" ${p.status=='SOLVED'?'selected':''}>SOLVED</option>
                            <option value="REVIEW" ${p.status=='REVIEW'?'selected':''}>REVIEW</option>
                        </select>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/problems/${p.id}/time" class="inline">
                        <input class="input small" type="number" name="solvedTimeSec" min="0" value="${p.solvedTimeSec}"/>
                        <button class="btn ghost" type="submit">저장</button>
                    </form>
                </td>
                <td class="right">
                    <a class="btn ghost" href="${pageContext.request.contextPath}/problems/${p.id}/timer">타이머</a>
                    <a class="btn ghost" href="${pageContext.request.contextPath}/problems/${p.id}/edit">수정</a>
                    <form method="post" action="${pageContext.request.contextPath}/problems/${p.id}/delete" class="inline"
                          onsubmit="return confirm('삭제할까?');">
                        <button class="btn danger" type="submit">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty items}">
            <tr><td colspan="7" class="muted">새 문제를 추가하고 멋진 전전 학부생이 되어보자!</td></tr>
        </c:if>
        </tbody>
    </table>
</div>

<div class="actions" style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/tasks"
       class="btn sm secondary">
        Task 페이지로 이동
    </a>
</div>

</body>
</html>
