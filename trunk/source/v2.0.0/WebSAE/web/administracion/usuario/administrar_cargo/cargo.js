function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_cargos();
    cargar_tipos_empresa();
}

var tipos_empresa_lista;

function cargar_tipos_empresa () {
    dojo.xhrPost({
        url: '/websae/F_mostrar_datos?tipo=tipos_empresas',
        handleAs: 'json',
        load: function(xhr) {
            tipos_empresa_lista = xhr.items;
        }
    });
}

//accion = 'registrar' || 'modificar';
function accion_cargo(accion){
    if (dijit.byId("frm_cargo").validate()) {
        dojo.xhrPost({
            url: '/websae/F_su_administrar_objetos?tipo='+accion+'_cargo',
            form: 'frm_cargo',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    var id;
                    if (accion=='registrar'){
                        id=xhr.id_cargo;
                    }else{
                        id=dojo.byId('txt_id_cargo').value;
                    }
                    alert(xhr.mensaje);
                    if (accion!='eliminar' && accion!='registrar') {
                        validar_asignar_cargos_tipo_empresa(id);
                    }else{
                        dijit.byId('div_cargo').hide();
                        tbl_cargos();
                    }
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function popup_registrar_cargo(){
    dijit.byId('frm_cargo').reset();
    dojo.byId('txt_id_cargo').value="-1";

/*    var html = '<form id="form_tipos_empresas" name="form_tipos_empresas">';
    for (var i=0; i<tipos_empresa_lista.length; i++) {
        html += '<label class="info"></label>';
        html += '<label class="info_dato"><input type="checkbox" id="tipos_empresas" dojoType="dijit.form.CheckBox" value="'+tipos_empresa_lista[i].id_tipo_empresa+'" /> '+tipos_empresa_lista[i].nombre+'</label>';
        html += '<div class="clearthefloats"></div>';
    }
    html += '</form>'
    dojo.byId("asignar-cargo").innerHTML = html;
*/
    dojo.byId("asignar-cargo").innerHTML = "";
    dijit.byId('div_cargo').show();

    mostrar_botones("R","");
}

function popup_modificar_cargo(id, nombre){
    dijit.byId('div_cargo').show();

    dojo.byId('txt_id_cargo').value=id;
    dojo.byId('txt_cargo').value=nombre;

    asignar_tipos_empresas(id);
    mostrar_botones("M","");
}

var num_tipos_empresas = 0;
var contador_tipos_empresas = 0;

function validar_asignar_cargos_tipo_empresa(id_cargo) {
    var opciones = document.form_tipos_empresas.tipos_empresas;

    num_tipos_empresas = opciones.length;
    contador_tipos_empresas = 0;
    for (var i=0; i<opciones.length; i++) {
        if (opciones[i].checked)
            asignar_cargo_tipo_empresa("V", id_cargo, opciones[i].value );
        else
            asignar_cargo_tipo_empresa("E", id_cargo, opciones[i].value );
    }
    dijit.byId('div_cargo').hide();
    tbl_cargos();
}

function asignar_cargo_tipo_empresa(accion, id_cargo, id_tipo_empresa) {
    //alert(accion+":"+id_cargo+":"+id_tipo_empresa);
    dojo.xhrPost({
        url: '/websae/F_su_administrar_objetos?tipo=asignar_tipo_empresa_cargo&estado='+accion+'&id_cargo='+id_cargo+"&id_tipo_empresa="+id_tipo_empresa,
        sync: true,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                contador_tipos_empresas++;
            }
            if (num_tipos_empresas == contador_tipos_empresas) {
                //alert(xhr.mensaje);
            }
        }
    });
}

function asignar_tipos_empresas(id_cargo) {
    dojo.xhrPost({
        url: '/websae/F_mostrar_datos?tipo=mostrar_tipos_empresas_por_cargo&id_cargo='+id_cargo,
        handleAs: 'json',
        load: function(xhr) {
            var html = '<form id="form_tipos_empresas" name="form_tipos_empresas">';
            for (var i=0; i<tipos_empresa_lista.length; i++) {
                html += '<label class="info"></label>';
                if (esta_asignado(tipos_empresa_lista[i].id_tipo_empresa, xhr.items)) {
                    html += '<label class="info_dato"><input type="checkbox" id="tipos_empresas" dojoType="dijit.form.CheckBox" checked="checked" value="'+tipos_empresa_lista[i].id_tipo_empresa+'" /> '+tipos_empresa_lista[i].nombre+'</label>';
                } else
                    html += '<label class="info_dato"><input type="checkbox" id="tipos_empresas" dojoType="dijit.form.CheckBox" value="'+tipos_empresa_lista[i].id_tipo_empresa+'" /> '+tipos_empresa_lista[i].nombre+'</label>';
                html += '<div class="clearthefloats"></div>';
            }
            html += '<br /><label class="info"></label></form>';
            dojo.byId("asignar-cargo").innerHTML = html;
        }
    });
}

function esta_asignado(id, lista) {
    for (var i=0; i<lista.length; i++) {
        if (id == lista[i].id_tipo_empresa)
            return true;
    }
    return false;
}

function tbl_cargos() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_modificar_cargo('" + oRecord.getData("id_cargo") + "','" + oRecord.getData("nombre") + "');","/websae/images/accessories-text-editor.png");
    },
    myColumnDefs = [
        {key:"nombre", label:parseIdioma("Nombre"), width:215},
        {label:parseIdioma("Accion"), formatter:formatUrl }
    ],
    fields = ["id_cargo","nombre"];

    tbl_generica("/websae/F_mostrar_datos?tipo=cargos",fields,myColumnDefs,"tbl_cargos");
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