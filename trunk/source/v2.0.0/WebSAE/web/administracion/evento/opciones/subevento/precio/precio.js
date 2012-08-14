  var id_subevento="";

  function inicializar(idioma, _id_subevento){
    id_subevento=_id_subevento;
    asignar_idioma(idioma);
    tbl_precios();
    tbl_descuentos();
    asignar_fecha_actual();
    asignar_info_evento();
}

//accion = 'registrar' || 'modificar' || 'eliminar';
function accion_precio(accion){
    if (dijit.byId("frm_precio").validate()) {
        if (devuelve_fecha(dojo.byId('txt_fecha_inicio').value)>devuelve_fecha(dojo.byId('txt_fecha_fin').value) && accion!="eliminar" ){
            alert(parseIdioma('fecha inicial final'));
        }else if (devuelve_fecha(dojo.byId('txt_fecha_fin').value)>devuelve_fecha(fecha_fin) && accion!="eliminar"){
            alert(parseIdioma('fecha fin final'));
        //}else if (devuelve_fecha(dojo.byId('txt_fecha_inicio').value)<devuelve_fecha(fecha_inicio) && accion!="eliminar"){
          //  alert(parseIdioma('fecha inicio inicial'));
        }else if (devuelve_fecha(dojo.byId('txt_fecha_inicio').value)<devuelve_fecha(fecha_actual) && accion!="eliminar"){
            alert(parseIdioma('fecha inicial actual'));
        }else{
            dojo.xhrPost({
                url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_categoria_subevento&id_subevento='+id_subevento,
                form: 'frm_precio',
                handleAs: 'json',
                load: function(xhr) {
                    if (xhr.tipo == "OK") {
                        alert(xhr.mensaje);
                        dijit.byId('div_precio').hide();
                        tbl_precios();
                        tbl_descuentos();
                    } else if (xhr.tipo == "ERROR") {
                        alert(xhr.mensaje);
                    }
                }
            });
        }
    }
}

function popup_registrar_precio(){
    dijit.byId('div_precio').show();
    dijit.byId('frm_precio').reset();
    dojo.byId('txt_id_precio').value="1";
    dojo.byId('cmb_categoria').value="";
    dijit.byId('cmb_categoria').setValue('1');
    dojo.byId('txt_precio').value=dojo.currency.format(0, {currency: "USD"});
    dojo.byId('txt_fecha_inicio').value="";
    dojo.byId('txt_fecha_fin').value="";

    mostrar_botones("R","");
    
}

function popup_modificar_precio(id_precio, id_categoria, precio, fecha_inicio, fecha_fin){
    dijit.byId('div_precio').show();
    dojo.byId('txt_id_precio').value=id_precio;
    dijit.byId('cmb_categoria').setValue(id_categoria);
    dojo.byId('txt_precio').value=dojo.currency.format(precio, {currency: "USD"});
    dojo.byId('txt_fecha_inicio').value=fecha_inicio;
    dojo.byId('txt_fecha_fin').value=fecha_fin;

    mostrar_botones("M","");
}

function tbl_precios() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_precio('" + oRecord.getData("id_categoria_evento") + "','" + oRecord.getData("categoria.id_categoria") + "','" + oRecord.getData("precio") + "','" + oRecord.getData("fecha_inicio") + "','" + oRecord.getData("fecha_fin") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_categoria_evento","precio","fecha_inicio","fecha_fin","categoria.nombre","categoria.id_categoria"],
    myColumnDefs = [
        {label:parseIdioma("categoria"),key:"categoria.nombre", width:120},
        {label:parseIdioma("precio"),formatter:"currency",key:"precio", width:50},
        {label:parseIdioma("fecha inicio"),key:"fecha_inicio"},
        {label:parseIdioma("fecha fin"),key:"fecha_fin"},
        {label:parseIdioma("accion"),formatter:formatUrl}
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_categorias_subevento&id_subevento="+id_subevento,fields,myColumnDefs,"tbl_precios");
}


function redireccionar_descuentos(id_categoria){
    window.location.href = "/websae/administracion/evento/opciones/subevento/precio/descuento/index.jsp?id_categoria="+id_categoria+'&id_subevento='+id_subevento;
}

function tbl_descuentos() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("redireccionar_descuentos('" + oRecord.getData("id_categoria") + "');","/websae/images/descuento.gif");
    },
    fields = ["id_categoria","nombre"],
    myColumnDefs = [
        {label:parseIdioma("categoria"),key:"nombre", width:120},
        {label:parseIdioma("descuento"),formatter:formatUrl}
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_categorias_por_subevento&id_subevento="+id_subevento,fields,myColumnDefs,"tbl_descuentos");
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


function devuelve_fecha(_fecha){
    if (_fecha=="") return "";
    var fecha = _fecha.replace(/[-]/g, "/");
    fecha = new Date(fecha);
    return fecha;
}

var fecha_actual = "";
var fecha_inicio = "";
var fecha_fin = "";

function asignar_fecha_actual(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_fecha_actual',
        handleAs: 'json',
        load: function(xhr) {
            fecha_actual = xhr.fecha_actual;
        }
    });
}

function asignar_info_evento(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_evento',
        handleAs: 'json',
        load: function(xhr) {
            fecha_inicio = xhr.fecha_inicio;
            fecha_fin = xhr.fecha_fin;
            dojo.byId('rango_fechas').innerHTML = parseIdioma('fecha evento') + ': ' + fecha_inicio + ' -:- ' + fecha_fin;
        }
    });
}