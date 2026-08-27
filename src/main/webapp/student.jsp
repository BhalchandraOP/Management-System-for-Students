<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${college}</title>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Segoe UI,Arial,sans-serif;
}

body{
background:linear-gradient(135deg,#4facfe,#00c6fb);
display:flex;
justify-content:center;
align-items:center;
height:100vh;
}

.container{
background:white;
width:850px;
padding:30px;
border-radius:15px;
box-shadow:0 12px 30px rgba(0,0,0,.25);
}

h1{
text-align:center;
margin-bottom:5px;
color:#2c3e50;
}

h3{
text-align:center;
color:#777;
margin-bottom:25px;
}

table{
width:100%;
border-collapse:collapse;
}

th{
background:#3498db;
color:white;
padding:12px;
}

td{
padding:12px;
text-align:center;
border-bottom:1px solid #ddd;
}

tr:nth-child(even){
background:#f8fbff;
}

tr:hover{
background:#ecf6ff;
}

.back{
text-align:center;
margin-top:25px;
}

button{
padding:12px 35px;
background:#27ae60;
color:white;
border:none;
border-radius:6px;
font-size:15px;
cursor:pointer;
}

button:hover{
background:#1f8a4d;
}

</style>

</head>

<body>

<div class="container">

<h1>Registered Students</h1>
<h3>${college}</h3>

<table>

<tr>
<th>ID</th>
<th>Name</th>
<th>Branch</th>
<th>Semester</th>
</tr>

<c:forEach items="${student}" var="student">

<tr>
<td>${student.sid}</td>
<td>${student.sname}</td>
<td>${student.sbranch}</td>
<td>${student.semester}</td>
</tr>

</c:forEach>

</table>

<div class="back">

<form action="/" method="get">
<button>Back to Registration</button>
</form>

</div>

</div>
<form action="${pageContext.request.contextPath}/logout"
      method="post">

    <button type="submit">

        Logout

    </button>

</form>
</body>
</html>