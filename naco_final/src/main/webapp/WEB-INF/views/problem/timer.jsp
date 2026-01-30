<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Timer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>타이머</h1>
        <a class="btn ghost" href="${pageContext.request.contextPath}/problems">목록</a>
    </div>

    <div class="card">
        <div class="timer-meta">
            <div class="timer-title">${problem.title}</div>
            <div class="muted">
                ${problem.platform} · ${problem.difficulty} · ${problem.topic} · 상태: ${problem.status}
            </div>
            <c:if test="${not empty problem.link}">
                <div style="margin-top:6px;">
                    <a class="muted" target="_blank" href="${problem.link}">문제 링크 열기</a>
                </div>
            </c:if>
        </div>

        <div class="timer-box">
            <div id="time" class="timer-display">00:00</div>

            <div class="timer-row">
                <label class="label" style="margin:0;">목표 시간(분)</label>
                <input id="targetMin" class="input small" type="number" min="1" value="30"/>
                <button class="btn ghost" type="button" onclick="applyTarget()">적용</button>
            </div>

            <div class="timer-actions">
                <button id="btnStart" class="btn" type="button" onclick="start()">Start</button>
                <button id="btnPause" class="btn ghost" type="button" onclick="pause()" disabled>Pause</button>
                <button id="btnReset" class="btn ghost" type="button" onclick="reset()" disabled>Reset</button>
            </div>

            <div class="timer-actions">
                <button class="btn" type="button" onclick="finish()">Finish (SOLVED)</button>
            </div>

            <div id="hint" class="muted" style="margin-top:10px;">
                Start → 풀이 → Finish 누르면 시간이 저장되고 상태가 SOLVED로 바뀝니다.
            </div>
        </div>

        <form id="finishForm" method="post" action="${pageContext.request.contextPath}/problems/${problem.id}/finish">
            <input type="hidden" name="solvedTimeSec" id="solvedTimeSec" value="0"/>
        </form>
    </div>
</div>

<script>
    let targetSec = 30 * 60;
    let elapsedSec = 0;
    let intervalId = null;

    function pad(n){ return (n < 10 ? "0" : "") + n; }

    function render() {
        const mm = Math.floor(elapsedSec / 60);
        const ss = elapsedSec % 60;
        document.getElementById("time").textContent = pad(mm) + ":" + pad(ss);

        const hint = document.getElementById("hint");
        if (elapsedSec >= targetSec) {
            hint.textContent = "목표 시간이 지났습니다.";
        }
    }

    function tick(){
        elapsedSec += 1;
        render();
    }

    function setButtons(running){
        document.getElementById("btnStart").disabled = running;
        document.getElementById("btnPause").disabled = !running;
        document.getElementById("btnReset").disabled = running ? false : (elapsedSec === 0);
    }

    function start(){
        if (intervalId) return;
        intervalId = setInterval(tick, 1000);
        setButtons(true);
    }

    function pause(){
        if (!intervalId) return;
        clearInterval(intervalId);
        intervalId = null;
        setButtons(false);
    }

    function reset(){
        pause();
        elapsedSec = 0;
        render();
        document.getElementById("btnReset").disabled = true;
    }

    function applyTarget(){
        const v = parseInt(document.getElementById("targetMin").value, 10);
        if (isNaN(v) || v < 1) {
            alert("야! 인간적으로 달성가능한 목표는 1분 이상으로 설정해야지!");
            return;
        }
        targetSec = v * 60;
        document.getElementById("hint").textContent = "목표 시간 " + v + "분 적용 완료.";
    }

    function finish(){
        pause();
        if (!confirm("Finish 하면 시간이 저장되고 상태가 SOLVED로 변경됩니다. 계속할까요?")) return;

        document.getElementById("solvedTimeSec").value = elapsedSec;
        document.getElementById("finishForm").submit();
    }

    // init
    render();
</script>
</body>
</html>
