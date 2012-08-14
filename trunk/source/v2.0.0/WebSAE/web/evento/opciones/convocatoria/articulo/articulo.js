var cant_temas=0;
var cant_autores=1;
var temas_existentes = "" || [];
var fecha_actual="";
var fecha_max_resumen="";
var fecha_max_presentacion="";
var fecha_max_evaluacion="";
var fecha_max_notificacion="";
var fecha_max_correccion="";

function inicializar(idioma, mostrar_autores){
    asignar_idioma(idioma);
    dojo.connect(dojo.byId('txt_clave'), "onkeydown", function(e) {
        key = e.keyCode;
        if (key == dojo.keys.ENTER) {
            validar();
            e.stopPropagation();
        }
    });
    dojo.connect(dojo.byId('email'), "onkeydown", function(e) {
        key = e.keyCode;
        if (key == dojo.keys.ENTER) {
            accion_autor("agregar",dojo.byId("email").value);
            e.stopPropagation();
        }
    });
    tbl_articulos();
    cargar_temas();
    dijit.byId('btn_agregar').domNode.style.display = "block";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    dijit.byId('btn_cancelar').domNode.style.display = "none";

    if (mostrar_autores) {
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_detalles?tipo=agregar_autor_convocatoria',
            handleAs: 'json',
            load: function(xhr) { 
                cargar_autores();
            }
        });
    }
    obtener_fechas_convocatoria();
    asignar_fecha_actual();
}

function accion_articulo(accion) {
    var estado_articulo = dojo.byId("estado_articulo").value;
    var bandera = false;
    if(parseVacio(fecha_max_resumen)!=""){
        if (accion=="subir"){
            if (fecha_actual<=devuelve_fecha(fecha_max_resumen)){
                bandera = true;
            }else{
                alert("La Fecha máxima para subir resumenes es hasta el " + fecha_max_resumen);
            }
        }else{
            if (fecha_actual<=devuelve_fecha(fecha_max_presentacion) || estado_articulo=="A"){
                bandera = true;
            //}else if (fecha_actual>devuelve_fecha(fecha_max_presentacion) && fecha_actual<=devuelve_fecha(fecha_max_evaluacion)){
            //    alert("No se puede realizar modificaciones mientras se encuentra en periodo de evaluación ["+fecha_max_presentacion+" - "+fecha_max_evaluacion+"].");
            }else if(fecha_actual<=devuelve_fecha(fecha_max_correccion)){
                bandera = true;
            }else{
                alert("Ya no puede realizar cambios en su paper. Fecha máxima de corrección fué hasta el " + fecha_max_correccion);
            }
        }
    }else{
        if (fecha_actual<=devuelve_fecha(fecha_max_presentacion) || estado_articulo=="A"){
            bandera = true;
        //}else if (fecha_actual>devuelve_fecha(fecha_max_presentacion) && fecha_actual<=devuelve_fecha(fecha_max_evaluacion)){
        //    alert("No se puede realizar modificaciones mientras se encuentra en periodo de evaluación ["+fecha_max_presentacion+" - "+fecha_max_evaluacion+"].");
        }else if(fecha_actual<=devuelve_fecha(fecha_max_correccion)){
            bandera = true;
        }else{
            alert("Ya no puede realizar cambios en su paper. Fecha máxima de corrección fué hasta el " + fecha_max_correccion);
        }
    }

    if (dijit.byId("frm_articulo").validate() && bandera) {
        if (cant_temas > 0) {
            if (parse_vacio(dojo.byId("txt_articulo2").value)!="") {
                if (cant_autores > 0){
                    dojo.xhrPost({
                        url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_articulo',
                        form: 'frm_articulo',
                        handleAs: 'json',
                        load: function(xhr) {
                            if (xhr.tipo == "OK") {
                                alert(xhr.mensaje);
                                tbl_articulos();
                                limpiar();
                            } else if (xhr.tipo == "ERROR") {
                                alert(xhr.mensaje);
                            }
                        }
                    });
                }else
                    alert(parseIdioma("ingrese autor"));
            }else
                alert(parseIdioma("subir archivo"));
        }else
            alert(parseIdioma("seleccionar tema"));
    }
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

function accion_autor(accion, email){
    dojo.byId("email").value = email;
    var autor_principal = dijit.byId("autor_principal").getValue()=="true"?"1":"0";
    if (dijit.byId("frm_autor").validate() || accion=="eliminar"){
        dojo.xhrPost({
            url: '/websae/F_ce_administrar_detalles?tipo=' + accion + '_autor&es_autor_principal='+autor_principal,
            form: 'frm_autor',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    if (accion=="agregar") cant_autores++; else cant_autores--;
                    cargar_autores();
                    dijit.byId("div_autor").hide();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function tbl_evaluaciones(num_evaluaciones, id_articulo){
    var fields, myColumnDefs;
    if(num_evaluaciones==1){
        fields = ["id","pregunta","respuesta_1"],
        myColumnDefs = [
            { key:"pregunta", label:parseIdioma("pregunta"), width:280},
            { key:"respuesta_1", label:"Evaluador1", width:100}
        ];
    }
    if(num_evaluaciones==2){
        fields = ["id","pregunta","respuesta_1","respuesta_2"],
        myColumnDefs = [
            { key:"pregunta", label:parseIdioma("pregunta"), width:280},
            { key:"respuesta_1", label:"Evaluador1", width:100},
            { key:"respuesta_2", label:"Evaluador2", width:100}
        ];
    }
    if(num_evaluaciones==3){
        fields = ["id","pregunta","respuesta_1","respuesta_2","respuesta_3"],
        myColumnDefs = [
            { key:"pregunta", label:parseIdioma("pregunta"), width:280},
            { key:"respuesta_1", label:"Evaluador1", width:100},
            { key:"respuesta_2", label:"Evaluador2", width:100},
            { key:"respuesta_3", label:"Evaluador3", width:100}
        ];
    }
    
    tbl_generica("/websae/F_ce_mostrar_datos?tipo=evaluacion_usuario_articulo&id_articulo="+id_articulo,fields,myColumnDefs,"tbl_evaluacion","evaluacion");
}

function tbl_articulos(){
    var formatArticulo = function(elCell, oRecord, oColumn, sData) {
        if (oRecord.getData("estado")!="R" && oRecord.getData("estado")!="A")
            elCell.innerHTML = format_innerHTML("popup_archivos('" + oRecord.getData("id")+"');",oRecord.getData("titulo"));
        else
            elCell.innerHTML = format_innerHTML("popup_archivos('" + oRecord.getData("id")+"');",oRecord.getData("titulo") + " <a href=\"javascript:popup_ver_evaluacion("+oRecord.getData("id")+");\" style='float:right;' >(ver evaluación)</a>");
    },
    formatAccion = function(elCell, oRecord, oColumn, sData) {
        if (oRecord.getData("estado")=="R")
            elCell.innerHTML = format_innerHTML("alert('Este artículo a sido rechazado, no puede ser editado.');", "/websae/images/accessories-text-editor.png");
        //else if (oRecord.getData("estado")=="A")
            //elCell.innerHTML = format_innerHTML("confirm('quieres?');","/websae/images/accessories-text-editor.png");
        //    elCell.innerHTML = format_innerHTML("alert('Este artículo ya ha sido Aprobado, no puede ser editado.');", "/websae/images/accessories-text-editor.png");
        else if (oRecord.getData("estado")=="EA")
            elCell.innerHTML = format_innerHTML("alert('Este artículo está siendo evaluado, no puede ser editado.');", "/websae/images/accessories-text-editor.png");
        else if (oRecord.getData("estado")=="EV")
            elCell.innerHTML = format_innerHTML("alert('Este artículo ha sido evaluado, esperar a su aprobación o rechazo.');", "/websae/images/accessories-text-editor.png");
        else
            elCell.innerHTML = format_innerHTML("cargar_articulo('"+oRecord.getData("id")+"','"+oRecord.getData("estado")+"');", "/websae/images/accessories-text-editor.png");
    },
    fields = ["id","titulo","estado"],
    myColumnDefs = [
    {
        label:parseIdioma("articulo"),
        key:"titulo",
        formatter:formatArticulo,
        width:330
    },

    {
        label:parseIdioma("accion"),
        formatter:formatAccion
    }
    ];
    
    tbl_generica("/websae/F_ce_mostrar_datos?tipo=mostrar_articulos",fields,myColumnDefs,"tbl_articulos");
}

function popup_ver_evaluacion(id_articulo){
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=evaluacion_usuario_articulo&id_articulo='+id_articulo,
        handleAs: 'json',
        load: function(xhr) {
            var cant_evaluadores = 0;
            if (xhr.evaluacion[0].respuesta_2==undefined) cant_evaluadores=1;
            else if (xhr.evaluacion[0].respuesta_3==undefined) cant_evaluadores=2;
            else cant_evaluadores=3;
            tbl_evaluaciones(cant_evaluadores, id_articulo);
            dijit.byId("dlg_evaluacion").show();
        }
    });
}

function popup_archivos(id_articulo){
    dijit.byId("div_archivos").show();
    var formatArchivo = function(elCell, oRecord, oColumn, sData) {
        elCell.innerHTML = "<a target='_blank' href='/websae/archivo/articulo/" + oRecord.getData("nombre")+"'>"+oRecord.getData("nombre")+"</a>";
        //elCell.innerHTML = "<a href=\"javascript:document.execCommand('SaveAs','1','/websae/archivo/articulo/" + oRecord.getData("nombre")+ "');\">" + oRecord.getData("nombre") + "</a>";
    },
    fields = ["id","nombre","fecha"],
    myColumnDefs = [
    { label:parseIdioma("archivo"), key:"nombre", formatter:formatArchivo, width:490 },
    { label:parseIdioma("fecha"), key:"fecha", width:80 }
    ];

    tbl_generica("/websae/F_ce_mostrar_datos?tipo=mostrar_archivos_por_articulo&id_articulo="+id_articulo,fields,myColumnDefs,"tbl_archivos");
}

function cargar_articulo(id_articulo, estado_articulo){
    dijit.byId('btn_agregar').domNode.style.display = "none";
    dijit.byId('btn_modificar').domNode.style.display = "block";
    dijit.byId('btn_cancelar').domNode.style.display = "block";
    dojo.byId("estado_articulo").value = estado_articulo;
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_articulo&id_articulo='+id_articulo,
        handleAs: 'json',
        load: function(xhr) {
            //if(parseVacio(xhr.titulo)!=""){
                //if (xhr.status=="R") alert("No puede modificar el articulo xq esta como rechazado");
                //if (xhr.status=="A") alert("No puede modificar el articulo ya ha sido aprobado");
                dojo.byId("id_articulo").value=xhr.id;
                dojo.byId("txt_nombre_articulo").value=xhr.titulo;
                dojo.byId("txt_resumen").value=xhr.resumen;
                dojo.byId("txt_articulo2").value=xhr.archivo.items[0].nombre;
                for(var m=0;m<temas_existentes.length;m++){
                    dijit.byId(temas_existentes[m]).attr("checked",false);
                }

                for(var i=0;i<xhr.tema_articulo.items.length;i++){
                    dijit.byId("tema_"+xhr.tema_articulo.items[i].tema.id_tema).attr("checked","checked");
                }
                cant_temas=xhr.tema_articulo.items.length;
                var html = "", tmp="";
                cant_autores=xhr.autor_articulo.items.length;
                for(var j=0;j<xhr.autor_articulo.items.length;j++){
                    if (xhr.autor_articulo.items[j].autor_principal)
                        tmp += "<label style='width:auto; margin: 0 5px 0 0; '> "+xhr.autor_articulo.items[j].usuario.nombre+" " + xhr.autor_articulo.items[j].usuario.apellido + " ( Autor Principal )</label> <a href='javascript:accion_autor(\"eliminar\",\""+xhr.autor_articulo.items[j].usuario.email+"\");'>-eliminar-</a><br />";
                    else
                        tmp += "<label style='width:auto; margin: 0 5px 0 0; '> "+xhr.autor_articulo.items[j].usuario.nombre+" " + xhr.autor_articulo.items[j].usuario.apellido  + "</label> <a href='javascript:accion_autor(\"eliminar\",\""+xhr.autor_articulo.items[j].usuario.email+"\");'>-eliminar-</a><br />";
                    html += tmp;
                    tmp = "";
                }
                dojo.byId("div_autores").innerHTML = html;
            //}else{
            //    alert("Ud. no puede modificar este artítulo porque no está asignado como autor principal.");
            //    limpiar();
            //}

        }
    });
}


function cargar_temas(){
    temas_existentes = "" || [];
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=temas_por_evento',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            if (xhr.items.length>0){
                html += "<label style='float:left; width:130px;'>Tema(s):</label>";
                html += "<div style='float:left; width:350px;'>";
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
            dojo.byId("div_temas").innerHTML = html;
            dojo.parser.parse(dojo.byId("div_temas"));
        }
    });
}

function cargar_autores(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=mostrar_autores',
        handleAs: 'json',
        load: function(xhr) {
            var cola ="";
            var tmp = "";
            if (xhr.items.length>0){
                for (var i=0; i<xhr.items.length; i++) {
                    if (xhr.items[i].autor_principal)
                        tmp += "<label style='width:auto; margin: 0 5px 0 0; '> "+xhr.items[i].usuario.nombre+" " + xhr.items[i].usuario.apellido + " ( Autor Principal )</label> <a href='javascript:accion_autor(\"eliminar\",\""+xhr.items[i].usuario.email+"\");'>-eliminar-</a><br />";
                    else
                        tmp += "<label style='width:auto; margin: 0 5px 0 0; '> "+xhr.items[i].usuario.nombre+" " + xhr.items[i].usuario.apellido + "</label> <a href='javascript:accion_autor(\"eliminar\",\""+xhr.items[i].usuario.email+"\");'>-eliminar-</a><br />";
                    cola = cola + tmp;
                    tmp = "";
                }
                html += cola;
            }else{
                html += "Ingrese a algún autor...";
            }
            dojo.byId("div_autores").innerHTML = html;
        }
    });
}

function popup_autores(){
    dijit.byId("div_autor").show();
    dijit.byId("frm_autor").reset();
}

function validar() {
    if (dijit.byId("form_sesion").validate()) {
        dojo.xhrPost({
            url: '/websae/F_autentificar_usuario?accion=autentificar',
            form: 'form_sesion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    window.location.reload();
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function parse_vacio(campo){
    if (campo == null || campo == "" || campo == "undefined"){
        return "";
    }else{
        return campo;
    }
}

function limpiar(){
    dojo.byId("frm_articulo").reset();
    dojo.byId("txt_articulo2").value = "";
    dijit.byId('btn_agregar').domNode.style.display = "block";
    dijit.byId('btn_modificar').domNode.style.display = "none";
    dijit.byId('btn_cancelar').domNode.style.display = "none";
    cant_autores=1;
    cant_temas=0;
    for(var m=0;m<temas_existentes.length;m++){
        dijit.byId(temas_existentes[m]).attr("checked",false);
    }
    dojo.xhrPost({
            url: '/websae/F_ce_administrar_detalles?tipo=agregar_autor_convocatoria',
            handleAs: 'json',
            load: function(xhr) {
                cargar_autores();
            }
        });
}

function parseVacio(campo){
    if (campo == null || campo == "null" ||campo == "" || campo == undefined || campo=="undefined" || campo.length == 0) return "";
    return campo;
}

function obtener_fechas_convocatoria(){
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=buscar_convocatoria',
        handleAs: 'json',
        load: function(xhr) {
            fecha_max_resumen=xhr.fecha_max_resumen;
            fecha_max_presentacion=xhr.fecha_max_presentacion;
            fecha_max_evaluacion=xhr.fecha_max_evaluacion;
            fecha_max_notificacion=xhr.fecha_max_notificacion;
            fecha_max_correccion=xhr.fecha_max_correccion;
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