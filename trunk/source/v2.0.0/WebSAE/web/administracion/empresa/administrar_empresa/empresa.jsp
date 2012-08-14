<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_empresa" dojoType="dijit.Dialog" title="<fmt:message key="adm.empresa" />" style="width:480px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_empresa" name="frm_empresa" action="">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_empresa" name="txt_id_empresa" value="" />
            <div dojoType="dojo.data.ItemFileReadStore" jsId="tipos_empresasStore" url="/websae/F_mostrar_datos?tipo=mostrar_tipos_empresas"></div>
            <label class="info_modificar"><fmt:message key="us.tipo_empresa" /></label>
            <input name="cmb_tipo_empresa" id="cmb_tipo_empresa"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="tipos_empresasStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <form name="form" action="" method="POST" enctype="multipart/form-data">
                <label id="logo_imagen" class="info_modificar"><img src="/websae/images/logo_empresa/default.JPG" height="40px" alt="" /></label>
                <input type="hidden" name="txt_logo2" id="txt_logo2" value="" />
                <input type="file" name="txt_logo" id="txt_logo" size="15px" style="float:left;" />
                <button style="float:left; margin-top:-1px;" id="buttonUpload" dojoType="dijit.form.Button" onclick="cargar_logo('images','logo_empresa','txt_logo');">
                    <fmt:message key="ge.subir" />
                </button>

                <div class="clearthefloats"></div>
                <!--iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe-->
            </form>

            <label class="info_modificar"><fmt:message key="us.empresa_universidad" />:</label>
            <input type="text" id="txt_empresa" name="txt_empresa"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="50" class="div_form" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.siglas" />:</label>
            <input name="txt_siglas" id="txt_siglas" maxlength="20" dojoType="dijit.form.ValidationTextBox" />
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.direccion" /></label>
            <textarea id="txt_direccion" name="txt_direccion" cols="30" rows="2" maxlength="300" onkeyup="return ismaxlength(this)"></textarea>
            <div class="clearthefloats"></div>

            <div dojoType="dojo.data.ItemFileReadStore" jsId="paisStore" url="/websae/F_mostrar_datos?tipo=mostrar_paises"></div>
            <label class="info_modificar"><fmt:message key="us.pais" /></label>
            <input name="cmb_pais" id="cmb_pais"
                   dojoType="dijit.form.FilteringSelect"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="paisStore" searchAttr="nombre" required="true"
                   onchange="cargar_ciudades_empresa" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <div dojoType="dojo.data.ItemFileReadStore" jsId="ciudad_empresaStore" url="/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=ECU"></div>
            <label class="info_modificar"><fmt:message key="us.ciudad" /></label>
            <input name="cmb_ciudad_empresa" id="cmb_ciudad_empresa" dojoType="dijit.form.FilteringSelect"
                   value="32669"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   store="ciudad_empresaStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.codigo_postal" />:</label>
            <input type="text" id="txt_codigo_postal" name="txt_codigo_postal" trim="true"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
                   maxlength="5" />
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.telefono" /></label>
            <input type="text" id="txt_telefono" name="txt_telefono" trim="true"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
                   maxlength="14" />
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.fax" />:</label>
            <input type="text" id="txt_fax" name="txt_fax" trim="true"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
                   maxlength="14" />
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.url" />:</label>
            <input id="txt_url" name="txt_url" dojoType="dijit.form.ValidationTextBox"
                   value=""  maxlength="50"
                   regExp="(ftp|http|https):\/\/(bloq|(\w+:{0,1}\w*@))?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?"
                   invalidMessage="<fmt:message key="ge.formato_invalido" />, e.: http://www.google.com" />
                   <div class="clearthefloats"></div>
            <br />
            <div class="botones_administracion">
                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_empresa('registrar');" class="div_form"style="margin-left:50px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_empresa('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_empresa('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_empresa').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_empresa').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
        </div>
    </form>
</div>