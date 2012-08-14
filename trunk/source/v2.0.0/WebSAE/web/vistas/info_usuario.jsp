<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${usuario != null}">
<div id="titulo">
    << ${usuario.us_email} >> <a href='/websae/F_cerrar_sesion' class='text_session'><fmt:message key="ge.cerrar_sesion" /></a>
</div>
</c:if>