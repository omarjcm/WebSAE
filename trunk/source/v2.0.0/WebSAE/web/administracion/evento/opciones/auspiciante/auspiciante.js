var id_evento="";

function inicializar(idioma,id){
    asignar_idioma(idioma);
    id_evento=id;
    tbl_auspiciantes();
}

dojo.addOnLoad(
  function(){
      //mostrar_tabla_auspiciante();
  }
);

//accion = 'registrar' || 'modificar';
function accion_auspiciante(accion){
    if (dijit.byId("frm_auspiciante").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_auspiciante',
            form: 'frm_auspiciante',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_auspiciante').hide();
                    tbl_auspiciantes();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_auspiciante(){
    dijit.byId('frm_auspiciante').reset();
    dojo.byId('txt_id_auspiciante').value="1";
    dijit.byId('cmb_tipo_empresa').setValue('1');
    dijit.byId('cmb_empresa').setValue('');
    dojo.byId('cmb_empresa').value="";
    dojo.byId('txt_monto').value=dojo.currency.format(0, {currency: "USD"});
    dojo.byId('txt_descripcion').value="";
    dijit.byId('div_auspiciante').show();
    mostrar_botones('R');
}

function popup_modificar_auspiciante(id_auspicio, tipo_empresa, empresa, monto, descripcion){
    dijit.byId('div_auspiciante').show();
    mostrar_botones('M');

    dojo.byId('txt_id_auspiciante').value=id_auspicio;
    dijit.byId('cmb_tipo_empresa').setValue(tipo_empresa);
    dijit.byId('cmb_empresa').setValue(empresa);
    dojo.byId('txt_monto').value=dojo.currency.format(monto, {currency: "USD"});
    dojo.byId('txt_descripcion').value = descripcion;
}

function tbl_auspiciantes() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_auspiciante('" + oRecord.getData("id_auspicio") + "','" + oRecord.getData("empresa.tipo_empresa.id_tipo_empresa") + "','" + oRecord.getData("empresa.id_empresa") + "','" + oRecord.getData("monto") + "','" + oRecord.getData("descripcion").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_auspicio","empresa.tipo_empresa.id_tipo_empresa","empresa.id_empresa","monto","descripcion","empresa.nombre"],
    myColumnDefs = [
        { key:"empresa.nombre", label:parseIdioma("Auspiciante"), width:130},
        { key:"monto", label:parseIdioma("Monto"), formatter:"currency", width:60},
        { key: "descripcion", label:parseIdioma("Descripcion"), width:180},
        { label:parseIdioma("Accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_auspiciantes",fields,myColumnDefs,"tbl_auspiciantes");
}

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

function cargar_tipo_empresa(id_tipo_empresa) {
    cargar_combobox('cmb_empresa', '/websae/F_mostrar_datos?tipo=mostrar_empresas&id_tipo_empresa=', id_tipo_empresa, "");
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