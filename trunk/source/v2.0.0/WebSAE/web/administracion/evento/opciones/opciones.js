/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

dojo.addOnLoad(
  function(){
      asignar_info_evento();
      cargar_opciones();
  }
);

var num_opciones = 0;
var contador_opciones = 0;

function modificar_menu(){
    var opciones = document.frm_opciones_asignadas.opciones;

    num_opciones = opciones.length;
    contador_opciones=0;
    for (var i=0; i<opciones.length; i++) {
        if (opciones[i].checked)
            asignar_evento_opciones("habilitar", opciones[i].value );
        else
            asignar_evento_opciones("deshabilitar", opciones[i].value );
    }
}

function asignar_evento_opciones(accion, id_opcion) {
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=asignar_evento_opcion&accion='+accion+"&id_opcion="+id_opcion,
        sync: true,
        handleAs: 'json',
        load: function(xhr) {
            contador_opciones++;
            if (num_opciones == contador_opciones) {
                //alert(xhr.mensaje);
                alert("Menu de opciones modificado con exito.");
                dijit.byId('asignar-opciones').hide();
                cargar_opciones();
            }
        }
    });
}

function editar_opciones(){
    dijit.byId('asignar-opciones').show();
    cargar_opciones_asignadas();
    cargar_opciones_modif();
}

function cargar_opciones() {
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_opciones_por_id_evento',
        handleAs: 'json',
        load: function(xhr) {
            var html = '<ul>';
            for (var i=0; i<xhr.opciones.length; i++) {
                html += '<li><a href="'+xhr.opciones[i].url+'">'+xhr.opciones[i].nombre+'</a></li>';
            }
            html += '</ul>';
            dojo.byId('opciones-asignadas').innerHTML = html;
        }
    });
}

function cargar_opciones_asignadas() {
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_opciones_por_id_evento',
        handleAs: 'json',
        load: function(xhr) {
            opciones_asignadas = xhr.opciones;
        }
    });
}

var opciones_asignadas;

function cargar_opciones_modif() {
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_opciones_evento',
        handleAs: 'json',
        load: function(xhr) {
            var html = '<form id="frm_opciones_asignadas" name="frm_opciones_asignadas" style="width:200px;">';
            for (var i=0; i<xhr.opciones.length; i++) {
                html += '<label style="float:left; width:80px;"></label>';
                if (esta_asignado(xhr.opciones[i].id_opcion, opciones_asignadas))
                    html += '<label style="text-align:left; float:left;"><input style="width:auto; margin: 0 5px 0 0;" type="checkbox" id="opciones" dojoType="dijit.form.CheckBox" checked="checked" value="'+xhr.opciones[i].id_opcion+'" /> '+xhr.opciones[i].nombre+'</label>';
                else
                    html += '<label style="text-align:left; float:left;"><input style="width:auto; margin: 0 5px 0 0;" type="checkbox" id="opciones" dojoType="dijit.form.CheckBox" value="'+xhr.opciones[i].id_opcion+'" /> '+xhr.opciones[i].nombre+'</label>';
                html += '<div class="clearthefloats"></div>';
            }
            html += '</form>';
            dojo.byId('opciones-asignadas-modif').innerHTML = html;
        }
    });
}


function esta_asignado(id, lista) {
    for (var i=0; i<lista.length; i++) {
        if (id == lista[i].id_opcion)
            return true;
    }
    return false;
}

function asignar_info_evento(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_evento',
        handleAs: 'json',
        load: function(xhr) {            
            dojo.byId('rango_fechas').innerHTML = xhr.fecha_inicio + ' -:- ' + xhr.fecha_fin;
        }
    });
}