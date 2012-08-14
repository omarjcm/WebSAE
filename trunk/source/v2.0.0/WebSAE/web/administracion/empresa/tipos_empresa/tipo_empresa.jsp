<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_tipo_empresa" dojoType="dijit.Dialog" title="<fmt:message key="adm.tipos_empresa" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_tipo_empresa" name="frm_tipo_empresa">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_tipo_empresa" name="txt_id_tipo_empresa" value="" />
            <label class="info_modificar"><fmt:message key="adm.tipo_empresa" />:</label>
            <input type="text" id="txt_tipo_empresa" name="txt_tipo_empresa"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="50" class="div_form" />
            <div class="clearthefloats"></div>
            <br />
            <div class="botones_administracion">
                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_tipo_empresa('registrar');" class="div_form" style="margin-left:50px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_tipo_empresa('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_tipo_empresa('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_tipo_empresa').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_tipo_empresa').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>
