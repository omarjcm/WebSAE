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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="mc.modificar_cuenta" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

        <link href="css/menu.css" rel="stylesheet" type="text/css" />
        <link href="css/links.css" rel="stylesheet" type="text/css" />
        <link href="css/evento.css" rel="stylesheet" type="text/css" />
        <link href="css/div.css" rel="stylesheet" type="text/css" />
        <link href="css/texto.css" rel="stylesheet" type="text/css" />
        <link href="css/forms.css" rel="stylesheet" type="text/css" />

        <jsp:include page="js/SpryAssets/spryassets.jsp" />

        <link type="text/css" href="css/custom-theme/jquery-ui-1.7.1.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.7.1.custom.min.js"></script>

        <script type="text/javascript" src="js/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/administrar_cuenta.js"></script>

        <style type="text/css">
            /*demo page css*/
            .demoHeaders { margin-top: 2em; }
            #dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
            #dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
            ul#icons {margin: 0; padding: 0;}
            ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
            ul#icons span.ui-icon {float: left; margin: 0 4px;}
        </style>
    </head>

    <body class="centrado" >
        <div id="div_body_general">
            <div class="align_right">
                <jsp:include page="menu_general.jsp" />
            </div>
            <jsp:include page="encabezado.jsp" />
            <div id="div_body_evento">
                <div id="div_izquierdo">
                    <div id="div_texto_izquierdo">
                        <span class="text_black_uppercase">
                            <fmt:message key="mc.modificar_cuenta" />
                        </span>
                        <p class="text_black_event">
                            <fmt:message key="mc.informacion" /> <a href="mailto:ayudantevlir@fiec.espol.edu.ec">ayudantevlir@fiec.espol.edu.</a>
                        </p>
                    </div>
                </div>
                <div id="div_central">
                    <div>
                        <form id="frmRegistro" method="post" action="javascript:modidicar_cuenta()">
                            <fieldset>
                                <div class="mensaje_estatico_bold">
                                    <fmt:message key="ge.campos_obligatorios" />
                                </div>
                                <div class="legend_rojo">
                                    <fmt:message key="mc.modificar_cuenta" />
                                </div>
                                <div class="medidas" spry:region="SpryUsuarioInfoJSON"  id="SpryCorreo">
                                    <label>
                                        <fmt:message key="us.correo_electronico" />
                                    </label>
                                    <input type="text" id="txt_correo" name="txt_correo" maxlength="50" disabled="true" value="{correo}"/>
                                    <span class="textfieldInvalidFormatMsg">
                                        <fmt:message key="ge.formato_invalido" />
                                    </span>
                                </div>
                                <div class="legend_rojo">
                                    <br /><fmt:message key="us.datos_personales" />
                                </div>
                                <div class="medidas" spry:region="SpryUsuarioInfoJSON" id="SpryNombres" >
                                    <label>
                                        <fmt:message key="us.nombres" />
                                    </label>
                                    <input type="text" id="txt_nombres" name="txt_nombres" maxlength="30" value="{nombres}" />
                                    <span class="lblasterisco"> (*)</span>
                                    <span class="textfieldRequiredMsg">
                                        <fmt:message key="ge.necesita_valor" />
                                    </span>
                                    <script type="text/javascript">
                                        var SpryNombres = new Spry.Widget.ValidationTextField("SpryNombres", "none", {validateOn:["blur","change"], isRequired:true, minValue: 999999, allowNegative:false});
                                    </script>
                                </div>
                                <div class="medidas" spry:region="SpryUsuarioInfoJSON" id="SpryApellidos">
                                    <label>
                                        <fmt:message key="us.apellidos" />
                                    </label>
                                    <input type="text" id="txt_apellidos" name="txt_apellidos" maxlength="30" value="{apellidos}" />
                                    <span class="lblasterisco"> (*)</span>
                                    <span class="textfieldRequiredMsg">
                                        <fmt:message key="ge.necesita_valor" />
                                    </span>
                                    <script type="text/javascript">
                                        var SpryApellidos = new Spry.Widget.ValidationTextField("SpryApellidos","none", {validateOn:["blur","change"], isRequired:true, minValue: 999999, allowNegative:false});
                                    </script>
                                </div>
                                <div class="medidas">
                                    <label>
                                        <fmt:message key="us.genero" />
                                    </label>
                                </div>
                                <div class="check" id="SpryGenero" spry:region="SpryUsuarioInfoJSON">
                                    <input id="opt_genero_0" name="genero" type="radio" value="m" checked="checked" />
                                    <label>
                                        <fmt:message key="us.masculino" />
                                    </label>
                                    <input id="opt_genero_1" name="genero" type="radio" value="f" />
                                    <label>
                                        <fmt:message key="us.femenino" />
                                    </label>
                                    <span class="lblasterisco"> (*)</span>
                                </div>
                                <div class="medidas" id="SpryDireccion" spry:region="SpryUsuarioInfoJSON">
                                    <label>
                                        <fmt:message key="us.direccion" />
                                    </label>
                                    <textarea id="txt_direccion" name="txt_direccion" cols="30" rows="2" onclick="cargar_datos()">{direccion}</textarea>
                                </div>
                                <div class="medidas" id="SpryTelefono" spry:region="SpryUsuarioInfoJSON">
                                    <label>
                                        <fmt:message key="us.telefono" />
                                    </label>
                                    <input type="text" id="txt_telefono" name="txt_telefono" maxlength="14" value="{telefono}" />
                                    <span class="textfieldInvalidFormatMsg">
                                        <fmt:message key="ge.solo_numeros" />
                                    </span>
                                    <span class="textfieldMinValueMsg">
                                        <fmt:message key="ge.minimo7d" />
                                    </span>
                                    <script type="text/javascript">
                                        var SpryTelefono = new Spry.Widget.ValidationTextField("SpryTelefono", "integer", {validateOn:["blur","change"], minValue: 999999, isRequired:false, allowNegative:false});
                                    </script>
                                </div>
                                <div class="medidas" id="SpryCelular" spry:region="SpryUsuarioInfoJSON">
                                    <label>
                                        <fmt:message key="us.celular" />
                                    </label>
                                    <input type="text" id="txt_celular" name="txt_celular" maxlength="14" value="{celular}" />
                                    <span class="textfieldInvalidFormatMsg">
                                        <fmt:message key="ge.solo_numeros" />
                                    </span>
                                    <span class="textfieldMinValueMsg">
                                        <fmt:message key="ge.minimo7d" />
                                    </span>
                                    <script type="text/javascript">
                                        var SpryCelular = new Spry.Widget.ValidationTextField("SpryCelular", "integer", {validateOn:["blur","change"], minValue: 999999, isRequired:false, allowNegative:false});
                                    </script>
                                </div>
                                <div class="medidas" id="SpryNacimiento" spry:region="SpryUsuarioInfoJSON">
                                    <label>
                                        <fmt:message key="us.fecha_nacimiento" />
                                    </label>
                                    <input id="txt_nacimiento" name="txt_nacimiento" type="text" value="{nacimiento}" />
                                    <span class="textfieldInvalidFormatMsg">
                                        <fmt:message key="ge.formato_invalido" />
                                    </span>
                                    <script type="text/javascript">
                                        var SpryNacimiento = new Spry.Widget.ValidationTextField("SpryNacimiento", "date", {format:"yyyy-mm-dd", isRequired:false, useCharacterMasking:true, validateOn:["blur"]});
                                    </script>
                                </div>
                                <div class="medidas" spry:region="SpryUsuarioInfoJSON SpryPaisesJSON" id="SpryPais">
                                    <label>
                                        <fmt:message key="us.pais" />
                                    </label>
                                    <select class="ancho_maximo_select" name="cmb_pais" id="cmb_pais" onchange="cargar_ciudades(this.value)" spry:repeatchildren="SpryPaisesJSON">
                                        <option spry:if="'{SpryPaisesJSON::id}'=='{SpryUsuarioInfoJSON::pais}'" selected="selected" value="{SpryPaisesJSON::id}">{SpryPaisesJSON::nombre}</option>
                                        <option spry:if="'{SpryPaisesJSON::id}'!='{SpryUsuarioInfoJSON::pais}'" value="{SpryPaisesJSON::id}">{SpryPaisesJSON::nombre}</option>
                                    </select>
                                    <span class="lblasterisco"> (*)</span>
                                </div>
                                <div class="medidas" id="SpryCiudad" spry:region="SpryUsuarioInfoJSON SpryCiudadesJSON">
                                    <label>
                                        <fmt:message key="us.ciudad" />
                                    </label>
                                    <select class="ancho_maximo_select" name="cmb_ciudad" id="cmb_ciudad" spry:repeatchildren="SpryCiudadesJSON">
                                        <option spry:if="'-1'=='{SpryUsuarioInfoJSON::ciudad}' && {SpryCiudadesJSON::ds_RowNumber}==0" value="-1" selected="selected"><fmt:message key="ge.seleccionar" /></option>
                                        <option spry:if="'-1'!='{SpryUsuarioInfoJSON::ciudad}' && {SpryCiudadesJSON::ds_RowNumber}==0" value="-1"><fmt:message key="ge.seleccionar" /></option>
                                        <option spry:if="'{SpryCiudadesJSON::id}'=='{SpryUsuarioInfoJSON::ciudad}'" selected="selected" value="{SpryCiudadesJSON::id}">{SpryCiudadesJSON::nombre}</option>
                                        <option spry:if="'{SpryCiudadesJSON::id}'!='{SpryUsuarioInfoJSON::ciudad}'" value="{SpryCiudadesJSON::id}">{SpryCiudadesJSON::nombre}</option>
                                    </select>
                                    <span class="lblasterisco"> (*)</span>
                                    <span class="selectInvalidMsg">
                                        <fmt:message key="ge.necesita_valor" />
                                    </span>
                                </div>
                                <div class="legend_rojo">
                                    <br /><fmt:message key="us.informacion_laboral" />
                                </div>
                                <div class="medidas" id="SpryTitulo" spry:region="SpryTitulosJSON SpryUsuarioInfoJSON">
                                    <label>
                                        <fmt:message key="us.carrera_titulo" />
                                    </label>
                                    <select class="ancho_maximo_select" name="cmb_titulo" id="cmb_titulo" spry:repeatchildren="SpryTitulosJSON">
                                        <option spry:if="'-1'=='{SpryUsuarioInfoJSON::titulo}' && {SpryTitulosJSON::ds_RowNumber}==0" value="-1" selected="selected"><fmt:message key="ge.seleccionar" /></option>
                                        <option spry:if="'-1'!='{SpryUsuarioInfoJSON::titulo}' && {SpryTitulosJSON::ds_RowNumber}==0" value="-1"><fmt:message key="ge.seleccionar" /></option>
                                        <option spry:if="'{SpryTitulosJSON::id}'=='{SpryUsuarioInfoJSON::titulo}'" selected="selected" value="{SpryTitulosJSON::id}">{SpryTitulosJSON::nombre}</option>
                                        <option spry:if="'{SpryTitulosJSON::id}'!='{SpryUsuarioInfoJSON::titulo}'" value="{SpryTitulosJSON::id}">{SpryTitulosJSON::nombre}</option>
                                    </select>
                                </div>
                                <div class="medidas" id="SpryTipoEmpresa" spry:region="SpryUsuarioInfoJSON SpryTipoEmpresaJSON">
                                    <label>
                                        <fmt:message key="us.tipo_empresa" />
                                    </label>
                                    <select name="cmb_tipo_empresa" id="cmb_tipo_empresa" class="ancho_maximo_select" onchange="cargar_tipoEmpresa(this.value)" spry:repeatchildren="SpryTipoEmpresaJSON">
                                        <option spry:if="'-1'=='{SpryUsuarioInfoJSON::tipoEmpresa}' && {SpryTipoEmpresaJSON::ds_RowNumber}==0" value="-1" selected="selected"><fmt:message key="ge.seleccionar" /></option>
                                        <option spry:if="'-1'!='{SpryUsuarioInfoJSON::tipoEmpresa}' && {SpryTipoEmpresaJSON::ds_RowNumber}==0" value="-1"><fmt:message key="ge.seleccionar" /></option>
                                        <option spry:if="'{SpryTipoEmpresaJSON::id_tipo_empresa}'=='{SpryUsuarioInfoJSON::tipoEmpresa}'" selected="selected" value="{SpryTipoEmpresaJSON::id_tipo_empresa}">{SpryTipoEmpresaJSON::nombre}</option>
                                        <option spry:if="'{SpryTipoEmpresaJSON::id_tipo_empresa}'!='{SpryUsuarioInfoJSON::tipoEmpresa}'" value="{SpryTipoEmpresaJSON::id_tipo_empresa}">{SpryTipoEmpresaJSON::nombre}</option>
                                    </select>
                                </div>
                                <div class="medidas" id="SpryCargo" spry:region="SpryUsuarioInfoJSON SpryCargosJSON">
                                    <label>
                                        <fmt:message key="us.cargo" />
                                    </label>
                                    <span spry:if="{SpryCargosJSON::ds_RowNumber}<0">
                                        <select class="ancho_maximo_select">
                                            <option value="-1"><fmt:message key="us.seleccionar_tipo_empresa" /></option>
                                        </select>
                                    </span>
                                    <span spry:if="{SpryCargosJSON::ds_RowNumber}>=0">
                                        <select name="cmb_cargo" id="cmb_cargo" class="ancho_maximo_select" spry:repeatchildren="SpryCargosJSON">
                                            <option spry:if="'-1'=='{SpryUsuarioInfoJSON::cargo}' && {SpryCargosJSON::ds_RowNumber}==0" value="-1" selected="selected"><fmt:message key="us.seleccionar_tipo_empresa" /></option>
                                            <option spry:if="'-1'!='{SpryUsuarioInfoJSON::cargo}' && {SpryCargosJSON::ds_RowNumber}==0" value="-1">us.seleccionar_tipo_empresa</option>
                                            <option spry:if="'{SpryCargosJSON::id}'=='{SpryUsuarioInfoJSON::cargo}'" selected="selected" value="{SpryCargosJSON::id}">{SpryCargosJSON::nombre}</option>
                                            <option spry:if="'{SpryCargosJSON::id}'!='{SpryUsuarioInfoJSON::cargo}'" value="{SpryCargosJSON::id}">{SpryCargosJSON::nombre}</option>
                                        </select>
                                    </span>
                                </div>
                                <div class="medidas" id="SpryEmpresa" spry:region="SpryUsuarioInfoJSON SpryEmpresasJSON">
                                    <label id="lbl_empresa">
                                        <fmt:message key="us.empresa_universidad" />
                                    </label>
                                    <span spry:if="{SpryEmpresasJSON::ds_RowNumber}<0">
                                        <select class="ancho_maximo_select">
                                            <option value="-1"><fmt:message key="us.seleccionar_tipo_empresa" /></option>
                                        </select>
                                    </span>
                                    <span spry:if="{SpryEmpresasJSON::ds_RowNumber}>=0">
                                        <select name="cmb_empresa" id="cmb_empresa" class="ancho_maximo_select" onchange="crear_empresa(this.value)" spry:repeatchildren="SpryEmpresasJSON">
                                            <option spry:if="'-1'=='{SpryUsuarioInfoJSON::empresa}' && {SpryEmpresasJSON::ds_RowNumber}==0" value="-1" selected="selected"><fmt:message key="us.seleccionar_tipo_empresa" /></option>
                                            <option spry:if="'-1'!='{SpryUsuarioInfoJSON::empresa}' && {SpryEmpresasJSON::ds_RowNumber}==0" value="-1"><fmt:message key="us.seleccionar_tipo_empresa" /></option>
                                            <option spry:if="{SpryEmpresasJSON::ds_RowNumber}==0" value="-5"><fmt:message key="us.ingresar_nueva_empresa" /></option>
                                            <option spry:if="'{SpryEmpresasJSON::id_empresa}'=='{SpryUsuarioInfoJSON::empresa}'" selected="selected" value="{SpryEmpresasJSON::id_empresa}">{SpryEmpresasJSON::nombre}</option>
                                            <option spry:if="'{SpryEmpresasJSON::id_empresa}'!='{SpryUsuarioInfoJSON::empresa}'" value="{SpryEmpresasJSON::id_empresa}">{SpryEmpresasJSON::nombre}</option>
                                        </select>
                                    </span>
                                </div>
                                <div class="medidas" id="SpryTelefonoOficina" spry:region="SpryUsuarioInfoJSON">
                                    <label>
                                        <fmt:message key="us.telefono_oficina" />
                                    </label>
                                    <input type="text" name="txt_telefono_oficina" id="txt_telefono_oficina" maxlength="14" value="{telefonoOficina}" />
                                    <span class="textfieldInvalidFormatMsg">
                                        <fmt:message key="ge.solo_numeros" />
                                    </span>
                                    <span class="textfieldMinValueMsg">
                                        <fmt:message key="ge.minimo7d" />
                                    </span>
                                </div>
                                <br />
                            </fieldset>
                            <div id="div_mensaje_espera" class="mensaje"></div>
                            <div id="mensaje_usuario" class="mensaje"></div>
                            <div class="div_align_center">
                                <input name="btnAceptar" type="submit" class="boton" value="<fmt:message key="ge.enviar_formulario" />" />
                            </div>
                        </form>
                        <script type="text/javascript">
                            var SpryGenero = new Spry.Widget.ValidationRadio("SpryGenero");
                            var SpryDireccion = new Spry.Widget.ValidationTextarea("SpryDireccion", {isRequired:false, validateOn:["blur"]});
                            var SpryPais = new Spry.Widget.ValidationSelect("SpryPais", {validateOn:["blur"]});
                            var SpryCiudad = new Spry.Widget.ValidationSelect("SpryCiudad", {validateOn:["blur"], invalidValue:"-1", validateOn:["change"]});
                            var SpryTitulo = new Spry.Widget.ValidationSelect("SpryTitulo", {validateOn:["blur"], isRequired:false});
                            var SpryTipoEmpresa = new Spry.Widget.ValidationSelect("SpryTipoEmpresa", {validateOn:["blur"], isRequired:false});
                            var SpryEmpresa = new Spry.Widget.ValidationSelect("SpryEmpresa", {validateOn:["blur"], isRequired:false});
                            var SpryCargo = new Spry.Widget.ValidationSelect("SpryCargo", {validateOn:["blur"], isRequired:false});
                            var SpryTelefonoOficina = new Spry.Widget.ValidationTextField("SpryTelefonoOficina", "integer", {validateOn:["blur","change"], minValue: 999999, isRequired:false, allowNegative:false});
                        </script>
                    </div>
                </div>
                <jsp:include page="pie_pagina.jsp" />
            </div>
            <jsp:include page="menu_submenu.jsp" />
        </div>
    </body>
</html>