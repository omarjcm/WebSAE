<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_subevento" dojoType="dijit.Dialog" title="<fmt:message key="adm.subevento" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_subevento" name="frm_subevento">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_subevento" name="txt_id_subevento" value="" />
            <div dojoType="dojo.data.ItemFileReadStore" jsId="tipos_eventosStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=tipos_eventos"></div>
            <label class="info_modificar"><fmt:message key="even.tipo_evento" />:</label>
            <input name="cmb_tipo_evento" id="cmb_tipo_evento"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="tipos_eventosStore" searchAttr="nombre" required="true" />
            <!--span class="lblasterisco"> (*)</span-->
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.subevento" />_ES:</label>
            <input type="text" id="txt_subevento_es" name="txt_subevento_es"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="100" class="div_form" />
            <span><a href="javascript:traduccion('en','es','txt_subevento_en','txt_subevento_es');">
                    <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                </a></span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.subevento" />_EN:</label>
            <input type="text" id="txt_subevento_en" name="txt_subevento_en"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="100" class="div_form" />
            <span><a href="javascript:traduccion('es','en','txt_subevento_es','txt_subevento_en');">
                    <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                </a></span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.subevento" />_PT:</label>
            <input type="text" id="txt_subevento_pt" name="txt_subevento_pt"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="100" class="div_form" />
            <span><a href="javascript:traduccion('es','pt','txt_subevento_es','txt_subevento_pt');">
                    <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                </a></span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.descripcion" />_ES:</label>
            <textarea id="txt_descripcion_es" name="txt_descripcion_es"
                      dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                      maxlength="500" required="false"
                      style="float:left; width:145px; height:45px;" >
            </textarea>
            <span><a href="javascript:traduccion('en','es','txt_descripcion_en','txt_descripcion_es');">
                    <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                </a></span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.descripcion" />_EN:</label>
            <textarea id="txt_descripcion_en" name="txt_descripcion_en"
                      dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                      maxlength="500" required="false"
                      style="float:left; width:145px; height:45px;" >
            </textarea>
            <span><a href="javascript:traduccion('es','en','txt_descripcion_es','txt_descripcion_en');">
                    <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                </a></span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.descripcion" />_PT:</label>
            <textarea id="txt_descripcion_pt" name="txt_descripcion_pt"
                      dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                      maxlength="500" required="false"
                      style="float:left; width:145px; height:45px;" >
            </textarea>
            <span><a href="javascript:traduccion('es','pt','txt_descripcion_es','txt_descripcion_pt');">
                    <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                </a></span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.cupo" />:</label>
            <input type="text" id="txt_cupo" name="txt_cupo" trim="true"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros" />"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="14" value="0" />
            <div class="clearthefloats"></div>
        </div>
        <div class="clearthefloats"></div>
        <div class="clearthefloats"></div>
    </form>
    <div class="botones_administracion"><br />
        <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_subevento('registrar');" class="div_form">
            <fmt:message key="ge.guardar" />
        </button>
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_subevento('modificar');" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_subevento('eliminar');" class="div_form">
            <fmt:message key="ge.eliminar" />
        </button>
        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_subevento').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button>
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_subevento').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
    </div>
</div>


