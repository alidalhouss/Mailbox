<%-- 
    Document   : view
    Created on : 4 mars 2015, 09:28:14
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Mailbox/ressources/css/bootstrap.css">
        <link rel="stylesheet" href="<c:url value="/ressources/css/main.css"/>" >
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">MailBox - Admin</a>
                </div>
                <p class="navbar-text pull-right">Signed in as <b>${connectedAs.getUsername()}</b></p>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/directory/user">User</a></li>
                        <li><a href="${pageContext.request.contextPath}/directory/newsgroup">News Group</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/directory/mymailbox">My Mailbox</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" role="main">
            <div class="page-header">
                <div class="pull-right">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/directory/my-mailbox/send">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                        &nbsp;Send new message
                    </a>
                </div>
                <div class="pull-right">
                </div>
                <h1>My Mailbox Messages</h1>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>From</th>
                        <th>Message Content</th>
                        <th>Sent at</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="message" items="${messageList}">
                    <tr>
                        <td>${message.idUserFrom.getUsername()}</td> 
                        <td>${message.content}</td> 
                        <td>${message.createdAt}</td> 
                        <td>
                    <c:if test="${message.isRead == true}">
                        <span class="label label-default">Read</span>
                    </c:if>
                    <c:if test="${message.isRead == false}">
                        <span class="label label-danger">New</span>
                    </c:if>
                    </td> 
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>            
    </body>
</html>
