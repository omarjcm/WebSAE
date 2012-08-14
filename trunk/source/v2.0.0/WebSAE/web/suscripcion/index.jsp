<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:spry="http://ns.adobe.com/spry/">
    <head>
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ms.crear_cuenta" /></title>
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
                <span class="text_black_uppercase">
                    <fmt:message key="cc.informacion" />
                </span>
                <br /><br />
                <p class="text_black_event">
                    <fmt:message key="cc.descripcion1" />
                </p>
                <p class="text_black_event">
                    <fmt:message key="cc.descripcion2" /> <fmt:message key="ge.inquietud" /> <a href="mailto:<fmt:message key="ge.correo_ayudante" />"><fmt:message key="ge.correo_ayudante" /></a>
                </p>
            </div>
            <div id="div_formulario">
                <form dojoType="dijit.form.Form" class="form-suscripcion" id="usuario" name="usuario">
                    <fieldset>
                        <div class="mensaje_estatico_bold"><fmt:message key="ge.campos_obligatorios" /></div>
                        
                        <jsp:include page="informacion.jsp" />
                        <br />
                        <jsp:include page="datos_personales.jsp" />
                        <br />
                        <jsp:include page="informacion_laboral.jsp" />
                        <br />
                    </fieldset>
                    
                    <div id="div_align_center">
                        <button dojoType="dijit.form.Button" class="submit">
                            <fmt:message key="cc.crear_cuenta" />
                            <script type="dojo/method" event="onClick">registrar_usuario();</script>
                        </button>
                    </div>
                </form>
                <div id="crear-empresa" dojoType="dijit.Dialog" href="registrar_empresa.jsp" title="<fmt:message key="emp.registrar_nueva_empresa" />"></div>
            </div>
            </div>
            <div class="clearthefloats"></div>
            
            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>