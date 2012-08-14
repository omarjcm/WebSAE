<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="titulo"><fmt:message key="cc.informacion" /></div>

<label for="txt_correo"><fmt:message key="us.correo_electronico" /></label>
<input type="text" id="txt_correo" name="txt_correo"
    dojoType="dijit.form.ValidationTextBox" trim="true"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    invalidMessage="<fmt:message key="ge.formato_invalido" />"
    trim="true" required="true" maxlength="50"
    lowercase="true" regExp=".*@.*" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<label for="txt_clave"><fmt:message key="us.contrasena" /></label>
<input type="password" id="txt_clave" name="txt_clave"
    dojoType="dijit.form.ValidationTextBox" required="true"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    invalidMessage="<fmt:message key="ge.minimo6c" />"
    lowercase="true" minlength="6" maxlength="15"
    intermediateChanges="false" regExp=".{6,15}" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />

<label for="txt_reclave"><fmt:message key="us.recontrasena" /></label>
<input type="password" id="txt_reclave" name="txt_reclave"
    dojoType="dijit.form.ValidationTextBox" required="true"
    promptMessage="<fmt:message key="ge.necesita_valor" />"
    invalidMessage="<fmt:message key="us.contrasenas_diferentes" />"
    lowercase="true" maxlength="15" minlength="6" constraints="{'other': 'txt_clave'}"
    validator="confirmPassword" intermediateChanges="false" />
<span class="lblasterisco"> (*)</span>
<div class="clearthefloats" />