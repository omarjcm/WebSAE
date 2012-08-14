function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    cargar_mini_info(id_evento);
}

function cargar_mini_info(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=buscar_evento_lang&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId('logo_evento').innerHTML = '<img class="div_logo_evento" src="/websae/images/evento/'+xhr.imagen+'" />';
            
            if (parseVacio(xhr.slogan)!="") html += "<div class='evento_slogan'>" + xhr.slogan + "</div><div class='clearfloat'></div>";
            if (parseVacio(xhr.descripcion)!="") html += "<div class='evento_atributo'>" + parseIdioma("objetivo") + ":</div><div class='evento_valor'>" + xhr.objetivo + "</div><div class='clearfloat'></div>";
            if (parseVacio(xhr.descripcion)!="") html += "<div class='evento_atributo'>" + parseIdioma("descripcion") + ":</div><div class='evento_valor'>" + xhr.descripcion + "</div><div class='clearfloat'></div>";
            if (parseVacio(xhr.dirigido)!="") html += "<div class='evento_atributo'>" + parseIdioma("dirigido") + ":</div><div class='evento_valor'>" + xhr.dirigido + "</div><div class='clearfloat'></div>";
            html += "<div class='evento_espacio'></div><div class='evento_atributo'>" + parseIdioma("lugar") + ":</div> <div class='evento_valor'>" + xhr.lugar + ", " + xhr.ciudad.nombre + " - " + xhr.ciudad.pais.nombre + "</div><div class='clearfloat'></div>";
            html += "<div class='evento_atributo'>" + parseIdioma("fecha") + ":</div> <div class='evento_valor'>" + parseIdioma("Del") + " " + xhr.fecha_inicio_long + " " + parseIdioma("al") + " " + xhr.fecha_fin_long + "</div><div class='clearfloat'></div>";
            //if (parseVacio(xhr.descripcion_registro)!="") html = html + parseIdioma("descripcion registro") + ": " + xhr.descripcion_registro + "<br /><br />";
            if (parseVacio(xhr.agenda_general)!="") html = html + xhr.agenda_general + "<br /><br />";
            if (parseVacio(xhr.email)!="") html = html + "<div class='evento_atributo'>Email de Contacto:</div> <div class='evento_valor'><a href='mailto:" + xhr.email + "'>" + xhr.email + "</a></div><div class='clearfloat'></div>";
            dojo.byId("descripcion_evento").innerHTML = html;
            cargar_subeventos();
        }
    });
}

function cargar_subeventos(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=subeventos',
        handleAs: 'json',
        load: function(xhr) {
            xhr_subeventos = xhr;
            if (xhr.items.length>0){
                var cola ="";
                var tmp = "";
                cola = "<div class='evento_titulo'>" + parseIdioma("actividades extras") + ":</div>";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "<div class='evento_informacion'>" + xhr.items[i].nombre + " - [<a href='javascript:detalle_subevento(" + xhr.items[i].id_evento + ",\"" + xhr.items[i].nombre + "\",\"" + parseVacio(xhr.items[i].descripcion) + "\")'>" + parseIdioma("detalles") + "</a>]</div><div class='clearfloat'></div>";
                    cola += tmp;
                    tmp = "";
                }
                html += cola;
                dojo.byId("descripcion_subeventos").innerHTML = html;
            }
        }
    });
}

function detalle_subevento(id_subevento, nombre_subevento, detalle_subevento){
    var html1="";
    html1 += "<b>" + nombre_subevento + "</b><br /><br />";
    if (detalle_subevento!=""){ html1 += detalle_subevento + "<br /><br />";}
    dojo.byId("detalle_subevento").innerHTML = html1;

    var html2="";
    /*dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_agenda_subevento&id_subevento='+id_subevento,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length>0){
                var cola ="";
                var tmp = "";
                cola = parseIdioma("agenda") + ":<br />";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += xhr.items[i].fecha_long + "<br />";
                    cola += tmp;
                    tmp = "";
                }
                html2 += cola;
            }
            dojo.byId("detalle_agenda_subevento").innerHTML = html2;
        }
    });*/
    html2 += parseIdioma("agenda") + ": Revisar calendario del Evento.<br />";
    dojo.byId("detalle_agenda_subevento").innerHTML = html2;

    var html3="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=conferencistas_subevento&id_subevento='+id_subevento,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length>0){
                var cola ="";
                var tmp = "";
                cola = "<br />" + parseIdioma("conferencista") + ":<br />";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += xhr.items[i].usuario.titulo.abreviatura + " " + xhr.items[i].usuario.nombre + " " + xhr.items[i].usuario.apellido + "<br />";
                    cola += tmp;
                    tmp = "";
                }
                html3 += cola;

            }
            dojo.byId("detalle_conferencistas_subevento").innerHTML = html3;
        }
    });

    var html4="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=materiales_subevento&id_subevento='+id_subevento,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length>0){
                var cola ="";
                var tmp = "";
                cola = "<br />" + parseIdioma("materiales") + ":<br />";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += xhr.items[i].tipo_material.nombre + "<br />";
                    cola += tmp;
                    tmp = "";
                }
                html4 += cola;
            }
            dojo.byId("detalle_materiales_subevento").innerHTML = html4;
        }
    });

    dijit.byId('div_detalle_subevento').show();
}

function parseVacio(campo){
    if (campo == null || campo == ""){
        return "";
    }else{
        return campo;
    }
}