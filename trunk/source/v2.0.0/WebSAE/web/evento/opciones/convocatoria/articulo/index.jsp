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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ge.subir_paper" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />

        <jsp:include page="declaracion.jsp" />
    </head>
    <body class="yui-skin-sam tundra" onload="inicializar('${lang}', <c:choose><c:when test="${usuario != null}">true</c:when><c:otherwise>false</c:otherwise></c:choose> )">
        <div id="div_body_general">
            <div id="div_cabecera">
                <jsp:include page="/menu/menu_general.jsp" />
            </div>
            <div id="div_banner_superior">
                <div class="div_menu">
                    <jsp:include page="/menu/encabezado.jsp" />
                </div>
                <jsp:include page="/vistas/logo_evento.jsp" />
            </div>

            <div id="div_contenedor_admin">

                <jsp:include page="/vistas/menu_evento_usuario.jsp" />
                <jsp:include page="/vistas/info_usuario.jsp" />

                <div id="ubicacion">
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <a href="/websae/evento/opciones/convocatoria/index.jsp"><fmt:message key="adm.convocatoria" /></a> ::. <span class="ubicacion"><fmt:message key="ge.subir_paper" /></span>
                </div>
                <div id="div_informacion_admin" style="">
                    <div class="titulo-principal">${nombre_evento}</div>
                    <div style="margin: 20px;">

                        <div id="descripcion">
                            <c:if test="${usuario == null}">
                                <form id="form_sesion" name="form_sesion" dojoType="dijit.form.Form" action="" style="margin: 15px 10%; padding:10px 2%; border: dotted 1px silver;">
                                    <div>
                                        <fmt:message key="ge.logeo_necesario" /><br /><br />
                                        <div class="clearthefloats"></div>
                                        <label for="txt_usuario" style="float:left; margin:0 0 0 50px;"><fmt:message key="ind.correo" /></label>
                                        <input id="txt_usuario" name="txt_usuario"  class="form_objects"
                                               dojoType="dijit.form.ValidationTextBox" trim="true" required="true"
                                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                                               regExp=".*@.*" invalidMessage="<fmt:message key="ge.formato_invalido" />"
                                               lowercase="true" size="50" maxlength="50" type="text" /><br />
                                        <div class="clearthefloats"></div>

                                        <label for="txt_clave" style="float:left; margin:0 0 0 50px;"><fmt:message key="ind.clave" /></label>
                                        <input id="txt_clave" name="txt_clave"  class="form_objects"
                                               dojoType="dijit.form.ValidationTextBox" trim="true" required="true"
                                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                                               lowercase="true" size="15" maxlength="15" type="password" /><br/>
                                        <div class="clearthefloats"></div>

                                        <button dojoType="dijit.form.Button" onclick="validar();" style="margin:0 0 0 155px;" >
                                            <fmt:message key="ge.ingresar" />
                                        </button>
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
                            </c:if>
                            <c:if test="${usuario != null}">
                                <form id="frm_articulo" name="frm_articulo" dojoType="dijit.form.Form" action="">
                                    <div style="margin-left:50px;">
                                        <input type="hidden" id="id_articulo" name="id_articulo" value="-1" />
                                        <input type="hidden" id="estado_articulo" name="estado_articulo" value="" />
                                        <label style="float:left; width:130px;"><fmt:message key="ge.nombre_articulo" />:</label>
                                        <input id="txt_nombre_articulo" name="txt_nombre_articulo" dojoType="dijit.form.ValidationTextBox" 
                                               value="" required="true" maxlength="100"
                                               invalidMessage="<fmt:message key="ge.necesita_valor" />"
                                               style="float:left; width:200px;" />
                                        <span class="lblasterisco"> (*)</span>
                                        <div class="clearthefloats"></div>

                                        <label style="float:left; width:130px;"><fmt:message key="ge.resumen" />:</label>
                                        <textarea id="txt_resumen" name="txt_resumen"
                                                  dojoType="dijit.form.SimpleTextarea" required="true"
                                                  invalidMessage="<fmt:message key="ge.necesita_valor" />"
                                                  maxlength="900"
                                                  style="float:left; width:194px; height:50px;" ></textarea>
                                        <span class="lblasterisco"> (*)</span>
                                        <div class="clearthefloats"></div>

                                        <div id="div_temas"></div>
                                        <div class="clearthefloats"></div>

                                        <label style="float:left; width:130px; padding-top:10px;"><fmt:message key="ge.autor" />:</label>
                                        <div style="width:300px; float:left; padding:10px 0 10px 0;">
                                            <button dojoType="dijit.form.Button" onclick="popup_autores();" style="margin-left:-2px;">
                                                + autor
                                            </button><br /><br />
                                            <div id="div_autores"></div>
                                        </div>
                                        <div class="clearthefloats"></div>

                                        <form name="form" action="" method="POST" enctype="multipart/form-data" target="upload_target" >
                                            <div>
                                                <label style="float:left; width:130px;"><fmt:message key="ge.archivo" />:</label>
                                                <input type="hidden" id="txt_articulo2" name="txt_articulo2" value="" />
                                                <input type="file" name="txt_articulo" id="txt_articulo" size="15px" style="float:left;" />
                                                <button style="float:left; margin-top:-1px;" id="buttonUpload" dojoType="dijit.form.Button" onclick="return ajaxFileUpload('archivo','articulo','txt_articulo');">
                                                    <fmt:message key="ge.subir" />
                                                </button>
                                                <div class="clearthefloats"></div>
                                            </div>
                                        </form>
                                        <div class="clearthefloats"></div>

                                        <button id="btn_agregar" dojoType="dijit.form.Button" onclick="accion_articulo('subir');" style="margin: 10px 0 0 200px;">
                                            <fmt:message key="ge.agregar" />
                                        </button>
                                        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_articulo('modificar');" style="margin: 10px 0 0 120px;">
                                            <fmt:message key="ge.modificar" />
                                        </button>
                                        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="limpiar();" style="margin: 10px 0 0 10px;">
                                            <fmt:message key="ge.cancelar" />
                                        </button><br /><br />
                                        <div class="clearthefloats"></div>
                                    </div>
                                </form>
                                <br />
                                <!--div style="text-align:center; width:540px; border:1px dotted silver;font-weight:bolder;margin:0 0 15px 0;padding:5px;">Artículos Subidos</div-->
                                <div id="tbl_articulos" style="width:410px; margin:0 auto 0 auto;"></div>
                            </c:if>
                        </div>
                    </div>

                    <div id="div_autor" dojoType="dijit.Dialog" title="<fmt:message key="ge.autor" />" style="width:370px;">
                        <form id="frm_autor" name="frm_autor" dojoType="dijit.form.Form" action="">
                            <div>
                                <label style="float:left; width:130px; text-align:right; margin-right:5px;"><fmt:message key="us.correo_electronico" /></label>
                                <input id="email" name="email" dojoType="dijit.form.ValidationTextBox"
                                       trim="true" required="true" lowercase="true" size="50" maxlength="50" type="text"
                                       promptMessage="<fmt:message key="ge.necesita_valor" />" />
                                <span class="lblasterisco"> (*)</span>
                                <div class="clearthefloats"></div>

                                <label style="float:left; width:130px; text-align:right; margin-right:5px;"><fmt:message key="ge.autor_principal" />?</label>
                                <input id="autor_principal" name="autor_principal" dojoType="dijit.form.CheckBox" 
                                       value="true" style="float:left;"/>
                                <div class="clearthefloats"></div>
                                <button dojoType="dijit.form.Button" onclick="accion_autor('agregar',dojo.byId('email').value);" style="margin: 10px 0 0 120px;">
                                    <fmt:message key="ge.agregar" />
                                </button>
                                <button dojoType="dijit.form.Button" onclick="dijit.byId('div_autor').hide();" style="margin: 10px 0 0 10px;">
                                    <fmt:message key="ge.cancelar" />
                                </button><br /><br />
                            </div>
                        </form>
                    </div>
                    <div id="div_archivos" dojoType="dijit.Dialog" title="<fmt:message key="ge.archivos" />" style="width:650px;">
                        <div id="tbl_archivos" style="width:610px; margin:0 auto 0 auto;"></div>
                        <div class="clearthefloats"></div>
                        <label style="float:left; width:500px; margin:15px 0 0 15px; text-align:left; font-size:small;">Para descargar un archivo, dar clic derecho, y poner Guardar Como...</label>
                        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_archivos').hide();" style="float:right; margin:10px 0;">
                            <fmt:message key="ge.cerrar" />
                        </button><br /><br />
                    </div>
                    <div id="dlg_evaluacion" dojoType="dijit.Dialog" title="<fmt:message key="ge.evaluacion" />" style="min-width:600px;">
                        <div id="tbl_evaluacion"></div>
                        <div class="clearthefloats"></div>
                        <button dojoType="dijit.form.Button" onclick="dijit.byId('dlg_evaluacion').hide();" style="float:right; margin:10px 0;">
                            <fmt:message key="ge.cerrar" />
                        </button><br /><br />
                    </div>
                </div>
                <div class="clearthefloats"></div>
                <div id="div_copyrigth">
                    <jsp:include page="/pie_pagina.jsp" />
                </div>
            </div>
    </body>
</html> 