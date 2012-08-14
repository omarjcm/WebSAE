<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="mg.contactenos" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />
        
        <jsp:include page="declaracion.jsp" />
    </head>

    <body class="yui-skin-sam tundra">
        <div id="div_body_general">
            <div id="div_cabecera">
                <jsp:include page="/menu/menu_general.jsp" />
            </div>
            <div id="div_banner_superior">
                <div class="div_menu">
                    <jsp:include page="/menu/encabezado.jsp" />
                </div>
                <jsp:include page="/vistas/logo_websae.jsp" />
            </div>

            <div id="div_contenedor">
                <div class="titulo"><fmt:message key="mg.contactenos" /></div>
                <span class="text_orange_uppercase_tittle"><fmt:message key="encabezado.software" /></span><br />
                <span class="text_legend">Ing. Mónica Villavicencio<br /></span>
                <img src="/websae/images/icono_mail.gif" width="30px" alt="" />mvillavi@espol.edu.ec<br />
                593-4-2269-313 <img src="/websae/images/iconoTelefono.jpg" width="15px" alt="" /><br /><br />
                <span class="text_orange_uppercase_tittle"><fmt:message key="encabezado.telecomunicaciones" /><br /></span>
                <span class="text_legend">Ing. Rebeca Estrada<br /></span>
                <img src="/websae/images/icono_mail.gif" width="30px" alt="" />vlir-tel@espol.edu.ec <br />
                593-4-2269-268 <img src="/websae/images/iconoTelefono.jpg" width="15px" alt="" /><br /><br />
                <span class="text_orange_uppercase_tittle"><fmt:message key="encabezado.robotica" /><br /></span>
                <span class="text_legend">Ing. Boris Vintimilla<br /></span>
                <img src="/websae/images/icono_mail.gif" width="30px" alt="" />cvr@espol.edu.ec<br />
                593-4-2269-000 <img src="/websae/images/iconoTelefono.jpg" width="12px" alt="" /><br /><br />
                <br /><br />
                <a href="mailto:<fmt:message key="ge.correo_ayudante" />"><fmt:message key="ge.correo_ayudante" /></a><br />

                <div class="clearthefloats" />
            </div>
            <div class="clearthefloats"></div>

            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>