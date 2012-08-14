<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_subcategoria" dojoType="dijit.Dialog" title="<fmt:message key="adm.subcategorias" />" style="width:400px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_subcategoria" name="frm_subcategoria">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_subcategoria" name="txt_id_subcategoria" value="" />
            <div dojoType="dojo.data.ItemFileReadStore" jsId="categoriasStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_categorias"></div>
            <label class="info_modificar"><fmt:message key="adm.categoria" /></label>
            <input name="cmb_categoria" id="cmb_categoria"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="categoriasStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="adm.subcategoria" />:</label>
            <input type="text" id="txt_subcategoria" name="txt_subcategoria"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="50" class="div_form" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>
            <br />
            <div class="botones_administracion">
                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_subcategoria('registrar');" class="div_form" style="margin-left:50px;">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_subcategoria('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_subcategoria('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_subcategoria').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
                <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_subcategoria').hide();" class="div_form">
                    <fmt:message key="ge.cerrar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>
