function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    cargar_mini_info(id_evento);
}

function cargar_mini_info(id_evento){
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_faq',
        handleAs: 'json',
        load: function(xhr) {
            if(xhr.texto.length>0)
                dojo.byId("descripcion").innerHTML = xhr.texto;
            else
                dojo.byId("descripcion").innerHTML = "No se ha ingresado ningún FAQ.";
        }
    });
}