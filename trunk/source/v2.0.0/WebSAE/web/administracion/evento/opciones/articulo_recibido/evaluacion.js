function administrar_evaluacion(accion){
    dojo.xhrPost({
        url: '/websae/F_ce_administrar_objetos?tipo='+accion+'_articulo&id_articulo='+dojo.byId("id_articulo_evaluacion").value,
        handleAs: 'json',
        load: function(xhr) {
           alert(xhr.mensaje);
           dijit.byId("dlg_evaluacion").hide();
           tbl_articulos_recibidos();
        }
    });
}
 
function popup_evaluaciones(id_articulo, lectura){
    dojo.byId("id_articulo_evaluacion").value=id_articulo;
    dojo.xhrPost({
        url: '/websae/F_ce_mostrar_datos?tipo=evaluacion_total_articulo&id_articulo='+id_articulo,
        handleAs: 'json',
        load: function(xhr) {
            var cant_evaluadores = 0;
            if (xhr.evaluacion[0].respuesta_2==undefined) cant_evaluadores=1;
            else if (xhr.evaluacion[0].respuesta_3==undefined) cant_evaluadores=2;
            else cant_evaluadores=3;
            tbl_evaluaciones(cant_evaluadores, id_articulo);
            dijit.byId("dlg_evaluacion").show();
            if(lectura=="T") {
                dojo.byId("botones_aprobacion").style.visibility = "hidden";
            }else{
                dojo.byId("botones_aprobacion").style.visibility = "visible";
            }
        }
    });
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
    //tbl_generica("/websae/F_ce_mostrar_datos?tipo=evaluacion_total_articulo&id_articulo="+dojo.byId("id_articulo").value,fields,myColumnDefs,"tbl_evaluacion");
    tbl_generica("/websae/F_ce_mostrar_datos?tipo=evaluacion_total_articulo&id_articulo="+id_articulo,fields,myColumnDefs,"tbl_evaluacion","evaluacion");
}