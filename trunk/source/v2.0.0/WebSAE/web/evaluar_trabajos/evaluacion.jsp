<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Formato de Evaluación</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script type="text/javascript" src="evaluacion.js"></script>
        <script type="text/javascript" src="${initParam["dojo.js"]}" djConfig="parseOnLoad: true" ></script>
        <style type="text/css">
            .evaluacion_etiqueta {
                float:left;
                width:70px;
                margin:3px 5px;
                text-align:left;
                font-weight:bold;
            }

            .evaluacion_descripcion{
                float:left;
                width:400px;
                margin:3px 20px;
                text-align:left;
            }

            .evaluacion_seccion{
                float:left;
                width:400px;
                margin:25px 5px 0 5px;
                text-align:center;
                border: dotted 1px silver;
                background-color:#E6E6E6;
            }
            .evaluacion_instruccion{
                float:left;
                width:400px;
                margin:3px 5px;
                text-align:center;
                font-style:italic;
                font-size:small;
            }
            .evaluacion_pregunta {
                float:left;
                width:400px;
                margin:3px 5px;
                text-align:left;
                font-weight:bold;
                font-style:italic;
            }

            .evaluacion_respuesta{
                float:left;
                width:400px;
                margin:3px 20px;
                text-align:left;
            }
        </style>
    </head>

    <body onload="cargar_formato_evaluacion()">
        <div>
            <input id="id_evaluacion" name="id_evaluacion" type="hidden" value=""/>
            <div id="contenedor_formato" style="float:left;"></div>
            <div class="clearthefloats"></div>
            <div style="width:250px; margin-top:20px;">
                <input type="button" onclick="evaluar_articulo()" value="Evaluar Trabajo" />
                <input type="button" onclick="window.close();" value="Cerrar Ventana" />
            </div>
            <div class="clearthefloats"></div><br />
        </div>
    </body>
</html>
