function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    cargar_mini_info(id_evento);
}

function cargar_mini_info(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_auspiciantes',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            html = html + "<br /><div class='info_centrada'>";
            for (var i=0; i<xhr.items.length; i++) {
                //forma1 de mostrar datos
                //tmp = xhr.items[i].empresa.nombre + " - " + xhr.items[i].empresa.ciudad.nombre + ", " + xhr.items[i].empresa.ciudad.pais.nombre + " - Monto: $" + xhr.items[i].monto + "<br />";
                //forma2 de mostrar datos
                if (parseVacio(xhr.items[i].empresa.nombre)!="")tmp += "<img height='50px' src='/websae/images/logo_empresa/"+xhr.items[i].empresa.logo+"' /><br />";
                tmp += xhr.items[i].empresa.nombre + "<br />" + xhr.items[i].empresa.ciudad.nombre + ", " + xhr.items[i].empresa.ciudad.pais.nombre + "<br />";
                //tmp += "Monto: $" + xhr.items[i].monto + "<br />";
                //if (parseVacio(xhr.items[i].descripcion)!=""){tmp += "Descripción: " + xhr.items[i].descripcion + "<br />"}
                cola = cola + tmp + "<br /><br />";
                tmp = "";
            }
            html += cola + "</div>";

            /*if (parseVacio(xhr.imagen)!="") html = html + "<img height=\"200px\" src=\"/websae/images/evento/" + xhr.imagen + "\"/><br /><br />";
            if (parseVacio(xhr.slogan)!="") html = html + xhr.slogan + "<br /><br />";
            if (parseVacio(xhr.descripcion)!="") html = html + parseIdioma("descripcion") + ": " + xhr.descripcion + "<br /><br />";
            if (parseVacio(xhr.dirigido)!="") html = html + parseIdioma("dirigido") + ": " + xhr.dirigido + "<br /><br />";
            html = html + "Intervalo del Evento:<br />";
            html = html + " - " + parseIdioma("fecha inicio") + ":" + xhr.fecha_inicio_long + "<br />";
            html = html + " - " + parseIdioma("fecha fin") + ":" + xhr.fecha_fin_long + "<br /><br />";
            if (parseVacio(xhr.descripcion_registro)!="") html = html + parseIdioma("descripcion registro") + ": " + xhr.descripcion_registro + "<br /><br />";
            if (parseVacio(xhr.agenda_general)!="") html = html + xhr.agenda_general + "<br /><br />";
            if (parseVacio(xhr.email)!="") html = html + "Email de Contacto: <a href='mailto:" + xhr.email + "'>" + xhr.email + "</a>";
            //html = html + "";*/
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