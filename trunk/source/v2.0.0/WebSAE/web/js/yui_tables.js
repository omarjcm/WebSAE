function format_innerHTML(javascript, value){
    if (value.indexOf(".png")>0 || value.indexOf(".jpg")>0 || value.indexOf(".gif")>0)
        return "<div style='width:40px; margin:0 auto; text-align:center'><a href=\"javascript:" + javascript + "\"><img width=\"25px\" height=\"25px\" src=\"" + value + "\" /></a></div>";
    else if (javascript!="")
        return "<a href=\"javascript:" + javascript + "\">" + value + "</a>";
    else
        return value;
}

function tbl_generica(myServlet, fields, myColumnDefs, div_tbl, resultList){
    var html = "";
    if (parseVacio(resultList)=="") resultList="items";
    html += "<div id='yui_" + div_tbl + "' style='margin:0 auto 0 auto; min-height:130px;'></div>";
    html += "<div id='paging_" + div_tbl + "' style='text-align:center;'></div>";
    dojo.byId(div_tbl).innerHTML = html;

    YAHOO.util.Event.onDOMReady(function() {
        YAHOO.example.ClientPagination = function() {
            var myDataSource = new YAHOO.util.DataSource(myServlet);
            myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
            myDataSource.connMethodPost = true;
            myDataSource.responseSchema = {
                resultsList: resultList,
                fields: fields
            };

            var oConfigs = {
                paginator: new YAHOO.widget.Paginator({
                    containers: 'paging_'+div_tbl,
                    rowsPerPage: 5,
                    previousPageLinkLabel: "&lt;&lt; Anterior",
                    nextPageLinkLabel: "Siguiente &gt;&gt;",
                    template: "<strong>{CurrentPageReport}</strong> {PreviousPageLink} {PageLinks} {NextPageLink}"
                }),
                MSG_EMPTY: "No se ha encontrado ningún resultado.",
                MSG_LOADING: "Cargando los datos...",
                MSG_ERROR: "Error al cargar los datos."

            //,initialRequest: myParams
            };
            var myDataTable = new YAHOO.widget.DataTable("yui_" + div_tbl, myColumnDefs, myDataSource, oConfigs);

            return {
                oDS: myDataSource,
                oDT: myDataTable
            };
        }();
    });
}

function parseVacio(campo){
    if (campo == null || campo == "null" ||campo == "" || campo == undefined || campo=="undefined" || campo.length == 0) return "";
    return campo;
}