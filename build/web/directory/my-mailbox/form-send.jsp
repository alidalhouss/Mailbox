<%-- 
    Document   : form-send
    Created on : 4 mars 2015, 10:09:39
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
                        <li class="active"><a href="${pageContext.request.contextPath}/directory/mymailbox">News Group Posts</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" role="main">
            <div class="alert alert-${messageStatus}" role="alert">${messageContent}</div>
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/directory/my-mailbox/send">
                <div class="form-group">
                    <label for="user" class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <select name="user-id" class="form-control">
                            <c:forEach var="user" items="${users}">
                                <option value="${user.idUser}">${user.username}</option>
                            </c:forEach>
                            <c:if test="${not empty newsGroup}">
                                <option value="${newsGroup.idNewsgroup}">${newsGroup.name}</option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="user" class="col-sm-2 control-label">Message</label>
                    <div class="col-sm-10">
                        <textarea name="message-content" class="form-control" rows="3"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="user" class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <button class="btn btn-primary">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
