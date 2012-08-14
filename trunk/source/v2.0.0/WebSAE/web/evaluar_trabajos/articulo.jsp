<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<label class="popup_paper_etiqueta"><fmt:message key="even.evento" />:</label>
<label id="paper_evento" class="popup_paper_respuesta">Evento</label>
<div class="clearthefloats"></div>

<label class="popup_paper_etiqueta"><fmt:message key="ge.titulo" />:</label>
<label id="paper_titulo" class="popup_paper_respuesta">Titulo</label>
<div class="clearthefloats"></div>

<label class="popup_paper_etiqueta"><fmt:message key="ge.autor" />:</label>
<div id="paper_autor" class="popup_paper_respuesta">Autor</div>
<div class="clearthefloats"></div>

<label class="popup_paper_etiqueta"><fmt:message key="ge.resumen" />:</label>
<div id="paper_resumen" class="popup_paper_respuesta">Resumen</div>
<div class="clearthefloats"></div>

<label class="popup_paper_etiqueta"><fmt:message key="ge.tema" />(s):</label>
<div id="paper_tema" class="popup_paper_respuesta">Tema</div>
<div class="clearthefloats"></div>

<label class="popup_paper_etiqueta"><fmt:message key="ge.articulo" />(s):</label>
<div id="paper_archivo" class="popup_paper_respuesta">Artículo</div>
<div class="clearthefloats"></div>

<button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_articulo').hide();" style="float:right;">
    <fmt:message key="ge.cerrar" />
</button><br/><br/>