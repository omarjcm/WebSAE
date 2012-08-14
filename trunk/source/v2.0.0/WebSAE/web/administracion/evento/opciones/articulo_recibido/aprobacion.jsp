<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_aprobacion" dojoType="dijit.Dialog" title="<fmt:message key="ge.aprobar_rechazar" />" style="width:300px;">
    <form id="frm_articulo" name="frm_articulo" dojoType="dijit.form.Form" action="">
        <div>
            <input type="hidden" id="id_articulo_aprobacion" name="id_articulo" value="" />
            <input type="hidden" id="accion_aprobacion" name="accion" value="" />
            <label style="float:left;">Aprobar/Rechazar?</label>
            <div class="clearthefloats"></div><br />
            <button id="btn_aprobar" dojoType="dijit.form.Button" onclick="decision_articulo('aprobar');" style="margin-left:60px;">
                Pre-<fmt:message key="ge.aprobar" />
            </button>
            <button id="btn_rechazar" dojoType="dijit.form.Button" onclick="decision_articulo('rechazar');">
                <fmt:message key="ge.rechazar" />
            </button>
            <div class="clearthefloats"></div>
            <div id="div_observacion" dojoType="dijit.layout.ContentPane">
                <label style="float:left; margin-top:20px;"><fmt:message key="ge.motivo" /></label>
                <textarea id="txt_observacion" name="txt_observacion"
                          dojoType="dijit.form.SimpleTextarea" value=" "
                          maxlength="200" required="false"
                          style="float:left; width:200px; height:40px; margin:10px 0 10px 30px;" >
                </textarea>
                <div class="clearthefloats"></div>
            </div>
            <div id="div_temas" dojoType="dijit.layout.ContentPane"
                 <label style="float:left; width:50px; max-height:200px; text-align:left;">Tema(s):</label>
                <div class="clearthefloats"></div>
                <div id="div_tema"></div>
            </div>
            <div id="div_btn_ac" dojoType="dijit.layout.ContentPane">
                <button id="btn_aceptar" dojoType="dijit.form.Button" onclick="administrar_articulo(dojo.byId('accion_aprobacion').value);" style="margin-left:60px;">
                    <fmt:message key="ge.aceptar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_aprobacion').hide();">
                    <fmt:message key="ge.cancelar" />
                </button>
            </div>
        </div>
    </form>
</div>