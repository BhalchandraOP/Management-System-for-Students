<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login - Student Registration System</title>

<style>

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Segoe UI, Arial, sans-serif;
}

body {
    background: linear-gradient(135deg,#4facfe,#00c6fb);

    display: flex;
    justify-content: center;
    align-items: center;

    min-height: 100vh;
}

.login-container {

    width: 400px;

    background: white;

    padding: 35px;

    border-radius: 15px;

    box-shadow: 0 12px 30px rgba(0,0,0,.25);
}

h1 {

    text-align: center;

    color: #2c3e50;

    margin-bottom: 10px;
}

.subtitle {

    text-align: center;

    color: gray;

    margin-bottom: 25px;
}

label {

    display: block;

    margin-top: 15px;

    margin-bottom: 5px;

    font-weight: bold;
}

input[type="text"],
input[type="password"] {

    width: 100%;

    padding: 11px;

    border: 1px solid #ccc;

    border-radius: 6px;
}

button {

    width: 100%;

    margin-top: 25px;

    padding: 12px;

    background: #3498db;

    color: white;

    border: none;

    border-radius: 6px;

    cursor: pointer;

    font-size: 15px;
}

button:hover {

    background: #2471a3;
}

.error {

    background: #f8d7da;

    color: #721c24;

    padding: 10px;

    border-radius: 6px;

    margin-bottom: 15px;

    text-align: center;
}

</style>

</head>

<body>

<div class="login-container">

    <h1>Login</h1>

    <div class="subtitle">
        Student Registration System
    </div>

    <% if ("true".equals(request.getParameter("error"))) { %>

        <div class="error">
            Invalid username or password
        </div>

    <% } %>

    <form action="${pageContext.request.contextPath}/login"
          method="post">

        <label>Username</label>

        <input type="text"
               name="username"
               required>

        <label>Password</label>

        <input type="password"
               name="password"
               required>

        <button type="submit">
            Login
        </button>

    </form>

</div>

</body>

</html>