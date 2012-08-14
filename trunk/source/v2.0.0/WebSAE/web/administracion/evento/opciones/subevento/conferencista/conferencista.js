var id_subevento="";

function inicializar(idioma, _id_subevento){
    id_subevento = _id_subevento;
    asignar_idioma(idioma);
    tbl_conferencistas_subevento();
}

function popup_mostrar_conferencistas(){
    dijit.byId('conferencista').show();
    tbl_conferencistas_evento();
}

function accion_conferencista(id_usuario, accion){
    dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo=' + accion + '_conferencista_subevento&txt_id_usuario=' + id_usuario + '&id_subevento=' + id_subevento,
            handleAs: 'json',
            load: function(xhr) {
                alert(xhr.mensaje);
                dijit.byId('conferencista').hide();
                tbl_conferencistas_subevento();
            }
    });
}

function tbl_conferencistas_evento() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("accion_conferencista('" + oRecord.getData("usuario.id_usuario") + "','registrar');","/websae/images/add.png");
    },
    formatConferencista = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = oRecord.getData("usuario.titulo.abreviatura") + " " + oRecord.getData("usuario.nombre") + " " + oRecord.getData("usuario.apellido");
    },
    fields = ["id_conferencista_evento", "usuario.id_usuario","usuario.nombre", "usuario.apellido", "usuario.titulo.abreviatura"],
    myColumnDefs = [
        {label:parseIdioma("conferencista"), formatter:formatConferencista, width:220},
        {label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=conferencistas_evento",fields,myColumnDefs,"tbl_conferencistas_evento");
}

function tbl_conferencistas_subevento() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("accion_conferencista('" + oRecord.getData("usuario.id_usuario") + "','eliminar');","/websae/images/unsupported.png");
    },
    formatConferencista = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = oRecord.getData("usuario.titulo.abreviatura") + " " + oRecord.getData("usuario.nombre") + " " + oRecord.getData("usuario.apellido");
    },
    fields = ["id_conferencista_evento", "usuario.id_usuario","usuario.nombre", "usuario.apellido", "usuario.titulo.abreviatura"],
    myColumnDefs = [
        {label:parseIdioma("conferencista"), formatter:formatConferencista, width:220},
        {label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=conferencistas_subevento&id_subevento="+id_subevento,fields,myColumnDefs,"tbl_conferencistas_subevento");
}