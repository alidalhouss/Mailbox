<%-- 
    Document   : index
    Created on : 2 mars 2015, 11:46:23
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Mailbox/ressources/css/bootstrap.css">
        <link rel="stylesheet" href="/Mailbox/ressources/css/main.css" >
        <title>Welcome Mailbox</title>
    </head>
    <body>
        <div class="container" role="main">
            <div class="page-header row col-sm-12" style="text-align: center;">
                <h1>Mailbox - Login Page</h1>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin" style="max-width: 330px; text-align: center; margin: 0 auto;">
                <br>
                <input name="user-name" type="text" id="inputEmail" class="form-control" placeholder="Username" required="true">
                <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="true">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
        
<!--        <div class="container" role="main">
            <div class="page-header row col-sm-12" style="text-align: center;">
                <h1>Mailbox - Homepage</h1>
            </div>
            <div class="row col-sm-6" style="text-align: right; margin-right:  10px;">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/directory/user?user-name=ali" role="button">Directory Manager</a>
            </div>
            <div class="row col-sm-6" style="text-align: left; margin-left:  10px;">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/mailbox-manager/newsgroup" role="button">MailBox Manager</a>
            </div>
            <div class="row col-sm-12" style="text-align: center; margin-top: 10px;">
                <input name="user-name" type="text" class="form-control col-lg-6" placeholder="Username" >
            </div>
        </div>-->
    </body>
</html>
