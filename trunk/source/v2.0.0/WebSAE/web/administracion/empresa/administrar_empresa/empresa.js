function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_empresas();
}

/*dojo.addOnLoad(
  function(){
      mostrar_tabla_empresa();
  }
);*/

//accion = 'registrar' || 'modificar' || 'eliminar';
function accion_empresa(accion){
    if (dijit.byId("frm_empresa").validate()) {
        dojo.xhrPost({
            url: '/websae/F_su_administrar_objetos?tipo='+accion+'_empresa',
            form: 'frm_empresa',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_empresa').hide();
                    tbl_empresas();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_empresa(){
    dijit.byId('div_empresa').show();
    dijit.byId('frm_empresa').reset();
    
    dojo.byId('txt_id_empresa').value="1";
    dijit.byId('cmb_tipo_empresa').setValue('1');
    dijit.byId('cmb_pais').setValue('ECU');
    //dojo.byId('cmb_ciudad_empresa').value="";
    dojo.byId('txt_direccion').value="";
    dijit.byId('cmb_ciudad_empresa').setValue("32669");
    dojo.byId('txt_logo2').value="default.JPG";
    dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/logo_empresa/default.JPG\" height=\"40px\" alt=\"\" />"
    
    mostrar_botones('R','');
}

function popup_modificar_empresa(id, nombre){
    dijit.byId('div_empresa').show();
    dijit.byId('frm_empresa').reset();

    dojo.byId('txt_id_empresa').value = id;
    dojo.byId('txt_empresa').value = nombre;

    mostrar_botones('M','');
    cargar_datos_empresa(id);
}

function cargar_datos_empresa(id_empresa){
        dojo.xhrPost({
        url: '/websae/F_mostrar_datos?tipo=buscar_empresa&id_empresa='+id_empresa,
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId('txt_direccion').value = parse_vacio(xhr.direccion);
            dijit.byId('cmb_tipo_empresa').setValue(xhr.tipo_empresa.id_tipo_empresa);
            dijit.byId('cmb_pais').setValue(xhr.ciudad.pais.id_pais);
            dijit.byId('cmb_ciudad_empresa').setValue(xhr.ciudad.id_ciudad);
            dojo.byId('cmb_ciudad_empresa').value=xhr.ciudad.nombre;
            dojo.byId('txt_siglas').value = parse_vacio(xhr.siglas);
            dojo.byId('txt_codigo_postal').value = parse_vacio(xhr.codigo_postal);
            dojo.byId('txt_telefono').value = parse_vacio(xhr.telefono);
            dojo.byId('txt_fax').value = parse_vacio(xhr.fax);
            dojo.byId('txt_logo2').value = xhr.logo;
            dojo.byId('txt_url').value = parse_vacio(xhr.web);
            dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/logo_empresa/" + xhr.logo + "\" height=\"40px\" alt=\"\" />"
        }
    });
}


function parse_vacio(campo){
    if (campo == null){
        return "";
    }else{
        return campo;
    }
}

function tbl_empresas() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_empresa('" + oRecord.getData("id_empresa") + "','" + oRecord.getData("nombre") + "');","/websae/images/accessories-text-editor.png");
    },
    formatLogo = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML =  "<img src=\"/websae/images/logo_empresa/"+oRecord.getData("logo")+"\" height=\"35px\" alt=\"\" ></img>";
    },
    fields = ["id_empresa","nombre","logo"],
    myColumnDefs = [
        { label:"Logo", formatter:formatLogo, width:130},
        { key:"nombre", label:parseIdioma("Nombre"), width:265},
        { label:parseIdioma("Accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/F_mostrar_datos?tipo=empresas",fields,myColumnDefs,"tbl_empresas");

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

function cargar_ciudades(id_pais) {
    cargar_combobox('cmb_ciudad', '/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=', id_pais, "");
}

function cargar_ciudades_empresa(id_pais) {
    cargar_combobox('cmb_ciudad_empresa', '/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=', id_pais, "");
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
    obj.value=obj.value.substring(0,mlength)
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

/*function ajaxFileUpload(tipo, directorio) {
    $.ajaxFileUpload({
        url:'/websae/F_adjuntar_archivo?tipo='+tipo+'&directorio='+directorio,
        secureuri:false,
        fileElementId:'txt_logo',
        dataType: 'json',
        success: function (data, status){ }
    });
    alert(parseIdioma("imagen cargada"));
    dojo.byId("logo_empresa").innerHTML="<img src=\"/websae/images/logo_empresa/"+ dojo.byId("txt_logo").value + "\" height=\"40px\" alt=\"\" />"
    dojo.byId("txt_logo2").value=dojo.byId("txt_logo").value;

    return false;
}*/

function cargar_logo(ruta1,ruta2,file){
    ajaxFileUpload(ruta1,ruta2,file,"/websae/images/logo_empresa/");
    //dojo.byId("logo_empresa").innerHTML="<img src=\+ dojo.byId(file+"2").value + "\" height=\"40px\" alt=\"\" />"
}