<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="conferencista_modificar" dojoType="dijit.Dialog" title="<fmt:message key="adm.conferencista" />" style="width:500px;">
    <form id="frm_conferencista" name="frm_conferencista" dojoType="dijit.form.Form" class="form-suscripcion" action="">
        <div id="div_form">
            <input type="hidden" id="txt_id_usuario" name="txt_id_usuario" value="-1.jpg" />
            <label style="float:left; width:130px; margin-left:40px;"><fmt:message key="ge.conferencista"/>: </label><span id="nombre_completo" style="float:left; margin:5px 0 0 0;"></span>
            <div class="clearthefloats"></div>
            <form name="form_conferencista" action="" method="POST" enctype="multipart/form-data" target="upload_target" >
                <label id="logo_imagen" class="info_modificar"></label>
                <input type="hidden" id="txt_imagen2" name="txt_imagen2" value="default.jpg" />
                <input class="inputFile" type="file" name="txt_imagen" id="txt_imagen" size="1px" style="float:left;" /><br />
                <button class="button" style="float:left; margin-top:-16px;" id="buttonUpload" dojoType="dijit.form.Button" onclick="cargar_foto('images','foto_usuario','txt_imagen');">
                    <fmt:message key="ge.subir_foto" />
                </button>
                <iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe>
                <div class="clearthefloats"></div>

                <label class="info_modificar" for="txt_biografia"><fmt:message key="ge.biografia" />:</label>
                <textarea id="txt_biografia" name="txt_biografia"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              style="float:left; width:180px; height:50px;" >
                    </textarea>
                <div class="clearthefloats"></div>

                <label class="info_modificar" for="txt_linkedin">LinkedIn: </label>
                <input id="txt_linkedin" name="txt_linkedin" dojoType="dijit.form.TextBox" />
                <div class="clearthefloats"></div>

                <label for="rb_invitado" style="display:none;"><fmt:message key="ge.invitado" />
                    <input type="checkbox" dojoType="dijit.form.CheckBox" name="rb_invitado" id="rb_invitado" />
                </label>
            </form>
            <div class="clearthefloats"></div>
            <div style="margin-left:170px;">
                <button dojoType="dijit.form.Button" onclick="actualizar_conferencista_infoextra();">
                    <fmt:message key="ge.modificar" />
                </button>
                <button dojoType="dijit.form.Button" onclick="dijit.byId('conferencista_modificar').hide();">
                    <fmt:message key="ge.cancelar" />
                </button>
            </div>
            <div class="clearthefloats"></div>
            <br />
        </div>
    </form>
</div>
