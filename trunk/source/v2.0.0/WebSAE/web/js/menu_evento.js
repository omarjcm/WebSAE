dojo.addOnLoad(
    function() {
        cargar_menu_evento();
    }
);

function cargar_menu_evento() {
    dojo.xhrPost({
        url: '/websae/F_mostrar_datos?tipo=mostrar_opciones_por_id_evento',
        handleAs: 'json',
        load: function(xhr) { fn_cargar_menu_evento(xhr); }
    });
}

function fn_cargar_menu_evento(xhr) {
    /*
         Initialize and render the Menu when its elements are ready
         to be scripted.
    */
    YAHOO.util.Event.onContentReady("menu_evento", function () {
        /*
             Instantiate a Menu:  The first argument passed to the
             constructor is the id of the element in the page
             representing the Menu; the second is an object literal
             of configuration properties.
        */
        var oMenu = new YAHOO.widget.Menu("menu_evento", {
                                                position: "static",
                                                hidedelay:  750,
                                                lazyload: true,
                                                effect: {
                	                                effect: YAHOO.widget.ContainerEffect.FADE,
                	                                duration: 0.25
                	                            } });
        /*
             Call the "render" method with no arguments since the
             markup for this Menu instance is already exists in the page.
        */
        oMenu.render();
    });

    var html = '<div class="bd"><ul class="first-of-type">';
    var opciones = xhr.opciones;
    html += '<li class="yuimenuitem"><a class="yuimenuitemlabel" href="/websae/evento/opciones/index.jsp">Datos Generales</a></li>';
    for (var i=0; i<opciones.length; i++) {
        html += '<li class="yuimenuitem">';
        html += '<a class="yuimenuitemlabel" href="'+opciones[i].url_usuario+'">'+opciones[i].nombre+'</a>';

        if (opciones[i].opciones != null) {
            html += '<div id="'+opciones[i].opciones.id+'" class="yuimenu">';
            html += '<div class="bd">';
            html += '<ul class="first-of-type">';
            var itemdata = opciones[i].opciones.itemdata;
            for (var j=0; j<itemdata.length; j++) {
                html += '<li class="yuimenuitem">';
                html += '<a class="yuimenuitemlabel" href="'+itemdata[j].url_usuario+'">'+itemdata[j].text+'</a>';
                html += '</li>';
            }
            html += '</ul>';
            html += '</div></div>';
        }
        html += '</li>';
    }
    html += '</ul></div>';
    dojo.byId('menu_evento').innerHTML = html;
}