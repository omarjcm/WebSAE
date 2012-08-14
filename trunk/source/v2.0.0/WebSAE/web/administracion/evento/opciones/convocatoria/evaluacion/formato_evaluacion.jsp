<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Formato de Evaluación</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script type="text/javascript" src="${initParam["dojo.js"]}" djConfig="parseOnLoad: true" ></script>
        <script type="text/javascript">
            function cargar_formato_evaluacion(){
                var html="";
                dojo.xhrPost({
                    url: '/websae/F_ce_mostrar_datos?tipo=mostrar_evaluacion',
                    handleAs: 'json',
                    load: function(xhr) {
                        html += "<div style=\"float:left; width:400px; margin:0 auto;\">";
                        html += "<label class='evaluacion_etiqueta'>Mensaje:</label><br /><label class='evaluacion_descripcion'>" + xhr.mensaje + "</label>";
                        html += "<div class=\"clearthefloats\"></div><br />";
                        html += "<label class='evaluacion_etiqueta'>Instrucciones:</label><br /><label class='evaluacion_descripcion'>\" "+ xhr.descripcion + " \"</label>";
                        for(var i=0;i!=xhr.secciones.items.length;i++){
                            html += "<div class=\"clearthefloats\"></div><br /><br />";
                            html += "<label class=\"evaluacion_seccion\">"+xhr.secciones.items[i].nombre;
                            if (xhr.secciones.items[i].oculta==true)
                                html += " (Confidencial)";
                            else
                                html += " (No Confidencial)";
                            html += "</label>";
                            html += "<label class=\"evaluacion_instruccion\">\" "+xhr.secciones.items[i].descripcion+" \"</label>";
                            html += "<div class=\"clearthefloats\"></div><br />";
                            if (xhr.secciones.items[i].preguntas.items.length>0){
                                for(var j=0;j!=xhr.secciones.items[i].preguntas.items.length;j++){
                                    if (xhr.secciones.items[i].preguntas.items[j].alternativas.items.length>0 || xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta==3){
                                        html += "<label class=\"evaluacion_pregunta\">" + xhr.secciones.items[i].preguntas.items[j].nombre+"</label>";
                                        if (xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta==3){
                                            html += "<label class=\"evaluacion_respuesta\"><textarea cols=\"45\" rows=\"4\"></textarea></label>";
                                            html += "<div class=\"clearthefloats\"></div><br />";
                                        }else{
                                            for(var k=0;k!=xhr.secciones.items[i].preguntas.items[j].alternativas.items.length;k++){
                                                if (xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta==1){
                                                    html += "<label class=\"evaluacion_respuesta\"><input type='radio' name='alt"+j+"'/>" + xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].nombre+"</label>";
                                                }
                                                if (xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta==2){
                                                    html += "<label class=\"evaluacion_respuesta\"><input type='checkbox' name='alt"+j+"'/>"  + xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].nombre+"</label>";
                                                }
                                            }
                                        }
                                        html += "<div class=\"clearthefloats\"></div><br /><br />";
                                    }
                                }
                            }else{}//html += "... las secciones deben tener preguntas para presentarse..."}
                        }
                        html += "</div>";
                        document.getElementById("contenedor_formato").innerHTML = html;
                    }
                });
            }

        </script>
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
            <div id="contenedor_formato"></div>
            <div class="clearthefloats"></div>
            <input type="button" onclick="window.close();" value="Cerrar" />
        </div>
    </body>
</html>
