<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="titulo"><fmt:message key="us.informacion_laboral" /></div>

<div dojoType="dojo.data.ItemFileReadStore" jsId="tituloStore" url="/websae/F_mostrar_datos?tipo=mostrar_titulos"></div>
<label for="cmb_titulo"><fmt:message key="us.carrera_titulo" /></label>
<input name="cmb_titulo" id="cmb_titulo"
    dojoType="dijit.form.FilteringSelect"
    invalidMessage="Sólo se permite seleccionar,<br />el ingreso de un nuevo título<br />no está permitido."
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    value="${usuario.ref_titulo.ti_id_titulo}" store="tituloStore" searchAttr="nombre" require="true" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<div dojoType="dojo.data.ItemFileReadStore" jsId="tipos_empresasStore" url="/websae/F_mostrar_datos?tipo=mostrar_tipos_empresas"></div>
<label for="cmb_tipo_empresa"><fmt:message key="us.tipo_empresa" /></label>
<input name="cmb_tipo_empresa" id="cmb_tipo_empresa_us"
    dojoType="dijit.form.FilteringSelect"
    invalidMessage="Sólo se permite seleccionar,<br />el ingreso de un nuevo Tipo de Empresa<br />no está permitido."
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    value="${usuario.ref_empresa_usuario.ref_empresa.ref_tipo_empresa.te_id_tipo_empresa}" store="tipos_empresasStore" searchAttr="nombre" require="true"
    onchange="cargar_tipo_empresa" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<div dojoType="dojo.data.ItemFileReadStore" jsId="cargosStore"
    url="../../F_mostrar_datos?tipo=mostrar_cargos&id_tipo_empresa=${usuario.ref_empresa_usuario.ref_empresa.ref_tipo_empresa.te_id_tipo_empresa}"></div>
<label for="cmb_cargo"><fmt:message key="us.cargo" /></label>
<input name="cmb_cargo" id="cmb_cargo"
    dojoType="dijit.form.FilteringSelect"
    invalidMessage="Sólo se permite seleccionar,<br />el ingreso de un nuevo Cargo<br />no está permitido."
    promptMessage="<fmt:message key="ge.necesita_valor" />" 
    value="${usuario.ref_empresa_usuario.ref_cargo.ca_id_cargo}"
    store="cargosStore" searchAttr="nombre" require="true" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<div dojoType="dojo.data.ItemFileReadStore" jsId="empresasStore"
    url="../../F_mostrar_datos?tipo=mostrar_empresas&id_tipo_empresa=${usuario.ref_empresa_usuario.ref_empresa.ref_tipo_empresa.te_id_tipo_empresa}"></div>
<label id="lbl_empresa"><fmt:message key="us.empresa_universidad" /></label>
<input name="cmb_empresa" id="cmb_empresa"
    dojoType="dijit.form.FilteringSelect"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    value="${usuario.ref_empresa_usuario.ref_empresa.em_id_empresa}"
    store="empresasStore" searchAttr="nombre" require="true" />
<span class="lblasterisco"> (*)</span>
<button dojoType="dijit.form.Button" onclick="dijit.byId('crear-empresa').show()">
    <fmt:message key="us.nueva_empresa" />
</button>
<div class="clearthefloats" />

<label><fmt:message key="us.telefono_oficina" /></label>
<input type="text" name="txt_telefono_oficina" id="txt_telefono_oficina"
    dojoType="dijit.form.ValidationTextBox" regExp="[\d*\s*-*\d*]*"
    invalidMessage="<fmt:message key="ge.solo_numeros_espacio" />"
    value="${usuario.ref_empresa_usuario.eu_telefono}" maxlength="14" />
