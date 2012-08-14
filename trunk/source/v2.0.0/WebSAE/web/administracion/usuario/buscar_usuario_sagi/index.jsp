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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.usuario.buscar_usuarios_saegi" /></title>
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
                <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/administracion/index.jsp"><fmt:message key="pistas.administracion" /></a> ::. <a href="/websae/administracion/usuario/index.jsp"><fmt:message key="adm.usuarios" /></a> ::. <span class="ubicacion"><fmt:message key="adm.usuario.buscar_usuarios_saegi" /></span>

            </div>
            <div id="div_informacion_admin">
                
                <div id="div_form">
                    <div class="titulo"><fmt:message key="adm.busqueda_usuarios" /></div>
                    
                    <label for="txt_nombre_apellido"><fmt:message key="us.nombre_apellido" />:</label>
                    <input type="text" id="txt_nombre_apellido" name="txt_nombre_apellido"
                        dojoType="dijit.form.ValidationTextBox" trim="true" maxlength="100" class="div_form" value="" />
                    <button dojoType="dijit.form.Button" onclick="buscar_usuario_por_nombre_apellido();">
                        <fmt:message key="ge.buscar" />
                    </button>
                    <div class="clearthefloats"></div>
                    <br />
                </div>
                <div id="paginated"></div>

            </div>
            </div>
            <div class="clearthefloats"></div>

            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>