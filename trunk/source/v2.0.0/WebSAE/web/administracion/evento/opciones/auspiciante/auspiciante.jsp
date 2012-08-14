<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_auspiciante" dojoType="dijit.Dialog" title="<fmt:message key="adm.auspiciante" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_auspiciante" name="frm_auspiciante">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_auspiciante" name="txt_id_auspiciante" value="" />

            <div dojoType="dojo.data.ItemFileReadStore" jsId="tipos_empresasStore" url="/websae/F_mostrar_datos?tipo=mostrar_tipos_empresas"></div>
            <label class="info_modificar"><fmt:message key="us.tipo_empresa" /></label>
            <input name="cmb_tipo_empresa" id="cmb_tipo_empresa"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   store="tipos_empresasStore" searchAttr="nombre" require="true"
                   onchange="cargar_tipo_empresa" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats" />

            <label class="info_modificar"><fmt:message key="adm.auspiciante" /></label>
            <input name="cmb_empresa" id="cmb_empresa"
                   dojoType="dijit.form.FilteringSelect"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   searchAttr="nombre" require="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats" />

            <label class="info_modificar"><fmt:message key="adm.monto" />:</label>
            <input type="text" id="txt_monto" name="txt_monto" value="0.00"
                   dojoType="dijit.form.CurrencyTextBox"
                   required="true" constraints="{fractional:true}"
                   currency="USD" required="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   invalidMessage="<fmt:message key="ge.solo_reales" />" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.descripcion" /></label>
            <textarea id="txt_descripcion" name="txt_descripcion" cols="30" rows="2" maxlength="90" onkeyup="return ismaxlength(this)"></textarea>
            <div class="clearthefloats" />

            <div class="botones_administracion">
                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_auspiciante('registrar');" class="div_form" style="margin-left:50px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_auspiciante('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_auspiciante('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_auspiciante').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_auspiciante').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>
