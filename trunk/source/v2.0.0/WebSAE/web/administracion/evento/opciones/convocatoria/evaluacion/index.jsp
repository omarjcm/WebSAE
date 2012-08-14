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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.evaluacion" /></title>
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
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/administracion/index.jsp"><fmt:message key="pistas.administracion" /></a> ::. <a href="/websae/administracion/evento/index.jsp"><fmt:message key="even.eventos" /></a> ::. <a href="/websae/administracion/evento/administrar_evento/index.jsp"><fmt:message key="even.administrar_evento" /></a> ::. <a href="/websae/administracion/evento/opciones/index.jsp"><fmt:message key="adm.opciones_asignadas" /></a> ::. <a href="/websae/administracion/evento/opciones/convocatoria/index.jsp"><fmt:message key="adm.convocatoria" /></a> ::. <span class="ubicacion"><fmt:message key="adm.evaluacion" /></span>
                </div>
                <div id="div_informacion_admin">
                    <div id="opcion-menu">
                        <div id="opcion-menu-item">
                            <a href="/websae/administracion/evento/opciones/convocatoria/index.jsp"><img src="/websae/images/regresar.png" height="55px" alt="" /><br /><fmt:message key="ge.regresar" /></a>
                        </div>
                        <div id="opcion-menu-item">
                            <a id="formato_evaluacion" href="formato_evaluacion.jsp" target="_blank" onclick="window.open(this.href, this.target, 'width=480,height=580,scrollbars=yes'); return false;"><img src="/websae/images/evaluacion.jpg" height="55px" alt="" /><br /><fmt:message key="ge.ver_evaluacion" /></a>
                        </div>
                        <div class="clearthefloats"></div>
                    </div>
                    <br />
                    <div id="div_form">
                        <div class="titulo-principal">${nombre_evento}</div>
                        <div class="titulo"><fmt:message key="adm.evaluacion" /></div>
                        <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_evaluacion" name="frm_evaluacion">
                            <div>
                                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_evaluacion" name="txt_id_evaluacion" value="-1" />
                                <label><fmt:message key="adm.mensaje_evaluador" />:</label>
                                <textarea id="txt_mensaje" name="txt_mensaje" cols="30" rows="2" maxlength="500" onkeyup="return ismaxlength(this)" required="true"></textarea>
                                <span class="lblasterisco"> (*)</span>
                                <div class="clearthefloats"></div>
                                <label><fmt:message key="adm.instrucciones" />:</label>
                                <textarea id="txt_instruccion" name="txt_instruccion" cols="30" rows="5" maxlength="500" onkeyup="return ismaxlength(this)" required="true"></textarea>
                                <span class="lblasterisco"> (*)</span>
                                <div class="clearthefloats"></div>
                                <div id="div_status" dojoType="dijit.layout.ContentPane">
                                    <label><fmt:message key="ge.estado" />:</label>
                                    <label for="rb_status" style="float:left; margin-left:-180px;">
                                        <input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_vigente" value="V"  /><fmt:message key="ge.vigente" />
                                    </label>
                                    <label for="rb_status" style="float:left; margin-left:-150px;">
                                        <input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_pendiente" value="P" checked="checked" /><fmt:message key="ge.pendiente" />
                                    </label>
                                </div>
                                <label></label>
                                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_evaluacion('registrar');" class="div_form">
                                    <fmt:message key="ge.actualizar" />
                                </button>
                                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_evaluacion('modificar');" class="div_form">
                                    <fmt:message key="ge.actualizar" />
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="clearthefloats"></div>
                    <div id="div_opciones_evaluacion" dojoType="dijit.layout.ContentPane">
                        <div class="titulo"><fmt:message key="ge.secciones_evaluacion" /></div>
                        <button style="margin-left:260px;" dojoType="dijit.form.Button" onclick="popup_secciones();">
                            <fmt:message key="ge.agregar" />
                        </button>
                        <div class="clearthefloats"></div>
                        <div id="tbl_secciones" style="width:400px; margin:0 auto;"></div>
                    </div>
                    <div class="clearthefloats"></div>
                    <jsp:include page="seccion.jsp" />
                </div>
            </div>
            <div class="clearthefloats"></div>
            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>