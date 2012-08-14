function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_tipos_material();
}

//accion = 'registrar' || 'modificar';
function accion_tipo_material(accion){
    if (dijit.byId("frm_tipo_material").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_tipo_material',
            form: 'frm_tipo_material',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_tipo_material').hide();
                    tbl_tipos_material();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_tipo_material(){
    dijit.byId('frm_tipo_material').reset();
    dojo.byId('txt_id_tipo_material').value="1";
    dijit.byId('div_tipo_material').show();

    mostrar_botones("R","");
}

function popup_modificar_tipo_material(id, nombre, estado){
    dijit.byId('div_tipo_material').show();    

    dojo.byId('txt_id_tipo_material').value=id;
    dojo.byId('txt_tipo_material').value=nombre;

    mostrar_botones("M","");
}

function tbl_tipos_material() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_tipo_material('" + oRecord.getData("id_tipo_material") + "','" + oRecord.getData("nombre").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_tipo_material","nombre"],
    myColumnDefs = [
        { key:"nombre", label:parseIdioma("nombre"), width:170},
        { label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=tipos_materiales",fields,myColumnDefs,"tbl_tipos_material");
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