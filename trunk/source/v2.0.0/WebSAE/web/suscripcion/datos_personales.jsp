<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="titulo"><fmt:message key="us.datos_personales" /></div>

<label for="txt_nombres"><fmt:message key="us.nombres" /></label>
<input type="text" id="txt_nombres" name="txt_nombres" trim="true"
    dojoType="dijit.form.ValidationTextBox" required="true"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    invalidMessage="<fmt:message key="ge.formato_invalido" />"
    maxlength="30" regExp="[*\s*[a-zA-ZáÁéÉíÍóÓúÚñÑ]*\s*]*" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<label for="txt_apellidos"><fmt:message key="us.apellidos" /></label>
<input type="text" id="txt_apellidos" name="txt_apellidos" trim="true"
    dojoType="dijit.form.ValidationTextBox" required="true"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    invalidMessage="<fmt:message key="ge.formato_invalido" />"
    maxlength="30" regExp="[*\s*[a-zA-ZáÁéÉíÍóÓúÚñÑ]*\s*]*" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<label><fmt:message key="us.genero" /></label>
<label class="genero" for="opt_genero_0">
    <input id="opt_genero_0" name="genero" type="radio" value="m" checked="checked" />
    <fmt:message key="us.masculino" />
</label>
<label class="genero" for="opt_genero_1">
    <input id="opt_genero_1" name="genero" type="radio" value="f" />
    <fmt:message key="us.femenino" />
</label>
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<label for="txt_direccion"><fmt:message key="us.direccion" /></label>
<textarea id="txt_direccion" name="txt_direccion" cols="30" rows="2" maxlength="90" onkeyup="return ismaxlength(this)"></textarea>
<div class="clearthefloats" />

<label for="txt_telefono"><fmt:message key="us.telefono" /></label>
<input type="text" id="txt_telefono" name="txt_telefono" trim="true"
    dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
    invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
    maxlength="14" />
<div class="clearthefloats" />

<label for="txt_celular"><fmt:message key="us.celular" /></label>
<input type="text" id="txt_celular" name="txt_celular" trim="true"
    dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
    invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
    maxlength="14" />
<div class="clearthefloats" />

<label for="txt_nacimiento"><fmt:message key="us.fecha_nacimiento" /></label>
<input id="txt_nacimiento" name="txt_nacimiento"
    dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
    invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
    promptMessage="yyyy-mm-dd" type="text" value="1991-01-01" />
<div class="clearthefloats" />

<div dojoType="dojo.data.ItemFileReadStore" jsId="paisStore" url="/websae/F_mostrar_datos?tipo=mostrar_paises"></div>
<label for="cmb_pais"><fmt:message key="us.pais" /></label>
<input name="cmb_pais" id="cmb_pais"
    dojoType="dijit.form.FilteringSelect"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
    value="ECU" store="paisStore" searchAttr="nombre" require="true"
    onChange="cargar_ciudades" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<div dojoType="dojo.data.ItemFileReadStore" jsId="ciudadStore" url="/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=ECU"></div>
<label for="cmb_ciudad"><fmt:message key="us.ciudad" /></label>
<input name="cmb_ciudad" id="cmb_ciudad"
    dojoType="dijit.form.FilteringSelect"
    invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    value="32669" store="ciudadStore" searchAttr="nombre" require="true" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />