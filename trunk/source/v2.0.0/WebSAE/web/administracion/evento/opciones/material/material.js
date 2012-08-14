var id_evento="";

function inicializar(idioma,id){
    asignar_idioma(idioma);
    id_evento=id;
    tbl_materiales();
}

dojo.addOnLoad(
  function(){
      //mostrar_tabla_material();
  }
);

//accion = 'registrar' || 'modificar';
function accion_material(accion){
    if (dijit.byId("frm_material").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_material',
            form: 'frm_material',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_material').hide();
                    tbl_materiales();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_material(){
    dijit.byId('frm_material').reset();
    dojo.byId('txt_id_material').value="1";
    dojo.byId('cmb_tipo_material').value="";
    dojo.byId('txt_descripcion').value="";
    dojo.byId('txt_precio').value=dojo.currency.format(0, {currency: "USD"});
    dojo.byId('txt_cantidad').value="0";
    dijit.byId('div_material').show();
    mostrar_botones('R');
}

function popup_modificar_material(id, tipo_material, descripcion, precio, cantidad){
    dijit.byId('div_material').show();
    mostrar_botones('M');

    dojo.byId('txt_id_material').value=id;
    dojo.byId('txt_descripcion').value=descripcion;
    dojo.byId('cmb_tipo_material').value=tipo_material;
    dojo.byId('cmb_tipo_material').focus();
    dojo.byId('txt_descripcion').focus();
    dojo.byId('txt_precio').value=dojo.currency.format(precio, {currency: "USD"});
    dojo.byId('txt_cantidad').value = cantidad;
}

function tbl_materiales() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML =  format_innerHTML("popup_modificar_material('" + oRecord.getData("id_material") + "','" + oRecord.getData("tipo_material.nombre") + "','" + oRecord.getData("descripcion").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "','" + oRecord.getData("precio") + "','" + oRecord.getData("cantidad_entregar") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_material","tipo_material.nombre","descripcion","cantidad_entregar","precio"],
    myColumnDefs = [
        { key:"tipo_material.nombre", label:parseIdioma("Nombre"), width:110},
        { key:"descripcion", label:parseIdioma("Descripcion"), width:180},
        { key:"cantidad_entregar", label:parseIdioma("Cantidad")},
        { key: "precio", label:parseIdioma("Precio"), formatter:"currency"},
        { label:parseIdioma("Accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=materiales&id_evento="+id_evento,fields,myColumnDefs,"tbl_materiales");
}

function mostrar_botones(accion){
    dijit.byId('btn_registrar').domNode.style.display = "none";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    dijit.byId('btn_eliminar').domNode.style.display = "none";
    dijit.byId('btn_cancelar').domNode.style.display = "none";
    dijit.byId('btn_cerrar').domNode.style.display = "none";

    if (accion=="R"){
        dijit.byId('btn_registrar').domNode.style.display = "block";
        dijit.byId('btn_cancelar').domNode.style.display = "block";
    }else if (accion=="M"){
        dijit.byId('btn_modificar').domNode.style.display = "block";
        dijit.byId('btn_eliminar').domNode.style.display = "block";
        dijit.byId('btn_cancelar').domNode.style.display = "block";
    }else{
        dijit.byId('btn_cerrar').domNode.style.display = "block";
    }
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
    obj.value=obj.value.substring(0,mlength)
}