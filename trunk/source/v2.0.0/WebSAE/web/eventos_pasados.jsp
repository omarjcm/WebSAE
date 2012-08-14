<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <script type="text/javascript" src="js/generales.js"></script>
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ms.eventos_pasados" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link href="css/menu.css" rel="stylesheet" type="text/css" />
        <link href="css/links.css" rel="stylesheet" type="text/css" />
        <link href="css/evento.css" rel="stylesheet" type="text/css" />
        <link href="css/div.css" rel="stylesheet" type="text/css" />
        <link href="css/texto.css" rel="stylesheet" type="text/css" />
        <link href="css/forms.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            @import "/websae/js/dijit/themes/tundra/tundra.css";
            @import "/websae/js/dojo/resources/dojo.css";
        </style>
    </head>

    <body>
        <div id="div_body_general">
            <div class="align_right">
                <jsp:include page="menu_general.jsp" />
            </div>
            <jsp:include page="encabezado.jsp" />
            <div id="div_body_evento">
                <div id="div_izquierdo">
                    <div id="div_texto_izquierdo">
                        <span class="text_black_uppercase"><fmt:message key="even.informacion_eventos" /></span>
                        <p class="text_black_event"><fmt:message key="even.descripcion2" /></p>
                        <p class="text_black_event"><fmt:message key="even.inquietud" /> <a href="mailto:<fmt:message key="ge.correo_ayudante" />"><fmt:message key="ge.correo_ayudante" /></a></p>
                    </div>
                </div>
                <div id="div_central">
                    <form method="post" action="">
                        <fieldset>
                            <legend>Componente 8 del Proyecto VLIR-ESPOL  <br><br> Eventos que se realizaron:</legend>
                                <div class="medidas"><!-- Aqui se debe cargar automaticamente desde la base-->
                                    <p><a id="36" href="evento/Controlador_Eventos?id_evento=36" class="text_blue_tittle_event">
                                            Jornadas de Sistemas de Telecomunicaciones 2008
                                        </a>miércoles, 14 de mayo de 2008 - viernes, 16 de mayo de 2008
                                    </p>
                                    <p><a id="45" href="evento/Controlador_Eventos?id_evento=45" class="text_blue_tittle_event">
                                            Simulink Aplicado a las Telecomunicaciones (abril 2008)
                                        </a>lunes, 7 de abril de 2008 - viernes, 18 de abril de 2008
                                    </p>
                                    <p><a id="46" href="evento/Controlador_Eventos?id_evento=46" class="text_blue_tittle_event">
                                            Sistemas de VoIP con Asterisk (Marzo 2008)
                                        </a>lunes, 10 de marzo de 2008 - martes, 25 de marzo de 2008
                                    </p>
                                </div>
                        </fieldset>
                    </form>
                </div>
                <jsp:include page="pie_pagina.jsp" />
            </div>
        </div>
    </body>
</html>