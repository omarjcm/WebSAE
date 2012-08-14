<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<label for="txt_correo"><fmt:message key="us.correo_electronico" /></label>
<input type="hidden" id="buscar_tipo" value="${param.tipo}" />
<input type="text" id="txt_correo" name="txt_correo"
       dojoType="dijit.form.ValidationTextBox" trim="true"
       promptMessage="<fmt:message key="ge.necesita_valor" />"
       invalidMessage="<fmt:message key="ge.formato_invalido" />"
       trim="true" required="true" maxlength="50"
       regExp=".*@.*" class="div_form" />

<button dojoType="dijit.form.Button" onclick="cargar_usuario()">
    <c:if test="${param.tipo != 'evaluador_usuario'}">
        <fmt:message key="ge.aceptar" />
    </c:if>
    <c:if test="${param.tipo == 'evaluador_usuario'}">
        <fmt:message key="ge.agregar" />
    </c:if>
</button>

<button dojoType="dijit.form.Button" onclick="dijit.byId('div_buscar_usuario').show();">
    <fmt:message key="ge.buscar" />
</button>

<div id="div_buscar_usuario" dojoType="dijit.Dialog" title="<fmt:message key="${param.titulo}" />" style="width:550px;">
    <div id="div_form">
        <label><fmt:message key="us.nombre_apellido" />:</label>
        <input type="text" id="buscar_nombre_apellido" name="buscar_nombre_apellido"
               dojoType="dijit.form.ValidationTextBox" trim="true"
               promptMessage="<fmt:message key="ge.necesita_valor" />"
               required="true" maxlength="100" class="div_form" />
        <button dojoType="dijit.form.Button" onclick="buscar_usuario('${param.tipo}');">
            <fmt:message key="ge.aceptar" />
        </button>
        <div class="clearthefloats"></div>
        <br />
        <div id="tbl_resultados" style="width:430px; margin:0 auto;"></div>
    </div>
</div>

