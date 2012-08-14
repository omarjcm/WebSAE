<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_descuento" dojoType="dijit.Dialog" title="<fmt:message key="adm.descuento" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_descuento" name="frm_descuento">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_descuento" name="txt_id_descuento" value="" />
            <div dojoType="dojo.data.ItemFileReadStore" jsId="subcategoriasStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_subcategorias&id_categoria=1"></div>
            <label class="info_modificar"><fmt:message key="adm.subcategoria" /></label>
            <div id="div_subcategorias"></div>
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="adm.descuento" /></label>
            <input type="text" id="txt_descuento" name="txt_descuento" trim="true"
                   dojoType="custom.form.PercentTextBox" 
                   invalidMessage="<fmt:message key="ge.solo_numeros" />"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="3" value="0" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>
        </div>
        <div class="clearthefloats"></div>
    </form>
    <div class="botones_administracion"><br />
        <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_descuento('registrar');" class="div_form">
            <fmt:message key="ge.guardar" />
        </button>
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_descuento('modificar');" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_descuento('eliminar');" class="div_form">
            <fmt:message key="ge.eliminar" />
        </button>
        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_descuento').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button><br /><br />
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_descuento').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
    </div>
</div>


