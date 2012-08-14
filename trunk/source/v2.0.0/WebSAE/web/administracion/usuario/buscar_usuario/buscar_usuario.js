function inicializar(idioma){
    asignar_idioma(idioma);
}

function cargar_usuario() {
    if (dijit.byId("txt_correo").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/usuario/F_mostrar_usuario?tipo=por_email&email='+dojo.byId("txt_correo").value,
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK")  {
                    dojo.byId("txt_correo").value = xhr.usuario.email;
                    //dojo.byId("informacion-personal").style.visibility = "visible";
                    //dojo.byId("informacion-personal").style.display = "block";

                    dojo.byId("buscar-usuario-admin").style.visibility = "visible";
                    dojo.byId("buscar-usuario-admin").style.display = "block";

                    //dojo.byId("clave").innerHTML = xhr.usuario.clave;
                    dojo.byId("nombre_apellido").innerHTML = xhr.usuario.nombre + ' ' + xhr.usuario.apellido;

                    if (xhr.usuario.genero != null){
                      if (xhr.usuario.genero=="f"){
                          dojo.byId("genero").innerHTML = "Femenino";
                      }else{
                          dojo.byId("genero").innerHTML = "Masculino";
                      }
                    }
                    else dojo.byId("genero").innerHTML = "";
                    if (xhr.usuario.direccion != null) dojo.byId("direccion").innerHTML = xhr.usuario.direccion;
                    else dojo.byId("direccion").innerHTML = "";
                    if (xhr.usuario.telefono != null) dojo.byId("telefono").innerHTML = xhr.usuario.telefono;
                    else dojo.byId("telefono").innerHTML = "";
                    if (xhr.usuario.celular != null) dojo.byId("celular").innerHTML = xhr.usuario.celular;
                    else dojo.byId("celular").innerHTML = "";
                    if (xhr.usuario.fecha_nacimiento != null) dojo.byId("fecha_nacimiento").innerHTML = xhr.usuario.fecha_nacimiento;
                    else dojo.byId("fecha_nacimiento").innerHTML = "";
                    dojo.byId("lugar").innerHTML = xhr.usuario.ciudad.nombre + ' - ' + xhr.usuario.ciudad.pais.nombre;

                    dojo.byId("titulo_usuario").innerHTML = xhr.usuario.titulo.nombre;
                    dojo.byId("empresa").innerHTML = xhr.usuario.empresa.nombre;
                    dojo.byId("cargo").innerHTML = xhr.usuario.cargo.nombre;

                    var perfiles = xhr.usuario.perfiles;
                    if (perfiles != null && perfiles.length > 0) {
                        //var html = '<div class="sub_titulo" onclick="visualizar(\''+"roles-asignados"+'\');">Roles Asignados</div>';
                        var html = '<div id="roles-asignados" style="visibility: hidden; display: none;">';
                        for (var i=0; i<perfiles.length; i++) {
                            html += '<label class="info">'+(i+1)+'. </label>'+
                                    '<label class="info_dato">'+perfiles[i].nombre+'</label>'+
                                    '<div class="clearthefloats"></div>';
                        }
                        html += '</div>';
                        dojo.byId("div_roles_asignados").innerHTML = html;
                    }
                } else if (xhr.tipo == "ERROR")  {
                    alert(xhr.mensaje);
                    dojo.byId("buscar-usuario-admin").style.visibility = "hidden";
                    dojo.byId("buscar-usuario-admin").style.display = "none";
                }
            }
        });
    }
}

function visualizar(id, more) {
    if (dojo.byId(id).style.visibility == "visible") {
        dojo.byId(id).style.visibility = "hidden";
        dojo.byId(id).style.display = "none";
        dojo.byId(more).innerHTML = "[+]";
    } else {
        dojo.byId(id).style.visibility = "visible";
        dojo.byId(id).style.display = "block";
        dojo.byId(more).innerHTML = "[-]";
    }
}