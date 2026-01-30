<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
  <title>Error</title>
</head>
<body>
<h2>문제가 발생했습니다.</h2>
<p>다시 시도해 주세요.</p>

<hr/>
<p style="color:#555; font-size: 12px;">
  (디버깅용) ${errorMessage}
</p>

<p><a href="${pageContext.request.contextPath}/">Home</a></p>
</body>
</html>
