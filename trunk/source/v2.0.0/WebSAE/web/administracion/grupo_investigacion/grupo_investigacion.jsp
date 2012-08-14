<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_grupo_investigacion" dojoType="dijit.Dialog" title="<fmt:message key="adm.grupo_investigacion" />" style="width:480px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_grupo_investigacion" name="frm_grupo_investigacion" action="">
        <div id="div_form">
            <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_grupo_investigacion" name="txt_id_grupo_investigacion" value="" />

            <form name="form" action="" method="POST" enctype="multipart/form-data">
                <label id="logo_imagen" class="info_modificar"><img src="/websae/images/evento/default.jpg" height="40px" alt="" /></label>
                <input type="hidden" id="txt_imagen2" name="txt_imagen2" value="" />
                <input type="file" name="txt_imagen" id="txt_imagen" size="15px" style="float:left;" />
                <button style="float:left; margin-top:-1px;" id="buttonUpload" dojoType="dijit.form.Button" onclick="cargar_logo('images','logo_grupo_investigacion','txt_imagen');">
                    <fmt:message key="ge.subir" />
                </button>

                <div class="clearthefloats"></div>
                <!--iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe--> 
            </form>
            <label class="info_modificar"><fmt:message key="adm.grupo_investigacion" />:</label>
            <input type="text" id="txt_grupo_investigacion" name="txt_grupo_investigacion"
                   dojoType="dijit.form.ValidationTextBox" trim="true"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   required="true" maxlength="50" class="div_form" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="even.objetivo" />:</label>
            <textarea id="txt_objetivo" name="txt_objetivo" cols="30" rows="1" maxlength="900" onkeyup="return ismaxlength(this)"></textarea>
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.telefono" /></label>
            <input type="text" id="txt_telefono" name="txt_telefono" trim="true"
                   dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
                   invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
                   maxlength="14" />
            <div class="clearthefloats"></div>

            <label class="info_modificar"><fmt:message key="us.url" />:</label>
            <input id="txt_url" name="txt_url" dojoType="dijit.form.ValidationTextBox"
                   class="long" value=""
                   regExp="(ftp|http|https):\/\/(bloq|(\w+:{0,1}\w*@))?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?"
                   trim="true" constraints={scheme:true}  maxlength="50"
                   invalidMessage="<fmt:message key="ge.formato_invalido" />, e.: http://www.google.com"
                   <div class="clearthefloats"></div>
            <br />
            <div class="botones_administracion">
                <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_grupo_investigacion('registrar');" class="div_form">
                    <fmt:message key="ge.guardar" />
                </button>
                <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_grupo_investigacion('modificar');" class="div_form">
                    <fmt:message key="ge.modificar" />
                </button>
                <button id="btn_eliminar" dojoType="dijit.form.Button" onclick="accion_grupo_investigacion('eliminar');" class="div_form">
                    <fmt:message key="ge.eliminar" />
                </button>
                <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_grupo_investigacion').hide();" class="div_form">
                    <fmt:message key="ge.cancelar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>