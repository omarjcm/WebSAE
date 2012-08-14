<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_material" dojoType="dijit.Dialog" title="<fmt:message key="adm.materiales" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_material" name="frm_material">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_material" name="txt_id_material" value="" />
            <div dojoType="dojo.data.ItemFileReadStore" jsId="tipos_materialesStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=tipos_materiales"></div>
            <label class="info_modificar"><fmt:message key="even.tipo_material" /></label>
            <input name="cmb_tipo_material" id="cmb_tipo_material"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="tipos_materialesStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.descripcion" /></label>
            <textarea id="txt_descripcion" name="txt_descripcion" cols="30" rows="2" maxlength="90" onkeyup="return ismaxlength(this)"></textarea>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.precio" />:</label>
            <input type="text" id="txt_precio" name="txt_precio" value="0.00"
                   dojoType="dijit.form.CurrencyTextBox"
                   required="true" constraints="{fractional:true}"
                   currency="USD" required="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   invalidMessage="<fmt:message key="ge.solo_reales" />" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.cantidad" />:</label>
            <input type="text" id="txt_cantidad" name="txt_cantidad" trim="true"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros" />"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="14" value="0" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <div class="botones_administracion">
                <button style="margin-left:50px;" id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_material('registrar');" class="div_form">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_material('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_material('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_material').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_material').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>
