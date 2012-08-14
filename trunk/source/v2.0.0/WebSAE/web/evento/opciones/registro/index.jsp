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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="adm.registro" /></title>
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
                <jsp:include page="/vistas/logo_evento.jsp" />
            </div>

            <div id="div_contenedor_admin">
                <jsp:include page="/vistas/menu_evento_usuario.jsp" />
                <jsp:include page="/vistas/info_usuario.jsp" />
                <div id="ubicacion">
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <span class="ubicacion"><fmt:message key="adm.registro" /></span>
                </div>
                <div id="div_informacion_admin">
                    <div id="div_form">
                        <div class="titulo-principal">${nombre_evento}</div>
                        <div class="titulo"><fmt:message key="adm.registro" /></div>
                        <div id="descripcion">
                            <c:if test="${usuario == null}">
                                <form id="form_sesion" name="form_sesion" dojoType="dijit.form.Form" action="" style="margin: 15px 10%; padding:10px 2%; border: dotted 1px silver;">
                                    <div>
                                        <fmt:message key="ge.logeo_necesario" /><br /><br />
                                        <div class="clearthefloats"></div>
                                        <label for="txt_usuario" style="float:left; margin:0 0 0 -120px;"><fmt:message key="ind.correo" /></label>
                                        <input id="txt_usuario" name="txt_usuario"  class="form_objects"
                                               dojoType="dijit.form.ValidationTextBox" trim="true" required="true"
                                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                                               regExp=".*@.*" invalidMessage="<fmt:message key="ge.formato_invalido" />"
                                               lowercase="true" size="50" maxlength="50" type="text" /><br />
                                        <div class="clearthefloats"></div>

                                        <label for="txt_clave" style="float:left; margin:0 0 0 -120px;"><fmt:message key="ind.clave" /></label>
                                        <input id="txt_clave" name="txt_clave"  class="form_objects"
                                               dojoType="dijit.form.ValidationTextBox" trim="true" required="true"
                                               promptMessage="<fmt:message key="ge.necesita_valor" />"
                                               lowercase="true" size="15" maxlength="15" type="password" /><br/>
                                        <div class="clearthefloats"></div>

                                        <button style="margin:0 0 0 155px;" dojoType="dijit.form.Button" onclick="validar();" ><fmt:message key="ge.ingresar" /></button>
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
                                <div id="descripcion_registro" style="margin-left:10px;"></div>
                                <!--form action="https://www.paypal.com/cgi-bin/webscr" method="post">
                                    <input type="hidden" name="cmd" value="_xclick">
                                    <input type="hidden" name="business" value="rafariva@gmail.com">
                                    <input type="hidden" name="item_name" value="hat">
                                    <INPUT TYPE="hidden" name="address_override" value="1">
                                    <input type="hidden" name="item_number" value="123">
                                    <input type="hidden" name="amount" value="15.00">
                                    <input type="hidden" name="first_name" value="John">
                                    <input type="hidden" name="last_name" value="Doe">
                                    <input type="hidden" name="address1" value="9 Elm Street">
                                    <input type="hidden" name="address2" value="Apt 5">
                                    <input type="hidden" name="city" value="Berwyn">
                                    <input type="hidden" name="state" value="PA">
                                    <input type="hidden" name="zip" value="19312">
                                    <input type="hidden" name="night_phone_a" value="610">
                                    <input type="hidden" name="night_phone_b" value="555">
                                    <input type="hidden" name="night_phone_c" value="1234">
                                    <input type="image" name="submit" border="0" src="https://www.paypal.com/en_US/i/btn/btn_buynow_LG.gif" alt="PayPal - The safer, easier way to pay online">
                                </form-->
                                <div id="detalles_registros">
                                    <div id="precio_descuento_evento" class="div_registro_evento">
                                        <br />
                                        <div style="float:left;">
                                            <div id="detalle_categorias" style="float:left;"></div>
                                            <div class="clearthefloats"></div>
                                            <div id="detalle_subcategorias" style="float:left; margin-top:5px;"></div>
                                        </div>
                                        <div id="div_btn_registrar_${id_evento}" style="float:right;"></div>
                                        <div class="clearthefloats"></div>
                                    </div>
                                    <div id="precio_descuento_subevento">
                                        <div id="detalle_subeventos"></div>
                                    </div>
                                </div>
                                <div class="clearthefloats"></div>
                                <!--button id="btn_registrarse" dojoType="dijit.form.Button" onclick="registrarse_evento();" class="div_form">
                                <fmt:message key="ge.registrarse" />
                            </button>
                            <button id="btn_pagar" dojoType="dijit.form.Button" onclick="pagar_evento();" class="div_form">
                                <fmt:message key="ge.pagar" />
                            </button-->
                            <!--div id="div_detalle_subevento" dojoType="dijit.Dialog" title="<fmt:message key="ge.detalle_subevento" />" style="width:440px;">
                                <div id="detalle_subevento"></div>
                                <div id="detalle_agenda_subevento"></div>
                                <div id="detalle_conferencistas_subevento"></div>
                                <div id="detalle_materiales_subevento"></div>
                                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_detalle_subevento').hide();" class="div_form">
                                <fmt:message key="ge.cerrar" />
                            </button><br /><br />
                        </div-->
                            </c:if>
                        </div>
                    </div>
                    <div class="clearthefloats"></div>
                    <div id="detalles_pagos" style="display:none;">
                        <div style="margin:20px 0 0 10px; "><b>Pago de Registros</b></div>
                        <div style="float:left; border:1px dotted silver; margin:10px; padding:5px;" class="div_registro_evento">
                            <label style="float:left;">Detalle de Registro:</label>
                            <div class="clearthefloats"></div>
                            <textarea id="txt_descripcion_registro" name="txt_descripcion_registro"
                                      dojoType="dijit.form.SimpleTextarea" value=" " trim="true" required="false" readonly="true"
                                      style="float:left; width:300px; height:35px; margin:10px;" >
                            </textarea>
                            <div class="clearthefloats"></div>
                            <label style="float:left;">Forma de Pago:&nbsp;&nbsp;</label>
                            <select id='cmb_forma_pago' style="float:left;">
                                <option value="E">Efectivo</option>
                                <option value="P">PayPal</option>
                            </select>
                            <div class="clearthefloats"></div><br />
                            <label style="float:left;">Total a Pagar:&nbsp;&nbsp;</label>
                            <input type="text" id="valor_total" name="valor_total" value="0.00"
                                   dojoType="dijit.form.CurrencyTextBox" style="width:100px;"
                                   required="true" constraints="{fractional:true}" readonly="true"
                                   currency="USD" required="true" />
                            <button id="btn_pagar" dojoType="dijit.form.Button" onclick="pagar_evento();" style="float:right;">
                                <fmt:message key="ge.pagar" />
                            </button>
                            <div id='paypal_boton'></div>
                            <form id="frm_paypal" method="post" action="https://www.sandbox.paypal.com/us/cgi-bin/webscr">
                                <div id="div_paypal" style="display:none;">
                                    <input type="hidden" name="cmd" value="_xclick"/>
                                    <input type="hidden" name="rm" value="2"/>
                                    <input type="hidden" name="bn" value="http://websae.com" />
                                    <input type="hidden" name="business" value="websae_1257117975_biz@gmail.com"/>
                                    <input id="item_name" type="hidden" name="item_name" value="" />
                                    <input type="hidden" name="item_number" value="6-14-12"/>
                                    <input type="hidden" name="amount" value="145.00"/>
                                    <input type="hidden" name="return" value="http://localhost:8081/websae/evento/opciones/registro/index.jsp"/>
                                    <input type="hidden" name="cancel" value=" http://www.hotmail.com"/>
                                    <input type="image" name="submit" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" border="0" align="top" alt="PayPal" />
                                </div>
                            </form>

                        </div>
                    </div>
                </div>

            </div>
            <div class="clearthefloats"></div>
            <form method="post" action="https://www.sandbox.paypal.com/us/cgi-bin/webscr">
                <div>
                    <input type="hidden" name="cmd" value="_xclick"/>
                    <input type="hidden" name="rm" value="2"/>
                    <input type="hidden" name="bn" value="http://websae.com" />
                    <!--input type="hidden" name="upload" value="1"/-->
                    <input type="hidden" name="business" value="websae_1257117975_biz@gmail.com"/>
                    <input type="hidden" name="item_name" value="Evento de Prueba N; SubEvento1; SubEvento3" />
                    <input type="hidden" name="item_number" value="6-14-12"/>
                    <!--input type="hidden" name="custom" value="rafariva"/-->
                    <input type="hidden" name="amount" value="145.00"/>
                    <input type="hidden" name="return" value="http://localhost:8081/websae/evento/opciones/registro/index.jsp"/>
                    <input type="hidden" name="cancel" value=" http://www.hotmail.com"/>
                    <!--input type="submit" value="enviar" /-->
                </div>
            </form>
            <div id="div_copyrigth">
                <jsp:include page="/pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>