<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<form dojoType="dijit.form.Form" class="form-suscripcion" id="frm_convocatoria" name="frm_convocatoria" action=""> 
    <div id="div_form">
        <input type="hidden" id="txt_id_convocatoria" value="" />
        <label><fmt:message key="adm.fecha_presentacion_resumen" />:</label>
        <input id="txt_fecha_resumen" name="txt_fecha_resumen"
               dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
               invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
               promptMessage="yyyy-mm-dd" type="text" value="" required="false" />
        <div class="clearthefloats"></div>

        <label><fmt:message key="adm.fecha_presentacion" />:</label>
        <input id="txt_fecha_presentacion" name="txt_fecha_presentacion"
               dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
               invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
               promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
        <span class="lblasterisco"> (*)</span>
        <div class="clearthefloats"></div>

        <label><fmt:message key="adm.fecha_evaluacion" />:</label>
        <input id="txt_fecha_evaluacion" name="txt_fecha_evaluacion"
               dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
               invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
               promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
        <span class="lblasterisco"> (*)</span>
        <div class="clearthefloats"></div>

        <label><fmt:message key="adm.fecha_notificacion" />:</label>
        <input id="txt_fecha_notificacion" name="txt_fecha_notificacion"
               dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
               invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
               promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
        <span class="lblasterisco"> (*)</span>
        <div class="clearthefloats"></div>

        <label><fmt:message key="adm.fecha_correccion" />:</label>
        <input id="txt_fecha_correccion" name="txt_fecha_correccion"
               dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
               invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
               promptMessage="yyyy-mm-dd" type="text" value="" required="true" />
        <span class="lblasterisco"> (*)</span>
        <div class="clearthefloats"></div>

        <label><fmt:message key="even.descripcion" />_ES:</label>
        <textarea id="txt_descripcion_es" name="txt_descripcion_es"
                  dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                  maxlength="600" required="false"
                  invalidMessage="<fmt:message key="ge.necesita_valor" />"
                  style="float:left; width:145px; height:50px;" >
        </textarea>
        <span><a href="javascript:traduccion('en','es','txt_descripcion_en','txt_descripcion_es');">
                <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
            </a></span>
        <div class="clearthefloats"></div>
        <label><fmt:message key="even.descripcion" />_EN:</label>
        <textarea id="txt_descripcion_en" name="txt_descripcion_en"
                  dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                  maxlength="600" required="false"
                  invalidMessage="<fmt:message key="ge.necesita_valor" />"
                  style="float:left; width:145px; height:50px;" >
        </textarea>
        <span><a href="javascript:traduccion('es','en','txt_descripcion_es','txt_descripcion_en');">
                <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
            </a></span>
        <div class="clearthefloats"></div>
        <label><fmt:message key="even.descripcion" />_PT:</label>
        <textarea id="txt_descripcion_pt" name="txt_descripcion_pt"
                  dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                  maxlength="600" required="false"
                  invalidMessage="<fmt:message key="ge.necesita_valor" />"
                  style="float:left; width:145px; height:50px;" >
        </textarea>
        <span><a href="javascript:traduccion('es','pt','txt_descripcion_es','txt_descripcion_pt');">
                <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
            </a></span>
        <div class="clearthefloats"></div>

        <form name="form" action="" method="POST" enctype="multipart/form-data" target="upload_target" >
            <label><fmt:message key="adm.formato_archivo" />:</label>
            <input type="hidden" id="txt_ruta_formato2" name="txt_ruta_formato2" value="" />
            <input class="inputFile" type="file" name="txt_ruta_formato" id="txt_ruta_formato" size="1px" /><br />
            <span class="lblasterisco" id="ref_ver"></span>
            <button id="btn_nuevo_formato" class="button" id="buttonUpload" dojoType="dijit.form.Button" onclick="subir_formato();">
                <fmt:message key="ge.añadir_formato" />
            </button>
            <button id="btn_modificar_formato" class="button" id="buttonUpload" dojoType="dijit.form.Button" onclick="subir_formato();">
                <fmt:message key="ge.modificar_formato" />
            </button>
            <span class="lblasterisco"><a href="javacript:ver_formato();"><label id="ref_ver"></label></a></span>
            <div class="clearthefloats"></div>
            <iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe>

            <div class="clearthefloats"></div>
            <label></label>
            <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_convocatoria('modificar');" class="div_form">
                <fmt:message key="ge.actualizar" />
            </button>
        </form>
    </div>
    <div class="clearthefloats"></div>
</form>
