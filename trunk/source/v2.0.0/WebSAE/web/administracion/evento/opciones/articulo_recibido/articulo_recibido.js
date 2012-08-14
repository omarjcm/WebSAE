 var temas_existentes = "" || [];
 var cant_temas = 0;
 
 function inicializar(idioma, id_evento){
    asignar_idioma(idioma);
    tbl_articulos_recibidos();
    cargar_temas();
    obtener_estado_evaluacion(id_evento);
    obtener_fechas_convocatoria();
    asignar_fecha_actual();
    cargar_evaluadores();
}

function administrar_articulo(accion){
    var bandera=0;
    if (accion=="aprobar") {
        if(cant_temas>0) {
            bandera=1;
        } else {
            alert("Debe seleccionar al menos un tema.");
        }
    } else {
        if(parseVacio(dojo.byId("txt_observacion").value)!=""){
            bandera=1;
        } else {
            alert("Debe ingresar algún motivo.");
        }
    }

    if (fecha_actual<devuelve_fecha(fecha_max_resumen)){
        alert("No puede " + accion + " un resumen hasta después de la Fecha Máxima de Resumen "+ fecha_max_resumen+".");
        bandera=0;
    }
    if (fecha_actual>devuelve_fecha(fecha_max_presentacion)){
        alert("Ya no puede realizar ninguna acción para este resumen.");
        bandera=0;
    }
    if (bandera==1){
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_resumen',
            handleAs: 'json',
            form:'frm_articulo',
            load: function(xhr) {
                alert(xhr.mensaje);
                dijit.byId('div_aprobacion').hide();
                tbl_articulos_recibidos();
            }
        });
    }
}

function decision_articulo(accion){
    dojo.byId("accion_aprobacion").value=accion;
    dijit.byId('div_btn_ac').domNode.style.display = "block";
    if (accion=="rechazar"){
        dijit.byId('div_observacion').domNode.style.display = "block";
        dijit.byId('div_temas').domNode.style.display = "none";
    }else{
        dijit.byId('div_temas').domNode.style.display = "block";
        dijit.byId('div_observacion').domNode.style.display = "none";
    }
    temas_seleccionados();
}

function tbl_articulos_recibidos(){
    var formatAccion = function(elCell, oRecord, oColumn, sData) {
        if(oRecord.getData("estado")=="EC") elCell.innerHTML = format_innerHTML("popup_aprobacion('"+oRecord.getData("id")+"');","/websae/images/enconsulta_ico.png");
        if(oRecord.getData("estado")=="P") elCell.innerHTML = format_innerHTML("popup_asignar_evaluador('"+oRecord.getData("id")+"','"+oRecord.getData("titulo")+"');","/websae/images/evaluador_ico.png");
        if(oRecord.getData("estado")=="EV") elCell.innerHTML = format_innerHTML("popup_evaluaciones('"+oRecord.getData("id")+"');","/websae/images/filefind.png");
        if(oRecord.getData("estado")=="EE") elCell.innerHTML = format_innerHTML("javascript:alert('En Espera');","/websae/images/timer_ico.png");
        if(oRecord.getData("estado")=="A") elCell.innerHTML = format_innerHTML("popup_evaluaciones('"+oRecord.getData("id")+"','T');javascript:alert('Articulo Aprobado');","/websae/images/yes_ico.png");
        if(oRecord.getData("estado")=="RE") elCell.innerHTML = format_innerHTML("popup_evaluaciones('"+oRecord.getData("id")+"','T');javascript:alert('Articulo Rechazado');","/websae/images/no_ico.png");

    },
    formatEstado = function(elCell, oRecord, oColumn, sdata){
        var estado = oRecord.getData("estado");
        elCell.innerHTML = estado=="P"?"PENDIENTE":(estado=="A"?"APROBADO":(estado=="RE"?"RECHAZADO":(estado=="EC"?"EN CONSULTA":(estado=="EV"?"EVALUADO":(estado=="EE"?"EN ESPERA":"OTRO")))));
    }, formatArchivo = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("window.location.href='/websae/archivo/articulo/" + oRecord.getData("archivo.items[0].nombre")+"'","/websae/images/download.png");
    }, formatArticulo = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = format_innerHTML("popup_articulo('" + oRecord.getData("id") + "');", oRecord.getData("titulo"));
    },

    fields = ["id","titulo","resumen","estado","archivo.items[0].nombre","resumen"],
    myColumnDefs = [
        { label:parseIdioma("titulo"), formatter:formatArticulo, width:280, className:"link_tabla"},
        { label:"Estado", key:"estado", formatter:formatEstado, width:80},
        //{ key:"archivo.items[0].nombre", label:parseIdioma("articulo"), formatter:formatArchivo},
        { label:parseIdioma("accion"), formatter:formatAccion}
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=mostrar_articulos_recibidos",fields,myColumnDefs,"tbl_articulos_recibidos");
}

function popup_aprobacion(id_paper){
    dojo.byId("id_articulo_aprobacion").value=id_paper;
    dijit.byId('div_aprobacion').show();
    dijit.byId('div_btn_ac').domNode.style.display = "none";
    dijit.byId('div_observacion').domNode.style.display = "none";
    dijit.byId('div_temas').domNode.style.display = "none";
}

function popup_articulo(id_articulo){
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


function cargar_temas(){
    temas_existentes = "" || [];
    var html="";
    dojo.byId("div_tema").innerHTML = html;
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=temas_por_evento',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            if (xhr.items.length>0){
                html += "<div style='float:left; width:230px; margin:10px 0 20px 30px; text-align:left;'>";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "<input type='checkbox' style='width:auto; margin: 0 5px 0 0;' id='tema_" + xhr.items[i].id_tema + "' dojoType='dijit.form.CheckBox' value='" + xhr.items[i].id_tema + "' onclick='accion_tema(this.value)' />"+xhr.items[i].nombre+"<br />";
                    cola = cola + tmp;
                    tmp = "";
                    temas_existentes.push("tema_"+xhr.items[i].id_tema);
                }
                html += cola + "</div>";
            }else{
                html += "No hay temas...";
            }
            dojo.byId("div_tema").innerHTML = html;
            dojo.parser.parse(dojo.byId("div_tema"));
            
        }
    });
}

function accion_tema(id_tema){
    var accion;
    dojo.byId("tema_"+id_tema).checked?accion="agregar":accion="eliminar";
    dojo.byId("tema_"+id_tema).checked?cant_temas++:cant_temas--;
    dojo.xhrPost({
        url: '/websae/F_ce_administrar_detalles?tipo=' + accion + '_tema&id_tema='+id_tema,
        handleAs: 'json',
        load: function(xhr) {
        }
    });
}

function temas_seleccionados(){
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=articulo_asignado&id_articulo='+dojo.byId("id_articulo_aprobacion").value,
        handleAs: 'json',
        load: function(xhr) {
            for(var m=0;m<temas_existentes.length;m++){
                dijit.byId(temas_existentes[m]).attr("checked",false);
            }
            for(var i=0;i<xhr.tema_articulo.items.length;i++){
                dijit.byId("tema_"+xhr.tema_articulo.items[i].tema.id_tema).attr("checked","checked");
            }
            cant_temas=xhr.tema_articulo.items.length;
        }
    });
}

function parseVacio(campo){
    if (campo == null || campo == "null" ||campo == "" || campo == undefined || campo=="undefined" || campo.length == 0) return "";
    return campo;
}