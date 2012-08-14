<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div style="padding:10px;">
    <div id="opciones-asignadas-modif" style="font-weight:normal; color:black; padding: 20px; margin-bottom:10px; overflow:auto; height:250px; border: 1px silver dotted;">
    </div>
    <div style="margin-left:40px;">
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="modificar_menu();" class="div_form">
            <fmt:message key="ge.modificar" />
        </button>
        <button dojoType="dijit.form.Button" onclick="dijit.byId('asignar-opciones').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button>
    </div>
    <div class="clearthefloats"></div>
</div>