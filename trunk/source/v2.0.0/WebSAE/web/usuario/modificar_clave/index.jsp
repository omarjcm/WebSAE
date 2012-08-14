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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="mc.modificar_contrasena" /></title>
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
                    <fmt:message key="mc.modificar_contrasena" />
                </div>
                <div class="div_menu_texto_descripcion">
                    <p><fmt:message key="mc.descripcion1" /></p>
                    <p><fmt:message key="ge.inquietud" /> <a href="mailto:<fmt:message key="ge.correo_ayudante" />"><fmt:message key="ge.correo_ayudante" /></a></p>
                </div>
            </div>
            <jsp:include page="/vistas/info_usuario.jsp" />
            <div id="ubicacion">
                <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <fmt:message key="pistas.perfil_usuario" /> ::. <fmt:message key="pistas.modificar_contrasena" />
            </div>
            <div id="div_formulario">
                <form dojoType="dijit.form.Form" class="form-modificacion" id="modificar" name="modificar">
                    <fieldset>
                        <div class="mensaje_estatico_bold"><fmt:message key="ge.campos_obligatorios" /></div>

                        <div class="titulo"><fmt:message key="mc.modificar_contrasena" /></div>

                        <label for="txt_clave_vieja"><fmt:message key="us.contrasena_actual" /></label>
                        <input type="password" id="txt_clave_vieja" name="txt_clave_vieja"
                               dojoType="dijit.form.ValidationTextBox" required="true"
                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                               invalidMessage="<fmt:message key="ge.minimo6c" />"
                               lowercase="true" maxlength="15" />
                        <span class="lblasterisco"> (*)</span>

                        <div class="clearthefloats" />
                        <label for="txt_clave"><fmt:message key="us.contrasena_nueva" /></label>
                        <input type="password" id="txt_clave" name="txt_clave"
                               dojoType="dijit.form.ValidationTextBox" required="true"
                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                               invalidMessage="<fmt:message key="ge.minimo6c" />"
                               lowercase="true" maxlength="15" intermediateChanges="false" />
                        <span class="lblasterisco"> (*)</span>
                        <div class="clearthefloats" />

                        <label for="txt_reclave"><fmt:message key="us.recontrasena_nueva" /></label>
                        <input type="password" id="txt_reclave" name="txt_reclave"
                               dojoType="dijit.form.ValidationTextBox" required="true"
                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                               invalidMessage="<fmt:message key="us.contrasenas_diferentes" />"
                               lowercase="true" maxlength="15" constraints="{'other': 'txt_clave'}"
                               validator="confirmPassword" intermediateChanges="false" />
                        <span class="lblasterisco"> (*)</span>
                        <div class="clearthefloats" />

                        <br />
                    </fieldset>

                    <div id="div_align_center">
                        <button dojoType="dijit.form.Button" class="submit">
                            <fmt:message key="mc.modificar_contrasena" />
                            <script type="dojo/method" event="onClick">modificar_contrasena();</script>
                        </button>
                    </div>
                    <br />
                    <br />
                </form>
            </div>
            </div>
            <div class="clearthefloats"></div>

            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>