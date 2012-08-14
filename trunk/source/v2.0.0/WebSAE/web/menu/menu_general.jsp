<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param.index==true}">
    <a href="javascript:cambiar_idioma('es');">
        <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
    </a>
    <a href="javascript:cambiar_idioma('en');">
        <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
    </a>
    <a href="javascript:cambiar_idioma('pt');">
        <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
    </a>
</c:if>

<div style="float:right; margin:1px 2px 0 15px;">
    <a href="/websae/home/index.jsp" class="direct_menu">
        <fmt:message key="mg.inicio" />
    </a> |
    <a href="/websae/quienes_somos.jsp" class="direct_menu" >
        <fmt:message key="mg.quienes_somos" />
    </a> |
    <a href="/websae/contactenos.jsp" class="direct_menu" >
        <fmt:message key="mg.contactenos" />
    </a>
</div>