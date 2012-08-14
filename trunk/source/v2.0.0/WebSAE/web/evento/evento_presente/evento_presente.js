 function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_eventos_presentes();
}

function tbl_eventos_presentes(){
    var formatEvento = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("window.location='/websae/evento/opciones/index.jsp?id_evento=" + oRecord.getData("id_evento")+"'",oRecord.getData("nombre"));
    },
    formatFechas = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = parseIdioma("del") + " " + oRecord.getData("fecha_inicio_long") + "<br />" +
                           parseIdioma("al") + " " + oRecord.getData("fecha_fin_long");
    },
    fields = ["id_evento","nombre","fecha_inicio_long","fecha_fin_long"],
    myColumnDefs = [
        {key:"nombre",label:parseIdioma("nombre"),formatter:formatEvento, width:330, className:"link_tabla"},
        {label:parseIdioma("fechas"),formatter:formatFechas, width:190}
    ];
    
    tbl_generica("/websae/F_mostrar_datos?tipo=mostrar_eventos_por_realizar",fields,myColumnDefs,"tbl_eventos_presentes");
}

function mostrar_tabla_eventos_presente() {
    YAHOO.util.Event.onDOMReady(function() {
        YAHOO.example.ClientPagination = function() {
            var formatEvento = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = "<a href='/websae/evento/opciones/index.jsp?id_evento=" + oRecord.getData("id_evento") + "'>" + oRecord.getData("nombre") + "</a>";
            };
            var formatFechas = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML =  parseIdioma("del") + " " + oRecord.getData("fecha_inicio_long") + "<br />" +
                                    parseIdioma("al") + " " + oRecord.getData("fecha_fin_long") + "<br />";
            }
            var myColumnDefs = [
            {label:parseIdioma("nombre"),formatter:formatEvento,key:"nombre"},
            {label:parseIdioma("fechas"),formatter:formatFechas}];

            var myDataSource = new YAHOO.util.DataSource("/websae/F_mostrar_datos");
            myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
            myDataSource.connMethodPost = true;
            myDataSource.responseSchema = {
                resultsList: "items",
                fields: ["id_evento","nombre","fecha_inicio_long","fecha_fin_long"]
            };

            var oConfigs = {
                paginator: new YAHOO.widget.Paginator({
                    rowsPerPage: 5
                }),
                initialRequest: "tipo=mostrar_eventos_por_realizar"
            };
            var myDataTable = new YAHOO.widget.DataTable("paginated", myColumnDefs, myDataSource, oConfigs);
            myDataTable.subscribe("rowClickEvent", myDataTable.onEventSelectRow);
            myDataTable.subscribe("cellDblclickEvent", myDataTable.onEventShowCellEditor);
            myDataTable.subscribe("editorBlurEvent", myDataTable.onEventSaveCellEditor);

            var onCellEdit = function(oArgs) {
                var elCell = oArgs.editor.getTdEl();
                var oOldData = oArgs.oldData;
                var oNewData = oArgs.newData;

                // Grab the row el and the 2 colors
                var elRow = this.getTrEl(elCell);
                var origColor = YAHOO.util.Dom.getStyle(elRow.cells[0], "backgroundColor");
                var pulseColor = "#ff0";

                // Create a temp anim instance that nulls out when anim is complete
                var rowColorAnim = new YAHOO.util.ColorAnim(elRow.cells, {
                    backgroundColor:{
                        to:origColor,
                        from:pulseColor
                    },
                    duration:2
                });
                var onComplete = function() {
                    rowColorAnim = null;
                    YAHOO.util.Dom.setStyle(elRow.cells, "backgroundColor", "");
                }
                rowColorAnim.onComplete.subscribe(onComplete);
                rowColorAnim.animate();
            }
            myDataTable.subscribe("editorSaveEvent", onCellEdit);

            return {
                oDS: myDataSource,
                oDT: myDataTable
            };
        }();
    });
}

