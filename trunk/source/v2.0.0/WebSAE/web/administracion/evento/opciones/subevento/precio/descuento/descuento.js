  var ref_id_categoria="";
  var id_subevento="";
  
  function inicializar(idioma, _ref_id_categoria, _id_subevento){
    asignar_idioma(idioma);
    ref_id_categoria = _ref_id_categoria;
    id_subevento = _id_subevento;
    asignar_categoria();
    tbl_descuentos();
    cargar_subcategorias2();
    //asignar_fecha_actual();
    //asignar_info_evento();
}

function redirect_back(){
    window.location.href = "/websae/administracion/evento/opciones/subevento/precio/index.jsp?id_subevento="+id_subevento;
}

function asignar_categoria(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=buscar_categoria&id_categoria='+ref_id_categoria,
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId('subtitulo_categoria').innerHTML = parseIdioma("categoria") + ": " + xhr.nombre;
        }
    });
}

//accion = 'registrar' || 'modificar' || 'eliminar';
function accion_descuento(accion){
    if (dijit.byId("frm_descuento").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_subcategoria_subevento&id_subevento='+id_subevento,
            form: 'frm_descuento',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_descuento').hide();
                    tbl_descuentos();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_descuento(){
    dijit.byId('div_descuento').show();
    dijit.byId('frm_descuento').reset();
    dojo.byId('txt_id_descuento').value="1";
    dojo.byId('txt_descuento').value="0";//dojo.currency.format(0, {currency: "USD"});
    
    mostrar_botones("R","");
}

function popup_modificar_descuento(id_subcategoria_evento, id_subcategoria, descuento){
    dijit.byId('div_descuento').show();

    dojo.byId('txt_id_descuento').value=id_subcategoria_evento;
    dijit.byId('cmb_subcategoria').setValue(id_subcategoria);
    dojo.byId('txt_descuento').value=descuento;
    
    mostrar_botones("M","");
}

function tbl_descuentos() {
    var formatPorcentaje = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = oRecord.getData("porcentaje_descuento") + "%";
    },
    formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_descuento('" + oRecord.getData("id_subcategoria_evento") + "','" + oRecord.getData("subcategoria.id_subcategoria") + "','" + oRecord.getData("porcentaje_descuento") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_subcategoria_evento","subcategoria.id_subcategoria","subcategoria.nombre","porcentaje_descuento"],
    myColumnDefs = [
        { label:parseIdioma("subcategoria"), key:"subcategoria.nombre", width:150},
        { label:parseIdioma("descuento"), formatter:formatPorcentaje},
        { label:parseIdioma("accion"), formatter:formatUrl}
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_subcategorias_subevento&id_categoria="+ref_id_categoria+'&id_subevento='+id_subevento,fields,myColumnDefs,"tbl_descuentos");
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

function cargar_subcategorias2() {
    dojo.xhrGet({
        url: "/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_subcategorias&id_categoria="+ref_id_categoria,
        handleAs: "json",
        load: function(xhr) {
            var cola ="";
            var temp;
            cola = "<select name=\"cmb_subcategoria\" id=\"cmb_subcategoria\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" >";
            for (var i=0; i<xhr.items.length; i++) {
                tmp = "<option value=\"" + xhr.items[i].id_subcategoria + "\">" + xhr.items[i].nombre + "</option>";
                cola = cola + tmp;
                tmp = "";
            }
            cola = cola + "</select>";
            dojo.byId("div_subcategorias").innerHTML = cola;
            dojo.parser.parse(dojo.byId("div_subcategorias"));
        }
    });
}