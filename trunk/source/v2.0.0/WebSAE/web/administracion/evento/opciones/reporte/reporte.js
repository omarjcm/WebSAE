function generar_reporte(accion){
    dojo.xhrPost({
        //url: '/websae/administracion/F_ae_administrar_reportes?tipo='+accion,
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=buscar_evento&id_evento=20',
        load: function(xhr) {

        }
    });
}