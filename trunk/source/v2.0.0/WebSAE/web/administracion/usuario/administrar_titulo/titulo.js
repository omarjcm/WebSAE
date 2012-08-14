function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_titulos();
}

function accion_titulo(accion){
    if (dijit.byId("frm_titulo").validate()) {
        dojo.xhrPost({
            url: '/websae/F_su_administrar_objetos?tipo='+accion+'_titulo',
            form: 'frm_titulo',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    tbl_titulos();
                    dijit.byId('div_titulo').hide();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_titulo(){
    dijit.byId('frm_titulo').reset();
    dojo.byId('txt_id_titulo').value="1";
    dijit.byId('div_titulo').show();
    dijit.byId('btn_registrar').domNode.style.display = "block";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    mostrar_botones("R","");
}

function popup_modificar_titulo(id, nombre, abreviatura){
    dijit.byId('div_titulo').show();
    dijit.byId('btn_registrar').domNode.style.display = "none";
    dijit.byId('btn_modificar').domNode.style.display = "block";

    dojo.byId('txt_id_titulo').value=id;
    dojo.byId('txt_titulo').value=nombre;
    dojo.byId('txt_abreviatura').value=abreviatura;
 
    mostrar_botones("M","");
}

function tbl_titulos() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_titulo('" + oRecord.getData("id_titulo") + "','" + oRecord.getData("nombre") + "','" + oRecord.getData("abreviatura") + "');", "/websae/images/accessories-text-editor.png");
    },
    fields = ["id_titulo","nombre","abreviatura"],
    myColumnDefs = [
        {key:"nombre", label:parseIdioma("nombre"), width:190},
        {key:"abreviatura", label:parseIdioma("abreviatura"), width:70},
        {label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/F_mostrar_datos?tipo=titulos",fields,myColumnDefs,"tbl_titulos");
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