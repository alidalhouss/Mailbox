<%-- 
    Document   : user-form.jsp
    Created on : 26 févr. 2015, 19:17:30
    Author     : Ali DALHOUSS <ali.dalhouss@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/directory/user">MailBox - Admin</a>
                </div>
                <p class="navbar-text pull-right">Signed in as <b>${connectedAs}</b></p>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="${pageContext.request.contextPath}/directory/user">User</a></li>
                        <li><a href="${pageContext.request.contextPath}/directory/post">News Group</a></li>
                        <li><a href="${pageContext.request.contextPath}/directory/mymailbox">My Mailbox</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" role="main">
            <div class="page-header">
                <h1>
                    <c:choose>
                        <c:when test="${not empty user.idUser}">
                            Edit a Mailbox user: <small>${user.username}</small></h1>
                        </c:when>
                        <c:otherwise>
                            Add a Mailbox user: <small>new user</small></h1>
                        </c:otherwise>
                    </c:choose>
            </div>
            <div class="alert alert-${messageStatus}" role="alert">${messageContent}</div>
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/directory/user/edit">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">Username</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" name="username" placeholder="Username" value="${user.username}">
                        </div>
                    </div>
                    <c:if test="${empty user.idUser}" >
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="username" name="password" placeholder="Password" value="${user.username}">
                        </div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label for="mailbox" class="col-sm-2 control-label">Mailbox name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="mailbox_name" id="mailbox" placeholder="Mailbox name" value="${user.mailbox}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="news_group" class="col-sm-2 control-label">News Group</label>
                        <div class="col-sm-10">
                            <select name="id_newsgroup" class="form-control">
                                <c:forEach var="newsGroup" items="${newsGroups}">
                                    <option value="${newsGroup.idNewsgroup}">${newsGroup.name}</option>
                                </c:forEach>
                                <c:if test="${not empty newsGroup}">
                                    <option value="${newsGroup.idNewsgroup}">${newsGroup.name}</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rights" class="col-sm-2 control-label">Rights</label>
                        <div class="col-sm-10">
                            <div class="checkbox">
                                <c:choose>
                                    <c:when test="${not empty user.idUser}">
                                        <label>
                                        <input type="checkbox" <c:if test="${user.readRight}">checked</c:if> name="read_right">
                                        Read Right
                                        </label>
                                    </c:when>
                                    <c:otherwise>
                                        <label>
                                        <input type="checkbox" name="read_right">
                                        Read Right
                                        </label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="checkbox">
                                    <c:choose>
                                        <c:when test="${not empty user.idUser}">
                                            <label>
                                                <input type="checkbox" <c:if test="${user.writeRight}">checked</c:if> name="write_right">
                                            Write Right
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                        <label>
                                        <input type="checkbox" name="write_right">
                                        Write Right
                                        </label>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="role" class="col-sm-2 control-label">Role</label>
                        <div class="col-sm-10">
                            <select name="role" class="form-control">
                                <c:choose>
                                    <c:when test="${user.role == 0}">
                                        <option value="0">Final User</option>
                                        <option value="1">Administrator</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="1">Administrator</option> 
                                        <option value="0">Final User</option>
                                    </c:otherwise>
                                </c:choose>                                
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                    <c:if test="${not empty user.idUser}">
                        <input type="hidden" name="user_id" value="${user.idUser}" />
                    </c:if>
                </form>
                    
        </div>
    </body>

