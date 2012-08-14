/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

dojo.addOnLoad(
  function(){
      cargar_faq();
  }
);

function cargar_faq(){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_mostrar_datos?tipo=mostrar_faq',
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.texto!=null || xhr.texto!="" )
                dijit.byId('txt_texto_editor').setValue(xhr.texto);
        }
    });
}

function modificar_faq(){
    dojo.byId('txt_texto').value = dijit.byId('txt_texto_editor').getValue(false);
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=actualizar_faq',
        form: 'frm_faq',
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                alert(xhr.mensaje);
                window.location.href = "/websae/administracion/evento/opciones/index.jsp";
            } else if (xhr.tipo == "ERROR") {
                alert(xhr.mensaje);
            }
        }
    });
}