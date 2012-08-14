 function inicializar(idioma){
    tbl_registros();
}

function administrar_evaluacion(){
    
}

function tbl_registros(){
    var formatAccion = function(elCell, oRecord, oColumn, sData) {
        if (oRecord.getData("estado")=="P")
            elCell.innerHTML =  format_innerHTML("popup_registro('"+oRecord.getData("id_registro")+"');","/websae/images/pagado.png");
        else
            elCell.innerHTML =  format_innerHTML("popup_pago('"+oRecord.getData("id_registro")+"');","/websae/images/precio_descuento.png");
    },formatValor = function(elCell, oRecord, oColumn, sData) {
        var descuento = 0;
        if(parseVacio(oRecord.getData("descuento.porcentaje_descuento"))!="") descuento = oRecord.getData("descuento.porcentaje_descuento");
        elCell.innerHTML = dojo.currency.format(parseFloat(oRecord.getData("precio.precio")) - parseFloat(parseFloat(oRecord.getData("precio.precio")*parseFloat(descuento))/100), {currency: "USD"});
        //elCell.innerHTML = "$" + parseFloat(oRecord.getData("precio.precio") - parseFloat(parseFloat(oRecord.getData("precio.precio"))*descuento/100));
    },
    fields = ["id_registro","evento_nombre","usuario_nombre_apellido","precio.precio","descuento.porcentaje_descuento","estado"],
    myColumnDefs = [
        {key:"usuario_nombre_apellido", label:"Usuario", width:110},
        {key:"evento_nombre", label:"Evento/SubEvento", width:280},
        {key:"valor", formatter:formatValor,width:70},
        //{key:"tipoRegistro", label:"Estado", width:70},
        {label:"Acción", formatter:formatAccion, width:40}
    ];

    tbl_generica("/websae/F_ae_mostrar_datos?tipo=usuarios_pendientes",fields,myColumnDefs,"tbl_registros");
}

function tbl_registros2(id_articulo){
    YAHOO.example.Data = {
        registros: [
            {id:1, usuario: "Guillermo Pizarro", evento:"Evento de Prueba N", valor:"$45", tipoRegistro:"P"},
            {id:2, usuario: "Guillermo Pizarro", evento:"Cena de Navidad", valor:"$25", tipoRegistro:"P"},
            {id:3, usuario: "Rafael Rivadeneira C.", evento:"Evento de Prueba N", valor:"$45", tipoRegistro:"P"},
            {id:4, usuario: "Rafael Rivadeneira C.", evento:"Cena de Navidad", valor:"$20", tipoRegistro:"R"},
            {id:5, usuario: "Rafael Rivadeneira C.", evento:"Despedida de Fin de Año", valor:"$25", tipoRegistro:"R"}
        ]
    }
    var formatAccion = function(elCell, oRecord, oColumn, sData) {
        if (oRecord.getData("tipoRegistro")=="P")
            elCell.innerHTML =  format_innerHTML("popup_registro('"+oRecord.getData("id")+"')","/websae/images/pagado.png");
        else
            elCell.innerHTML =  format_innerHTML("popup_pago('"+oRecord.getData("id")+"')","/websae/images/precio_descuento.png");
    }
    YAHOO.example.Basic = function() {
        var myColumnDefs = [
            {key:"usuario", label:"Usuario", width:130},
            {key:"evento", label:"Evento/SubEvento", width:300},
            {key:"valor", label:"Valor", width:30},
            //{key:"tipoRegistro", label:"Estado", width:70},
            {label:"Acción", formatter:formatAccion, width:40}
        ];

        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.registros);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["id","usuario","evento","valor","tipoRegistro"]
        };

        var myDataTable = new YAHOO.widget.DataTable("tbl_registros",
                myColumnDefs, myDataSource, {caption:"Registros"});

        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
}