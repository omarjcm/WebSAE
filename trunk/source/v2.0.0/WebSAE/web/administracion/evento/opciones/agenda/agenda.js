 var id_fecha_evento="";

 function inicializar(idioma){
    asignar_idioma(idioma);
    mostrar_tabla_agenda();
    asignar_fecha_actual();
    asignar_info_evento();
    cargar_expositores();
}

//accion = 'registrar' || 'modificar' || 'eliminar';
function accion_agenda(accion){
    if (dijit.byId("frm_agenda").validate()) {
        fecha_ingresar = dojo.byId('txt_fecha').value;
        if (devuelve_fecha(fecha_ingresar)< devuelve_fecha(fecha_inicio) ||devuelve_fecha(fecha_ingresar)> devuelve_fecha(fecha_fin) ){
            alert(parseIdioma('rango agenda'));
        }else{
            dojo.xhrPost({
                url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_agenda',
                form: 'frm_agenda',
                handleAs: 'json',
                load: function(xhr) {
                    if (xhr.tipo == "OK") {
                        alert(xhr.mensaje);
                        dijit.byId('div_agenda').hide();
                        mostrar_tabla_agenda();
                    } else if (xhr.tipo == "ERROR") {
                        alert(xhr.mensaje);
                    }
                }
            });
        }
    }
}

function popup_registrar_agenda(){
    dijit.byId('div_agenda').show();
    dijit.byId('frm_agenda').reset();
    dojo.byId('txt_id_fecha_evento').value="1";
    dojo.byId('txt_fecha').value=fecha_inicio;

    mostrar_botones("R","");

    dojo.byId('rango_fechas').innerHTML = parseIdioma('fecha evento') + ': ' + fecha_inicio + ' -:- ' + fecha_fin;
}

function popup_modificar_agenda(id_fecha, fecha){
    dijit.byId('div_agenda').show();

    dojo.byId('txt_id_fecha_evento').value=id_fecha;
    dojo.byId('txt_fecha').value=fecha;

    mostrar_botones("M","");

    dojo.byId('rango_fechas').innerHTML = parseIdioma('fecha evento') + ': ' + fecha_inicio + ' -:- ' + fecha_fin;
}

function mostrar_tabla_agenda() {
    YAHOO.util.Event.onDOMReady(function() {
        YAHOO.example.ClientPagination = function() {
            var formatFecha = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = "<a href='#' onclick=\"popup_horario('" + oRecord.getData("id_fecha_evento") + "','" + oRecord.getData("fecha_long") + "');\">" + oRecord.getData("fecha_long") + "</a>";
            };
            var formatUrl = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = "&nbsp&nbsp<a href='#' onclick=\"popup_modificar_agenda('" + oRecord.getData("id_fecha_evento") + "','" + oRecord.getData("fecha") + "');\"><img alt=\"ver\" width=\"25px\" src=\"/websae/images/accessories-text-editor.png\" title=\"" + parseIdioma("modificar") + "\" /></a>";
            };
            var myColumnDefs = [
                {label:parseIdioma("dia"),formatter:formatFecha,key:"fecha_long"},
                {label:parseIdioma("accion"),formatter:formatUrl}
            ];

            var myDataSource = new YAHOO.util.DataSource("/websae/administracion/F_ae_mostrar_datos");
            myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
            myDataSource.connMethodPost = true;
            myDataSource.responseSchema = {
                resultsList: "items",
                fields: ["id_fecha_evento","fecha","fecha_long"]
            };

            var oConfigs = {
                paginator: new YAHOO.widget.Paginator({
                    rowsPerPage: 5
                }),
                initialRequest: "tipo=mostrar_agenda"
            };
            var myDataTable = new YAHOO.widget.DataTable("paginated", myColumnDefs, myDataSource, oConfigs);
            myDataTable.subscribe("rowClickEvent", myDataTable.onEventSelectRow);
            myDataTable.subscribe("cellDblclickEvent", myDataTable.onEventShowCellEditor);
            myDataTable.subscribe("editorBlurEvent", myDataTable.onEventSaveCellEditor);

            var onCellEdit = function(oArgs) {
                var elCell = oArgs.editor.getTdEl();
                var oOldData = oArgs.oldData;
                var oNewData = oArgs.newData;

                // Grab the row el and the 2 colors
                var elRow = this.getTrEl(elCell);
                var origColor = YAHOO.util.Dom.getStyle(elRow.cells[0], "backgroundColor");
                var pulseColor = "#ff0";

                // Create a temp anim instance that nulls out when anim is complete
                var rowColorAnim = new YAHOO.util.ColorAnim(elRow.cells, {
                    backgroundColor:{
                        to:origColor,
                        from:pulseColor
                    },
                    duration:2
                });
                var onComplete = function() {
                    rowColorAnim = null;
                    YAHOO.util.Dom.setStyle(elRow.cells, "backgroundColor", "");
                }
                rowColorAnim.onComplete.subscribe(onComplete);
                rowColorAnim.animate();
            }
            myDataTable.subscribe("editorSaveEvent", onCellEdit);

            return {
                oDS: myDataSource,
                oDT: myDataTable
            };
        }();
    });
}


function accion_horario(accion){
    if (dijit.byId("frm_horario").validate()) {
        if(CompararHoras(dojo.byId("txt_hora_inicio2").value,dojo.byId("txt_hora_fin2").value)){
            dojo.byId('txt_hora_inicio').value = dojo.byId("txt_hora_inicio2").value;
            dojo.byId('txt_hora_fin').value = dojo.byId("txt_hora_fin2").value
            dojo.xhrPost({
                url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_horario',
                form: 'frm_horario',
                handleAs: 'json',
                load: function(xhr) {
                    if (xhr.tipo == "OK") {
                        alert(xhr.mensaje);
                        limpiar_horario();
                        mostrar_tabla_horario();
                    } else if (xhr.tipo == "ERROR") {
                        alert(xhr.mensaje);
                    }
                }
            });
        }
    }else{
        alert("La Hora Final debe ser mayor a la Hora de Inicio.");
    }
}

function limpiar_horario(){
    dojo.byId('txt_id_actividad').value = "1";
    dojo.byId('txt_hora_inicio2').value= "09:45:00";
    dojo.byId('txt_hora_fin2').value="10:45:00";
    dojo.byId('txt_actividad').value = "";
    dojo.byId('txt_expositor').value="";
    mostrar_botones("R","_h");
    dijit.byId('btn_cancelar_h').domNode.style.display = "block";
    dijit.byId('btn_cancelar_h2').domNode.style.display = "none";
}

function popup_horario(id_fecha, fecha_long){
    dijit.byId('div_horario').show();
    mostrar_botones("R","_h")
    dijit.byId('btn_cancelar_h').domNode.style.display = "block";
    dijit.byId('btn_cancelar_h2').domNode.style.display = "none";

    dojo.byId('lbl_fecha').innerHTML = fecha_long.toUpperCase();
    dojo.byId('txt_id_fecha_evento_h').value = id_fecha;
    id_fecha_evento=id_fecha;
    mostrar_tabla_horario();
}

function popup_modificar_horario(id_actividad, fecha_inicio, fecha_fin, actividad, expositor){
    dijit.byId('div_horario').show();
    mostrar_botones("M","_h")
    dijit.byId('btn_cancelar_h').domNode.style.display = "none";
    dijit.byId('btn_cancelar_h2').domNode.style.display = "block";

    dojo.byId('txt_id_actividad').value = id_actividad;
    dojo.byId('txt_hora_inicio2').value = fecha_inicio;
    dojo.byId('txt_hora_fin2').value = fecha_fin;
    dojo.byId('txt_actividad').value = actividad;
    dojo.byId('txt_expositor').value = expositor;
}

function mostrar_tabla_horario() {
    YAHOO.util.Event.onDOMReady(function() {
        YAHOO.example.ClientPagination = function() {
            var formatUrl = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = "&nbsp&nbsp<a href='#' onclick=\"popup_modificar_horario('" + oRecord.getData("id_actividad") + "','" + oRecord.getData("hora_inicio") + "','" + oRecord.getData("hora_fin") + "','" + oRecord.getData("actividad") + "','" + oRecord.getData("expositor") + "');\"><img alt=\"ver\" width=\"25px\" src=\"/websae/images/accessories-text-editor.png\" title=\"" + parseIdioma("modificar") + "\" /></a>";
            };
            var myColumnDefs = [
                {label:parseIdioma("hora inicio"),key:"hora_inicio"},
                {label:parseIdioma("hora fin"),key:"hora_fin"},
                {label:parseIdioma("actividad"),key:"actividad"},
                {label:parseIdioma("expositor"),key:"expositor"},
                {label:parseIdioma("accion"),formatter:formatUrl}
            ];

            var myDataSource = new YAHOO.util.DataSource("/websae/administracion/F_ae_mostrar_datos");
            myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
            myDataSource.connMethodPost = true;
            myDataSource.responseSchema = {
                resultsList: "items",
                fields: ["id_actividad","hora_inicio","hora_fin","actividad","expositor"]
            };

            var oConfigs = {
                paginator: new YAHOO.widget.Paginator({
                    rowsPerPage: 5
                }),
                initialRequest: "tipo=mostrar_actividad&id_fecha_evento="+id_fecha_evento
            };
            var myDataTable = new YAHOO.widget.DataTable("paginated2", myColumnDefs, myDataSource, oConfigs);
            myDataTable.subscribe("rowClickEvent", myDataTable.onEventSelectRow);
            myDataTable.subscribe("cellDblclickEvent", myDataTable.onEventShowCellEditor);
            myDataTable.subscribe("editorBlurEvent", myDataTable.onEventSaveCellEditor);

            var onCellEdit = function(oArgs) {
                var elCell = oArgs.editor.getTdEl();
                var oOldData = oArgs.oldData;
                var oNewData = oArgs.newData;

                // Grab the row el and the 2 colors
                var elRow = this.getTrEl(elCell);
                var origColor = YAHOO.util.Dom.getStyle(elRow.cells[0], "backgroundColor");
                var pulseColor = "#ff0";

                // Create a temp anim instance that nulls out when anim is complete
                var rowColorAnim = new YAHOO.util.ColorAnim(elRow.cells, {
                    backgroundColor:{
                        to:origColor,
                        from:pulseColor
                    },
                    duration:2
                });
                var onComplete = function() {
                    rowColorAnim = null;
                    YAHOO.util.Dom.setStyle(elRow.cells, "backgroundColor", "");
                }
                rowColorAnim.onComplete.subscribe(onComplete);
                rowColorAnim.animate();
            }
            myDataTable.subscribe("editorSaveEvent", onCellEdit);

            return {
                oDS: myDataSource,
                oDT: myDataTable
            };
        }();
    });
}

function cargar_combobox(id_elemento, url_objeto, valor) {
    var url_ = url_objeto;

    var datos = new dojo.data.ItemFileReadStore({url: url_});
    var elemento = dijit.byId(id_elemento);
    elemento.setDisplayedValue(valor);
    elemento.store = datos;
}

function cargar_expositores() {
    dojo.xhrGet({
		url: "/websae/administracion/F_ae_mostrar_datos?tipo=conferencistas_evento",
		handleAs: "json",
		load: function(xhr) {
            var cola = "";
            var temp;
            cola = "<select name=\"txt_expositor\" id=\"txt_expositor\" dojoType=\"dijit.form.ComboBox\" autocomplete=\"true\" value=\" \" >";
            //for (i in xhr.usuario){
            for (var i=0; i<xhr.items.length; i++) {
                tmp = "<option value=" + xhr.items[i].usuario.id_usuario + ">" + xhr.items[i].usuario.titulo.abreviatura + " " + xhr.items[i].usuario.nombre + " " + xhr.items[i].usuario.apellido + "</option>";
                cola = cola + tmp;
                tmp = "";
            }
            cola = cola + "</select>";
            dojo.byId("espacio_expositores").innerHTML = cola;
            dojo.parser.parse(dojo.byId("espacio_expositores"));
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
        dijit.byId('btn_cancelar'+ref).domNode.style.display = "block";
    }else if (accion=="M"){
        dijit.byId('btn_modificar'+ref).domNode.style.display = "block";
        dijit.byId('btn_eliminar'+ref).domNode.style.display = "block";
        dijit.byId('btn_cancelar'+ref).domNode.style.display = "block";
    }else{
        dijit.byId('btn_cerrar'+ref).domNode.style.display = "block";
    }
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
        obj.value=obj.value.substring(0,mlength)
}

function devuelve_fecha(fecha){
    if (fecha=="") return "";
    fecha = fecha.replace(/[-]/g, "/");
    fecha = new Date(fecha);
    return fecha
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

var fecha_actual = "";
var fecha_inicio = "";
var fecha_fin = "";

function asignar_info_evento(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_evento',
        handleAs: 'json',
        load: function(xhr) {
            fecha_inicio = xhr.fecha_inicio;
            fecha_fin = xhr.fecha_fin;
        }
    });   
}

function CompararHoras(sHora1, sHora2) {
    var arHora1 = sHora1.split(":");
    var arHora2 = sHora2.split(":");

    // Obtener horas y minutos (hora 1)
    var hh1 = parseInt(arHora1[0],10)*60;
    var mm1 = parseInt(arHora1[1],10);

    // Obtener horas y minutos (hora 2)
    var hh2 = parseInt(arHora2[0],10)*60;
    var mm2 = parseInt(arHora2[1],10);

    // Comparar
    if ((hh1+mm1)<(hh2+mm2)){
    //    alert("mayor");
        return true;
    }else{
    //    alert("menor")
        return false;
    }
    return false;
}