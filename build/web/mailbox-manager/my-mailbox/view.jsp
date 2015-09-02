<%-- 
    Document   : vew
    Created on : 2 mars 2015, 19:44:28
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/ressources/css/bootstrap.css"/>" >
        <link rel="stylesheet" href="<c:url value="/ressources/css/main.css"/>" >

              <title>My MailBox</title>

    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">MailBox - Manager</a>
                </div>
                <p class="navbar-text pull-right">Signed in as <b>${connectedAs}</b></p>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/mailbox-manager/newsgroup">News Group</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/mailbox-manager/mymailbox">My Mailbox</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" role="main">
            <div class="page-header">
                <div class="pull-right">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/mailbox-manager/mymailbox/send">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                        &nbsp;Send new message
                    </a>
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
