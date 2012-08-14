<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_conferencista" dojoType="dijit.Dialog" title="<fmt:message key="ge.conferencista_detalle" />" style="width:500px;">
    
        <div id="foto_conferencista" class="foto_cara"></div>
        <div class="descripcion_conferencista">
            <label id="nombre_completo" class="nombre_conferencista" ></label><br />
            <label id="hoja_vida" class="mini_bio"></label><br />
        </div>
        <div class="clearthefloats"></div>
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_conferencista').hide();" class="div_form">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
        <div class="clearthefloats"></div>
        <div id="paginated"></div>
    
</div>