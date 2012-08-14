function administrar_registro(accion){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_registro_usuario&id_registro='+dojo.byId("id_registro").value,
        handleAs: 'json',
        load: function(xhr) {
            alert(xhr.mensaje);
            dijit.byId('dlg_registro').hide();
            tbl_registros();
        }
    });
}

function administrar_pago(accion){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo='+accion+'_pago_usuario&id_registro='+dojo.byId("id_registro").value,
        handleAs: 'json',
        load: function(xhr) {
            alert(xhr.mensaje);
            dijit.byId('dlg_pago').hide();
            tbl_registros();
        }
    });
}

function popup_registro(id){
    dijit.byId("dlg_registro").show();
    dojo.byId("id_registro").value = id;
}

function popup_pago(id){
    dijit.byId("dlg_pago").show();
    dojo.byId("id_registro").value = id;
}