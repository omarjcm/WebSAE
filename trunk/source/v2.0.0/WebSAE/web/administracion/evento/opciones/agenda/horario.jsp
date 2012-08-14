<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_horario" dojoType="dijit.Dialog" title="<fmt:message key="adm.horario" />" style="width:500px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_horario" name="frm_horario">
        <fieldset>
            <div id="div_form">
                <div class="titulo-principal" id="lbl_fecha"></div>
                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_actividad" name="txt_id_actividad" value="1" />
                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_fecha_evento_h" name="txt_id_fecha_evento" value="" />
                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_hora_inicio" name="txt_hora_inicio" value="1" />
                <input type="hidden" dojoType="dijit.form.TextBox" id="txt_hora_fin" name="txt_hora_fin" value="1" />
                <label class="info_modificar"><fmt:message key="adm.hora_inicio" /></label>
                <input id="txt_hora_inicio2" type="text" name="txt_hora_inicio2" class="medium" value="T09:45:00"
                    dojoType="dijit.form.TimeTextBox"
                    constraints="{timePattern:'HH:mm:ss'}"
                    required="true"
                    invalidMessage="<fmt:message key="ge.formato_invalido" />">
                <span class="lblasterisco"> (*)</span>
                <div class="clearthefloats" />

                <label class="info_modificar"><fmt:message key="adm.hora_fin" /></label>
                <input id="txt_hora_fin2" type="text" name="txt_hora_fin2" class="medium" value="T10:45:00"
                    dojoType="dijit.form.TimeTextBox"
                    constraints="{timePattern:'HH:mm:ss'}"
                    required="true"
                    invalidMessage="<fmt:message key="ge.formato_invalido" />">
               <span class="lblasterisco"> (*)</span>
                <div class="clearthefloats" />

                <label class="info_modificar"><fmt:message key="adm.actividad" /></label>
                <textarea id="txt_actividad" name="txt_actividad" cols="30" rows="2" maxlength="200" onkeyup="return ismaxlength(this)"></textarea>
                <div class="clearthefloats" />

                <div dojoType="dojo.data.ItemFileReadStore" jsId="expositoresStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=conferencistas_evento"></div>
                <label class="info_modificar"><fmt:message key="adm.expositor" /></label>
                <div id="espacio_expositores"></div>
                <!--input name="cmb_expositor" id="cmb_expositor"
                       dojoType="dijit.form.FilteringSelect"
                       invalidMessage="<fmt:message key="ge.solo_valores_lista" />" 
                       store="expositoresStore" searchAttr="items.usuario.nombre" />
                <div class="clearthefloats"></div-->
            </div>
        </fieldset>
    </form>
    <div class="botones_administracion"><br />
        <button id="btn_registrar_h" dojoType="dijit.form.Button" onclick="accion_horario('registrar');" class="div_form">
            <fmt:message key="ge.agregar" />
        </button>
        <button id="btn_modificar_h" dojoType="dijit.form.Button" onclick="accion_horario('modificar');" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_eliminar_h" dojoType="dijit.form.Button" onclick="accion_horario('eliminar');" class="div_form">
            <fmt:message key="ge.eliminar" />
        </button>
        <button id="btn_cancelar_h" dojoType="dijit.form.Button" onclick="dijit.byId('div_horario').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button>
        <button id="btn_cancelar_h2" dojoType="dijit.form.Button" onclick="limpiar_horario();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button>
        <button id="btn_cerrar_h" dojoType="dijit.form.Button" onclick="dijit.byId('div_horario').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
    </div>
    <div id="paginated2"></div>
</div>


