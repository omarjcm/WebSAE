<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_precio" dojoType="dijit.Dialog" title="<fmt:message key="adm.precio" />" style="width:400px;">
    <div class="titulo" id="rango_fechas"></div>
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_precio" name="frm_precio">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_precio" name="txt_id_precio" value="" />
            <div dojoType="dojo.data.ItemFileReadStore" jsId="categoriasStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_categorias"></div>
            <label class="info_modificar"><fmt:message key="adm.categoria" /></label>
            <input name="cmb_categoria" id="cmb_categoria"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="categoriasStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>

            <div class="clearthefloats"></div>
            <label class="info_modificar"><fmt:message key="adm.precio" />:</label>
            <input type="text" id="txt_precio" name="txt_precio" value="0.00"
                   dojoType="dijit.form.CurrencyTextBox"
                   required="true" constraints="{fractional:true}"
                   currency="USD" required="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   invalidMessage="<fmt:message key="ge.solo_reales" />" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="adm.fecha_inicio" /></label>
            <input id="txt_fecha_inicio" name="txt_fecha_inicio"
                   dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
                   invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
                   promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="adm.fecha_fin" /></label>
            <input id="txt_fecha_fin" name="txt_fecha_fin"
                   dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
                   invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
                   promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>
        </div>
        <div class="clearthefloats"></div>
    </form>
    <div class="botones_administracion"><br />
        <button style="margin-left:50px;" id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_precio('registrar');" class="div_form">
            <fmt:message key="ge.guardar" />
        </button>
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_precio('modificar');" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_precio('eliminar');" class="div_form">
            <fmt:message key="ge.eliminar" />
        </button>
        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_precio').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button><br /><br />
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_precio').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
    </div>
</div>


