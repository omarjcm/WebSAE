function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_conferencistas();
}

function accion_conferencista(id_usuario, accion){
    dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo=' + accion + '_conferencista_evento&txt_id_usuario=' + id_usuario,
            handleAs: 'json',
            load: function(xhr) {
                alert(xhr.mensaje);
                tbl_conferencistas();
            }
    });
}

function cargar_usuario(){
    if (dijit.byId("txt_correo").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/usuario/F_mostrar_usuario?tipo=buscar_conferencista_por_email&email=' + dojo.byId("txt_correo").value,
            handleAs: 'json',
            load: function(xhr) {
                if(xhr.usuario.nombre==null){
                    alert(parseIdioma("no match"));
                }else{
                    accion_conferencista(xhr.usuario.id_usuario,'registrar');
                }
            }
        });
    }
}

function construir_imagen( imagen ) {
    if (imagen == null || imagen == "")
        return "/websae/images/foto_usuario/default.jpg";
    else
        return "/websae/images/foto_usuario/" + imagen;
}

function obtener_dato( dato ) {
    return ((dato != null) ? dato : "" );
}

function popup_conferencista(id_conferencista, invitado){
    var foto_usuario = "";
    dojo.xhrPost({
        url: '/websae/administracion/usuario/F_mostrar_usuario?tipo=buscar_usuario&id_usuario='+id_conferencista,
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId("nombre_completo").innerHTML = xhr.titulo.abreviatura + " " + xhr.nombre + " " + xhr.apellido;
            var foto_usuario = parse_vacio(xhr.foto)==""?"default.jpg":xhr.foto
            dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/foto_usuario/" + foto_usuario + "\" height=\"40px\" alt=\"\" />"
//            dojo.byId("foto").innerHTML = '<label id="foto_usuario" class="info_modificar"><img src="'+ construir_imagen( xhr.foto ) +'" height="40px" alt="" /></label>';
            dojo.byId("txt_id_usuario").value = id_conferencista;
            dojo.byId("txt_biografia").value = obtener_dato( xhr.hoja_vida );
            dojo.byId("txt_linkedin").value = obtener_dato( xhr.linkedin );
            dijit.byId("rb_invitado").setValue( invitado );
            if (xhr.foto != null && xhr.foto != "")
                dojo.byId("txt_imagen2").value = xhr.foto;
            else
                dojo.byId("txt_imagen2").value = dojo.byId("txt_imagen").value;
        }
    });   
    dijit.byId('conferencista_modificar').show();
}

function actualizar_conferencista_infoextra(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=actualizar_conferencista_evento',
        form: 'frm_conferencista',
        handleAs: 'json',
        load: function(xhr) {
            if(xhr.tipo=="OK"){
                alert("Actualizado con éxito");
                tbl_conferencistas();
                dijit.byId('conferencista_modificar').hide();
            }
        }
    });
}

function tbl_conferencistas() {
    dojo.byId("txt_imagen2").value="";
    dijit.byId("txt_correo").setValue("");

    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("accion_conferencista('" + oRecord.getData("usuario.id_usuario") + "','eliminar');","/websae/images/unsupported.png");
    },
    formatConferencista = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_conferencista('" + oRecord.getData("usuario.id_usuario") + "', " + oRecord.getData("invitado") + ");",oRecord.getData("usuario.titulo.abreviatura") + " " + oRecord.getData("usuario.nombre") + " " + oRecord.getData("usuario.apellido"));
    },
    fields = ["id_conferencista_evento", "invitado", "usuario.id_usuario","usuario.nombre", "usuario.apellido", "usuario.titulo.abreviatura"],
    myColumnDefs = [
        {label:parseIdioma("conferencista"), formatter:formatConferencista, width:300, className:"link_tabla"},
        {label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=conferencistas_evento",fields,myColumnDefs,"tbl_conferencistas");
}

function parse_vacio(campo){
    if (campo == null || campo == "" || campo == "undefined"){
        return "";
    }else{
        return campo;
    }
}

function cargar_foto(ruta1,ruta2,file){
    ajaxFileUpload(ruta1,ruta2,file,"/websae/images/foto_usuario/");
    //dojo.byId("foto").innerHTML = '<label id="foto_usuario" class="info_modificar"><img src="'+ construir_imagen( dojo.byId(file+"2").value ) +'" height="40px" alt="" id="imagen_cargada" /></label>';
}