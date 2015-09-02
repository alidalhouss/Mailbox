<%-- 
    Document   : view
    Created on : 2 mars 2015, 12:17:19
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
                    <a class="navbar-brand" href="#">MailBox - Admin</a>
                </div>
                <p class="navbar-text pull-right">Signed in as <b>${connectedAs.getUsername()}</b></p>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/directory/user">User</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/directory/newsgroup">News Group</a></li>
                        <li><a href="${pageContext.request.contextPath}/directory/mymailbox">My Mailbox</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" role="main">
            <div class="page-header">
                <h1>List of News Group Messages</h1>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Post id</th>
                        <th>From</th>
                        <th>Message Content</th>
                        <th>Sent at</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="post" items="${postList}">
                        <tr>
                            <td>${post.idPost}</td>
                            <td>${post.idUserFrom.getUsername()}</td> 
                            <td>${post.content}</td> 
                            <td>${post.createdAt}</td> 
                            <td>
                                <c:if test="${post.isRead == true}">
                                    <span class="label label-default">Read</span>
                                </c:if>
                                <c:if test="${post.isRead == false}">
                                    <span class="label label-danger">New</span>
                                </c:if>
                            </td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form method="POST" action="${pageContext.request.contextPath}/directory/newsgroup">
                <div class="form-inline">
                    <input required="true" name="post-content" id="textField" type="text" class="form-control" style="width: 89%;" placeholder="Your message" />    
                    <button value="Send" class="btn btn-primary ">Send message</button>
                </div>
            </form>
        </div>
    </body>
</html>
