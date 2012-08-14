<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <div id="div_info">
                <div class="div_menu_admin_titulo">
                    <fmt:message key="ge.menu_evento" />
                </div>
                <div class="div_menu_admin">
                    <jsp:include page="/menu/menu_evento.jsp" />
                </div>
            </div>
