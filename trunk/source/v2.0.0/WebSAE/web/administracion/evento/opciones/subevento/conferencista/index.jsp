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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.conferencista" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />
        
        <jsp:include page="declaracion.jsp" />
    </head>

    <body class="yui-skin-sam tundra" onload="inicializar('${lang}',${param.id_subevento})">
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

            <div id="div_contenedor_admin">
                <div id="div_info">
                    <div class="div_menu_admin_titulo">
                        <fmt:message key="adm.menu_administrativo" />
                    </div>
                    <div class="div_menu_admin">
                        <jsp:include page="/menu/menu_administrador.jsp" />
                    </div>
                </div>
                <jsp:include page="/vistas/info_usuario.jsp" />
                <div id="ubicacion">
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/administracion/index.jsp"><fmt:message key="pistas.administracion" /></a> ::. <a href="/websae/administracion/evento/index.jsp"><fmt:message key="even.eventos" /></a> ::. <a href="/websae/administracion/evento/administrar_evento/index.jsp"><fmt:message key="even.administrar_evento" /></a> ::. <a href="/websae/administracion/evento/opciones/index.jsp"><fmt:message key="adm.opciones_asignadas" /></a> ::. <a href="/websae/administracion/evento/opciones/subevento/index.jsp"><fmt:message key="adm.subeventos" /></a> ::. <span class="ubicacion"><fmt:message key="adm.conferencista" /></span>
                </div>
                <div id="div_informacion_admin">
                    <div id="opcion-menu">
                        <div id="opcion-menu-item">
                            <a href="/websae/administracion/evento/opciones/subevento/index.jsp"><img src="/websae/images/regresar.png" height="55px" alt="" /><br /><fmt:message key="ge.regresar" /></a>
                        </div>
                        <div id="opcion-menu-item">
                            <a href="javascript:popup_mostrar_conferencistas();"><img src="/websae/images/nuevo.png" height="55px" alt="" /><br /><fmt:message key="ge.nuevo" /></a>
                        </div>
                        <div class="clearthefloats"></div>
                    </div>
                    <br />
                    <div id="div_form">
                        <div class="titulo-principal">${nombre_evento}</div>
                        <div class="titulo"><fmt:message key="adm.conferencista" /></div>
                        <div id="tbl_conferencistas_subevento" style="width:310px; margin:0 auto;"></div>
                        <jsp:include page="conferencista.jsp" />
                    </div>
                </div>
            </div>
            <div class="clearthefloats"></div>

            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>