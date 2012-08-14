dojo.addOnLoad(
    function() {
        dijit.byId('div_sesion').domNode.style.display = "none";
        
        dojo.connect(dojo.byId('txt_usuario'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                validar();
                e.stopPropagation();
            }
        });
        dojo.connect(dojo.byId('txt_clave'), "onkeydown", function(e) {
            key = e.keyCode;
            if (key == dojo.keys.ENTER) {
                validar();
                e.stopPropagation();
            }
        });
        dojo.xhrPost({
            url: '/websae/F_autentificar_usuario?accion=verificar',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK"){
                    dojo.cookie("WEBSAE_idioma_asignado","false",{
                        expires:-1
                    });
                    visualizar_login(xhr);
                }
            }
        });

        $(document).ready(function() {
            $(".pane-list li").click(function() {
                window.location = $(this).find("a").attr("href");
                return false;
            });
        }); //close doc ready

        var sel_lang = dojo.cookie("WEBSAE_idioma");
        if(parseVacio(sel_lang)==""){
            if (parseVacio(dojo.cookie("WEBSAE_idioma_asignado"))==""){
                dojo.cookie("WEBSAE_idioma","es",{
                    expires:7
                });
                dojo.cookie("WEBSAE_idioma_asignado","true",{
                    expires:2
                });
                window.location.href = "/websae/home/index.jsp?lang=es";
            }

            /*dojo.cookie("WEBSAE_idioma","ES",{
                expires:7
            });*/
        }else{
            if (parseVacio(dojo.cookie("WEBSAE_idioma_asignado"))==""){
                dojo.cookie("WEBSAE_idioma",sel_lang,{
                    expires:7
                });
                dojo.cookie("WEBSAE_idioma_asignado","true",{
                    expires:2
                });
                window.location.href = "/websae/home/index.jsp?lang="+sel_lang;
            }
        }
        cargar_eventos_presentes();
        cargar_grupos_investigacion();
    //dojo.byId("div_notificaciones").innerHTML = "<ul><li>- Reunión con el Gerente General.</li><li>- Cumpleaños de JVC.</li><li>- Vaciar su cuenta de correo Electrónico.</li></ul>";
    });

function cambiar_idioma(idioma){
    dojo.cookie("WEBSAE_idioma",idioma,{
        expires:7
    });
    dojo.cookie("WEBSAE_idioma_asignado","true",{
        expires:2
    });
    window.location.href = "/websae/home/index.jsp?lang="+idioma
}

function cargar_eventos_presentes(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_mostrar_datos?tipo=mostrar_eventos_por_realizar',
        handleAs: 'json',
        load: function(xhr) {
            for (var i=0; i<xhr.items.length; i++) {
                if(i<3){
                    html += "<span class='text_black_date_event'>["+ xhr.items[i].fecha_inicio +" - "+ xhr.items[i].fecha_fin +"]</span><br />";
                    html += "<a href='/websae/evento/opciones/index.jsp?id_evento="+xhr.items[i].id_evento+"' class='text_blue_tittle_event'>" + xhr.items[i].nombre + "</a><br />";
                    html += "<span class='text_black_event'>" + xhr.items[i].lugar + "</span><br /><br />";
                }
            }
            dojo.byId("div_detalle_eventos").innerHTML = html;
        }
    });
}

function cargar_grupos_investigacion(){
    var html="";
    dojo.xhrPost({
        url: '/websae/F_gi_mostrar_datos?tipo=grupos_investigacion',
        handleAs: 'json',
        load: function(xhr) {
            html += "<ul class='pane-list'>"
            for (var i=0; i<xhr.items.length; i++) {
                html += "<li>";
                html += "<h3><a href='" + xhr.items[i].web+ "' class='text_black_uppercase_paper_link'><span>" + xhr.items[i].nombre + "</span></a></h3>";
                html += "<span class='text_black_event'>" + xhr.items[i].objetivo + "</span>";
                html += "</li>";
            }
            html += "</ul>";
            dojo.byId("div_grupos_investigacion").innerHTML = html;
        }
    });
}

function url_id_evento(id_evento){
    document.location.href = "detalle_evento.jsp?id_evento=" + id_evento;
}

function visualizar_login(xhr) {
    var html = "<div style='text-align:center;'> "+xhr.usuario.nombre+" "+xhr.usuario.apellido+" </div>";
    html += "<div style='font-weight:bold; text-align:center; font-style:oblique;'> << "+xhr.usuario.email+" >> </div><br />";
    //html += "<div align='center'> <a href='../F_cerrar_sesion' class='text_black_uppercase_paper_link'>Logout</a> </div>";
    dojo.byId("div_sub_login").innerHTML = html;
    dijit.byId('div_sesion').domNode.style.display = "block";
}

function validar() {
    if (dijit.byId("form_sesion").validate()) {
        dojo.xhrPost({
            url: '/websae/F_autentificar_usuario?accion=autentificar',
            form: 'form_sesion',
            handleAs: 'json',
            load: function(xhr) {
                if (xhr.tipo == "OK") {
                    window.location.href = 'index.jsp';
                } else if (xhr.tipo == "ERROR") {
                    alert(xhr.mensaje);
                }
            }
        });
    }
}

function DOJO_Alert(txtTitle, txtContent){
    var thisdialog = new dijit.Dialog({
        title: txtTitle,
        content: txtContent
    });
    dojo.body().appendChild(thisdialog.domNode);
    thisdialog.startup();
    thisdialog.show();
}

function parseVacio(campo){
    if (campo == null || campo == "null" ||campo == "" || campo == undefined || campo=="undefined" || campo.length == 0) return "";
    return campo;
}
