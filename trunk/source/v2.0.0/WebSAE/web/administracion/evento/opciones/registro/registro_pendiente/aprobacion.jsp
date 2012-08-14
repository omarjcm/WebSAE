<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<input id="id_registro" type="hidden" value ="-1"  />
<div id="dlg_registro" dojoType="dijit.Dialog" title="<fmt:message key="ge.aprobacion_registro" />" style="width:440px;">
    <div id="div_registro">Aprobación de Registro</div>
    <div class="clearthefloats"></div>
    <button dojoType="dijit.form.Button" onclick="administrar_registro('aprobar');">
        <fmt:message key="ge.aprobar" />
    </button>
    <button dojoType="dijit.form.Button" onclick="administrar_registro('rechazar');">
        <fmt:message key="ge.rechazar" />
    </button>
    <div class="clearthefloats"></div>
    <button dojoType="dijit.form.Button" onclick="dijit.byId('dlg_registro').hide();" style="float:right;">
        <fmt:message key="ge.cerrar" />
    </button><br /><br />
</div>

<div id="dlg_pago" dojoType="dijit.Dialog" title="<fmt:message key="ge.confirmar_pago" />" style="width:440px;">
    <div id="div_pago">Confirmar Pago</div>
    <div class="clearthefloats"></div>
    <button dojoType="dijit.form.Button" onclick="administrar_pago('aprobar');">
        <fmt:message key="ge.registrar" />
    </button>
    <button dojoType="dijit.form.Button" onclick="administrar_pago('rechazar');">
        <fmt:message key="ge.no_pagado" />
    </button>
    <div class="clearthefloats"></div>
    <button dojoType="dijit.form.Button" onclick="dijit.byId('dlg_pago').hide();" style="float:right;">
        <fmt:message key="ge.cerrar" />
    </button><br /><br />
</div>