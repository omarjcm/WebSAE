
<%-- 
    Document   : index
    Created on : 18/04/2009, 04:09:32 AM
    Author     : Guillermo Pizarro
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session" />
</c:if>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ms.crear_cuenta" /> </title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="refresh" content="0;URL=http://localhost:8080/websae/home/index.jsp?lang=es" />
    </head>
    <body>
    </body>
</html>
