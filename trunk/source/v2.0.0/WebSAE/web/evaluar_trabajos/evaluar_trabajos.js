var fecha_max_evaluacion="";
var fecha_actual="";

function inicializar(idioma){
    asignar_idioma(idioma);
    tbl_articulos();
    obtener_fechas_convocatoria();
    asignar_fecha_actual();
}
 
function tbl_articulos(){
    var formatAutores = function(elCell, oRecord, oColumn, sData) {
        var autores="";
        var autor = oRecord.getData("autor_articulo").items;
        for(var i=0;i<autor.length;i++){
            autores += autor[i].usuario.nombre + " " + autor[i].usuario.apellido;
            if (i<autor.length-1) autores += "; ";
        }
        elCell.innerHTML = autores;
    },
    formatArticulo = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("cargar_articulo('" + oRecord.getData("id") + "');", oRecord.getData("titulo"));
    },
    formatAccion = function(elCell, oRecord, oColumn, sData) {
        if(oRecord.getData("estado")=="EV"){
            elCell.innerHTML = "<img src=\"/websae/images/yes_ico.png\" height=\"25px\" alt=\"\" />";
        }else{
            elCell.innerHTML = "<a id=\"evaluacion\" href=\"evaluacion.jsp?id_articulo="+oRecord.getData("id")+"\" target=\"_blank\" onclick=\"window.open(this.href, this.target, 'width=480,height=580,scrollbars=yes'); return false;\"><img src=\"/websae/images/evaluacion.jpg\" height=\"25px\" alt=\"\" /></a>";
        }
    },
    fields = ["id","autor_articulo","titulo","estado"],
    myColumnDefs = [
        { label:parseIdioma("autor(es)"),formatter:formatAutores,width:120},
        { label:parseIdioma("titulo"),key:"titulo",formatter:formatArticulo, width:250, className:"link_tabla"},
        { label:parseIdioma("accion"),formatter:formatAccion}
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=articulos_evaluador",fields,myColumnDefs,"tbl_papers");
}

function cargar_articulo(id_articulo){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_articulo_evaluacion&id_articulo='+id_articulo,
        handleAs: 'json',
        load: function(xhr) {
            dijit.byId('div_articulo').show();
            dojo.byId("paper_evento").innerHTML = xhr.evento.nombre_es;
            dojo.byId("paper_titulo").innerHTML = xhr.titulo;
            html="";
            for(var i=0;i<xhr.autor_articulo.items.length;i++){
                html+=xhr.autor_articulo.items[i].usuario.nombre + " " + xhr.autor_articulo.items[i].usuario.apellido +  "<br />";
            }
            dojo.byId("paper_autor").innerHTML = html;
            dojo.byId("paper_resumen").innerHTML = xhr.resumen;
            html="";
            for(var j=0;j<xhr.tema_articulo.items.length;j++){
                html+=xhr.tema_articulo.items[j].tema.nombre+"<br />";
            }
            dojo.byId("paper_tema").innerHTML = html;
            dojo.byId("paper_archivo").innerHTML = "<a title='Clic Derecho, Guardar Enlace Como...' style='text-decoration:underline' target='_blank' href='/websae/archivo/articulo/" + xhr.archivo.items[0].nombre + "'>Descargar</a>";
        }
    });
}

function obtener_fechas_convocatoria(){
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_convocatoria',
        handleAs: 'json',
        load: function(xhr) {
            fecha_max_evaluacion=xhr.fecha_max_evaluacion;
        }
    });
}

function asignar_fecha_actual(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_fecha_actual',
        handleAs: 'json',
        load: function(xhr) {
            fecha_actual = devuelve_fecha(xhr.fecha_actual);
        }
    });
}

function devuelve_fecha(fecha){
    if (fecha=="") return "";
    fecha = fecha.replace(/[-]/g, "/");
    fecha = new Date(fecha);
    return fecha
} 