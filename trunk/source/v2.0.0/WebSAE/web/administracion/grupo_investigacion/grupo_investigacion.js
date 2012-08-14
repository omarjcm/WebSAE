function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_grupos_investigacion();
}

/*dojo.addOnLoad( 
  function(){
      mostrar_tabla_grupo_investigacion();
  }
);*/
/*function cargar_logo(ruta1,ruta2,file){
    ajaxFileUpload(ruta1,ruta2,file);
    dojo.byId("logo_imagen").innerHTML="<img src=\"/websae/images/logo_grupo_investigacion/"+ dojo.byId(file+"2").value + "\" height=\"40px\" alt=\"\" />"
}*/

function cargar_logo(ruta1,ruta2,file){
    ajaxFileUpload(ruta1,ruta2,file,"/websae/images/logo_grupo_investigacion/");
    //dojo.byId("logo_evento").innerHTML="<img src=\"/websae/images/evento/"+ dojo.byId(file+"2").value + "\" height=\"40px\" alt=\"\" />"
}


/*function ajaxFileUpload(tipo, directorio) {
    $.ajaxFileUpload({
        url:'/websae/F_adjuntar_archivo?tipo='+tipo+'&directorio='+directorio,
        secureuri:false,
        fileElementId:'txt_logo',
        dataType: 'json',
        success: function (data, status){
            /*if(typeof(data.error) != 'undefined') {
                if(data.error != ''){
                    alert(data.error);
                }else{
                    alert(data.msg);
                }
            }
        },
        error: function (data, status, e){
            //alert(e);
        }
    })
    
    dojo.byId("logo_imagen").innerHTML="<img src=\"/websae/images/logo_grupo_investigacion/"+ dojo.byId("txt_logo").value + "\" height=\"40px\" alt=\"\" />"
    dojo.byId("txt_logo2").value=dojo.byId("txt_logo").value;
    return false;
}*/


//accion = 'registrar' || 'modificar';
function accion_grupo_investigacion(accion){
    if (dijit.byId("frm_grupo_investigacion").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_gi_administrar_objetos?tipo='+accion+'_grupo_investigacion',
            form: 'frm_grupo_investigacion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_grupo_investigacion').hide();
                    tbl_grupos_investigacion();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_grupo_investigacion(){
    dijit.byId('div_grupo_investigacion').show();
    dijit.byId('frm_grupo_investigacion').reset();
    dojo.byId('txt_id_grupo_investigacion').value="1";
    dojo.byId('txt_grupo_investigacion').value="";
    dojo.byId('txt_imagen2').value="default.jpg";
    dojo.byId('txt_objetivo').value="";
    dojo.byId('txt_telefono').value="";
    dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/logo_grupo_investigacion/default.jpg\" height=\"40px\" alt=\"\" />"
    
    mostrar_botones("R","");
}

function popup_modificar_grupo_investigacion(id, nombre, objetivo, telefono, web, logo){
    dijit.byId('frm_grupo_investigacion').reset();
    dijit.byId('div_grupo_investigacion').show();

    dojo.byId('txt_id_grupo_investigacion').value = id;
    dojo.byId('txt_grupo_investigacion').value = parse_vacio(nombre);
    dojo.byId('txt_objetivo').value = parse_vacio(objetivo);
    dojo.byId('txt_telefono').value = parse_vacio(telefono);
    dojo.byId('txt_url').value = parse_vacio(web);
    dojo.byId('txt_imagen2').value = logo;
    dojo.byId('logo_imagen').innerHTML = "<img src=\"/websae/images/logo_grupo_investigacion/" + logo + "\" height=\"40px\" alt=\"\" />"

    mostrar_botones("M","");
}

function parse_vacio(campo){
    if (campo == null || campo == "" || campo == "undefined"){
        return "";
    }else{
        return campo;
    }
}

function tbl_grupos_investigacion() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_grupo_investigacion('" + oRecord.getData("id_grupo_investigacion") + "','" + oRecord.getData("nombre").replace(/\'/gi,"").replace(/\"/gi,"").replace(/\\/gi,"") + "','" + oRecord.getData("objetivo").replace("\'","").replace("\"","") + "','" + oRecord.getData("telefono") + "','" + oRecord.getData("web") + "','" + oRecord.getData("logo") + "');","/websae/images/accessories-text-editor.png");
    },
    fields = ["id_grupo_investigacion","nombre","objetivo","telefono","web","logo"],
    myColumnDefs = [
        {label:parseIdioma("nombre"), key:"nombre", width:180},
        {label:parseIdioma("telefono"), key:"telefono", width:70},
        {label:parseIdioma("paginaweb"), key:"web", width:190},
        {label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/F_gi_mostrar_datos?tipo=grupos_investigacion",fields,myColumnDefs,"tbl_grupos_investigacion");
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

    if (accion=="R"){
        dijit.byId('btn_registrar'+ref).domNode.style.display = "block";
        dijit.byId('btn_cancelar'+ref).domNode.style.display = "block";
    }else if (accion=="M"){
        dijit.byId('btn_modificar'+ref).domNode.style.display = "block";
        dijit.byId('btn_eliminar'+ref).domNode.style.display = "block";
        dijit.byId('btn_cancelar'+ref).domNode.style.display = "block";
    }
}

