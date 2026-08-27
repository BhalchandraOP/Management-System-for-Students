<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Student Registration System</title>

<style>

/* =========================
   GLOBAL
   ========================= */

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Segoe UI, Arial, sans-serif;
}

body {

    background: linear-gradient(135deg, #4facfe, #00c6fb);

    display: flex;

    justify-content: center;

    align-items: flex-start;

    min-height: 100vh;

    padding: 30px;
}


/* =========================
   MAIN CONTAINER
   ========================= */

.container {

    width: 100%;

    max-width: 1000px;

    background: white;

    padding: 30px;

    border-radius: 15px;

    box-shadow: 0 12px 30px rgba(0,0,0,.25);
}


/* =========================
   HEADER
   ========================= */

h1 {

    text-align: center;

    color: #2c3e50;

    margin-bottom: 5px;
}

.subtitle {

    text-align: center;

    color: gray;

    margin-bottom: 20px;
}


/* =========================
   LOGIN SECTION
   ========================= */

.login-section {

    margin: 20px 0 30px 0;

    padding: 15px 20px;

    border: 2px solid #34495e;

    border-radius: 10px;

    background: #f8f9fa;

    display: flex;

    justify-content: space-between;

    align-items: center;
}

.login-section h2 {

    color: #2c3e50;

    font-size: 20px;

    margin: 0;
}


/* Login / Logout button */

.login-button {

    display: inline-block;

    padding: 10px 25px;

    background: #3498db;

    color: white;

    text-decoration: none;

    border: none;

    border-radius: 6px;

    font-size: 15px;

    cursor: pointer;

    transition: .3s;
}

.login-button:hover {

    background: #2471a3;
}


/* Logout form */

.logout-form {

    margin: 0;
}


/* =========================
   MAIN CONTENT
   ========================= */

.main {

    display: flex;

    gap: 30px;
}

.left,
.right {

    flex: 1;
}


/* =========================
   CARDS
   ========================= */

.card {

    border: 3px solid;

    border-radius: 12px;

    padding: 20px;

    margin-bottom: 20px;

    background: #fafafa;
}

.card h2 {

    text-align: center;

    margin-bottom: 15px;

    color: #2c3e50;
}


/* =========================
   FORM
   ========================= */

label {

    display: block;

    margin-top: 10px;

    margin-bottom: 5px;

    font-weight: bold;
}

input {

    width: 100%;

    padding: 10px;

    border: 1px solid #ccc;

    border-radius: 6px;
}

input:focus {

    outline: none;

    border-color: #3498db;
}


/* =========================
   ROW
   ========================= */

.row {

    display: flex;

    gap: 15px;
}

.field {

    flex: 1;
}


/* =========================
   CARD COLORS
   ========================= */

.register {

    border-color: #2ecc71;
}

.find {

    border-color: #3498db;
}

.update {

    border-color: #f39c12;
}

.delete {

    border-color: #e74c3c;
}

.view {

    border-color: #9b59b6;
}


/* =========================
   BUTTONS
   ========================= */

button,
input[type="submit"] {

    width: 100%;

    margin-top: 20px;

    padding: 12px;

    background: #3498db;

    color: white;

    border: none;

    border-radius: 6px;

    cursor: pointer;

    font-size: 15px;

    transition: .3s;
}

button:hover,
input[type="submit"]:hover {

    background: #2471a3;
}


/* =========================
   LOGIN BUTTON OVERRIDE
   ========================= */

.login-button {

    width: auto;

    margin-top: 0;
}


/* =========================
   RESPONSIVE
   ========================= */

@media (max-width: 800px) {

    body {

        padding: 15px;
    }

    .container {

        padding: 20px;
    }

    .main {

        flex-direction: column;

        gap: 0;
    }

    .login-section {

        flex-direction: column;

        gap: 12px;

        text-align: center;
    }
}

@media (max-width: 500px) {

    .row {

        flex-direction: column;

        gap: 0;
    }

    h1 {

        font-size: 26px;
    }
}

</style>

</head>


<body>


<%
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    boolean loggedIn =
        authentication != null &&
        authentication.isAuthenticated() &&
        !"anonymousUser".equals(authentication.getPrincipal());
%>


<div class="container">


    <!-- =========================
         HEADER
         ========================= -->

    <h1>
        Student Registration System
    </h1>

    <div class="subtitle">

        Spring MVC + Spring Data JPA CRUD Demo

    </div>



    <!-- =========================
         LOGIN / LOGOUT
         ========================= -->

    <div class="login-section">

        <% if (!loggedIn) { %>


            <!-- NOT LOGGED IN -->

            <h2>
                Welcome, Guest
            </h2>

            <div class="login-buttons">

                <a href="${pageContext.request.contextPath}/login"
                   class="login-button">

                    Login

                </a>

            </div>


        <% } else { %>


            <!-- LOGGED IN -->

            <h2>

                Welcome,
                <%= authentication.getName() %>

            </h2>

            <div class="login-buttons">

                <!-- Logout must be POST -->

                <form action="${pageContext.request.contextPath}/logout"
                      method="post"
                      class="logout-form">

                    <button type="submit"
                            class="login-button">

                        Logout

                    </button>

                </form>

            </div>


        <% } %>

    </div>



    <!-- =========================
         MAIN CONTENT
         ========================= -->

    <div class="main">


        <!-- =========================
             LEFT SIDE
             ========================= -->

        <div class="left">


            <!-- REGISTER -->

            <div class="card register">

                <h2>
                    Register Student
                </h2>

                <form action="addStudent" method="post">


                    <label>
                        Student ID
                    </label>

                    <input type="number"
                           name="sid"
                           required>


                    <label>
                        Student Name
                    </label>

                    <input type="text"
                           name="sname"
                           required>


                    <div class="row">


                        <div class="field">

                            <label>
                                Branch
                            </label>

                            <input type="text"
                                   name="sbranch"
                                   required>

                        </div>


                        <div class="field">

                            <label>
                                Semester
                            </label>

                            <input type="number"
                                   name="semester"
                                   min="1"
                                   max="8"
                                   required>

                        </div>


                    </div>


                    <button type="submit">

                        Register Student

                    </button>


                </form>

            </div>



            <!-- UPDATE -->

            <div class="card update">

                <h2>
                    Update Student
                </h2>

                <form action="updateStudent" method="post">


                    <label>
                        Student ID
                    </label>

                    <input type="number"
                           name="sid"
                           required>


                    <label>
                        Student Name
                    </label>

                    <input type="text"
                           name="sname"
                           required>


                    <div class="row">


                        <div class="field">

                            <label>
                                Branch
                            </label>

                            <input type="text"
                                   name="sbranch"
                                   required>

                        </div>


                        <div class="field">

                            <label>
                                Semester
                            </label>

                            <input type="number"
                                   name="semester"
                                   min="1"
                                   max="8"
                                   required>

                        </div>


                    </div>


                    <button type="submit">

                        Update Student

                    </button>


                </form>

            </div>

        </div>



        <!-- =========================
             RIGHT SIDE
             ========================= -->

        <div class="right">


            <!-- FIND -->

            <div class="card find">

                <h2>
                    Find Student
                </h2>

                <form action="getStudent" method="get">


                    <label>
                        Student ID
                    </label>

                    <input type="number"
                           name="sid"
                           required>


                    <button type="submit">

                        Find Student

                    </button>


                </form>

            </div>



            <!-- DELETE -->

            <div class="card delete">

                <h2>
                    Delete Student
                </h2>

                <form action="deleteStudent" method="post">


                    <label>
                        Student ID
                    </label>

                    <input type="number"
                           name="sid"
                           required>


                    <button type="submit">

                        Delete Student

                    </button>


                </form>

            </div>



            <!-- SHOW ALL -->

            <div class="card view">

                <h2>
                    View Students
                </h2>

                <form action="showStudents" method="get">

                    <input type="submit"
                           value="Show All Students">

                </form>

            </div>


        </div>

    </div>

</div>

</body>

</html>