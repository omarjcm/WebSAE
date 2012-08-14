dojo.addOnLoad(
    function() {
        dojo.connect(dojo.byId('txt_correo'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                cargar_usuario();
                e.stopPropagation();
            }
        });
        /*dojo.connect(dojo.byId('txt_correo'), "onchange", function(e) {
            key = e.keyCode;
            cargar_usuario();
        });*/
        dojo.connect(dojo.byId('txt_nombre_apellido'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                buscar_usuario(dojo.byId("buscar_tipo").value);
                e.stopPropagation();
            }
        });
    }
);

function seleccionar_usuario(email) {
    dijit.byId("div_buscar_usuario").hide();
    dojo.byId("txt_correo").value = email;
    cargar_usuario();
}

function seleccionar_usuario_sin_accion(email) {
    dijit.byId("div_buscar_usuario").hide();
    dojo.byId("txt_correo").value = email;
}

function buscar_usuario(tipo) {
    if (dijit.byId("buscar_nombre_apellido").validate()) {
        var formatAccion = function(elCell, oRecord, oColumn, sData) {
            if(tipo!="evaluador_usuario")
                elCell.innerHTML = format_innerHTML("seleccionar_usuario('" + oRecord.getData("email")+"');","/websae/images/filefind.png");
            else
                elCell.innerHTML = format_innerHTML("seleccionar_usuario_sin_accion('" + oRecord.getData("email")+"');","/websae/images/filefind.png");
        },
        fields = ["email","apellido","nombre"],
        myColumnDefs = [
            { label:parseIdioma("correo"), key:"email", width:170 },
            { label:parseIdioma("Apellido"), key:"apellido", width:70 },
            { label:parseIdioma("Nombre"), key:"nombre", width:70 },
            { label:parseIdioma("Seleccionar"), formatter:formatAccion }];
            
        if(tipo=="buscar_usuario")
            tbl_generica("/websae/administracion/usuario/F_mostrar_usuario?tipo=por_nombre_apellido&results=10&nombre_apellido="+dojo.byId("buscar_nombre_apellido").value,fields,myColumnDefs,"tbl_resultados");
        if(tipo=="rol_usuario")
            tbl_generica("/websae/administracion/usuario/F_mostrar_usuario?tipo=por_nombre_apellido&results=10&nombre_apellido="+dojo.byId("buscar_nombre_apellido").value,fields,myColumnDefs,"tbl_resultados");
        if(tipo=="conferencista_usuario")
            tbl_generica("/websae/administracion/usuario/F_mostrar_usuario?tipo=buscar_conferencista_por_nombre_apellido&results=10&nombre_apellido=" + dojo.byId("buscar_nombre_apellido").value,fields,myColumnDefs,"tbl_resultados");
        if(tipo=="evaluador_usuario")
            tbl_generica("/websae/administracion/usuario/F_mostrar_usuario?tipo=por_nombre_apellido&results=10&nombre_apellido="+dojo.byId("buscar_nombre_apellido").value,fields,myColumnDefs,"tbl_resultados");
    }
}