var ref_id_seccion="";

function inicializar(idioma, _id_seccion){
    ref_id_seccion = _id_seccion;
    asignar_idioma(idioma);
    //cargar_datos_pregunta();
    buscar_seccion(_id_seccion);
    tbl_preguntas();
    limpiar_pregunta();
}

function accion_pregunta(accion){
    if (dijit.byId("frm_pregunta").validate()) {
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_pregunta&id_seccion='+ref_id_seccion,
            form: 'frm_pregunta',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    limpiar_pregunta();
                    tbl_preguntas();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function accion_alternativa(accion){
    if (dijit.byId("frm_alternativa").validate()) {
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_alternativa&id_pregunta='+dojo.byId("txt_id_pregunta").value,
            form: 'frm_alternativa',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    limpiar_alternativa();
                    tbl_alternativas(dojo.byId("txt_id_pregunta").value);
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}


function buscar_seccion(id_seccion){
     dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_seccion&id_seccion='+id_seccion,
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId("div_seccion").innerHTML=xhr.nombre;
        }
    });
}

function limpiar_pregunta(){
    dojo.byId("txt_id_pregunta").value = "-1";
    dojo.byId("txt_orden").value = "";
    dojo.byId("txt_pregunta").value = "";
    dijit.byId("rb_unica").setValue(true);

     mostrar_botones("R", "_p");
}

function limpiar_alternativa(){
    dojo.byId("txt_id_alternativa").value = "-1";
    dojo.byId("txt_orden_alt").value = "";
    dojo.byId("txt_alternativa").value = "";

     mostrar_botones("R", "_a");
     //tbl_alternativas(dojo.byId("txt_id_pregunta").value);
}

function modificar_pregunta(_id_pregunta, _nombre, _tipo_pregunta,_orden){
    ref_id_pregunta = _id_pregunta;
    dojo.byId("txt_id_pregunta").value = _id_pregunta;
    dojo.byId("txt_orden").value = _orden;
    dojo.byId("txt_pregunta").value = _nombre;

    if(_tipo_pregunta=="1") dijit.byId("rb_unica").setValue(true);
    if(_tipo_pregunta=="2") dijit.byId("rb_multiple").setValue(true);
    if(_tipo_pregunta=="3") dijit.byId("rb_libre").setValue(true);
    
    mostrar_botones("M","_p");
}

function modificar_alternativa(_id_alternativa, _nombre, _orden){
    dojo.byId("txt_id_alternativa").value = _id_alternativa;
    dojo.byId("txt_orden_alt").value = _orden;
    dojo.byId("txt_alternativa").value = _nombre;

    mostrar_botones("M","_a");
}


function cargar_alternativas(id_pregunta, txt_pregunta){
    dijit.byId('div_alternativa').show();
    dojo.byId("txt_id_pregunta").value=id_pregunta;
    dojo.byId("lbl_pregunta").innerHTML = txt_pregunta;
    limpiar_alternativa();
    tbl_alternativas(id_pregunta);
}

function tbl_preguntas() {
    mostrar_botones("R", "_p");
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = "&nbsp&nbsp<a href='javascript:modificar_pregunta(\"" + oRecord.getData("id_pregunta") + "\",\"" + oRecord.getData("nombre") + "\",\"" + oRecord.getData("tipo_pregunta.id_tipo_pregunta") + "\",\"" + oRecord.getData("orden") + "\");'\"><img alt=\"ver\" width=\"25px\" src=\"/websae/images/accessories-text-editor.png\" title=\"" + parseIdioma("modificar") + " " + oRecord.getData("nombre") + "\" /></a>";
    },
    formatPregunta = function(elCell, oRecord, oColumn, sData) {
        if (oRecord.getData("tipo_pregunta.id_tipo_pregunta")=="3")
            elCell.innerHTML = "<a href='javascript:alert(\"Esta pregunta es abierta. No puede tener alternativas\");'>"+oRecord.getData("nombre")+"</a>";
        else
            elCell.innerHTML = "<a href='javascript:cargar_alternativas("+ oRecord.getData("id_pregunta")+",\""+ oRecord.getData("nombre")+"\");'>"+oRecord.getData("nombre")+"</a>";
    },
    fields = ["id_pregunta","nombre","tipo_pregunta.id_tipo_pregunta","tipo_pregunta.nombre","orden"],
    myColumnDefs = [
        { label:parseIdioma("pregunta"), formatter:formatPregunta, width:240, className:"link_tabla"},
        { key:"orden", label:parseIdioma("orden")},
        { key:"tipo_pregunta.nombre", label:parseIdioma("tipo pregunta"), width:100},
        { label:parseIdioma("accion"),formatter:formatUrl}
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=preguntas&id_seccion="+ref_id_seccion,fields,myColumnDefs,"tbl_preguntas");               
}

function tbl_alternativas(id_pregunta) {
    mostrar_botones("R", "_a");
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = "&nbsp&nbsp<a href='javascript:modificar_alternativa(\"" + oRecord.getData("id_alternativa") + "\",\"" + oRecord.getData("nombre") + "\",\"" + oRecord.getData("orden") + "\");'\"><img alt=\"ver\" width=\"25px\" src=\"/websae/images/accessories-text-editor.png\" title=\"" + parseIdioma("modificar") + " " + oRecord.getData("nombre") + "\" /></a>";
    },
    fields = ["id_alternativa","nombre","orden"],
    myColumnDefs = [
        {label:parseIdioma("alternativa"), key:"nombre", width:240},
        {label:parseIdioma("orden"),key:"orden"},
        {label:parseIdioma("accion"),formatter:formatUrl}
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=alternativas&id_pregunta="+id_pregunta,fields,myColumnDefs,"tbl_alternativas");
}


function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
        obj.value=obj.value.substring(0,mlength);
}

function mostrar_botones(accion, ref){
    dijit.byId('btn_registrar'+ref).domNode.style.display = "none";
    dijit.byId('btn_modificar'+ref).domNode.style.display = "none";
    dijit.byId('btn_eliminar'+ref).domNode.style.display = "none";
    dijit.byId('btn_cancelar'+ref).domNode.style.display = "none";
    if (ref=="_a") dijit.byId('btn_cerrar'+ref).domNode.style.display = "none";

    if (accion=="R"){
        dijit.byId('btn_registrar'+ref).domNode.style.display = "block";
        if (ref=="_a") dijit.byId('btn_cerrar'+ref).domNode.style.display = "block";
    }else if (accion=="M"){
        dijit.byId('btn_modificar'+ref).domNode.style.display = "block";
        dijit.byId('btn_eliminar'+ref).domNode.style.display = "block";
        dijit.byId('btn_cancelar'+ref).domNode.style.display = "block";
    }else{
        if (ref=="_a") dijit.byId('btn_cerrar'+ref).domNode.style.display = "block";
    }
}

function parseVacio(campo){
    if (campo == null || campo == ""){
        return "";
    }else{
        return campo;
    }
}

function redireccionar_pregunta(id_seccion){
    window.location.href = "/websae/administracion/evento/opciones/convocatoria/evaluacion/pregunta/index.jsp?id_seccion="+id_seccion;
}
