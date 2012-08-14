function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_subcategorias();
}

//accion = 'registrar' || 'modificar';
function accion_subcategoria(accion){
    if (dijit.byId("frm_subcategoria").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_subcategoria',
            form: 'frm_subcategoria',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_subcategoria').hide();
                    tbl_subcategorias();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_subcategoria(){
    dijit.byId('frm_subcategoria').reset();
    dojo.byId('txt_id_subcategoria').value="1";
    dijit.byId('cmb_categoria').setValue("1");
    dojo.byId('txt_subcategoria').value="";
    dijit.byId('div_subcategoria').show();

    mostrar_botones("R","");
}

function popup_modificar_subcategoria(id, nombre, categoria){
    dijit.byId('div_subcategoria').show();

    dojo.byId('txt_id_subcategoria').value=id;
    dojo.byId('txt_subcategoria').value=nombre;
    dijit.byId('cmb_categoria').setValue(categoria);

    mostrar_botones("M","");
}

function tbl_subcategorias() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_subcategoria('" + oRecord.getData("id_subcategoria") + "','" + oRecord.getData("nombre").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "','" + oRecord.getData("categoria.id_categoria") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_subcategoria","nombre","categoria.id_categoria","categoria.nombre"],
    myColumnDefs = [
        { key:"categoria.nombre", label:parseIdioma("Categoria"), width:120},
        { key:"nombre", label:parseIdioma("SubCategoria"), width:100},
        {label:parseIdioma("Accion"), formatter:formatUrl }
    ];
    
    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=subcategorias",fields,myColumnDefs,"tbl_subcategorias");
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