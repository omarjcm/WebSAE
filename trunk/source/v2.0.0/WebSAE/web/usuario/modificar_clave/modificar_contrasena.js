function modificar_contrasena() {
    if (dijit.byId("modificar").validate()) {
        dojo.xhrPost({
            url: 'F_modificar_clave',
            form: 'modificar',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    alert(xhr.mensaje);
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
                dijit.byId("modificar").reset();
            }
        });
    }
}
function confirmPassword(value, constraints) {
    var isValid = false;
    if(constraints && constraints.other) {
        var otherInput =  dijit.byId(constraints.other);
        if(otherInput) {
            var otherValue = otherInput.value;
            isValid = (value == otherValue);
        }
    }
    return isValid;
}
