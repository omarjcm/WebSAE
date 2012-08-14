function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    cargar_convocatoria(id_evento, idioma);
    //cargar_temas(id_evento); 
}

function cargar_convocatoria(id_evento, idioma){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_convocatoria',
        handleAs: 'json',
        load: function(xhr) {
            html += "<br /><p align='left'><b>"+ parseIdioma("descripcion")+":</b></p><br />";
            if (idioma == "es" || idioma == "es#" )
                html += parseVacio(xhr.descripcion_es) + "<br /><br /><br />";
            else if (idioma == "en" || idioma == "en#" )
                html += parseVacio(xhr.descripcion_en) + "<br /><br /><br />";
            else 
                html += parseVacio(xhr.descripcion_pt) + "<br /><br /><br />";
            
            if (parseVacio(xhr.ruta_formato)!=""){
                html += "<b>" + parseIdioma("formato de Presentacion de Articulo") + "</b><br /><br />";
                html += "Para bajar la guía o formato de entrega para la presentación de artículos de clic en descargar<br />";
                html += "<p align='center'><a title='Clic Derecho, Guardar Enlace Como...' href='/websae/archivo/formato/"+xhr.ruta_formato+"' target='_blank'>["+ parseIdioma("descargar") +"]</a></p><br /><br />";
            }

            html += "<b>" + parseIdioma("Fechas Importantes") +":</b><br /><br /><ul>";
            if (parseVacio(xhr.fecha_max_resumen)!="") html += "<li>Fecha Limite de Recepción del Resumen: " + xhr.fecha_max_resumen + "</li>";
            html += "<li>Fecha Limite de Recepción de Artículo: " + xhr.fecha_max_presentacion + "</li>";
            html += "<li>Fecha de Notificación de Resultados: " + xhr.fecha_max_notificacion + "</li>";
            html += "<li>Fecha Limite de Recepción de Corrección: " + xhr.fecha_max_correccion + "</li>";
            
            if (parseVacio(xhr.fecha_max_presentacion)!=""){
               dijit.byId('div_temas').domNode.style.display = "block";
               cargar_temas(id_evento);
            }else{
                dijit.byId('div_temas').domNode.style.display = "none";
            }

            dojo.byId("descripcion_convocatoria").innerHTML = html;
        }
    });
}
function cargar_temas(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=temas_por_evento&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            
            html += "<br />";
            if (xhr.items.length>0){
                html += "<b>Temas y/o Áreas:</b><br /><br /><ul>";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "<li> - "+xhr.items[i].nombre+"</li>";
                    cola = cola + tmp;
                    tmp = "";
                }
                html += cola + "</ul>";
            }else{
                html += "No se ha ingresado ningún tema a tratar.<br/>\n\
                        Espere a que existan temas para poder subir un trabajo.";
                dijit.byId('btn_subir_trabajo').domNode.style.display = "none";
            }
            html += "";
            dojo.byId("descripcion_temas").innerHTML = html;
        }
    });
}

function parseVacio(campo){
    if (campo == null || campo == ""){
        return "";
    }else{
        return campo;
    }
}