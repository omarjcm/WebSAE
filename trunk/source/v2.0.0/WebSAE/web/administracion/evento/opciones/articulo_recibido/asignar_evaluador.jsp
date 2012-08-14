<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_asignar_evaluador" dojoType="dijit.Dialog" title="<fmt:message key="adm.asignar_evaluador" />" style="width:400px;">
    <form id="frm_asignar_evaluador" name="frm_asignar_evaluador" dojoType="dijit.form.Form" action="">
        <div>
            <input type="hidden" id="id_articulo" name="id_articulo" value="-1" />
            <input type="hidden" id="accion" name="accion" value="" />
            <div id="titulo_articulo" style="float:left; width:340px; text-align:center; font-weight:bold; "></div>
            <div class="clearthefloats"></div><br />
            <!--div dojoType="dojo.data.ItemFileReadStore" jsId="usuariosStore" url="/websae/administracion/F_ce_mostrar_datos?tipo=evaluadores_evento"></div-->
            <label for="destino" style="float:left; width:100px; text-align:right;">Evaluador:</label>
            <div id="div_evaluadores"></div>
            <button dojoType="dijit.form.Button" onclick="administrar_asignar_evaluador('registrar','-1');" style="margin-top:-4px;">
                <fmt:message key="ge.agregar" />
            </button>
            <div class="clearthefloats"></div>
            <div id="tbl_evaluadores" style="width:320px; margin:10px auto;"></div>
            <div class="clearthefloats"></div>
            <button dojoType="dijit.form.Button" onclick="dijit.byId('div_asignar_evaluador').hide();" style="float:right;">
                <fmt:message key="ge.cerrar" />
            </button>
            <div class="clearthefloats"></div>
        </div>
    </form>
</div>