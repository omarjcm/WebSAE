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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ge.datos_generales" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />

        <jsp:include page="declaracion.jsp" />
    </head>
    
    <body class="yui-skin-sam tundra" onload="inicializar('${lang}','${id_evento}')">
        <div id="div_body_general">
            <div id="div_cabecera">
                <jsp:include page="/menu/menu_general.jsp" />
            </div>
            <div id="div_banner_superior">
                <div class="div_menu">
                    <jsp:include page="/menu/encabezado.jsp" />
                </div>
                <div id="logo_evento" class="logo_evento"></div>
            </div>

            <div id="div_contenedor_admin">

            <jsp:include page="/vistas/menu_evento_usuario.jsp" />
            <jsp:include page="/vistas/info_usuario.jsp" />
            
            <div id="ubicacion">
                <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <span class="ubicacion"><fmt:message key="ge.datos_generales" /></span>
            </div>
            <div id="div_informacion_admin" style="">
                <div id="div_form">
                    <div class="titulo-principal">${nombre_evento}</div>
                    <div id="descripcion_evento"></div>
                    <div id="descripcion_subeventos"></div>
                    <div id="div_detalle_subevento" dojoType="dijit.Dialog" title="<fmt:message key="ge.detalle_subevento" />" style="width:440px;">
                         <div id="detalle_subevento"></div>
                         <div id="detalle_agenda_subevento"></div>
                         <div id="detalle_conferencistas_subevento"></div>
                         <div id="detalle_materiales_subevento"></div><br />
                         <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_detalle_subevento').hide();" class="div_form">
                            <fmt:message key="ge.cerrar" />
                         </button><br /><br />
                     </div>
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