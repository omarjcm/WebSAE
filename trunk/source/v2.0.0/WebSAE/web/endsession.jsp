<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session" />
</c:if>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ge.sesion_finalizada" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

        <link href="css/links.css" rel="stylesheet" type="text/css" />
        <link href="css/evento.css" rel="stylesheet" type="text/css" />
        <link href="css/div.css" rel="stylesheet" type="text/css" />
        <link href="css/texto.css" rel="stylesheet" type="text/css" />
        <link href="css/home.css" rel="stylesheet" type="text/css" />
        <link href="css/menu.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            @import "/websae/js/dijit/themes/tundra/tundra.css";
            @import "/websae/js/dojo/resources/dojo.css";
        </style>
    </head>
    <body>
        <div id="div_body">
            <jsp:include page="menu_general.jsp" />
            <jsp:include page="encabezado.jsp" />
            <div>
                <br />
                <form method="post" action="">
                    <fieldset>
                        <legend class="text_orange_uppercase_tittle"><fmt:message key="ge.desconectarse" />...</legend>
                        <div class="medidas">
                            <span class="text_black_event"><fmt:message key="ge.finalizado_sesion" /></span>
                            <br /><br />
                            <a class="text_blue_link_paper" href="/websae/home.jsp"><fmt:message key="ge.volver_ingresar" /></a>
                            <br /><br />
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>