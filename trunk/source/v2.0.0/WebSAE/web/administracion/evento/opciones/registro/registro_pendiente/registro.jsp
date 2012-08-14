<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="dlg_evaluacion" dojoType="dijit.Dialog" title="<fmt:message key="ge.evaluacion" />" style="min-width:600px;">
    <div id="tbl_evaluacion"></div>
    <input id="id_articulo_evaluacion" name="id_articulo" type="hidden" value="-1" />
    <div class="clearthefloats"></div><br />
    <div style="width:250px; margin: 0 auto;">
        <button dojoType="dijit.form.Button" onclick="administrar_evaluacion('aprobar');" >
            <fmt:message key="ge.aprobar" />
        </button>
        <button dojoType="dijit.form.Button" onclick="administrar_evaluacion('rechazar');">
            <fmt:message key="ge.rechazar" />
        </button>
        <button dojoType="dijit.form.Button" onclick="dijit.byId('dlg_evaluacion').hide();">
            <fmt:message key="ge.cancelar" />
        </button>
    </div>
    <div class="clearthefloats"></div><br />
</div>
