<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_alternativa" dojoType="dijit.Dialog" title="<fmt:message key="adm.alternativas" />" style="width:500px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_alternativa" name="frm_alternativa">
        <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_alternativa" name="txt_id_alternativa" value="-1" />
        <label style="width:180px;"><fmt:message key="ge.pregunta" />:</label>
        <b><label style="width:180px; float:left; text-align:left;" id="lbl_pregunta"></label></b>
        <div class="clearthefloats"></div>
        <label style="width:180px;"><fmt:message key="ge.alternativa" />:</label>
        <textarea id="txt_alternativa" name="txt_alternativa"
                  dojoType="dijit.form.SimpleTextarea" required="true"
                  invalidMessage="<fmt:message key="ge.necesita_valor" />"
                  maxlength="500"
                  style="float:left; width:180px; height:50px;" >
        </textarea>
        <span class="lblasterisco"> (*)</span>
        <div class="clearthefloats"></div>
        <label style="width:180px;"><fmt:message key="ge.orden" />:</label>
        <input type="text" id="txt_orden_alt" name="txt_orden_alt"
               dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
               invalidMessage="<fmt:message key="ge.solo_numeros" />"
               required="true" maxlength="2" class="div_form" />
        <span class="lblasterisco"> (*)</span>
        <div class="clearthefloats"></div>
        <label style="width:180px;"></label>
        <div class="botones_administracion" style="width:300px;">
            <button id="btn_registrar_a" dojoType="dijit.form.Button" onclick="accion_alternativa('registrar');" >
                <fmt:message key="ge.agregar" />
            </button>
            <button id="btn_modificar_a" dojoType="dijit.form.Button" onclick="accion_alternativa('modificar');" style="margin-left:-50px;">
                <fmt:message key="ge.modificar" />
            </button>
            <button id="btn_eliminar_a" dojoType="dijit.form.Button" onclick="accion_alternativa('eliminar');" class="div_form">
                <fmt:message key="ge.eliminar" />
            </button>
            <button id="btn_cancelar_a" dojoType="dijit.form.Button" onclick="limpiar_alternativa();" class="div_form">
                <fmt:message key="ge.cancelar" />
            </button>
            <button id="btn_cerrar_a" dojoType="dijit.form.Button" onclick="dijit.byId('div_alternativa').hide();" class="div_form">
                <fmt:message key="ge.cerrar" />
            </button>
        </div>
        <div class="clearthefloats"></div>
        <div id="tbl_alternativas" style="width:380px; margin:0 auto;"></div>
    </form>
</div>
