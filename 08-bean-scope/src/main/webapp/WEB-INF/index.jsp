<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>request</h1>
index.html
<br/>
<br/>

<hr/>
搜索顺序：page -> request -> session -> servletContext
<br/>

<hr/>
\${userRequest.id} : ${userRequest.id}
<br/>
\${userRequest.name} : ${userRequest.name}
<hr/>
<br/>
<br/>
<hr/>
\${userSession.id} : ${userSession.id}
<br/>
\${userSession.name} : ${userSession.name}
<hr/>
<br/>
<br/>
<hr/>
\${applicationScope['scopedTarget.userApplication'].id} : ${applicationScope['scopedTarget.userApplication'].id}
<br/>
\${applicationScope['scopedTarget.userApplication'].name} : ${applicationScope['scopedTarget.userApplication'].name}
<hr/>
<br/>
<br/>
<hr/>
\${userApplicationModle.id} : ${userApplicationModle.id}
<br/>
\${userApplicationModle.name} : ${userApplicationModle.name}
<hr/>


</body>
</html>
