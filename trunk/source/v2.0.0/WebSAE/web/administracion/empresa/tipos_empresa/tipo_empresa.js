function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_tipos_empresas();
}

//accion = 'registrar' || 'modificar'; 
function accion_tipo_empresa(accion){
    if (dijit.byId("frm_tipo_empresa").validate()) {
        dojo.xhrPost({
            url: '/websae/F_su_administrar_objetos?tipo='+accion+'_tipo_empresa',
            form: 'frm_tipo_empresa',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_tipo_empresa').hide();
                    tbl_tipos_empresas();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_tipo_empresa(){
    dijit.byId('frm_tipo_empresa').reset();
    dojo.byId('txt_id_tipo_empresa').value="1";
    dijit.byId('div_tipo_empresa').show();
    
    mostrar_botones("R","");
}

function popup_modificar_tipo_empresa(id, nombre){
    dijit.byId('div_tipo_empresa').show();

    dojo.byId('txt_id_tipo_empresa').value=id;
    dojo.byId('txt_tipo_empresa').value=nombre;

    mostrar_botones("M","");
}

function tbl_tipos_empresas() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_tipo_empresa('" + oRecord.getData("id_tipo_empresa") + "','" + oRecord.getData("nombre").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_tipo_empresa","nombre"],
    myColumnDefs = [
        { label:parseIdioma("nombre"), key:"nombre", width:200 },
        { label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/F_mostrar_datos?tipo=tipos_empresas",fields,myColumnDefs,"tbl_tipos_empresas");
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