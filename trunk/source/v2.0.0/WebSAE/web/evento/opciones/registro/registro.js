var total_pagar=0;
var xhr_subeventos;
var ref_id_evento;

function inicializar(idioma, id_evento){
    ref_id_evento=id_evento;
    asignar_idioma(idioma);
    verificar_estado_registro(id_evento);
    dojo.connect(dojo.byId('txt_clave'), "onkeydown", function(e) {
        key = e.keyCode;
        if (key == dojo.keys.ENTER) {
            validar();
            e.stopPropagation();
        }
    });
}

function verificar_estado_registro(id_evento){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=buscar_evento_lang&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.estado=='V'){
                if (xhr.estado_registro=='V'){
                    if (parseVacio(xhr.descripcion_registro!="")) html += "<b>" + parseIdioma("pasos registrarse") + "</b><br />" + xhr.descripcion_registro + "<br /><br />";
                    existe_precio_evento();
                //existe_precio_subevento();
                }
            }else{
                html += parseIdioma("registro inactivo");
                dojo.byId("detalles_registros").innerHTML = "";
            }
            dojo.byId("descripcion_registro").innerHTML = html;
        }
    });
}

function existe_precio_evento(){
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
                var boton_registrar = "<button id=\"btn_registrarse_"+ref_id_evento+"\" dojoType=\"dijit.form.Button\" onclick=\"solicitar_registro('"+ref_id_evento+"','"+dojo.byId("cmb_categoria").value+"',"+dojo.byId("cmb_subcategoria").value+");\">Registrarse</button>";
                dojo.byId("div_btn_registrar_"+ref_id_evento).innerHTML = boton_registrar;
            }else{
                cargar_precios();
            }
            estado_registro_evento(ref_id_evento);
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
                cola = parseIdioma("precio") + ":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"cmb_categoria\" id=\"cmb_categoria\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" onchange='cargar_descuentos(value)'  style='min-width:150px;'>";
                primer_id_categoria = xhr.items[0].categoria.id_categoria;
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "<option value=\"" + xhr.items[i].id_categoria_evento + "\">" + xhr.items[i].categoria.nombre + " - $" + xhr.items[i].precio + "</option>";
                    cola += tmp;
                    tmp = "";
                }
                cola += "</select>";
                html += cola;
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
                cola = parseIdioma("descuento") + ": <select name=\"cmb_subcategoria\" id=\"cmb_subcategoria\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" onchange=\"actualizar_boton_registro_evento();\"  style='min-width:150px;'>";
                tmp += "<option value=\"-1\"> % </option>";
                for (var i=0; i<xhr.items.length; i++) {
                    tmp += "<option value=\"" + xhr.items[i].id_subcategoria_evento + "\">" + xhr.items[i].subcategoria.nombre + " - " + xhr.items[i].porcentaje_descuento + "%</option>";
                    cola += tmp;
                    tmp = "";
                }
                cola += "</select>";
                html += cola;
                dojo.byId("detalle_subcategorias").innerHTML = html;
            }else{
                dojo.byId("detalle_subcategorias").innerHTML = "<input type='hidden' id='cmb_subcategoria' value='-1' />";
            }
            actualizar_boton_registro_evento();
        }
    });
}

function actualizar_boton_registro_evento(){
    var boton_registrar = "<button id=\"btn_registrarse_"+ref_id_evento+"\" dojoType=\"dijit.form.Button\" onclick=\"solicitar_registro('"+ref_id_evento+"','"+dojo.byId("cmb_categoria").value+"',"+dojo.byId("cmb_subcategoria").value+");\">Solicitar Registro</button>";
    dojo.byId("div_btn_registrar_"+ref_id_evento).innerHTML = boton_registrar;
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
                cola = "<div style='float:left; margin:20px 0 0 10px;'><b>" + parseIdioma("Actividades Extra") + ":</b></div><div class='clearthefloats'></div>";
                for (var i=0; i<xhr.items.length; i++) {
                    //tmp += xhr.items[i].nombre + " <span id='id_precio_" + xhr.items[i].id_evento + "'></span> <span id='id_descuento_" + xhr.items[i].id_evento +"'></span> - [<a href='javascript:detalle_subevento(" + xhr.items[i].id_evento + ",\"" + xhr.items[i].nombre + "\",\"" + parseVacio(xhr.items[i].descripcion) + "\")'>" + parseIdioma("detalles") + "</a>] - " + parseIdioma("registrar") + "? <input type='checkbox' id='chk_subevento_" + xhr.items[i].id_evento + "' dojoType='dijit.form.CheckBox' value='' /> <br />";
                    tmp += "<div id='div_subevento_"+xhr.items[i].id_evento+"' class='div_registro_subevento'>";
                    tmp += "<div id='div_titulo_"+xhr.items[i].id_evento+"' style='float:left;'><u>" + xhr.items[i].nombre + "</u></div><div class='clearthefloats'></div>";
                    tmp += "<div style='float:left;'><span id='id_precio_" + xhr.items[i].id_evento + "' style='float:left; margin-top:5px;'></span><div class='clearthefloats'></div>";
                    tmp += "<span id='id_descuento_" + xhr.items[i].id_evento +"' style='float:left; margin-top:5px;'></span><div class='clearthefloats'></div>";
                    //tmp += "<span style='float:right;'>" + parseIdioma("registrar") + "? <input type='checkbox' id='chk_subevento_" + xhr.items[i].id_evento + "' dojoType='dijit.form.CheckBox' value='' /></span> <br />";
                    tmp += "</div><div id='div_btn_registrar_"+xhr.items[i].id_evento+"' style='float:right;'></div><div class='clearthefloats'></div>"
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
                    html += "<input type='hidden' value='-1' id=\'cmb_categoria_subevento_" + id_subevento + "'/>";
                    dojo.byId("id_descuento_"+id_subevento).innerHTML = "<input type='hidden' value='-1' id=\'cmb_subcategoria_subevento_" + id_subevento + "'/>";
                    var boton_registrar = "<button id=\"btn_registrarse_"+id_subevento+"\" dojoType=\"dijit.form.Button\" onclick=\"solicitar_registro('"+id_subevento+"','-1','-1');\">Registrarse</button>";
                    dojo.byId("div_btn_registrar_"+id_subevento).innerHTML = boton_registrar;
                }else{
                    var cola ="";
                    var tmp = "";
                    cola = parseIdioma("precio") + ":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"cmb_categoria_subevento_" + id_subevento + "\" id=\"cmb_categoria_subevento_" + id_subevento + "\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" onchange='cargar_descuentos_subeventos("+ id_subevento + ",value)' style='min-width:150px;'>";
                    primer_id_categoria = xhr.items[0].categoria.id_categoria;
                    for (var i=0; i<xhr.items.length; i++) {
                        tmp += "<option value=\"" + xhr.items[i].id_categoria_evento + "\">" + xhr.items[i].categoria.nombre + " - $" + xhr.items[i].precio + "</option>";
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
                    cola = parseIdioma("descuento") + ": <select name=\"cmb_subcategoria_subevento_" + id_subevento + "\" id=\"cmb_subcategoria_subevento_" + id_subevento + "\" dojoType=\"dijit.form.FilteringSelect\" required=\"true\" autoComplete=\"true\" value=\" \" onchange=\"actualizar_boton_registro_subevento('"+id_subevento+"');\"  style='min-width:150px;'>";
                    tmp += "<option value=\"-1\"> % </option>";
                    for (var i=0; i<xhr.items.length; i++) {
                        tmp += "<option value=\"" + xhr.items[i].id_subcategoria_evento + "\">" + xhr.items[i].subcategoria.nombre + " - " + xhr.items[i].porcentaje_descuento + "%</option>";
                        cola += tmp;
                        tmp = "";
                    }
                    cola += "</select></div>";
                    html += cola;
                    dojo.byId("id_descuento_"+id_subevento).innerHTML = html;
                }else{
                    html += "<input type='hidden' value='-1'  id=\'cmb_subcategoria_subevento_" + id_subevento + "'/>";
                    dojo.byId("id_descuento_"+id_subevento).innerHTML = html;
                }
                actualizar_boton_registro_subevento(id_subevento);
                estado_registro_evento(id_subevento);
            }
        });
    }
}

function actualizar_boton_registro_subevento(id_subevento){
    var boton_registrar = "<button id=\"btn_registrarse_"+id_subevento+"\" dojoType=\"dijit.form.Button\" onclick=\"solicitar_registro('"+id_subevento+"','"+dojo.byId("cmb_categoria_subevento_"+id_subevento).value+"',"+dojo.byId("cmb_subcategoria_subevento_"+id_subevento).value+");\">Solicitar Registro</button>";
    dojo.byId("div_btn_registrar_"+id_subevento).innerHTML = boton_registrar;
}

function solicitar_registro(id, categoria, subcategoria){
    //alert(id + " " + categoria + " " + subcategoria);
    //var boton_registrar = "<button id=\"btn_registrarse_"+id+"\" dojoType=\"dijit.form.Button\" onclick=\"cancelar_solicitud_registro('"+id+"');\">Cancelar Solicitud</button>";
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=registrar_usuario_evento&id='+id+'&categoria='+categoria+'&subcategoria='+subcategoria,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                //dojo.byId("div_btn_registrar_"+id).innerHTML = boton_registrar;
                //alert(xhr.mensaje + "\n" + "Revise su correo.");
                //window.location.href = "/websae/evento/opciones/registro/index.jsp";
            }
            alert(xhr.mensaje);
            estado_registro_evento(id);
        }
    });
}

function cancelar_solicitud_registro(id){
    alert(id);
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=cancelar_registrar_usuario_evento&id='+id,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                if(id==ref_id_evento){
                    actualizar_boton_registro_evento();
                }else{
                    actualizar_boton_registro_subevento(id);
                }
            }
            alert(xhr.mensaje);
        }
    });
}

function estado_registro_evento(id_evento){
    dojo.xhrPost({
        url: '/websae/F_ae_mostrar_datos?tipo=estado_registro_evento&id_evento='+id_evento,
        handleAs: 'json',
        load: function(xhr) {
            var descuento = "";
            if (parseVacio(xhr.items[0].descuento.porcentaje_descuento)!="") descuento = ". Descuento: " + xhr.items[0].descuento.porcentaje_descuento;
            if(ref_id_evento == id_evento){
                if(xhr.items[0].estado=="P"){
                    dojo.byId("precio_descuento_evento").innerHTML = "Esperar Aprobación de Registro...";
                }
                if(xhr.items[0].estado=="AR"){
                    var boton_pagar = "<button id=\"btn_pagar_registro_"+id_evento+"\" dojoType=\"dijit.form.Button\" onclick=\"solicitar_pago('"+id_evento+"');\">Pagar Registro</button>";
                    dojo.byId("precio_descuento_evento").innerHTML = parseIdioma("Precio") + ": $" + xhr.items[0].precio.precio + descuento + " -----> " + boton_pagar;
                }
                if(xhr.items[0].estado=="PP"){
                    dojo.byId("precio_descuento_evento").innerHTML = "Esperar Aprobación de Pago...";
                }
                if(xhr.items[0].estado=="R"){
                    dojo.byId("precio_descuento_evento").innerHTML = "REGISTRADO CON ÉXITO!!!";
                }
                if(xhr.items[0].estado=="RR"){
                    dojo.byId("precio_descuento_evento").innerHTML = "Solicitud de Registro <b>Rechazada!</b> para Categoría: " + xhr.items[0].precio.precio + descuento;
                }
                if(xhr.items[0].estado=="RP"){
                    dojo.byId("precio_descuento_evento").innerHTML = "No se pudo verificar su pago.";
                }
            }else{
                if(xhr.items[0].estado=="P"){
                     //var boton_registrar = "<button id=\"btn_pendiente_registro_"+id_evento+"\" dojoType=\"dijit.form.Button\" onclick=\"alert('Esperar aprobación de Registro');\">Pendiente Registro</button>";
                    //dojo.byId("div_btn_registrar_"+id_evento).innerHTML = boton_registrar;
                    dojo.byId("div_subevento_"+id_evento).innerHTML = dojo.byId("div_titulo_"+id_evento).innerHTML + "<br />Esperar Aprobación de Registro...";
                }
                if(xhr.items[0].estado=="AR"){
                    boton_pagar = "<button id=\"btn_pagar_registro_"+id_evento+"\" dojoType=\"dijit.form.Button\" onclick=\"solicitar_pago('"+id_evento+"');\">Pagar Registro</button>";
                    dojo.byId("div_subevento_"+id_evento).innerHTML = dojo.byId("div_titulo_"+id_evento).innerHTML + ". " + parseIdioma("Precio") + ": $" + xhr.items[0].precio.precio + descuento + " -----> " + boton_pagar;
                }
                if(xhr.items[0].estado=="PP"){
                    dojo.byId("div_subevento_"+id_evento).innerHTML = dojo.byId("div_titulo_"+id_evento).innerHTML + ". <br />Esperar Confirmación de Pago...";
                }
                if(xhr.items[0].estado=="R"){
                    dojo.byId("div_subevento_"+id_evento).innerHTML = dojo.byId("div_titulo_"+id_evento).innerHTML + ". REGISTRADO CON ÉXITO!!!";
                }
                if(xhr.items[0].estado=="RR"){
                    dojo.byId("div_subevento_"+id_evento).innerHTML = dojo.byId("div_titulo_"+id_evento).innerHTML + ". <br />Solicitud de Registro <b>Rechazada</b>! para Categoria " + xhr.items[0].precio.precio + descuento;
                }
                if(xhr.items[0].estado=="RP"){
                    dojo.byId("div_subevento_"+id_evento).innerHTML = dojo.byId("div_titulo_"+id_evento).innerHTML + ". <br />No se pudo verificar su pago.";
                }
            }            
        }
    });
}

function solicitar_pago(id){
    dojo.xhrPost({
        url: '/websae/administracion/F_ae_administrar_objetos?tipo=pagar_usuario_evento&id_evento='+id,
        handleAs: 'json',
        load: function(xhr) {
            if (xhr.tipo == "OK") {
                //dojo.byId("div_btn_registrar_"+id).innerHTML = boton_registrar;
                //alert(xhr.mensaje + "\n" + "Revise su correo.");
                //window.location.href = "/websae/evento/opciones/registro/index.jsp";
            }
            alert(xhr.mensaje);
            estado_registro_evento(id);
        }
    });
}

function cambiar_valor_descripcion(frm){
    alert("hola");
    frm.submit;
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

function verificar_estado_registro_2(id_evento){
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
    /*if (tipo.split(',')[0]=="efectivo"){
        dijit.byId('btn_pagar').domNode.style.display = "block";
        //dojo.byId("paypal_boton").innerHTML = "";
        dojo.byId("div_paypal").style.display = "none";
    }else{
        dojo.byId("div_paypal").style.display = "block";
        dijit.byId('btn_pagar').domNode.style.display = "none";
        dojo.byId("paypal_boton").innerHTML = "";
    }*/
}




function pagar_evento(){
    dojo.byId("item_name").value = dojo.byId("txt_descripcion_registro").value;
    dojo.byId("frm_paypal").submit();
    alert("Forma de Pago: " + dojo.byId("cmb_forma_pago").value);
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


















//////////PAYPAL//////////

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