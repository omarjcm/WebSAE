<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_empresa" style="width:450px; border: 1px maroon; margin:10px;">
    <form dojoType="dijit.form.Form" class="form-suscripcion" id="empresa" name="empresa" action="">
        <div>
            <div class="titulo"><fmt:message key="ce.datos_empresa_nueva" /></div>
            <input type="hidden" value="1" id="txt_id_empresa" name="txt_id_empresa" />
            <label for="cmb_tipo_nueva_empresa"><fmt:message key="us.tipo_empresa" /></label>
            <input name="cmb_tipo_empresa" id="cmb_tipo_empresa"
                   dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   store="tipos_empresasStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats" />

            <label><fmt:message key="us.empresa_universidad" /></label>
            <input type="text" name="txt_empresa" id="txt_empresa"
                   dojoType="dijit.form.ValidationTextBox" maxlength="50"
                   promptMessage="<fmt:message key="ge.necesita_valor" />" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats" />

            <label for="cmb_tipo_nueva_empresa"><fmt:message key="us.direccion" /></label>
            <textarea id="txt_direccion" name="txt_direccion" cols="30" rows="2" maxlength="100" onkeyup="return ismaxlength(this)"></textarea>
            <div class="clearthefloats" />

            <label><fmt:message key="us.pais" /></label>
            <input name="cmb_pais_empresa" id="cmb_pais_empresa"
                   dojoType="dijit.form.FilteringSelect"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   value="ECU" store="paisStore" searchAttr="nombre" required="true"
                   onChange="cargar_ciudades_empresa" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats" />

            <div dojoType="dojo.data.ItemFileReadStore" jsId="ciudad_empresaStore" url="/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=ECU"></div>
            <label><fmt:message key="us.ciudad" /></label>
            <input name="cmb_ciudad_empresa" id="cmb_ciudad_empresa" dojoType="dijit.form.FilteringSelect"
                   invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                   promptMessage="<fmt:message key="ge.necesita_valor" />"
                   value="32669" store="ciudad_empresaStore" searchAttr="nombre" required="true" />
            <span class="lblasterisco"> (*)</span>
            <div class="clearthefloats" />
            <input type="hidden" value="V" id="rb_status" name="rb_status" />

            <button dojoType="dijit.form.Button" class="submit">
                <fmt:message key="ge.guardar" />
                <script type="dojo/method" event="onClick">registrar_empresa();</script>
            </button>
            <button dojoType="dijit.form.Button" style="margin-top: 15px; margin-left:10px;">
                <fmt:message key="ge.cancelar" />
                <script type="dojo/method" event="onClick">cerrar_ventana();</script>
            </button>
        </div>
    </form>
</div>
