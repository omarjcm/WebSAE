<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_tema" dojoType="dijit.Dialog" title="<fmt:message key="adm.tema" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_tema" name="frm_tema">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_tema" name="txt_id_tema" value="-1" />

            <label class="info_modificar_corto"><fmt:message key="adm.tema" />:</label>
            <textarea id="txt_tema" name="txt_tema"
                      dojoType="dijit.form.SimpleTextarea" required="true"
                      invalidMessage="<fmt:message key="ge.necesita_valor" />"
                      maxlength="100"
                      style="float:left; width:180px; height:50px;" >
            </textarea>
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <div class="botones_administracion">
                <button id="btn_registrar_t" dojoType="dijit.form.Button" onclick="accion_tema('registrar');" class="div_form"style="margin-left:70px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar_t" dojoType="dijit.form.Button" onclick="accion_tema('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar_t" dojoType="dijit.form.Button" onclick="accion_tema('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar_t" dojoType="dijit.form.Button" onclick="popup_temas();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar_t" dojoType="dijit.form.Button" onclick="dijit.byId('div_tema').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
            <div id="tbl_temas" style="width:285px; margin:0 auto;"></div>
        </div>
    </form>
</div>
