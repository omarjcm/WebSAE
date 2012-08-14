<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_agenda" dojoType="dijit.Dialog" title="<fmt:message key="adm.agenda" />" style="width:400px;">
    <div class="titulo" id="rango_fechas"></div>
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_agenda" name="frm_agenda">
        <fieldset>
            <div id="div_form">
                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_fecha_evento" name="txt_id_fecha_evento" value="" />
                <label class="info_modificar"><fmt:message key="adm.dia" /></label>
                <input id="txt_fecha" name="txt_fecha"
                       dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
                       invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
                       promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
               <span class="lblasterisco"> (*)</span>
                <div class="clearthefloats" />
            </div>
        </fieldset>
        <div class="clearthefloats"></div>
    </form>
    <div class="botones_administracion"><br />
        <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_agenda('registrar');" class="div_form">
            <fmt:message key="ge.guardar" />
        </button>
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_agenda('modificar');" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_agenda('eliminar');" class="div_form">
            <fmt:message key="ge.eliminar" />
        </button>
        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_agenda').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button><br /><br />
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_agenda').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
    </div>
</div>


