dojo.addOnLoad(
    function() {
        cargar_menu_admin();
    }
);

function cargar_menu_admin() {
    dojo.xhrPost({
        url: '/websae/F_mostrar_opcion?tipo=A',
        handleAs: 'json',
        load: function(xhr) { fn_cargar_menu_admin(xhr); }
    });
}

function fn_cargar_menu_admin(xhr) {
    /*
         Initialize and render the Menu when its elements are ready
         to be scripted.
    */
    YAHOO.util.Event.onContentReady("menu_admin", function () {
        /*
             Instantiate a Menu:  The first argument passed to the
             constructor is the id of the element in the page
             representing the Menu; the second is an object literal
             of configuration properties.
        */
        var oMenu = new YAHOO.widget.Menu("menu_admin", {
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
    for (var i=0; i<opciones.length; i++) {
        html += '<li class="yuimenuitem">';
        html += '<a class="yuimenuitemlabel" href="'+opciones[i].url+'">'+opciones[i].nombre+'</a>';

        if (opciones[i].opciones != null) {
            html += '<div id="'+opciones[i].opciones.id+'" class="yuimenu">';
            html += '<div class="bd">';
            html += '<ul class="first-of-type">';
            var itemdata = opciones[i].opciones.itemdata;
            for (var j=0; j<itemdata.length; j++) {
                html += '<li class="yuimenuitem">';
                html += '<a class="yuimenuitemlabel" href="'+itemdata[j].url+'">'+itemdata[j].text+'</a>';
                html += '</li>';
            }
            html += '</ul>';
            html += '</div></div>';
        }
        html += '</li>';
    }
    html += '</ul></div>';
    dojo.byId('menu_admin').innerHTML = html;
}