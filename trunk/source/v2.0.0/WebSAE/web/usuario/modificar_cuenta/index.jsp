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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="mc.modificar_cuenta" /></title>
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
                <div class="div_menu_texto">
                    <fmt:message key="cc.informacion" />
                </div>
                <div class="div_menu_texto_descripcion">
                    <p><fmt:message key="cc.descripcion1" /></p>
                    <p><fmt:message key="cc.descripcion2" /></p>
                    <p><fmt:message key="ge.inquietud" /> <a href="mailto:<fmt:message key="ge.correo_ayudante" />"><fmt:message key="ge.correo_ayudante" /></a></p>
                </div>
            </div>
            <jsp:include page="/vistas/info_usuario.jsp" />
            <div id="ubicacion">
                <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <fmt:message key="pistas.perfil_usuario" /> ::. <fmt:message key="pistas.modificar_cuenta" />
            </div>
            <div id="div_formulario">
                <form dojoType="dijit.form.Form" class="form-suscripcion" id="usuario" name="usuario">
                    <fieldset>
                        <div class="mensaje_estatico_bold"><fmt:message key="ge.campos_obligatorios" /></div>
                        
                        <input type="hidden" id="txt_correo" name="txt_correo" value="${usuario.us_email}" />
                        <jsp:include page="datos_personales.jsp" />
                        <br />
                        <jsp:include page="informacion_laboral.jsp" />
                        <br />
                    </fieldset>
                    
                    <div id="div_align_center">
                        <button dojoType="dijit.form.Button" class="submit">
                            <fmt:message key="mc.modificar_cuenta" />
                            <script type="dojo/method" event="onClick">modificar_usuario();</script>
                        </button>
                    </div>
                    <br />
                    <br />
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