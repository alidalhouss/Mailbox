<%-- 
    Document   : index
    Created on : 2 mars 2015, 12:09:13
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="/Mailbox/ressources/css/main.css" >
        <title>Welcome Mailbox</title>
    </head>
    <body>
        <div class="container" role="main">
            <div class="page-header row col-sm-12" style="text-align: center;">
                <h1>Mailbox - Homepage</h1>
            </div>
            <div class="row col-sm-6" style="text-align: right; margin-right:  10px;">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/directory/user" role="button">Directory Manager</a>
            </div>
            <div class="row col-sm-6" style="text-align: left; margin-left:  10px;">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/mailbox-manager" role="button">MailBox Manager</a>
            </div>
        </div>
    </body>
</html>
