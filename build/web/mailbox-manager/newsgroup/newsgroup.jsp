<%-- 
    Document   : newsgroup
    Created on : 1 mars 2015, 00:47:09
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<f:view>
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
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">MailBox - Manager</a>
                    </div>
                    <p class="navbar-text pull-right">Signed in as <b>${connectedAs.getUsername()}</b></p>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.request.contextPath}/mailbox-manager/newsgroup">News Group</a></li>
                            <li><a href="${pageContext.request.contextPath}/mailbox-manager/mymailbox">My Mailbox</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </nav>
            
            <div class="container" role="main">
                <c:choose>
                <c:when test="${connectedAs.getReadRight() == true}">
                <div class="page-header">
                    <h1>NEWS GROUP <small>${postWithUser.get(0).idNewsgroup.getName()}</small></h1>
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
                        <c:forEach var="post" items="${postWithUser}">
                            <tr>
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
                <c:choose>
                    <c:when test="${connectedAs.getWriteRight() == true}">
                        <form method="POST" action="${pageContext.request.contextPath}/mailbox-manager/newsgroup">
                            <div class="form-inline">
                                <input name="post-content" id="textField" type="text" class="form-control" style="width: 89%;" placeholder="Your message" />    
                                <button value="Send" class="btn btn-primary ">Send message</button>
                            </div>
                        </form>
                    </c:when>
                </c:choose>
            </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger" role="alert">You do not have enough rights to perform this action!</div>
            </c:otherwise>
            </c:choose>
            <script type="text/javascript" src="<c:url value="/mailbox-manager/newsgroup/websocket.js" />"> </script>
        </body>
    </html>
</f:view>
