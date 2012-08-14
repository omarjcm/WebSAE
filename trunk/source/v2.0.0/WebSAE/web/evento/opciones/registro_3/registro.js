var total_pagar=0;

dojo.addOnLoad(
    function() {
        dojo.connect(dojo.byId('txt_clave'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                validar();
                e.stopPropagation();
            }
        });
    }); //close doc ready


////http://integrationwizard.x.com/ecpaypal/code.php
//variable de sesion 'Payment_Amount'
var xhr_subeventos;
var ref_id_evento;

function inicializar(idioma, id_evento){
    ref_id_evento=id_evento;
    asignar_idioma(idioma);
    verificar_estado_registro(id_evento);
}

function registrarse_evento(){
    var cadena="";
    if (xhr_subeventos.items.length>0){
        var cola ="";
        var tmp = "";
        var registro="";
        for (var i=0; i<xhr_subeventos.items.length; i++) {
            if (dojo.byId("chk_subevento_"+xhr_subeventos.items[i].id_evento).checked){
                registro=1;
            }else{
                registro=0;
            }
            tmp += xhr_subeventos.items[i].id_evento + "," + dojo.byId("cmb_categoria_subevento_" + xhr_subeventos.items[i].id_evento).value + "," + dojo.byId("cmb_subcategoria_subevento_" + xhr_subeventos.items[i].id_evento).value + "," + registro + "@";
            cola += tmp;
            tmp = "";
        }
        cadena += cola;
    }else{
        cadena += "-1,-1@";
    }
    
    var respuesta = "evento:" + ref_id_evento + "," + dojo.byId("cmb_categoria").value + "," + dojo.byId("cmb_subcategoria").value + ",1;subeventos:" + cadena;
    //alert(respuesta);
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=registrar_usuario_evento&cadena='+respuesta,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                alert(xhr.mensaje + "\n" + "Revise su correo.");
                window.location.href = "/websae/evento/opciones/registro/index.jsp";
            }
        }
    });
}

function verificar_estado_registro(id_evento){
    dijit.byId('btn_registrarse').domNode.style.display = "none";
    dijit.byId('btn_pagar').domNode.style.display = "none";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=registros',
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length==0){
                cargar_info_evento(id_evento);
            }else{
                var html="";
                if (xhr.items[0].estado=='S') {
                    html += "Su información de Registro al Evento debe ser aprobada por el administrador... <br />";
                    html += "Espere que le llegue un correo de aprobación y siga las instrucciones.";
                } else if(xhr.items[0].estado=='P' || xhr.items[0].estado=='A'){
                    for (var i=0; i<xhr.items.length; i++) {
                        total_pagar += parseVacio(xhr.items[i].precio)!=""?parseInt(xhr.items[i].precio.precio):0;
                    }
                    dijit.byId('btn_pagar').domNode.style.display = "block";
                    dojo.byId("costo").value=total_pagar;
                    if(xhr.items[0].estado=='P'){
                        html += "Su información de Registro al Evento ha sido aprobada con Éxito.<br />";
                    }
                    html += "Valor Total a Pagar: $" + total_pagar + "<br />"
                    html += "Escoga su forma de pago: ";
                    html += "<select id='cmb_forma_pago' onchange='valida_pago(value)'>";
                    html += "<option value='efectivo,"+total_pagar+"'>Efectivo</option>";
                    html += "<option value='tarjeta,"+total_pagar+"'>PayPal</option>";
                    html += "</select><div id='paypal_boton'></div>";
                }else if(xhr.items[0].estado=='F'){
                    html = "Debe acercarse a nuestras oficinas para pagar en efectivo para constar como pagado.";
                }else if(xhr.items[0].estado=='E'){
                    html = "Su registro con descuento fue rechazado.\n\
                            Si desea volver a registrarse de clic AQUI.";
                }else if(xhr.items[0].estado=='C'){
                    html = "Su registro al evento ha caducado. \n\
                            Si ud. a pagado en efectivo de clic AQUI\n\
                            Caso contrario vuelva a registrar dando clic AQUI.";
                }else if(xhr.items[0].estado=='R'){
                    html = "Ud. se encuentra registrado y NO tiene ninguna deuda.";
                }
                dojo.byId("descripcion_registro").innerHTML = html;
            }
        }
    });
}

function valida_pago(tipo){
    if (tipo.split(',')[0]=="efectivo"){
        dijit.byId('btn_pagar').domNode.style.display = "block";
        //dojo.byId("paypal_boton").innerHTML = "";
        dojo.byId("div_paypal").style.display = "none";
    }else{
        dojo.byId("div_paypal").style.display = "block";
        dijit.byId('btn_pagar').domNode.style.display = "none";
        dojo.byId("paypal_boton").innerHTML = "";
    }
}

function pagar_evento(){
    alert("Forma de Pago: " + dojo.byId("cmb_forma_pago").value);
}

function cargar_info_evento(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=buscar_evento_lang&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.estado_registro=='V'){
                if (parseVacio(xhr.descripcion_registro!="")) html += "<b>" + parseIdioma("pasos registrarse") + "</b><br />" + xhr.descripcion_registro + "<br /><br />";
                existe_precio();
            }else{
                html += parseIdioma("registro inactivo");
            }
            dojo.byId("descripcion_registro").innerHTML = html;
        }
    });
}

function existe_precio(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_categorias_evento',
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length==0){
                html += "<input type='hidden' id='cmb_categoria' value='-1' />";
                html += "<input type='hidden' id='cmb_subcategoria' value='-1' />";
                html += parseIdioma("evento gratis");
                dojo.byId("detalle_categorias").innerHTML = html;
                dijit.byId('btn_registrarse').domNode.style.display = "block";
                cargar_subeventos();
            }else{
                cargar_precios();
            }
        }
    });
}

function cargar_precios(){
    var primer_id_categoria="";
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_categorias_evento_entrehoy',
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length==0){
                html += parseIdioma("consultar precios descuentos");
            }else{
                var cola ="";
                var tmp = "";
                cola = "<b>" + parseIdioma("evento") + "</b><br />" + parseIdioma("precio") + ":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"cmb_categoria\" id=\"cmb_categoria\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" onchange='cargar_descuentos(value)'>";
                primer_id_categoria = xhr.items[0].categoria.id_categoria;
                for (var i=0; i<xhr.items.length; i++) {    
                    tmp += "<option value=\"" + xhr.items[i].categoria.id_categoria + "\">" + xhr.items[i].categoria.nombre + " - $" + xhr.items[i].precio + "</option>";
                    cola += tmp;
                    tmp = "";
                }
                cola += "</select>";
                html += cola;
                dijit.byId('btn_registrarse').domNode.style.display = "block";
                cargar_subeventos();
            }
            dojo.byId("detalle_categorias").innerHTML = html;
            cargar_descuentos(primer_id_categoria);
        }
    });
}

function cargar_descuentos(id_categoria){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=mostrar_subcategorias_evento&id_categoria='+id_categoria,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.items.length>0){
                var cola ="";
                var tmp = "";
                cola = parseIdioma("descuento") + ": <select name=\"cmb_subcategoria\" id=\"cmb_subcategoria\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" >";
                tmp += "<option value=\"-1\"> % </option>";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "<option value=\"" + xhr.items[i].subcategoria.id_subcategoria + "\">" + xhr.items[i].subcategoria.nombre + " - " + xhr.items[i].porcentaje_descuento + "%</option>";
                    cola += tmp;
                    tmp = "";
                }
                cola += "</select>";
                html += cola;
                dojo.byId("detalle_subcategorias").innerHTML = html;   
            }
        }
    });
}

function cargar_subeventos(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=subeventos',
        handleAs: 'json',
        load: function(xhr) {
            xhr_subeventos = xhr;
            if (xhr.items.length>=0){
                var cola ="";
                var tmp = "";
                cola = "<br /><b>" + parseIdioma("Actividades Extra") + ":</b><br />";
                for (var i=0; i<xhr.items.length; i++) {
                    //tmp += xhr.items[i].nombre + " <span id='id_precio_" + xhr.items[i].id_evento + "'></span> <span id='id_descuento_" + xhr.items[i].id_evento +"'></span> - [<a href='javascript:detalle_subevento(" + xhr.items[i].id_evento + ",\"" + xhr.items[i].nombre + "\",\"" + parseVacio(xhr.items[i].descripcion) + "\")'>" + parseIdioma("detalles") + "</a>] - " + parseIdioma("registrar") + "? <input type='checkbox' id='chk_subevento_" + xhr.items[i].id_evento + "' dojoType='dijit.form.CheckBox' value='' /> <br />";
                    tmp += "<div class='div_registro_subevento'>";
                    tmp += xhr.items[i].nombre + "<br /> <span id='id_precio_" + xhr.items[i].id_evento + "'></span> <span id='id_descuento_" + xhr.items[i].id_evento +"'></span> <span style='float:right;'>" + parseIdioma("registrar") + "? <input type='checkbox' id='chk_subevento_" + xhr.items[i].id_evento + "' dojoType='dijit.form.CheckBox' value='' /></span> <br />";
                    cola += tmp + "</div>";
                    tmp = "";
                    cargar_precios_subeventos(xhr.items[i].id_evento);
                }
                html += cola;
                dojo.byId("detalle_subeventos").innerHTML = html;
            }
        }
    });
}

function cargar_precios_subeventos(id_subevento){
    if (id_subevento!=""){
        var primer_id_categoria="";
        var html="";
        dojo.xhrPost({
            url: '/websae/F_ae_mostrar_datos?tipo=mostrar_categorias_subevento&id_subevento='+id_subevento,
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.items.length==0){
                    html += parseIdioma("precio") + ": " + parseIdioma("gratis");
                    html += "<input type='hidden' value='-1'  id=\'cmb_categoria_subevento_" + id_subevento + "'/>";
                    dojo.byId("id_descuento_"+id_subevento).innerHTML = "<input type='hidden' value='-1'  id=\'cmb_subcategoria_subevento_" + id_subevento + "'/>";
                }else{
                    var cola ="";
                    var tmp = "";
                    cola = parseIdioma("precio") + ": <select name=\"cmb_categoria_subevento_" + id_subevento + "\" id=\"cmb_categoria_subevento_" + id_subevento + "\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" onchange='cargar_descuentos_subeventos("+ id_subevento + ",value)'>";
                    primer_id_categoria = xhr.items[0].categoria.id_categoria;
                    for (var i=0; i<xhr.items.length; i++) {
                        tmp += "<option value=\"" + xhr.items[i].categoria.id_categoria + "\">" + xhr.items[i].categoria.nombre + " - $" + xhr.items[i].precio + "</option>";
                        cola += tmp;
                        tmp = "";
                    }
                    cola += "</select>";
                    html += cola;
                }
                dojo.byId("id_precio_"+id_subevento).innerHTML = html;
                cargar_descuentos_subeventos(id_subevento, primer_id_categoria);
            }
        });
    }
}

function cargar_descuentos_subeventos(id_subevento, id_categoria){
    if (id_categoria!=""){
        var html="";
        dojo.xhrPost({
            url: '/websae/F_ae_mostrar_datos?tipo=mostrar_subcategorias_subevento&id_categoria='+id_categoria+'&id_subevento='+id_subevento,
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.items.length>0){
                    var cola ="";
                    var tmp = "";
                    cola = " - " + parseIdioma("descuento") + ": <select name=\"cmb_subcategoria_subevento_" + id_subevento + "\" id=\"cmb_subcategoria_subevento_" + id_subevento + "\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" >";
                    tmp += "<option value=\"-1\"> % </option>";
                    for (var i=0; i<xhr.items.length; i++) {
                        tmp += "<option value=\"" + xhr.items[i].subcategoria.id_subcategoria + "\">" + xhr.items[i].subcategoria.nombre + " - " + xhr.items[i].porcentaje_descuento + "%</option>";
                        cola += tmp;
                        tmp = "";
                    }
                    cola += "</select>";
                    html += cola;
                    dojo.byId("id_descuento_"+id_subevento).innerHTML = html;
                }else{
                    html += "<input type='hidden' value='-1'  id=\'cmb_subcategoria_subevento_" + id_subevento + "'/>";
                    dojo.byId("id_descuento_"+id_subevento).innerHTML = html;
                }
            }
        });
    }
}



function validar() {
    if (dijit.byId("form_sesion").validate()) {
        dojo.xhrPost({
            url: '/websae/F_autentificar_usuario?accion=autentificar',
            form: 'form_sesion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    window.location.href = '/websae/evento/opciones/registro/index.jsp';
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function parseVacio(campo){
    if (campo == null || campo == ""){
        return "";
    }else{
        return campo;
    }
}




////////////////////

 function putValues(form){
      form.quantity_1.value = 1;
      form.amount_1.value = 100;
      form.submit();
 }
/*
 *
 *<form method="post" action="https://www.sandbox.paypal.com/us/cgi-bin/webscr">
      <input type="hidden" name="cmd" value="_xclick"/>
      <input type="hidden" name="rm" value="2"/>
      <input type="hidden" name="bn" value="http://websae.com" />
      <input type="hidden" name="upload" value="1"/>
      <input type="hidden" name="business" value="websae_1257117975_biz@gmail.com"/>
      <input type="hidden" name="item_name" value="Subscripcion al evento"/>
      <input type="hidden" name="item_number" value="666"/>
      <input type="hidden" name="custom" value="rafariva"/>
      <input type="hidden" name="amount" value="145.00"/>
      <input type="hidden" name="return" value="http://www.google.com"/>
      <input type="hidden" name="cancel" value=" http://www.hotmail.com"/>
      <input type="submit" value="enviar" />
 </form>

 vendedor: user:websae_1257117975_biz@gmail.com   pass:websae123
 comprador: user:user_1257118019_per@gmail.com pass:websae123

 https://cms.paypal.com/us/cgi-bin/?cmd=_render-content&content_ID=developer/e_howto_html_formbasics
*/

function pago_paypal_api(){
    dojo.xhrPost({
        url: 'https://api-3t.sandbox.paypal.com/nvp',
        form: 'frm_paypal_api',
        handleAs: 'text',
        load: function(xhr) {
            alert(xhr);
        }
    });
}