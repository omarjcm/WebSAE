function inicializar(idioma, id_evento){
    cargar_materiales(id_evento);
    asignar_idioma(idioma);
    //cargar_mini_info(id_evento);
}

var xhr_precios;
function cargar_precios(){
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_categorias_evento',
        handleAs: 'json',
        load: function(xhr) {
            xhr_precios=xhr;
            cargar_mini_info();
        }
    });
}

function obtener_descuento(id_categoria){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_subcategorias_evento&id_categoria='+id_categoria,
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            if (xhr.items.length>0){
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "-" + xhr.items[i].subcategoria.nombre + ": " + xhr.items[i].porcentaje_descuento + "% <br />";
                    cola = cola + tmp;
                    tmp = "";
                }
                html += cola;
                dojo.byId("id_"+id_categoria).innerHTML = html;
            }else{
                dojo.byId("id_cat_"+id_categoria).innerHTML = "";
            }
        }
    });
}

function cargar_mini_info(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_categorias_por_evento',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            html = html + "<br /><div class='info_centrada'>";
            for (var i=0; i<xhr.items.length; i++) {
                tmp += xhr.items[i].nombre + "<br />";
                tmp += "<div align='center'><table border='1' cellpadding='500'><tr>";
                tmp += "<td align='center'>Periodo</td><td align='center'>Inversión</td>";
                tmp += "</tr>";
                cola = cola + tmp;
                tmp = "";
                var tmp2 = "";
                for (var j=0; j<xhr_precios.items.length; j++) {
                    if (xhr_precios.items[j].categoria.nombre==xhr.items[i].nombre){
                        tmp2 += "<tr>"
                        tmp2 += "<td width='60%' align='center'>&nbsp&nbsp " + xhr_precios.items[j].fecha_inicio + " / " + xhr_precios.items[j].fecha_fin + "&nbsp&nbsp</td><td width='40%' align='center'>$" + xhr_precios.items[j].precio + "</td>";
                        tmp2 += "</tr>"
                        cola = cola + tmp2;

                    }
                    tmp2 = "";
                }
                cola += "</table></div><br />";
            }
            html += cola + "</div>";

            dojo.byId("descripcion1").innerHTML = html;
        }
    });
    var html2="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_categorias_por_evento',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            html2 = html2 + "<br /><div class='info_centrada'>";
            for (var i=0; i<xhr.items.length; i++) {
                tmp += "<span id='id_cat_" + xhr.items[i].id_categoria + "'>" +xhr.items[i].nombre + "</span><br />";
                tmp += "<div id='id_" + xhr.items[i].id_categoria + "'></div>";
                cola = cola + tmp;
                tmp = "";
                obtener_descuento(xhr.items[i].id_categoria);
                cola += "<br />";
            }
            html2 += cola + "</div>";

            dojo.byId("descripcion2").innerHTML = html2;
        }
    });
}

function cargar_materiales(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=materiales&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length>0){
                html += "<div class='titulo'>La Inversión Incluye:</div><div style='float:left; margin-left:50px; '>";
                for (var i=0; i<xhr.items.length; i++) {
                    html += " - " + xhr.items[i].descripcion + "<br />";
                }
                dojo.byId("materiales_entregar").innerHTML = "</div>" + html;
            }
            cargar_precios();
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