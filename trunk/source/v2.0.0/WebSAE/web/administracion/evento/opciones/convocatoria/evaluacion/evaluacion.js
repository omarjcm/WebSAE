function inicializar(idioma){
    asignar_idioma(idioma);
    cargar_datos_evaluacion();
}

//accion = 'modificar';
function accion_evaluacion(accion){
    if (dijit.byId("frm_evaluacion").validate()) {
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_evaluacion',
            form: 'frm_evaluacion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    window.location.reload();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function accion_seccion(accion){
    if (dijit.byId("frm_seccion").validate()) {
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_seccion&id_evaluacion='+dojo.byId("txt_id_evaluacion").value,
            form: 'frm_seccion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    tbl_secciones();
                    dijit.byId('div_seccion').hide();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function cargar_datos_evaluacion(){
    dijit.byId('btn_registrar').domNode.style.display = "block";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    dojo.byId("txt_id_evaluacion").value="-1";
    dojo.byId("txt_mensaje").value="";
    dojo.byId("txt_instruccion").value="";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_evaluacion',
        handleAs: 'json',
        load: function(xhr) {
            if (parseVacio(xhr.estado)!=""){
                dojo.byId("txt_id_evaluacion").value=xhr.id_evaluacion;
                dojo.byId("txt_mensaje").value=xhr.mensaje;
                dojo.byId("txt_instruccion").value=xhr.descripcion;
                if(xhr.estado=='V'){
                    dijit.byId("rb_vigente").setValue(true);
                }else {
                    dijit.byId("rb_pendiente").setValue(true);
                }

                dijit.byId('btn_registrar').domNode.style.display = "none";
                dijit.byId('btn_modificar').domNode.style.display = "block";
                dijit.byId('div_opciones_evaluacion').domNode.style.display = "block";
                tbl_secciones();
            }else{
                dojo.byId('formato_evaluacion').innerHTML = "";
                dijit.byId('div_opciones_evaluacion').domNode.style.display = "none";
            }
        }
    });
}

function popup_secciones(){
    dijit.byId('div_seccion').show();
    dojo.byId("txt_id_seccion").value = "-1";
    dojo.byId("txt_orden").value = "";
    dojo.byId("txt_titulo").value = "";
    dojo.byId("txt_descripcion").value = "";
     dijit.byId("rb_pendiente_o").setValue(true);
    mostrar_botones('R','_s');
    tbl_secciones();
}

function modificar_seccion(_id_seccion, _nombre, _descripcion, _oculta, _orden){
    popup_secciones();
    dojo.byId("txt_id_seccion").value = _id_seccion;
    dojo.byId("txt_orden").value = _orden;
    dojo.byId("txt_titulo").value = _nombre;
    dojo.byId("txt_descripcion").value = _descripcion;

    if(_oculta=="true"){
        dijit.byId("rb_vigente_o").setValue(true);
    }else {
        dijit.byId("rb_pendiente_o").setValue(true);
    }    
    mostrar_botones("M","_s");
}

function tbl_secciones() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("modificar_seccion('" + oRecord.getData("id_seccion") + "','" + oRecord.getData("nombre") + "','" + oRecord.getData("descripcion") + "','" + oRecord.getData("oculta") + "','" + oRecord.getData("orden") + "');","/websae/images/accessories-text-editor.png");
    },
    formatOculto = function(elCell, oRecord, oColumn, sData) {
        if (oRecord.getData("oculta") == true)
            elCell.innerHTML = parseIdioma("si");
        else
            elCell.innerHTML = parseIdioma("no");
    },
    formatSeccion = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("redireccionar_pregunta('"+ oRecord.getData("id_seccion")+"');",oRecord.getData("nombre"));
    },
    fields = ["id_seccion","nombre","descripcion","oculta","orden"],
    myColumnDefs = [
        {label:parseIdioma("seccion"), formatter:formatSeccion, width:200, className:"link_tabla"},
        {label:parseIdioma("orden"),key:"orden"},
        {label:parseIdioma("oculto"), formatter:formatOculto},
        {label:parseIdioma("accion"),formatter:formatUrl}
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=secciones&id_evaluacion="+dojo.byId("txt_id_evaluacion").value,fields,myColumnDefs,"tbl_secciones");
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
    dijit.byId('btn_cerrar'+ref).domNode.style.display = "none";

    if (accion=="R"){
        dijit.byId('btn_registrar'+ref).domNode.style.display = "block";
        dijit.byId('btn_cerrar'+ref).domNode.style.display = "block";
    }else if (accion=="M"){
        dijit.byId('btn_modificar'+ref).domNode.style.display = "block";
        dijit.byId('btn_eliminar'+ref).domNode.style.display = "block";
        dijit.byId('btn_cancelar'+ref).domNode.style.display = "block";
    }else{
        dijit.byId('btn_cerrar'+ref).domNode.style.display = "block";
    }
}

/* Funciones para el manejo del AutoIdioma sugerido mediante google
google.load("language", "1");

function traducir(opcTraducir,texto){
    var traducido = document.getElementById(texto);
    google.language.translate(traducido.innerHTML, "es", opcTraducir,
        function(result) {
            if (result.translation) {
                traducido.removeChild(traducido.firstChild);
                traducido.innerHTML = result.translation;
            }
        });
}

function traduccion(opcOrg,opcTraducir,texto,donde){
    var traducido = document.getElementById(texto);
    var donde2=dojo.byId(donde);
    google.language.translate(traducido.value, opcOrg, opcTraducir,
        function(result) {
            if (result.translation) {
                donde2.value=result.translation;
            }else{
                alert(parseIdioma("error traduccion"));
            }
        });
}*/


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