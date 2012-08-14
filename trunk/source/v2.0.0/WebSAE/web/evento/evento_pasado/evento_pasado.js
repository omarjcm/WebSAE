 function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_eventos_pasados();
}

function tbl_eventos_pasados(){
    var formatEvento = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("window.location='/websae/evento/opciones/index.jsp?id_evento=" + oRecord.getData("id_evento")+"'",oRecord.getData("nombre"));
    },
    formatFechas = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = parseIdioma("del") + " " + oRecord.getData("fecha_inicio_long") + "<br />" +
                           parseIdioma("al") + " " + oRecord.getData("fecha_fin_long") + "<br />";
    },
    fields = ["id_evento","nombre","fecha_inicio_long","fecha_fin_long"],
    myColumnDefs = [
        { key:"nombre", label:parseIdioma("nombre"), formatter:formatEvento, width:330, className:"link_tabla"},
        { label:parseIdioma("fechas"), formatter:formatFechas, width:190}];

    tbl_generica("/websae/F_mostrar_datos?tipo=mostrar_eventos_pasados",fields,myColumnDefs,"tbl_eventos_pasados");
}
