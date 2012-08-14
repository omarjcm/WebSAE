dojo.addOnLoad(
    function() {
        dojo.connect(dojo.byId('txt_nombre_apellido'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                cargar_usuarios_sagi();
                e.stopPropagation();
            }
        });
        cargar_usuarios_sagi();
    }
);

function buscar_por_email(email) {
    dojo.xhrPost({
        url: '../F_mostrar_usuario?tipo=sagi_por_email&email='+email,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK")  {
                registrar_usuario(xhr);
            } else if (xhr.tipo == "ERROR")  {
                alert(xhr.mensaje);
            }
        }
    });
}

function registrar_usuario(xhr) {
    var params = {"txt_correo":xhr.usuario.email, "txt_clave":xhr.usuario.clave,
                  "txt_nombres":xhr.usuario.nombre, "txt_apellidos":xhr.usuario.apellido,
                  "genero":xhr.usuario.genero, "txt_direccion":xhr.usuario.direccion,
                  "txt_telefono":xhr.usuario.telefono, "txt_celular":xhr.usuario.celular,
                  "txt_nacimiento":xhr.usuario.fecha_nacimiento, "cmb_ciudad":xhr.usuario.ciudad.id_ciudad,
                  "cmb_titulo":xhr.usuario.titulo.id_titulo, "cmb_cargo":xhr.usuario.cargo.id_cargo,
                  "cmb_empresa":xhr.usuario.empresa.id_empresa};

    dojo.xhrPost({
        url: '/websae/F_su_administrar_objetos?tipo=registrar_usuario',
        contentType: "application/x-www-form-urlencoded; charset=iso-8859-1",
        content: params,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                alert(xhr.mensaje);
            } else if (xhr.tipo == "ERROR") {
                alert(xhr.mensaje);
            }
        }
    });
}

function seleccionar_usuario(email) {
    buscar_por_email(email);
}

function visualizar(id) {
    if (dojo.byId(id).style.visibility == "visible") {
        dojo.byId(id).style.visibility = "hidden";
        dojo.byId(id).style.display = "none";
        //dojo.byId(more).innerHTML = "[-]";
    } else {
        dojo.byId(id).style.visibility = "visible";
        dojo.byId(id).style.display = "block";
        //dojo.byId(more).innerHTML = "[+]";
    }
}


function cargar_usuarios_sagi() {
    YAHOO.util.Event.onDOMReady(function() {
        YAHOO.example.ClientPagination = function() {
            var formatUrl = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = "<a href='#' onclick=\"seleccionar_usuario('"+oRecord.getData("email")+"');\"><img alt=\"ver\" src=\"/websae/images/add.png\" title=\"Agregar Usuario\" /></a>";
            };
            var myColumnDefs = [
                {key:"email", label:"Email"},
                {key:"apellido", label:"Apellido"},
                {key:"nombre", label:"Nombre"},
                {label:"Registrar", formatter:formatUrl }
            ];

            var myDataSource = new YAHOO.util.DataSource("../F_mostrar_usuario");
            myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
            myDataSource.connMethodPost = true;
            myDataSource.responseSchema = {
                resultsList: "records", fields: ["email","apellido","nombre"]
            };

            var oConfigs = {
                sortedBy:{key:"apellido", dir:"asc"},
                paginator: new YAHOO.widget.Paginator({
                    rowsPerPage: 5
                }),
                initialRequest: "tipo=sagi_por_nombre_apellido&results=20&nombre_apellido="+dojo.byId("txt_nombre_apellido").value
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
                        backgroundColor:{to:origColor, from:pulseColor}, duration:2});
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
