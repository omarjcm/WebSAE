var id_articulo="";
function evaluar_articulo(){
    dojo.xhrPost({
        url: '/websae/F_ce_administrar_objetos?tipo=evaluar_articulo&id_articulo='+id_articulo+'&id_evaluacion='+dojo.byId("id_evaluacion").value,
        handleAs: 'json',
        load: function(xhr) {
            window.opener.tbl_articulos();
            //alert(xhr.mensaje);
            alert("Su evaluación ha sido ingresada con éxito.");
            window.close();
        }
    });
}

function cargar_formato_evaluacion(){
    if (fecha_actual<=devuelve_fecha(fecha_max_evaluacion)){
        id_articulo = obtener_articulo_url("id_articulo");
        var html="";
        dojo.xhrPost({
            url: '/websae/F_ce_mostrar_datos?tipo=mostrar_evaluacion_articulo&id_articulo='+id_articulo,
            handleAs: 'json',
            load: function(xhr) {
                dojo.byId("id_evaluacion").value = xhr.id_evaluacion;
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
                                    html += "<label class=\"evaluacion_respuesta\"><textarea cols=\"45\" rows=\"4\" maxlength=\"200\" onkeyup=\"return ismaxlength(this)\" onchange=\"ingresar_respuesta('"+id_articulo+"','"+xhr.secciones.items[i].id_seccion+"','" + xhr.secciones.items[i].oculta + "','"+xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta+"','"+xhr.secciones.items[i].preguntas.items[j].id_pregunta+"','',this.value);\"></textarea></label>";
                                    html += "<div class=\"clearthefloats\"></div><br />";
                                }else{
                                    for(var k=0;k!=xhr.secciones.items[i].preguntas.items[j].alternativas.items.length;k++){
                                        if (xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta==1){
                                            //html += "<label class=\"evaluacion_respuesta\"><input type='radio' onchange='javascript:alert(\"hola\");' name='alt"+j+"'/>" + xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].nombre+"</label>";
                                            html += "<label class=\"evaluacion_respuesta\"><input type='radio' onchange=\"ingresar_respuesta('"+id_articulo+"','"+xhr.secciones.items[i].id_seccion+"','" + xhr.secciones.items[i].oculta + "','"+xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta+"','"+xhr.secciones.items[i].preguntas.items[j].id_pregunta+"','"+xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].id_alternativa+"','');\" name='alt"+j+"'/>" + xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].nombre+"</label>";
                                        }
                                        if (xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta==2){
                                            html += "<label class=\"evaluacion_respuesta\"><input type='checkbox' onchange=\"ingresar_respuesta('"+id_articulo+"','"+xhr.secciones.items[i].id_seccion+"','" + xhr.secciones.items[i].oculta + "','"+xhr.secciones.items[i].preguntas.items[j].tipo_pregunta.id_tipo_pregunta+"','"+xhr.secciones.items[i].preguntas.items[j].id_pregunta+"','"+xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].id_alternativa+"','');\" name='alt"+j+"'/>" + xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].nombre+"</label>";
                                            //html += "<label class=\"evaluacion_respuesta\"><input type='checkbox' name='alt"+j+"'/>"  + xhr.secciones.items[i].preguntas.items[j].alternativas.items[k].nombre+"</label>";
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
    }else{
        alert("La fecha máxima de evaluación era hasta el " + fecha_max_evaluacion);
    }
}

function ingresar_respuesta(id_articulo, id_seccion, tipo_seccion, tipo_pregunta, id_pregunta, id_respuesta, txt_respuesta){
    var param = "id_articulo=" + id_articulo + "&id_seccion=" + id_seccion +  "&tipo_seccion=" + tipo_seccion + "&tipo_pregunta="+tipo_pregunta;
        param += "&id_pregunta=" + id_pregunta + "&id_respuesta=" + id_respuesta + "&txt_respuesta=" + txt_respuesta;

    dojo.xhrPost({
        url: '/websae/F_ce_administrar_detalles?tipo=ingresar_respuesta&'+param,
        handleAs: 'json',
        load: function(xhr) {}
    });
}

function obtener_articulo_url(){
    var Url = location.href;
    Url = Url.replace(/.*\?(.*?)/,"$1");
    Variables = Url.split ("&");
    for (i = 0; i < Variables.length; i++) {
       Separ = Variables[i].split("=");
       eval ('var '+Separ[0]+'="'+Separ[1]+'"');
    }
    return(id_articulo);
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
        obj.value=obj.value.substring(0,mlength)
}