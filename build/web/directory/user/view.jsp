<%-- 
    Document   : view
    Created on : 24 févr. 2015, 09:33:34
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/directory/user">MailBox - Admin</a>
                    
                </div>
                <p class="navbar-text pull-right">Signed in as <b>${connectedAs}</b></p>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="${pageContext.request.contextPath}/directory/user">User</a></li>
                        <li><a href="${pageContext.request.contextPath}/directory/newsgroup">News Group</a></li>
                        <li><a href="${pageContext.request.contextPath}/directory/mymailbox">My Mailbox</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" role="main">
            <div class="page-header">
            <div class="pull-right">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/directory/user/edit">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    &nbsp;Add new user
                </a>
            </div>
                <h1>List of Mailbox users</h1>
            </div>
                <c:if test="${messageContent != null}">
                    <div class="alert alert-${messageStatus}" role="alert">${messageContent}</div>
                </c:if>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Username</th>
                        <th>Mailbox name</th>
                        <th>User role</th>
                        <th>Read right</th>
                        <th>Write right</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/directory/user/edit?user_id=${user.idUser}">${user.idUser}</a></td> 
                            <td><a href="${pageContext.request.contextPath}/directory/user/edit?user_id=${user.idUser}">${user.username}</a></td> 
                            <td>${user.mailbox}</td> 
                            <td>
                                <c:if test="${user.role == 1}">
                                    Admin
                                </c:if>
                                <c:if test="${user.role == 0}">
                                    Final User
                                </c:if>
                            </td> 
                            <td>${user.readRight}</td> 
                            <td>${user.writeRight}</td>
                            <td class="center">
                                <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/directory/user/edit?user_id=${user.idUser}">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                    Edit
                                </a>
                                <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/directory/user/remove?user_id=${user.idUser}">
                                    <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
                                    Remove
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
