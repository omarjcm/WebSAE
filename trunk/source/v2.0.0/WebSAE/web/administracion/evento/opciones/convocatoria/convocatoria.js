function inicializar(idioma){
    asignar_idioma(idioma);
    cargar_datos_convocatoria();
    //mostrar_tabla_temas(); 
    asignar_fecha_actual();
    asignar_info_evento();
    dojo.byId("txt_tema").value = "";
}

//accion = 'modificar';
function accion_convocatoria(accion){
    if (dijit.byId("frm_convocatoria").validate()) {
        var fecha_resumen = devuelve_fecha(dojo.byId("txt_fecha_resumen").value);
        var fecha_presentacion = devuelve_fecha(dojo.byId("txt_fecha_presentacion").value);
        var fecha_evaluacion = devuelve_fecha(dojo.byId("txt_fecha_evaluacion").value);
        var fecha_notificacion = devuelve_fecha(dojo.byId("txt_fecha_notificacion").value);
        var fecha_correccion = devuelve_fecha(dojo.byId("txt_fecha_correccion").value);
        var fecha_inicio_ev = devuelve_fecha(fecha_inicio);


        if (fecha_actual>fecha_resumen && fecha_resumen!=""){
            alert(parseIdioma("fecha actual resumen"));
        }else if (fecha_resumen>fecha_presentacion){
            alert(parseIdioma("fecha resumen presentacion"));
        }else if (fecha_presentacion>fecha_evaluacion){
            alert(parseIdioma("fecha presentacion evaluacion"));
        }else if (fecha_evaluacion>fecha_notificacion){
            alert(parseIdioma("fecha evaluacion notificacion"));
        }else if (fecha_notificacion>fecha_correccion){
            alert(parseIdioma("fecha notificacion correccion"));
        }else if (fecha_correccion>fecha_inicio_ev){
            alert(parseIdioma("fecha correccion evento"));
        }else{
            dojo.xhrPost({
                url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_convocatoria',
                form: 'frm_convocatoria',
                handleAs: 'json',
                load: function(xhr) {
                    if (xhr.tipo == "OK") {
                        alert(xhr.mensaje);
                        if (parseVacio(xhr.id_convocatoria)!=""){dojo.byId("txt_id_convocatoria").value = xhr.id_convocatoria;}
                        dijit.byId('div_opciones_convocatoria').domNode.style.display = "block";
                    } else if (xhr.tipo == "ERROR") {
                        alert(xhr.mensaje);
                    }
                }
            });
        }
    }
}

function cargar_datos_convocatoria(){
    dijit.byId('btn_nuevo_formato').domNode.style.display = "none";
    dijit.byId('btn_modificar_formato').domNode.style.display = "none";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_convocatoria',
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId("txt_id_convocatoria").value = parseVacio(xhr.id_convocatoria);
            dojo.byId("txt_fecha_resumen").value = parseVacio(xhr.fecha_max_resumen);
            dojo.byId("txt_fecha_presentacion").value = parseVacio(xhr.fecha_max_presentacion);
            dojo.byId("txt_fecha_evaluacion").value = parseVacio(xhr.fecha_max_evaluacion);
            dojo.byId("txt_fecha_notificacion").value = parseVacio(xhr.fecha_max_notificacion);
            dojo.byId("txt_fecha_correccion").value = parseVacio(xhr.fecha_max_correccion);
            dojo.byId("txt_descripcion_es").value = parseVacio(xhr.descripcion_es);
            dojo.byId("txt_descripcion_en").value = parseVacio(xhr.descripcion_en);
            dojo.byId("txt_descripcion_pt").value = parseVacio(xhr.descripcion_pt);
            dojo.byId("txt_ruta_formato2").value = parseVacio(xhr.ruta_formato);
            if (parseVacio(xhr.fecha_max_presentacion)!=""){
               dijit.byId('div_opciones_convocatoria').domNode.style.display = "block";
            }else{
                dijit.byId('div_opciones_convocatoria').domNode.style.display = "none";
            }
            if (parseVacio(xhr.ruta_formato)!=""){
                dojo.byId('ref_ver').innerHTML = "<a title='Clic Derecho, Guardar Enlace Como...' href='/websae/archivo/formato/"+xhr.ruta_formato+"' target='_blank'>["+ parseIdioma("ver") +"]</a>";
                dijit.byId('btn_modificar_formato').domNode.style.display = "block";
            }else{
                dijit.byId('btn_nuevo_formato').domNode.style.display = "block";
            }
        }
    });
}

function accion_tema(accion){
    if (dijit.byId("frm_tema").validate()) {
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_tema&txt_id_convocatoria='+dojo.byId("txt_id_convocatoria").value,
            form: 'frm_tema',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    popup_temas();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function modificar_tema(_id_tema, _tema){
    dojo.byId("txt_id_tema").value = _id_tema;
    dojo.byId("txt_tema").value = _tema;
    mostrar_botones("M","_t");
}

function tbl_temas() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("modificar_tema('" + oRecord.getData("id_tema") + "','" + oRecord.getData("nombre").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_tema","nombre"],
    myColumnDefs = [
    { key:"nombre", label:parseIdioma("tema"), width:200},
    { label:parseIdioma("accion"), formatter:formatUrl}];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=temas&id_convocatoria=" + dojo.byId("txt_id_convocatoria").value,fields,myColumnDefs,"tbl_temas");
}

function popup_temas(){
    dijit.byId('div_tema').show();
    dojo.byId("txt_tema").value = "";
    tbl_temas();
    mostrar_botones('R','_t');
}

function redireccionar_evaluacion(){
    window.location.href = "/websae/administracion/evento/opciones/convocatoria/evaluacion/index.jsp";
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
        obj.value=obj.value.substring(0,mlength);
}

function devuelve_fecha(fecha){
    if (fecha=="") return "";
    fecha = fecha.replace(/[-]/g, "/");
    fecha = new Date(fecha);
    return fecha;
}

var fecha_inicio = "";
var fecha_fin = "";

function asignar_info_evento(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_evento',
        handleAs: 'json',
        load: function(xhr) {
            fecha_inicio = xhr.fecha_inicio;
            fecha_fin = xhr.fecha_fin;
            dojo.byId('rango_fechas').innerHTML = xhr.fecha_inicio + ' -:- ' + xhr.fecha_fin;
        }
    });
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


/* Funciones para el manejo del AutoIdioma sugerido mediante google*/
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
}


function parseVacio(campo){
    if (campo == null || campo == ""){
        return "";
    }else{
        return campo;
    }
}

function subir_formato(){
    ajaxFileUpload("archivo","formato","txt_ruta_formato");
}

/*
function ajaxFileUpload(tipo, directorio){
    $.ajaxFileUpload({
        url:'/websae/F_adjuntar_archivo?tipo='+tipo+'&directorio='+directorio,
        secureuri:false,
        fileElementId:'txt_ruta_formato',
        dataType: 'json',
        success: function (data, status){ }
    });
    dojo.byId("txt_ruta_formato2").value=dojo.byId("txt_ruta_formato").value;
    alert("Formato Subido Con Éxito.");

    dojo.byId('ref_ver').innerHTML = "<a href='/websae/archivo/formato/"+dojo.byId("txt_ruta_formato2").value+"' target='_blank'>["+ parseIdioma("ver") +"]</a>";
    dijit.byId('btn_nuevo_formato').domNode.style.display = "none";
    dijit.byId('btn_modificar_formato').domNode.style.display = "block";

    return false;
}*/

var fecha_actual="";

function asignar_fecha_actual(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_fecha_actual',
        handleAs: 'json',
        load: function(xhr) {
            fecha_actual = devuelve_fecha(xhr.fecha_actual);
        }
    });
}

dojo.declare(
    "ValidationTextarea",
    [dijit.form.ValidationTextBox,dijit.form.SimpleTextarea],
    {
        invalidMessage: parseIdioma("campo obligatorio"),

        postCreate: function() {
            this.inherited(arguments);
        },

        validate: function() {
            this.inherited(arguments);
            if (arguments.length==0){
                this.validate(true);
            }else{
                this.validate(false);
            }
        },

        onFocus: function() {
            if (!this.isValid()) {
                this.displayMessage(this.getErrorMessage());
            }
        },

        onBlur: function() {
            this.validate(false);
        }
     }
);