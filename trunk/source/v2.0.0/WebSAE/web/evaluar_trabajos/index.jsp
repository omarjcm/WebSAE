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
        <title><fmt:message key="ge.tituloProyecto" /> - <fmt:message key="ge.evaluacion" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <jsp:include page="/icono.jsp" />

        <jsp:include page="declaracion.jsp" />
        <style type="text/css">
            .popup_paper_etiqueta {
                float:left;
                width:70px;
                margin:3px 5px;
                text-align:right;
                font-weight:bold;
            }

            .popup_paper_respuesta{
                float:left;
                width:330px;
                margin:3px 5px;
                text-align:left;
            }
        </style>

    </head>

    <body class="yui-skin-sam tundra" onload="inicializar('${lang}')">
        <div id="div_body_general">
            <div id="div_cabecera">
                <jsp:include page="/menu/menu_general.jsp" />
            </div>
            <div id="div_banner_superior">
                <div class="div_menu">
                    <jsp:include page="/menu/encabezado.jsp" />
                </div>
                <jsp:include page="/vistas/logo_websae.jsp" />
            </div>

            <div id="div_contenedor_admin">
                <div id="div_info">
                    <div class="div_menu_admin_titulo">
                        <fmt:message key="us.eventos_pasados" />
                    </div>
                    <div class="div_menu_descripcion">
                        Aquí podrá encontrar las evaluaciones pendientes de los diferentes eventos por realizar... <br />
                    </div>
                </div>
                <jsp:include page="/vistas/info_usuario.jsp" />
                <div id="ubicacion">
                    <span class="ubicacion"><fmt:message key="pistas.ud_esta_en" />:</span> <span class="ubicacion"><fmt:message key="ge.evaluacion" /></span>
                </div>

                <div id="div_informacion_admin">
                    <label style="float:left; margin:10px 0 0 10px;">Para ver los detalles del paper, de clic sobre el nombre.</label><br />
                    <div class="clearthefloats"></div><br />
                    <div id="tbl_papers" style="width:470px; margin:0 auto 50px auto;"></div>
                    <!--a href="javascript:dijit.byId('div_paper').show();">paper</a>
                    <a href="evaluacion.jsp" target="_blank" onclick="window.open(this.href, this.target, 'width=480,height=580,scrollbars=yes'); return false;"><img src="/websae/images/evaluacion.jpg" height="25px" alt="" /></a-->
                    <div class="clearthefloats"></div>
                </div>
                <div class="clearthefloats"></div>
            </div>
        </div>
        <div id="div_articulo" dojoType="dijit.Dialog" title="<fmt:message key="ge.informacion_articulo" />" style="width:470px;">
            <jsp:include page="articulo.jsp" />
        </div>
        <div class="clearthefloats"></div>

        <div class="clearthefloats"></div>
        <div id="div_copyrigth">
            <jsp:include page="/pie_pagina.jsp" />
        </div>
    </body>
</html>