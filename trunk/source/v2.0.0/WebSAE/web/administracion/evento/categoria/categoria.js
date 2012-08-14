function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_categorias();
}

//accion = 'registrar' || 'modificar';
function accion_categoria(accion){
    if (dijit.byId("frm_categoria").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_categoria',
            form: 'frm_categoria',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_categoria').hide();
                    tbl_categorias();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_categoria(){
    dijit.byId('frm_categoria').reset();
    dojo.byId('txt_id_categoria').value="1";
    dijit.byId('div_categoria').show();
    
    mostrar_botones("R","");
}

function popup_modificar_categoria(id, nombre, estado){
    dijit.byId('div_categoria').show();

    dojo.byId('txt_id_categoria').value=id;
    dojo.byId('txt_categoria').value=nombre;

    mostrar_botones("M","");
}

function tbl_categorias() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_categoria('" + oRecord.getData("id_categoria") + "','" + oRecord.getData("nombre").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_categoria","nombre"],
    myColumnDefs = [
        { key:"nombre", label:parseIdioma("Nombre"), width:170},
        { label:parseIdioma("Accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=categorias",fields,myColumnDefs,"tbl_categorias");
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