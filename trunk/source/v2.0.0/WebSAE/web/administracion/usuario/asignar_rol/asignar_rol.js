var perfiles_lista;

function inicializar(idioma){
    asignar_idioma(idioma);
    cargar_perfiles();
}

function cargar_perfiles () {
    dojo.xhrPost({
        url: '/websae/administracion/F_mostrar_perfil',
        handleAs: 'json',
        load: function(xhr) {
            perfiles_lista = xhr.perfiles;
        }
    });
}

function cargar_usuario() {
    if (dijit.byId("txt_correo").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/usuario/F_mostrar_usuario?tipo=por_email&email='+dojo.byId("txt_correo").value,
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK")  {
                    dojo.byId("txt_correo").value = xhr.usuario.email;

                    dojo.byId("buscar-usuario-admin").style.visibility = "visible";
                    dojo.byId("buscar-usuario-admin").style.display = "block";

                    var html = '<form id="form_perfiles" name="form_perfiles">';
                    for (var i=0; i<perfiles_lista.length; i++) {
                        html += '<label class="info"></label>';
                        if (esta_asignado(perfiles_lista[i].id_perfil, xhr.usuario.perfiles)) {
                            html += '<label class="info_dato"><input type="checkbox" id="perfiles" dojoType="dijit.form.CheckBox" checked="checked" value="'+perfiles_lista[i].id_perfil+'" /> '+perfiles_lista[i].nombre+'</label>';
                        } else
                            html += '<label class="info_dato"><input type="checkbox" id="perfiles" dojoType="dijit.form.CheckBox" value="'+perfiles_lista[i].id_perfil+'" /> '+perfiles_lista[i].nombre+'</label>';
                        html += '<div class="clearthefloats"></div>';
                    }
                    html += '<br /><label class="info"></label></form>';
                    dojo.byId("asignar-rol").innerHTML = html;
                } else if (xhr.tipo == "ERROR")  {
                    alert(xhr.mensaje);
                    dojo.byId("buscar-usuario-admin").style.visibility = "hidden";
                    dojo.byId("buscar-usuario-admin").style.display = "none";
                }
            }
        });
    }
}

function visualizar(id) {
    if (dojo.byId(id).style.visibility == "visible") {
        dojo.byId(id).style.visibility = "hidden";
        dojo.byId(id).style.display = "none";
    } else {
        dojo.byId(id).style.visibility = "visible";
        dojo.byId(id).style.display = "block";
    }
}

function esta_asignado(id, lista) {
    for (var i=0; i<lista.length; i++) {
        if (id == lista[i].id_perfil)
            return true;
    }
    return false;
}

var num_perfiles = 0;
var contador_perfiles = 0;

function validar_asignar_usuario_perfil() {
    var opciones = document.form_perfiles.perfiles;
    
    num_perfiles = opciones.length;
    contador_perfiles=0;
    for (var i=0; i<opciones.length; i++) {
        if (opciones[i].checked)
            asignar_usuario_perfil("habilitar", opciones[i].value );
        else
            asignar_usuario_perfil("deshabilitar", opciones[i].value );
    }
}

function asignar_usuario_perfil(accion, id_perfil) {
    dojo.xhrPost({
        url: 'F_asignar_usuario_perfil?accion='+accion+'&correo='+dojo.byId("txt_correo").value+"&id_perfil="+id_perfil,
        sync: true,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                contador_perfiles++;
            }
            if (num_perfiles == contador_perfiles) {
                alert(xhr.mensaje);
            }
        }
    });
}