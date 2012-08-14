<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_cargo" dojoType="dijit.Dialog" title="<fmt:message key="adm.usuario.administrar_cargos" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_cargo" name="frm_cargo">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_cargo" name="txt_id_cargo" value="-1" />
            <label class="info_modificar"><fmt:message key="us.cargo" />:</label>
            <input type="text" id="txt_cargo" name="txt_cargo"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="50" class="div_form" />
            <div class="clearthefloats"></div>

            <div class="clearthefloats"></div>
        </div>
    </form>
    <div id="asignar-cargo"></div><br />
    <div class="botones_administracion">
        <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_cargo('registrar');" class="div_form" style="margin-left:50px;">
            <fmt:message key="ge.guardar" />
        </button>
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_cargo('modificar');" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_cargo('eliminar');" class="div_form">
            <fmt:message key="ge.eliminar" />
        </button>
        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_cargo').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button>
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_cargo').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button>
    </div>
    <div class="clearthefloats"></div>
    <br />
</div>
