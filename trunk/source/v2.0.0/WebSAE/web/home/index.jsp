<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><fmt:message key="ge.tituloProyecto" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />

        <jsp:include page="declaracion.jsp" />
    </head>
    <body class="yui-skin-sam tundra">
        <div id="div_body_general">
            <div id="div_cabecera">
                <jsp:include page="/menu/menu_general.jsp" >
                    <jsp:param name="index" value="true"></jsp:param>
                </jsp:include>
            </div>
            <div id="div_banner_superior">
                <div class="div_menu">
                    <jsp:include page="/menu/encabezado.jsp" />
                </div>
                <jsp:include page="/vistas/logo_websae.jsp" />
            </div>

            <div id="div_contenedor">

                <div id="div_eventos">
                    <span class="text_orange_uppercase_tittle"><fmt:message key="ge.eventos" /><br /></span>
                    <div id="div_detalle_eventos">
                        <!--span class="text_black_date_event">[18 jun 2009 - 20 jun 2009]</span><br />
                        <a href="evento/Controlador_Eventos?id_evento=51" class="text_blue_tittle_event">
                            Jornadas de Sistemas de Telecomunicaciones 2009
                        </a><br />
                        <span class="text_black_event">Cuenca</span><br /-->
                    </div>
                </div>

                <div id="div_informacion_centro">
                    <span class="text_orange_uppercase_tittle"><fmt:message key="ind.bienvenido_portal" /><br /></span>
                    <span class="text_black_event">
                        <fmt:message key="ind.descripcion1" />
                    </span>
                    <div>
                        <br />
                        <span class="text_orange_uppercase_tittle"><fmt:message key="ind.areas_investigacion" /></span>
                        <div id="div_grupos_investigacion" style="border: dotted 1px silver;">
                            <!--ul class="pane-list">
                                <li>
                                    <h3><a href="#" class="text_black_uppercase_paper_link"><span><fmt:message key="ge.grp.software" /></span></a></h3>
                                    <span class="text_black_event"><fmt:message key="ge.grp.software.desc" /></span>
                                </li>
                                <li>
                                    <h3><a href="#" class="text_black_uppercase_paper_link"><span><fmt:message key="ge.grp.robotica" /></span></a></h3>
                                    <span class="text_black_event"><fmt:message key="ge.grp.robotica.desc" /></span>
                                </li>
                                <li>
                                    <h3><a href="#" class="text_black_uppercase_paper_link"><span><fmt:message key="ge.grp.telecomunicaciones" /></span></a></h3>
                                    <span class="text_black_event"><fmt:message key="ge.grp.telecomunicaciones.desc" /></span>
                                </li>
                                <li>
                                    <h3><a href="#" class="text_black_uppercase_paper_link"><span>Comunidad de Software Libre</span></a></h3>
                                    <span class="text_black_event"><fmt:message key="ge.grp.telecomunicaciones.desc" /></span>
                                </li>
                            </ul-->
                        </div>
                    </div>
                </div>
                <div id="div_login">
                    <div id="div_sub_login">
                        <form dojoType="dijit.form.Form" id="form_sesion" name="form_sesion" action="">
                            <div>
                                <div class="titulo_login">
                                    <fmt:message key="ind.ingreso_sistema" />
                                </div>
                                <label for="txt_usuario" class="form_label"><fmt:message key="ind.correo" /></label>
                                <input id="txt_usuario" name="txt_usuario"  class="form_objects"
                                       dojoType="dijit.form.ValidationTextBox" trim="true" required="true"
                                       promptMessage="<fmt:message key="ge.necesita_valor" />"
                                       regExp=".*@.*" invalidMessage="<fmt:message key="ge.formato_invalido" />"
                                       size="50" maxlength="50" type="text" /><br />
                                <div class="clearthefloats"></div>

                                <label for="txt_clave" class="form_label"><fmt:message key="ind.clave" /></label>
                                <input id="txt_clave" name="txt_clave"  class="form_objects"
                                       dojoType="dijit.form.ValidationTextBox" trim="true" required="true"
                                       promptMessage="<fmt:message key="ge.necesita_valor" />"
                                       size="15" maxlength="15" type="password" /><br/>
                                <div class="clearthefloats"></div>

                                <button class="form_button" dojoType="dijit.form.Button" onclick="validar();" ><fmt:message key="ge.ingresar" /></button>
                                <div class="clearthefloats"></div>
                                <br />
                                <a href="/websae/suscripcion/index.jsp" class="text_black_uppercase_paper_link">
                                    <fmt:message key="ms.crear_cuenta" />
                                </a>
                                <div class="clearthefloats"></div>
                                <a href="/websae/recordar_clave/index.jsp" class="text_black_uppercase_paper_link">
                                    <fmt:message key="ge.olvido_contrasena" />
                                </a>
                            </div>
                        </form>
                    </div>
                    <div id="div_sesion" dojoType="dijit.layout.ContentPane" align='center'>
                        <a href='../F_cerrar_sesion' class='text_black_uppercase_paper_link'>
                            <fmt:message key="ge.cerrar_sesion" />
                        </a>
                    </div>
                </div>

                <div class="clearthefloats"></div>
            </div>

            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>

        </div>
        <!--div style="float:right;" dojoType="dijit.form.DropDownButton">
            <span>Notificaciones</span>
            <div dojoType="dijit.TooltipDialog" id="notificaciones" title="Notificaciones" execute="" style="width:220px;">
                <div id="div_notificaciones" style="text-align:left;"></div>
            </div>
        </div-->
    </body>
</html>