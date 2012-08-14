function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    cargar_mini_info(id_evento);
}

function cargar_mini_info(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=conferencistas_evento',
        handleAs: 'json',
        load: function(xhr) {
            var html = "";
            for (var i=0; i<xhr.items.length; i++) {
                html += '<div class="evento_informacion">';
                html += '<img class="imagen_conferencista" src="' + construir_imagen( xhr.items[i].usuario.foto ) + '" />';
                html += '<div class="info_conferencista">';
                html += '<p>' + xhr.items[i].usuario.titulo.abreviatura + ' ' + xhr.items[i].usuario.nombre + ' ' + xhr.items[i].usuario.apellido;
                if (parseVacio(xhr.items[i].usuario.linkedin) != "") html += '<a target="blank" style="padding-right: 16px; background-image: url(http://static.linkedin.com/img/icon/icon_company_insider_in_12x12.gif); background-repeat: no-repeat; background-position: right bottom;" id="linkedin_badge_gen_0" class="linkedin-profileinsider-popup" href="' + xhr.items[i].usuario.linkedin + '"></a>';
                html += '</p>';
                html += '<p>' + xhr.items[i].usuario.ciudad.nombre + ', ' + xhr.items[i].usuario.ciudad.pais.nombre + '<p>';
                html += '<div class="evento_espacio"></div>';
                if (parseVacio(xhr.items[i].usuario.hoja_vida)!="") html += '<p>' + xhr.items[i].usuario.hoja_vida + '</p>';
                html += '</div>';
                html += '</div><div class="clearfloat"></div>';
            }
            dojo.byId("descripcion").innerHTML = html;
        }
    });
}

function construir_imagen(imagen) {
    if (parseVacio(imagen) == "")
        return "/websae/images/foto_usuario/default.jpg";
    else
        return "/websae/images/foto_usuario/" + imagen;
}

function parseVacio(campo){
    if (campo == null || campo == "" || campo == undefined || campo == "undefined" || campo.lenght == 0){
        return "";
    }else{
        return campo;
    }
}