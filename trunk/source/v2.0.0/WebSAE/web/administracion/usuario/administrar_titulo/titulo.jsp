<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_titulo" dojoType="dijit.Dialog" title="<fmt:message key="adm.usuario.administrar_titulo" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_titulo" name="frm_titulo">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_titulo" name="txt_id_titulo" value="" />
            <label class="info_modificar"><fmt:message key="us.carrera_titulo" />:</label>
            <input type="text" id="txt_titulo" name="txt_titulo"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="50" class="div_form" />
            <div class="clearthefloats"></div>
            <label class="info_modificar"><fmt:message key="us.abreviatura" />:</label>
            <input type="text" id="txt_abreviatura" name="txt_abreviatura"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="10" class="div_form" />
            <div class="clearthefloats"></div>
            <!--div id="div_status" dojoType="dijit.layout.ContentPane">
                <label class="info_modificar"><fmt:message key="ge.estado" />:</label>
                <label class="info_dato_check"><input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_vigente" value="V" checked="checked" /><fmt:message key="ge.vigente" /></label>
                <label class="info_dato_check"><input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_eliminado" value="E" /><fmt:message key="ge.eliminado" /></label>
            </div-->
            <div class="clearthefloats"></div>
            <br />
            <div class="botones_administracion">
                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_titulo('registrar');" class="div_form" style="margin-left:50px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_titulo('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_titulo('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_titulo').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_titulo').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>
