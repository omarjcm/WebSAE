var fecha_actual="";
var fecha_max_resumen="";
var fecha_max_presentacion="";
var fecha_max_evaluacion="";
var fecha_max_notificacion="";
var fecha_max_correccion="";

function administrar_asignar_evaluador(accion,id_usuario){
    if (accion=="eliminar") dijit.byId("evaluador").setValue(id_usuario);
    if(dijit.byId("frm_asignar_evaluador").validate()){
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_asignar_evaluador',
            handleAs: 'json',
            form:'frm_asignar_evaluador',
            load: function(xhr) {
                alert(xhr.mensaje);
                tbl_evaluadores();
                /*if (xhr.tipo=="OK"){
                   alert(xhr.mensaje);
                    cargar_evaluadores();
                else{
                    alert(xhr.mensaje);
                }*/
            }
        });
    }
}

function tbl_evaluadores(){
    var formatNombre = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = oRecord.getData("usuario.titulo.abreviatura") + " " + oRecord.getData("usuario.nombre") + " " + oRecord.getData("usuario.apellido");
    },
    formatAccion = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("administrar_asignar_evaluador('eliminar','"+oRecord.getData("usuario.id_usuario")+"');","/websae/images/unsupported.png");
    },
    fields = ["usuario.id_usuario","usuario.titulo.abreviatura","usuario.nombre","usuario.apellido"],
    myColumnDefs = [
        { label:parseIdioma("evaluador"), formatter:formatNombre, width:230},
        { label:parseIdioma("accion"), formatter:formatAccion}
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=evaluadores_articulo&id_articulo="+dojo.byId("id_articulo").value,fields,myColumnDefs,"tbl_evaluadores");
}


function popup_asignar_evaluador(id, titulo){
    if (estado_evaluacion=="P"){
        alert("No se puede asignar evaluadores. No existe una Evaluación, o está Inactiva.")
    }else{
        //if(fecha_actual<devuelve_fecha(fecha_max_presentacion)){
        //    alert("No puede asignar evaluadores hasta después de la Fecha Máxima de Entrega de Artículo ["+fecha_max_presentacion+"].");
        //}else if(fecha_actual>=devuelve_fecha(fecha_max_evaluacion) && fecha_actual<=devuelve_fecha(fecha_max_notificacion)){
        //    alert("Solo puede asignar evaluadores entre las Fechas Máxima de Presentacion y Fecha Máxima de Evaluación ["+fecha_max_presentacion+" - "+fecha_max_evaluacion+"].");
        //}else
            if(fecha_actual>devuelve_fecha(fecha_max_notificacion)){
            alert("Ya no se pueden asignar evaluadores. Fecha máxima hasta " + fecha_max_notificacion);
        }else{
            dijit.byId("div_asignar_evaluador").show();
            dojo.byId("titulo_articulo").innerHTML = titulo;
            dojo.byId("id_articulo").value = id;
            tbl_evaluadores();
        }
    }
}

function cargar_evaluadores(){
    var html="";
    dojo.xhrPost({
        url: '/websae/administracion/F_ce_mostrar_datos?tipo=evaluadores_evento&id_articulo='+dojo.byId("id_articulo").value,
        handleAs: 'json',
        load: function(xhr) {
            if (parseVacio(xhr)!==""){
                html += "<select id='evaluador' name='evaluador' dojoType='dijit.form.FilteringSelect'";
                html += "style='float:left; width:180px; margin:-2px 0 8px 3px;'";
                html += "required='true' invalidMessage='<fmt:message key='ge.solo_valores_lista' />'";
                html += "promptMessage='<fmt:message key='ge.necesita_valor' />' >";
                for(var i=0;i<xhr.items.length;i++){
                    html += "<option value='" + xhr.items[i].usuario.id_usuario + "'>" + xhr.items[i].usuario.titulo.abreviatura + " " + xhr.items[i].usuario.nombre + " " + xhr.items[i].usuario.apellido + "</option>";
                }
            }else{
                html += "<select id='evaluador' name='evaluador' dojoType='dijit.form.FilteringSelect'";
                html += "style='float:left; width:180px; margin:-2px 0 8px 3px;'";
                html += "required='true' invalidMessage='<fmt:message key='ge.solo_valores_lista' />'";
                html += "promptMessage='<fmt:message key='ge.necesita_valor' />' >";
                html += "<option value=''></option>";
            }
            dojo.byId("div_evaluadores").innerHTML = html + "</select>";
                
            //dijit.byId("evaluador").setValue("");
            //tbl_evaluadores();
            dojo.parser.parse(dojo.byId("div_evaluadores"));
        }
    });
}

var estado_evaluacion="";
function obtener_estado_evaluacion(id_evento){
    dojo.xhrPost({
        url: '/websae/administracion/F_ce_mostrar_datos?tipo=buscar_evaluacion_evento&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            if (parseVacio(xhr)!=""){
                estado_evaluacion=xhr.estado;
            }else
                estado_evaluacion="P";
        }
    });
}

function obtener_fechas_convocatoria(){
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_convocatoria',
        handleAs: 'json',
        load: function(xhr) {
            fecha_max_resumen=xhr.fecha_max_resumen;
            fecha_max_presentacion=xhr.fecha_max_presentacion;
            fecha_max_evaluacion=xhr.fecha_max_evaluacion;
            fecha_max_notificacion=xhr.fecha_max_notificacion;
            fecha_max_correccion=xhr.fecha_max_correccion;
        }
    });
}

function asignar_fecha_actual(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_fecha_actual',
        handleAs: 'json',
        load: function(xhr) {
            fecha_actual = devuelve_fecha(xhr.fecha_actual);
        }
    });
}

function devuelve_fecha(fecha){
    if (fecha=="") return "";
    fecha = fecha.replace(/[-]/g, "/");
    fecha = new Date(fecha);
    return fecha
}