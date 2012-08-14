function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    cargar_mini_info(id_evento);
}

function cargar_mini_info(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_organizadores_evento',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            html = html + "<br /><div class='info_centrada'>";
            for (var i=0; i<xhr.items.length; i++) {
                if (parseVacio(xhr.items[i].logo)!="")tmp += "<img height='50px' src='/websae/images/logo_empresa/"+xhr.items[i].logo+"' /><br />";
                tmp += xhr.items[i].nombre + "<br />";
                if (parseVacio(xhr.items[i].url)) tmp += "<a href='" + xhr.items[i].url +"' target='_blank'>" + xhr.items[i].url + "</a><br />";
                if (parseVacio(xhr.items[i].telefono)) tmp += xhr.items[i].telefono + "<br />";
                cola = cola + tmp + "<br /><br />";
                tmp = "";
            }
            html += cola + "</div>";

            dojo.byId("descripcion").innerHTML = html;
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