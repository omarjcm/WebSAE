dojo.addOnLoad(
    function() {
        dojo.connect(dojo.byId('txt_correo'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                recuperar_contrasena();
                e.stopPropagation();
            }
        });
    }
);


function recuperar_contrasena() {
    if (dijit.byId("recuperacion").validate()) {
        dojo.xhrPost({
            url: '/websae/recordar_clave/F_recordar_clave',
            form: 'recuperacion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                    dojo.byId("txt_correo").value="";
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}
