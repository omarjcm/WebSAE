function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_organizadores();
    cargar_organizadores();
    cargar_tipo_empresa();
}

function accion_organizador(accion, _id_grupo_investigacion, _id_empresa, tipo, id_organizador){
    var id_grupo="";
    var id_empresa="";
    
    if (tipo=="G"){
        if (_id_grupo_investigacion==""){
            id_grupo=dojo.byId("cmb_organizadores").value;
        }else{
            id_grupo=_id_grupo_investigacion;
        }
        dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo=' + accion + '_organizador&txt_id_grupo_investigacion=' + id_grupo+'&txt_tipo='+tipo+'&id_organizador='+id_organizador,
            handleAs: 'json',
            load: function(xhr) {
                alert(xhr.mensaje);
                tbl_organizadores();
            }
        });
    }else{
        if (_id_empresa==""){
            id_empresa=dojo.byId("cmb_empresa").value;
        }else{
            id_empresa=_id_empresa;
        }
        if (id_empresa!=""){
            dojo.xhrPost({
            url: '/websae/administracion/F_ae_administrar_objetos?tipo=' + accion + '_organizador&id_empresa='+id_empresa+'&txt_tipo='+tipo+'&id_organizador='+id_organizador,
            handleAs: 'json',
            load: function(xhr) {
                alert(xhr.mensaje);
                tbl_organizadores();
            }
    });
        }else{
            alert("Seleccione una Empresa.");
        }
    }
}

function tbl_organizadores() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("accion_organizador('eliminar','" + oRecord.getData("id_organizador") + "','" + oRecord.getData("id_organizador") + "','" + oRecord.getData("tipo") + "','" + oRecord.getData("id_organizador") + "');","/websae/images/unsupported.png");
    },
    fields = ["id_organizador", "nombre","tipo"],
    myColumnDefs = [
        { key:"nombre", label:parseIdioma("organizador"), width:260},
        { label:parseIdioma("accion"), formatter:formatUrl }
    ];

    tbl_generica("/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_organizadores_evento",fields,myColumnDefs,"tbl_organizadores");
}

function cargar_organizadores() {
    dojo.xhrGet({
		url: "/websae/F_gi_mostrar_datos?tipo=grupos_investigacion",
		handleAs: "json",
		load: function(xhr) {
            var cola ="";
            var tmp;
            cola = "<select name=\"cmb_organizadores\" id=\"cmb_organizadores\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" >";
            for (var i=0; i<xhr.items.length; i++) {
                tmp = "<option value=\"" + xhr.items[i].id_grupo_investigacion + "\">" + xhr.items[i].nombre + "</option>";
                cola = cola + tmp;
                tmp = "";
            }
            cola = cola + "</select>";
            dojo.byId("div_organizadores").innerHTML = cola;
            dojo.parser.parse(dojo.byId("div_organizadores"));
        }
	});
}

function cargar_tipo_empresa() {
    dojo.xhrGet({
		url: "/websae/F_mostrar_datos?tipo=mostrar_tipos_empresas",
		handleAs: "json",
		load: function(xhr) {
            var cola ="";
            var tmp;
            cola = "<select name=\"cmb_tipo_empresa\" id=\"cmb_tipo_empresa\" dojoType=\"dijit.form.FilteringSelect\" onchange=\"cargar_empresa()\" required=\"true\" autoComplete=\"true\" value=\" \" >";
            tmp = "<option value=\"-1\"></option>";
            cola = cola + tmp;
            for (var i=0; i<xhr.items.length; i++) {
                tmp = "<option value=\"" + xhr.items[i].id_tipo_empresa + "\">" + xhr.items[i].nombre + "</option>";
                cola = cola + tmp;
                tmp = "";
            }
            cola = cola + "</select>";
            dojo.byId("div_tipo_empresa").innerHTML = cola;
            dojo.parser.parse(dojo.byId("div_tipo_empresa"));
        }
	});
}

function cargar_empresa() {
    dojo.xhrGet({
		url: "/websae/F_mostrar_datos?tipo=mostrar_empresas&id_tipo_empresa="+dojo.byId("cmb_tipo_empresa").value,
		handleAs: "json",
		load: function(xhr) {
            var cola ="";
            var tmp;
            cola = "<select name=\"cmb_empresa\" id=\"cmb_empresa\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" >";
            for (var i=0; i<xhr.items.length; i++) {
                tmp = "<option value=\"" + xhr.items[i].id_empresa + "\">" + xhr.items[i].nombre + "</option>";
                cola = cola + tmp;
                tmp = "";
            }
            cola = cola + "</select>";
            dojo.byId("div_empresa").innerHTML = cola;
            dojo.parser.parse(dojo.byId("div_empresa"));
        }
	});
}

/* Manejo de TABS mediante DOJO */
dojo.addOnLoad(function(){
    var tc = new dijit.layout.TabContainer({
        style:"left:50px;width:80%;height:160px",
        selected:true
    },
        "tabs"
    );

    var tab_info1 = new dijit.layout.ContentPane({
        title:"Grupo Investigación"
    },
    "tab_info1"
    );

    var tab_info2 = new dijit.layout.ContentPane({
        title:"Empresa/Universidad"
    },
    "tab_info2"
    );

    var tb = dijit.byId("tabs");
    tb.addChild(tab_info1);
    tb.addChild(tab_info2);

    tb.startup();
});