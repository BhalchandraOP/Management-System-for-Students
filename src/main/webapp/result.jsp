<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>

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
width:430px;
background:white;
padding:30px;
border-radius:15px;
box-shadow:0 12px 30px rgba(0,0,0,.25);
text-align:center;
}

.success{
font-size:55px;
color:#2ecc71;
margin-bottom:10px;
}

h2{
color:#2c3e50;
margin-bottom:5px;
}

.college{
color:#666;
margin-bottom:20px;
}

table{
width:100%;
border-collapse:collapse;
margin-bottom:20px;
}

td{
padding:10px;
border-bottom:1px solid #eee;
}

td:first-child{
font-weight:bold;
text-align:left;
}

td:last-child{
text-align:right;
}

.buttons{
    display:flex;
    justify-content:center;
    align-items:center;
    gap:15px;
    margin-top:20px;
}
button{
    width:170px;
    padding:11px;
    border:none;
    border-radius:6px;
    cursor:pointer;
    color:white;
    font-size:15px;
    transition:.3s;
}

.home{
background:#27ae60;
}

.home:hover{
background:#1f8a4d;
}

.list{
background:#3498db;
}

.list:hover{
background:#2471a3;
}

</style>

</head>

<body>

<div class="container">

<div class="success">✔</div>

<h2>Student Details</h2>

<div class="college">${college}</div>

<table>

<tr>
<td>Student ID</td>
<td>${student.sid}</td>
</tr>

<tr>
<td>Name</td>
<td>${student.sname}</td>
</tr>

<tr>
<td>Branch</td>
<td>${student.sbranch}</td>
</tr>

<tr>
<td>Semester</td>
<td>${student.semester}</td>
</tr>

</table>

<div class="buttons">

<form action="/" method="get">
<button class="home">Register Another</button>
</form>

<form action="showStudents" method="get">
<button class="list">All Students</button>
</form>

</div>

</div>

</body>
</html>