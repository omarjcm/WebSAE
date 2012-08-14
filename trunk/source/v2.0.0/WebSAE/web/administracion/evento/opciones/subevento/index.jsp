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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.subeventos" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />
        
        <jsp:include page="declaracion.jsp" />
    </head>

    <body class="yui-skin-sam tundra" onload="inicializar('${lang}')">
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
                <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/administracion/index.jsp"><fmt:message key="pistas.administracion" /></a> ::. <a href="/websae/administracion/evento/index.jsp"><fmt:message key="even.eventos" /></a> ::. <a href="/websae/administracion/evento/administrar_evento/index.jsp"><fmt:message key="even.administrar_evento" /></a> ::. <a href="/websae/administracion/evento/opciones/index.jsp"><fmt:message key="adm.opciones_asignadas" /></a> ::. <span class="ubicacion"><fmt:message key="adm.subeventos" /></span>
            </div>
            <div id="div_informacion_admin">
                <div id="opcion-menu">
                    <div id="opcion-menu-item">
                        <a href="/websae/administracion/evento/opciones/index.jsp"><img src="/websae/images/regresar.png" height="55px" alt="" /><br /><fmt:message key="ge.regresar" /></a>
                    </div>
                    <div id="opcion-menu-item">
                        <a href="javascript:popup_registrar_subevento();"><img src="/websae/images/nuevo.png" height="55px" alt="" /><br /><fmt:message key="ge.nuevo" /></a>
                    </div>
                    <div class="clearthefloats"></div>
                </div>
                <br />
                <div id="div_form">
                    <div class="titulo-principal">${nombre_evento}</div>
                    <div class="titulo"><fmt:message key="adm.subeventos" /></div>
                    <div id="tbl_subeventos" style="width:500px; margin:0 auto;"></div>
                    <jsp:include page="subevento.jsp" />
                </div>
            </div>
            </div>
            <div class="clearthefloats"></div>
            <div id="div_boton_opciones" dojoType="dijit.Dialog" title="<fmt:message key="ge.boton_opciones" />" style="width:150px;">
                <!--button id="btn_agenda" dojoType="dijit.form.Button" onclick="redireccionar('agenda');" class="div_form" style="width:120px">
                    <fmt:message key="ge.agenda" />
                </button-->
                <button id="btn_conferencista" dojoType="dijit.form.Button" onclick="redireccionar('conferencista');" class="div_form" style="width:120px">
                    <fmt:message key="ge.conferencista" />
                </button>
                <button id="btn_material" dojoType="dijit.form.Button" onclick="redireccionar('material');" class="div_form" style="width:120px">
                    <fmt:message key="ge.material" />
                </button>
                <button id="btn_precio" dojoType="dijit.form.Button" onclick="redireccionar('precio');" class="div_form" style="width:120px">
                    <fmt:message key="ge.precio" />
                </button>
                <div class="clearthefloats"></div>
            </div>
            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>