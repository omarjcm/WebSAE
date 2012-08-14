 function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_subeventos();
}

var ref_id_subevento = "";

function redireccionar(tipo){
    window.location.href = "/websae/administracion/evento/opciones/subevento/" + tipo + "/index.jsp?id_subevento=" + ref_id_subevento;
}

function popup_submenu(id_subevento){
    dijit.byId('div_boton_opciones').show();
    ref_id_subevento=id_subevento;
}

//accion = 'registrar' || 'modificar' || Eliminar;
function accion_subevento(accion){ 
    if (dijit.byId("frm_subevento").validate()) {
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_subevento',
            form: 'frm_subevento',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('div_subevento').hide();
                    tbl_subeventos();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_subevento(){
    dijit.byId('div_subevento').show();
    dijit.byId('frm_subevento').reset();
    dojo.byId('txt_id_subevento').value="1";
    dijit.byId('cmb_tipo_evento').setValue('1');
    dojo.byId('txt_subevento_es').value = "";
    dojo.byId('txt_subevento_en').value = "";
    dojo.byId('txt_subevento_pt').value = "";
    dojo.byId('txt_descripcion_es').value = "";
    dojo.byId('txt_descripcion_en').value = "";
    dojo.byId('txt_descripcion_pt').value = "";
    dojo.byId('txt_cupo').value = "0";
    
    mostrar_botones("R","");
}

function popup_modificar_subevento(id_tipo_evento, id_subevento, cupo){
    dijit.byId('div_subevento').show();
    mostrar_botones("M","");

    dijit.byId('cmb_tipo_evento').setValue(id_tipo_evento);
    dojo.byId('txt_id_subevento').value = id_subevento;
    dojo.byId('txt_cupo').value = cupo;

    cargar_datos_subevento(id_subevento);
}

function cargar_datos_subevento(id_subevento){
        dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=buscar_evento&id_evento='+id_subevento,
        handleAs: 'json',
        load: function(xhr) {
            dojo.byId('txt_subevento_es').value = xhr.nombre_es;
            dojo.byId('txt_subevento_en').value = xhr.nombre_en;
            dojo.byId('txt_subevento_pt').value = xhr.nombre_pt;
            dojo.byId('txt_descripcion_es').value = parse_vacio(xhr.descripcion_es);
            dojo.byId('txt_descripcion_en').value = parse_vacio(xhr.descripcion_en);
            dojo.byId('txt_descripcion_pt').value = parse_vacio(xhr.descripcion_pt);
        }
    });
}


function tbl_subeventos() {
            var formatsubevento = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = format_innerHTML("popup_submenu("+ oRecord.getData("id_evento") +");",oRecord.getData("nombre"));
            },
            formatUrl = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = format_innerHTML("popup_modificar_subevento('" + oRecord.getData("tipo_evento.id_tipo_evento") + "','" + oRecord.getData("id_evento") + "','" + oRecord.getData("cupo_maximo") + "');","/websae/images/accessories-text-editor.png");
            },
            fields = ["tipo_evento.id_tipo_evento","tipo_evento.nombre","id_evento","nombre","descripcion","cupo_maximo"],
            myColumnDefs = [
            { label:parseIdioma("tipo"),key:"tipo_evento.nombre", width:70},
            { label:parseIdioma("nombre"),formatter:formatsubevento,key:"nombre", width:100, className:"link_tabla"},
            { label:parseIdioma("descripcion"),key:"descripcion", width:200},
            { label:parseIdioma("accion"),formatter:formatUrl}];

            tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=subeventos",fields,myColumnDefs,"tbl_subeventos");
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

function parse_vacio(campo){
    if (campo == null){
        return "";
    }else{
        return campo;
    }
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
        obj.value=obj.value.substring(0,mlength)
}

google.load("language", "1");

function traducir(opcTraducir,texto){
    var traducido = document.getElementById(texto);
    google.language.translate(traducido.innerHTML, "es", opcTraducir,
        function(result) {
            if (result.translation) {
                traducido.removeChild(traducido.firstChild);
                traducido.innerHTML = result.translation;
            }
        });
}

function traduccion(opcOrg,opcTraducir,texto,donde){
    var traducido = document.getElementById(texto);
    var donde2=dojo.byId(donde);
    google.language.translate(traducido.value, opcOrg, opcTraducir,
        function(result) {
            if (result.translation) {
                donde2.value=result.translation;
            }else{
                alert(parseIdioma("error traduccion"));
            }
        });
}