 function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_eventos();
    asignar_fecha_actual();
}

//accion = 'registrar' || 'modificar'; 
function accion_evento(accion){
    if (accion=="registrar") dojo.byId("txt_id_evento").value = "-1";
    // si de modifica a estado eliminado NO importa las fechas!!!
    var fecha_inicio = devuelve_fecha(dojo.byId("txt_fecha_inicio").value);
    var fecha_fin = devuelve_fecha(dojo.byId("txt_fecha_fin").value);
    if (dijit.byId("frm_evento").validate()) {
        if (dijit.byId("rb_vigente").getValue()=="V"){
            if((fecha_inicio=="" && fecha_fin != "") || (fecha_inicio!="" && fecha_fin == "") ){
                alert(parseIdioma("intervalo fecha")); //"El intervalo de Fecha está incompleto."
            }else if (fecha_inicio>fecha_fin ){
                alert(parseIdioma("fecha inicial final"));//La Fecha inicial no puede ser mayor a la Final.")
            }else if (fecha_inicio<fecha_actual ){  // && !dijit.byId("rb_realizado").getValue()){
                alert(parseIdioma("fecha inicial actual"));//La Fecha inicial no puede ser menor a la Fecha Actual.")
            }else{
                dojo.xhrPost({
                    url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_evento',
                    form: 'frm_evento',
                    handleAs: 'json',
                    load: function(xhr) {
                        if (xhr.tipo == "OK") {
                            alert(xhr.mensaje);
                            dijit.byId('div_evento').hide();
                            tbl_eventos();
                        } else if (xhr.tipo == "ERROR") {
                            alert(xhr.mensaje);
                        }
                    }
                });
            }
        }else{
            if (dijit.byId("rb_realizado").getValue()=="R" && fecha_fin>fecha_actual){
                alert(parseIdioma("fecha fin actual"));
            }else{
                dojo.xhrPost({
                    url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_evento',
                    form: 'frm_evento',
                    handleAs: 'json',
                    load: function(xhr) {
                        if (xhr.tipo == "OK") {
                            alert(xhr.mensaje);
                            dijit.byId('div_evento').hide();
                            tbl_eventos();
                        } else if (xhr.tipo == "ERROR") {
                            alert(xhr.mensaje);
                        }
                    }
                });
            }
        }
    }
}

function popup_registrar_evento(){
    dijit.byId('div_evento').show();
    dijit.byId('tabs').selectChild(dijit.byId('tab_info1'));
    dijit.byId('frm_evento').reset();
    dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/evento/default.jpg\" height=\"40px\" alt=\"\" />";

    dojo.byId('txt_imagen').value = "";
    dojo.byId('txt_imagen2').value = "default.jpg";
    dojo.byId("txt_lugar").value = "";
    dijit.byId("cmb_ciudad").setValue("32669");
    
    //dijit.byId("rb_pendiente").setValue(true);
    
    dijit.byId('btn_registrar').domNode.style.display = "block";
    dijit.byId('btn_cancelar').domNode.style.display = "block";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    dijit.byId('btn_cerrar').domNode.style.display = "none";
    dijit.byId('div_status').domNode.style.display = "none";
}

function popup_modificar_evento(id, fecha_inicio, fecha_fin, estado){
    dijit.byId('div_evento').show();

    dijit.byId('div_status').domNode.style.display = "block";
    dijit.byId('btn_registrar').domNode.style.display = "none";
    dijit.byId('btn_cerrar').domNode.style.display = "none";
    dijit.byId('btn_modificar').domNode.style.display = "block";
    dijit.byId('btn_cancelar').domNode.style.display = "block";
    
    fecha_inicio = devuelve_fecha(fecha_inicio);
    fecha_fin = devuelve_fecha(fecha_fin);

    dojo.byId('txt_id_evento').value=id;

    if( estado=='V'){
        dijit.byId("rb_vigente").setValue(true);
        //if(fecha_inicio<=fecha_actual) btns_ver();
    }else if( estado=='P'){
       dijit.byId("rb_pendiente").setValue(true);
       //if(fecha_fin<=fecha_actual) btns_ver();
    }else if( estado=='R'){
        if(fecha_fin<=fecha_actual) btns_ver();
        //btns_ver();
    }else{
        dijit.byId("rb_eliminado").setValue(true);
        if(fecha_fin<=fecha_actual) btns_ver();
    }
    cargar_datos_evento(id);
}

function btns_modificar(){
    dijit.byId('div_status').domNode.style.display = "block";
    dijit.byId('btn_modificar').domNode.style.display = "block";
    dijit.byId('btn_cancelar').domNode.style.display = "block";
    dijit.byId('btn_cerrar').domNode.style.display = "none";
}

function btns_ver(){
    dijit.byId('div_status').domNode.style.display = "none";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    dijit.byId('btn_cancelar').domNode.style.display = "none";
    dijit.byId('btn_cerrar').domNode.style.display = "block";
}

function cargar_datos_evento(id_evento){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=buscar_evento&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            dijit.byId('tabs').selectChild(dijit.byId('tab_info1'));
            dijit.byId('cmb_tipo_evento').setValue(xhr.tipo_evento.id_tipo_evento);
            dojo.byId('txt_evento_es').value = xhr.nombre_es;
            dojo.byId('txt_evento_en').value = xhr.nombre_en;
            dojo.byId('txt_evento_pt').value = xhr.nombre_pt;
            dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/evento/" + xhr.imagen + "\" height=\"40px\" alt=\"\" />"
            dojo.byId('txt_imagen2').value = xhr.imagen;
            dojo.byId('txt_fecha_inicio').value = parse_vacio(xhr.fecha_inicio);
            dojo.byId('txt_fecha_fin').value = parse_vacio(xhr.fecha_fin);
            dojo.byId('txt_slogan_es').value = parse_vacio(xhr.slogan_es);
            dojo.byId('txt_slogan_en').value = parse_vacio(xhr.slogan_en);
            dojo.byId('txt_slogan_pt').value = parse_vacio(xhr.slogan_pt);
            dijit.byId('cmb_pais').setValue(xhr.ciudad.pais.id_pais);
            dijit.byId('cmb_ciudad').setValue(xhr.ciudad.id_ciudad);
            dojo.byId('txt_lugar').value = parse_vacio(xhr.lugar);
            dojo.byId('txt_objetivo_es').value = parse_vacio(xhr.objetivo_es);
            dojo.byId('txt_objetivo_en').value = parse_vacio(xhr.objetivo_en);
            dojo.byId('txt_objetivo_pt').value = parse_vacio(xhr.objetivo_pt);
            dojo.byId('txt_descripcion_es').value = parse_vacio(xhr.descripcion_es);
            dojo.byId('txt_descripcion_en').value = parse_vacio(xhr.descripcion_en);
            dojo.byId('txt_descripcion_pt').value = parse_vacio(xhr.descripcion_pt);
            dojo.byId('txt_dirigido_es').value = parse_vacio(xhr.dirigido_es);
            dojo.byId('txt_dirigido_en').value = parse_vacio(xhr.dirigido_en);
            dojo.byId('txt_dirigido_pt').value = parse_vacio(xhr.dirigido_pt);
            dojo.byId('txt_descripcion_registro_es').value = parse_vacio(xhr.descripcion_registro_es);
            dojo.byId('txt_descripcion_registro_en').value = parse_vacio(xhr.descripcion_registro_en);
            dojo.byId('txt_descripcion_registro_pt').value = parse_vacio(xhr.descripcion_registro_pt);
            dojo.byId('txt_email').value = parse_vacio(xhr.email);
            dojo.byId('txt_agenda_general').value = parse_vacio(xhr.agenda_general);
            
            var estado_registro = xhr.estado_registro;
            if( estado_registro=='V'){
               dijit.byId("rb_vigente_reg").setValue(true);
            }else {
               dijit.byId("rb_pendiente_reg").setValue(true);
            }
        }
    });
}

function parse_vacio(campo){
    if (campo == null || campo == "null" ||campo == "" || campo == undefined || campo=="undefined" || campo.length == 0) return "";
    return campo;
}

function parseVacio(campo){
    if (campo == null || campo == "null" ||campo == "" || campo == undefined || campo=="undefined" || campo.length == 0) return "";
    return campo;
}

function parse_estado(estado) {
    if (estado == "V")
        return "VIGENTE";
    else if (estado == "P")
        return "PENDIENTE";
    else if (estado == "R")
        return "REALIZADO";
    else
        return "ELIMINADO";
}

function tbl_eventos() {
    var formatEvento = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("window.location='/websae/administracion/evento/opciones/index.jsp?id_evento=" + oRecord.getData("id_evento") + "'",oRecord.getData("nombre"));
    },
    formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_evento('" + oRecord.getData("id_evento") + "','" + oRecord.getData("fecha_inicio") + "','" + oRecord.getData("fecha_fin") + "','" + oRecord.getData("estado") + "');","/websae/images/accessories-text-editor.png");
    },
    formatEstado = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = parseIdioma(parse_estado(oRecord.getData("estado")));
    },
    fields = ["id_evento","nombre","fecha_inicio","fecha_fin","estado"],
    myColumnDefs = [
        { key:"nombre", label:parseIdioma("nombre"), formatter:formatEvento, width: 220, className:"link_tabla"},
        { key:"fecha_inicio", label:parseIdioma("Fecha Inicio")},
        { key:"fecha_fin", label:parseIdioma("Fecha Fin")},
        { label:parseIdioma("estado"),formatter:formatEstado, width:70},
        { label:parseIdioma("Accion"),formatter:formatUrl}];

        tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=eventos",fields,myColumnDefs,"tbl_eventos");
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
        obj.value=obj.value.substring(0,mlength)
}


/* Manejo de TABS mediante DOJO */
dojo.addOnLoad(function(){
    var tc = new dijit.layout.TabContainer({
        style:"width:100%;height:310px",
        selected:true
    },
        "tabs"
    );

    var tab_info1 = new dijit.layout.ContentPane({
        title:"Evento"
    },
    "tab_info1"
    );

    var tab_info2 = new dijit.layout.ContentPane({
        title:"Ubicación"
    },
    "tab_info2"
    );

    var tab_info3 = new dijit.layout.ContentPane({
        title:"Descripción"
    },
    "tab_info3"
    );

    var tab_info4 = new dijit.layout.ContentPane({
        title:"Información"
    },
    "tab_info4"
    );

    var tab_info5 = new dijit.layout.ContentPane({
        title:"Referencia"
    },
    "tab_info5"
    );


    var tb = dijit.byId("tabs");
    tb.addChild(tab_info1);
    tb.addChild(tab_info2);
    tb.addChild(tab_info3);
    tb.addChild(tab_info4);
    tb.addChild(tab_info5);

    tb.startup();
});

function cargar_combobox(id_elemento, url_objeto, id_objeto, valor) {
    if (id_objeto != "") {
        var url_ = url_objeto+id_objeto;

        var datos = new dojo.data.ItemFileReadStore({url: url_});
        var elemento = dijit.byId(id_elemento);
        elemento.setDisplayedValue(valor);
        elemento.store = datos;
    }
    return;
}

function cargar_ciudades(id_pais) {
    cargar_combobox('cmb_ciudad', '/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=', id_pais, "");
}

function devuelve_fecha(fecha){
    if (fecha=="") return "";
    fecha = fecha.replace(/[-]/g, "/");
    fecha = new Date(fecha);
    return fecha
}

var fecha_actual = "";

function asignar_fecha_actual(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_fecha_actual',
        handleAs: 'json',
        load: function(xhr) {
            fecha_actual = devuelve_fecha(xhr.fecha_actual);
        }
    });
}

function cargar_logo(ruta1,ruta2,file){
    ajaxFileUpload(ruta1,ruta2,file,"/websae/images/evento/");
    //dojo.byId("logo_evento").innerHTML="<img src=\"/websae/images/evento/"+ dojo.byId(file+"2").value + "\" height=\"40px\" alt=\"\" />"
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