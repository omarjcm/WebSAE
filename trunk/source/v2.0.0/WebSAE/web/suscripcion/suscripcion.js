function registrar_usuario() {
    if (dijit.byId("usuario").validate()) {
        if (mayor_edad(dojo.byId("txt_nacimiento").value)){
            dojo.xhrPost({
                url: '/websae/F_su_administrar_objetos?tipo=registrar_usuario',
                form: 'usuario',
                handleAs: 'json',
                load: function(xhr) {
                    if (xhr.tipo == "OK") {
                        alert(xhr.mensaje);
                        window.location.href = "/websae/home/index.jsp";
                    } else if (xhr.tipo == "ERROR") {
                        alert(xhr.mensaje);
                    }
                }
            });
        }else{
            alert(parseIdioma("menor edad"));
        }
    }
}

function registrar_empresa() {
    if (dijit.byId("empresa").validate()) {
        dojo.xhrPost({
            url: '/websae/F_su_administrar_objetos?tipo=registrar_empresa',
            form: 'empresa',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dijit.byId('crear-empresa').hide();
                    dijit.byId('cmb_tipo_empresa_us').setValue('');
                    dojo.byId("cmb_tipo_empresa_us").value="";
                    dijit.byId('cmb_tipo_empresa').setValue('');
                    dojo.byId("cmb_tipo_empresa").value="";
                    dijit.byId('cmb_tipo_empresa_us').setValue('');
                    dojo.byId("cmb_tipo_empresa_us").value="";
                    dojo.byId("txt_nombre_empresa").value="";
                    dijit.byId('cmb_pais_empresa').setValue('ECU');
                    dijit.byId('cmb_ciudad').setValue('');
                    dojo.byId("cmb_ciudad").value="";
                    dijit.byId("cmb_ciudad").setValue("32669");
                    dijit.byId('cmb_ciudad_empresa').setValue('');
                    dojo.byId("cmb_ciudad_empresa").value="";
                    dijit.byId('cmb_cargo').setValue('');
                    dojo.byId("cmb_cargo").value="";
                    dijit.byId('cmb_empresa').setValue('');
                    dojo.byId("cmb_empresa").value="";
                    dijit.byId("empresa").reset();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
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

function cargar_tipo_empresa(id_tipo_empresa) {
    cargar_combobox('cmb_cargo', '/websae/F_mostrar_datos?tipo=mostrar_cargos&id_tipo_empresa=', id_tipo_empresa, "");
    cargar_combobox('cmb_empresa', '/websae/F_mostrar_datos?tipo=mostrar_empresas&id_tipo_empresa=', id_tipo_empresa, "");
}

function cargar_empresas(id_tipo_empresa, valor) {
    cargar_combobox('cmb_empresa', '/websae/F_mostrar_datos?tipo=mostrar_empresas&id_tipo_empresa=', id_tipo_empresa, valor);
}

function cerrar_ventana() {
    dijit.byId('crear-empresa').hide();
}

function confirmPassword(value, constraints) {
    var isValid = false;
    if(constraints && constraints.other) {
        var otherInput =  dijit.byId(constraints.other);
        if(otherInput) {
            var otherValue = otherInput.value;
            isValid = (value == otherValue);
        }
    }
    return isValid;
}

function ismaxlength(obj){
    var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length>mlength)
    obj.value=obj.value.substring(0,mlength)
}

function mayor_edad(fecha){
    if (fecha=="") return true;
    var año_nac = fecha.substring(0,fecha.indexOf("-"));
    var minimo = new Date().getYear();
    if(minimo < 2000) { minimo = minimo + 1900; }
    minimo=minimo-18;
    if (año_nac<=minimo) return true
    return false;
}