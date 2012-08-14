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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.miembro_comite" /></title>
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
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/administracion/index.jsp"><fmt:message key="pistas.administracion" /></a> ::. <a href="/websae/administracion/evento/index.jsp"><fmt:message key="even.eventos" /></a> ::. <a href="/websae/administracion/evento/administrar_evento/index.jsp"><fmt:message key="even.administrar_evento" /></a> ::. <a href="/websae/administracion/evento/opciones/index.jsp"><fmt:message key="adm.opciones_asignadas" /></a> ::. <span class="ubicacion"><fmt:message key="adm.miembros_comite" /></span>
                </div>
                <div id="div_informacion_admin">
                    <div id="opcion-menu">
                        <div id="opcion-menu-item">
                            <a href="/websae/administracion/evento/opciones/index.jsp"><img src="/websae/images/regresar.png" alt="" /><br /><fmt:message key="ge.regresar" /></a>
                        </div>
                        <div class="clearthefloats"></div>
                    </div>
                    <br />
                    <div id="div_form">
                        <div class="titulo-principal">${nombre_evento}</div>
                        <div class="titulo"><fmt:message key="adm.miembro_comite" /></div>

                        <jsp:include page="/administracion/usuario/buscar_usuario.jsp">
                            <jsp:param name="titulo" value="adm.miembro_comite" />
                            <jsp:param name="tipo" value="evaluador_usuario" />
                        </jsp:include>

                        <label style="float:left; margin-top:-1px; ">Administrador:</label>
                        <input id="chk_administrador" name="chk_administrador" dojoType="dijit.form.CheckBox"
                               value="A" checked="checked" style="float: left; margin-top:3px;" />
                        <span id="span_administrador" style="float:left; margin-top:1px;">(Si)</span>
                        <input type="hidden" id="administrador" name="administrador" value="1" />
                        <div class="clearthefloats"></div>
                        <input type="hidden" id="id_miembro" value="-1" />
                        <label style="float:left; margin-top:-1px; ">Evaluador:</label>
                        <input id="chk_evaluador" name="chk_evaluador" dojoType="dijit.form.CheckBox"
                               value="A" checked="checked" style="float: left; margin-top:3px;" />
                        <span id="span_evaluador" style="float:left; margin-top:1px;">(Si)</span>
                        <input type="hidden" id="evaluador" name="evaluador" value="1" />
                        <div class="clearthefloats"></div><br />
                        <div id="div_botones_modificacion" style="display:none;">
                            <button dojoType="dijit.form.Button" onclick="accion_evaluador_modificar('modificar')" style="margin-left:200px;">
                                <fmt:message key="ge.modificar" />
                            </button>
                            <button dojoType="dijit.form.Button" onclick="accion_evaluador_modificar('eliminar')">
                                <fmt:message key="ge.eliminar" />
                            </button>
                            <button dojoType="dijit.form.Button" onclick="limpiar_campos();">
                                <fmt:message key="ge.cancelar" />
                            </button>
                        </div>
                        <div class="clearthefloats"></div><br />
                    </div>
                    <div id="tbl_miembros_comite" style="width:490px; margin:0 auto;"></div>
                </div>
            </div>
            <div class="clearthefloats"></div>

            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>