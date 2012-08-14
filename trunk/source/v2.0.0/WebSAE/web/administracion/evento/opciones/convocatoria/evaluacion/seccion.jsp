<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_seccion" dojoType="dijit.Dialog" title="<fmt:message key="adm.seccion" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_seccion" name="frm_seccion">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_seccion" name="txt_id_seccion" value="-1" />
            <label style="width:120px;"><fmt:message key="adm.seccion" />:</label>
            <textarea id="txt_titulo" name="txt_titulo"
                      dojoType="dijit.form.SimpleTextarea" required="true"
                      invalidMessage="<fmt:message key="ge.necesita_valor" />"
                      maxlength="500"
                      style="float:left; width:180px; height:50px;" >
            </textarea>
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>
            <label style="width:120px;"><fmt:message key="ge.orden" />:</label>
            <input type="text" id="txt_orden" name="txt_orden"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros" />"
                   required="true" maxlength="2" style="width:50px;" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>
            <label style="width:120px;"><fmt:message key="adm.instruccion" />:</label>
            <textarea id="txt_descripcion" name="txt_descripcion"
                      dojoType="dijit.form.SimpleTextarea" required="true"
                      invalidMessage="<fmt:message key="ge.necesita_valor" />"
                      maxlength="500"
                      style="float:left; width:180px; height:50px;" >
            </textarea>
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>
            <div id="div_status_o" dojoType="dijit.layout.ContentPane">
                <label style="width:180px;"><fmt:message key="ge.oculta" />:</label>
                <label style="margin-left:-200px;">
                    <input dojoType="dijit.form.RadioButton" type="radio" name="txt_oculta" id="rb_vigente_o" value="1"  /><fmt:message key="ge.si" />
                </label>
                <label style="margin-left:-200px;">
                    <input dojoType="dijit.form.RadioButton" type="radio" name="txt_oculta" id="rb_pendiente_o" value="0" checked="checked" /><fmt:message key="ge.no" />
                </label>
            </div>
            <div class="clearthefloats"></div>

            <div class="botones_administracion">
                <button id="btn_registrar_s" dojoType="dijit.form.Button" onclick="accion_seccion('registrar');" class="div_form" style="margin-left:50px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar_s" dojoType="dijit.form.Button" onclick="accion_seccion('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar_s" dojoType="dijit.form.Button" onclick="accion_seccion('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar_s" dojoType="dijit.form.Button" onclick="dijit.byId('div_seccion').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar_s" dojoType="dijit.form.Button" onclick="dijit.byId('div_seccion').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />

        </div>
    </form>
</div>
