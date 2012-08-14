var tipo_accion = "registrar";
function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_miembros_comite();

    dojo.connect(dojo.byId('chk_administrador'), "onclick", function(e) {
        cambiar_estado("administrador");
        e.stopPropagation();
    });
    dojo.connect(dojo.byId('chk_evaluador'), "onclick", function(e) {
        cambiar_estado("evaluador");
        e.stopPropagation();
    });
}

function accion_evaluador(id_usuario, accion){
    if(accion=="eliminar"){
        accion="modificar";
        dojo.byId("administrador").value = 0;
        dojo.byId("evaluador").value = 0;
    }
    dojo.xhrPost({
        url: '/websae/F_ce_administrar_objetos?tipo=' + accion + '_miembro_comite&txt_id_usuario=' + id_usuario+"&administrador="+dojo.byId("administrador").value+"&evaluador="+dojo.byId("evaluador").value+"&id_miembro="+dojo.byId("id_miembro").value,
        handleAs: 'json',
        load: function(xhr) {
            if(xhr.tipo=="OK"){
                alert(xhr.mensaje);
                limpiar_campos();
                tbl_miembros_comite();
            } else {
                alert("error:" + xhr.mensaje);
            }
        }
    });
}

function accion_evaluador_modificar(tipo){
    tipo_accion = tipo;
    cargar_usuario();
}

function limpiar_campos(){
    tipo_accion = "registrar";
    dojo.byId("div_botones_modificacion").style.display = "none";
    dojo.byId("txt_correo").value = "";
    dijit.byId("chk_administrador").setAttribute("checked",true);
    dijit.byId("chk_evaluador").setAttribute("checked",true);
    cambiar_estado("administrador");
    cambiar_estado("evaluador");
}

function cargar_usuario(){
    if (dijit.byId("txt_correo").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/usuario/F_mostrar_usuario?tipo=por_email&email=' + dojo.byId("txt_correo").value,
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo=="OK")
                    accion_evaluador(xhr.usuario.id_usuario,tipo_accion);
                else
                    alert(parseIdioma("no match"))
            }
        });
    }
}

function tbl_miembros_comite() {
    dijit.byId("txt_correo").setValue("");
    var formatNombre =function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = oRecord.getData("usuario.titulo.abreviatura") + " " + oRecord.getData("usuario.nombre") + " " + oRecord.getData("usuario.apellido");
    },
    formatAdministrador = function(elCell, oRecord, oColumn, sData) {
        var ico = oRecord.getData("es_administrador")==true?"yes_ico.png":"no_ico.png";
        elCell.innerHTML = "<div style='text-align:center; width:50px; margin:0 auto;'><img src='/websae/images/"+ico+"' width='25px' /></div>";
    },
    formatEvaluador = function(elCell, oRecord, oColumn, sData) {
        var ico = oRecord.getData("es_evaluador")==true?"yes_ico.png":"no_ico.png";
        elCell.innerHTML = "<div style='text-align:center; width:25px; margin:0 auto; '><img src='/websae/images/"+ico+"' width='25px' /></div>";

    },
    formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("modificar_evaluador('" + oRecord.getData("usuario.email") + "','" + oRecord.getData("es_administrador") + "','"+oRecord.getData("es_evaluador") + "','modificar','"+oRecord.getData("id")+"');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id","usuario.id_usuario","usuario.titulo.abreviatura","usuario.nombre", "usuario.apellido","usuario.email","es_administrador", "es_evaluador"],
    myColumnDefs = [
        {label:parseIdioma("miembro"), formatter:formatNombre, width:230},
        {label:parseIdioma("administrador"), formatter:formatAdministrador },
        {label:parseIdioma("evaluador"), formatter:formatEvaluador },
        {label:parseIdioma("accion"), formatter:formatUrl }
    ];
    
    tbl_generica("/websae/administracion/F_ce_mostrar_datos?tipo=miembros_comite_tecnico",fields,myColumnDefs,"tbl_miembros_comite");
}

function modificar_evaluador(email, es_admin, es_eval, tipo, id_miembro){
    dijit.byId("txt_correo").setValue(email);
    dijit.byId("chk_administrador").setAttribute("checked",es_admin=="true"?true:false);
    dijit.byId("chk_evaluador").setAttribute("checked",es_eval=="true"?true:false);
    cambiar_estado("administrador");
    cambiar_estado("evaluador");
    tipo_accion = tipo;
    dojo.byId("div_botones_modificacion").style.display = "block";
    dojo.byId("id_miembro").value = id_miembro;
}


function parse_vacio(campo){
    if (campo == null || campo == "null" || campo == "" || campo == "undefined"){
        return "";
    }else{
        return campo;
    }
}

function cambiar_estado(tipo){
    if(tipo=="administrador"){
        dojo.byId("span_administrador").innerHTML = dijit.byId("chk_administrador").checked?"(Si)":"(No)";
        dojo.byId("administrador").value = dijit.byId("chk_administrador").checked?"1":"0";
    }else{
        dojo.byId("span_evaluador").innerHTML = dijit.byId("chk_evaluador").checked?"(Si)":"(No)";
        dojo.byId("evaluador").value = dijit.byId("chk_evaluador").checked?"1":"0";
    }
}