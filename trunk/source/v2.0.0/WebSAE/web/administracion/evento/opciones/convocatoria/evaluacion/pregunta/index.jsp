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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.preguntas" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />

        <jsp:include page="declaracion.jsp" />
    </head>

    <body class="yui-skin-sam tundra" onload="inicializar('${lang}','${param.id_seccion}')">
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
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/administracion/index.jsp"><fmt:message key="pistas.administracion" /></a> ::. <a href="/websae/administracion/evento/index.jsp"><fmt:message key="even.eventos" /></a> ::. <a href="/websae/administracion/evento/administrar_evento/index.jsp"><fmt:message key="even.administrar_evento" /></a> ::. <a href="/websae/administracion/evento/opciones/index.jsp"><fmt:message key="adm.opciones_asignadas" /></a> ::. <a href="/websae/administracion/evento/opciones/convocatoria/index.jsp"><fmt:message key="adm.convocatoria" /></a> ::. <a href="/websae/administracion/evento/opciones/convocatoria/evaluacion/index.jsp"><fmt:message key="adm.evaluacion" /></a> ::. <span class="ubicacion"><fmt:message key="adm.preguntas" /></span>
                </div>
                <div id="div_informacion_admin">
                    <div id="opcion-menu">
                        <div id="opcion-menu-item">
                            <a href="/websae/administracion/evento/opciones/convocatoria/evaluacion/index.jsp"><img src="/websae/images/regresar.png" height="55px" alt="" /><br /><fmt:message key="ge.regresar" /></a>
                        </div>
                        <div id="opcion-menu-item">
                            <a id="formato_evaluacion" href="../formato_evaluacion.jsp" target="_blank" onclick="window.open(this.href, this.target, 'width=480,height=580,scrollbars=yes'); return false;"><img src="/websae/images/evaluacion.jpg" height="55px" alt="" /><br /><fmt:message key="ge.ver_evaluacion" /></a>
                        </div>
                        <div class="clearthefloats"></div>
                    </div>
                    <br />
                    <div id="div_form">
                        <div class="titulo-principal">${nombre_evento}</div>
                        <div class="titulo" id="div_seccion" style="color:teal;"></div>
                        <div class="titulo"><fmt:message key="adm.preguntas" /></div>
                        <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_pregunta" name="frm_pregunta">
                            <div>
                                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_pregunta" name="txt_id_pregunta" value="-1" />
                                <label><fmt:message key="ge.pregunta" />:</label>
                                <textarea id="txt_pregunta" name="txt_pregunta"
                                          dojoType="dijit.form.SimpleTextarea" required="true"
                                          invalidMessage="<fmt:message key="ge.necesita_valor" />"
                                          maxlength="500"
                                          style="float:left; width:180px; height:50px;" >
                                </textarea>
                                <span class="lblasterisco"> (*)</span>
                                <div class="clearthefloats"></div>
                                <label><fmt:message key="ge.orden" />:</label>
                                <input type="text" id="txt_orden" name="txt_orden"
                                       dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                                       invalidMessage="<fmt:message key="ge.solo_numeros" />"
                                       required="true" maxlength="2" style="width:50px;" />
                                <span class="lblasterisco"> (*)</span>
                                <div class="clearthefloats"></div>
                                <div id="div_status" dojoType="dijit.layout.ContentPane">
                                    <label><fmt:message key="ge.tipo_pregunta" />:</label>
                                    <label for="rb_unica" style="float:left; text-align:left;">
                                        <input dojoType="dijit.form.RadioButton" type="radio" name="rb_tipo_pregunta" id="rb_unica" value="1" checked="checked" /><fmt:message key="ge.seleccion_simple" />
                                    </label>
                                    <label for="rb_multiple" style="margin-left:245px; clear:both; text-align:left;">
                                        <input dojoType="dijit.form.RadioButton" type="radio" name="rb_tipo_pregunta" id="rb_multiple" value="2" /><fmt:message key="ge.seleccion_multiple" />
                                    </label>
                                    <label for="rb_libre" style="margin-left:245px; clear:both; text-align:left;">
                                        <input dojoType="dijit.form.RadioButton" type="radio" name="rb_tipo_pregunta" id="rb_libre" value="3" /><fmt:message key="ge.abierta" />
                                    </label>
                                </div>
                                <div class="clearthefloats"></div>
                                <div class="botones_administracion">
                                    <button id="btn_registrar_p" dojoType="dijit.form.Button" onclick="accion_pregunta('registrar');" style="margin:0 0 10px 80px;">
                                        <fmt:message key="ge.agregar" />
                                    </button>
                                    <button id="btn_modificar_p" dojoType="dijit.form.Button" onclick="accion_pregunta('modificar');" class="div_form">
                                        <fmt:message key="ge.modificar" />
                                    </button>
                                    <button id="btn_eliminar_p" dojoType="dijit.form.Button" onclick="accion_pregunta('eliminar');" class="div_form">
                                        <fmt:message key="ge.eliminar" />
                                    </button>
                                    <button id="btn_cancelar_p" dojoType="dijit.form.Button" onclick="limpiar_pregunta();" class="div_form">
                                        <fmt:message key="ge.cancelar" />
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="clearthefloats"></div><br />
                    <div id="tbl_preguntas" style="width:500px; margin:0 auto;"></div>

                    <jsp:include page="alternativa.jsp" />
                    <div class="clearthefloats"></div>
                </div>
            </div>

            <div class="clearthefloats"></div>
            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>