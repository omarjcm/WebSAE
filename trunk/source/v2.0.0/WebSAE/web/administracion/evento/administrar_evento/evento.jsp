<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="div_evento" dojoType="dijit.Dialog" title="<fmt:message key="even.evento" />" style="width:440px;">
    <form id="frm_evento" name="frm_evento" dojoType="dijit.form.Form" class="form-suscripcion"  action="">
        <div id="div_form">
            <div id="tabs">
                <div id="tab_info1">
                    <input type="hidden" dojoType="dijit.form.TextBox" id="txt_id_evento" name="txt_id_evento" value="-1" />
                    <div dojoType="dojo.data.ItemFileReadStore" jsId="tipos_eventosStore" url="/websae/administracion/F_ae_mostrar_datos?tipo=tipos_eventos"></div>
                    <label class="info_modificar"><fmt:message key="even.tipo_evento" />:</label>
                    <input name="cmb_tipo_evento" id="cmb_tipo_evento"
                           dojoType="dijit.form.FilteringSelect"
                           invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                           store="tipos_eventosStore" searchAttr="nombre" required="true" />
                    <!--span class="lblasterisco"> (*)</span-->
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.evento" /> (ES):</label>
                    <input type="text" id="txt_evento_es" name="txt_evento_es"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           promptMessage="<fmt:message key="ge.necesita_valor" />"
                           required="true" maxlength="200" class="div_form" />
                    <span><a href="javascript:traduccion('en','es','txt_evento_en','txt_evento_es');" style="float:left; margin:3px 0 0 5px;">
                            <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.evento" /> (EN):</label>
                    <input type="text" id="txt_evento_en" name="txt_evento_en"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           promptMessage="<fmt:message key="ge.necesita_valor" />"
                           required="true" maxlength="200" class="div_form" />
                    <span><a href="javascript:traduccion('es','en','txt_evento_es','txt_evento_en');" style="float:left; margin:3px 0 0 5px;">
                            <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.evento" /> (PT):</label>
                    <input type="text" id="txt_evento_pt" name="txt_evento_pt"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           promptMessage="<fmt:message key="ge.necesita_valor" />"
                           required="true" maxlength="200" class="div_form" />
                    <span><a href="javascript:traduccion('es','pt','txt_evento_es','txt_evento_pt');" style="float:left; margin:3px 0 0 5px;">
                            <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.fecha_inicio" />:</label>
                    <input id="txt_fecha_inicio" name="txt_fecha_inicio"
                           dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
                           invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
                           promptMessage="yyyy-mm-dd" type="text" value="" />
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.fecha_fin" />:</label>
                    <input id="txt_fecha_fin" name="txt_fecha_fin"
                           dojoType="dijit.form.DateTextBox" constraints="{datePattern:'yyyy-MM-dd'}"
                           invalidMessage="<fmt:message key="ge.formato_invalido_fecha" />"
                           promptMessage="yyyy-mm-dd" type="text" value="" />
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.estado_registro" />:</label>
                    <label class="info_dato_check"><input dojoType="dijit.form.RadioButton" type="radio" name="rb_estado_registro" id="rb_vigente_reg" value="V" checked="checked" /><fmt:message key="ge.vigente" /></label>
                    <label class="info_dato_check"><input dojoType="dijit.form.RadioButton" type="radio" name="rb_estado_registro" id="rb_pendiente_reg" value="P" /><fmt:message key="ge.pendiente" /></label>
                    <div class="clearthefloats"></div>
                </div>

                <div id="tab_info2">
                    <form name="form" action="" method="POST" enctype="multipart/form-data" target="upload_target" >
                        <label id="logo_imagen" class="info_modificar"><img src="/websae/images/evento/default.jpg" height="40px" alt="" /></label>
                        <input type="hidden" id="txt_imagen2" name="txt_imagen2" value="" />
                        <input type="file" name="txt_imagen" id="txt_imagen" size="15px" style="float:left;" />
                        <button style="float:left; margin-top:-1px;" id="buttonUpload" dojoType="dijit.form.Button" onclick="cargar_logo('images','evento','txt_imagen');">
                            <fmt:message key="ge.subir" />
                        </button>

                        <div class="clearthefloats"></div>
                        <!--iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe-->
                    </form>

                    <label class="info_modificar"><fmt:message key="even.slogan" /> (ES):</label>
                    <input type="text" id="txt_slogan_es" name="txt_slogan_es"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           maxlength="150" class="div_form" />
                    <span><a href="javascript:traduccion('en','es','txt_slogan_en','txt_slogan_es');" style="float:left; margin:3px 0 0 5px;">
                            <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.slogan" /> (EN):</label>
                    <input type="text" id="txt_slogan_en" name="txt_slogan_en"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           maxlength="150" class="div_form" />
                    <span><a href="javascript:traduccion('es','en','txt_slogan_es','txt_slogan_en');" style="float:left; margin:3px 0 0 5px;">
                            <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.slogan" /> (PT):</label>
                    <input type="text" id="txt_slogan_pt" name="txt_slogan_pt"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           maxlength="150" class="div_form" />
                    <span><a href="javascript:traduccion('es','pt','txt_slogan_es','txt_slogan_pt');" style="float:left; margin:3px 0 0 5px;">
                            <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>

                    <div dojoType="dojo.data.ItemFileReadStore" jsId="paisStore" url="/websae/F_mostrar_datos?tipo=mostrar_paises"></div>
                    <label class="info_modificar"><fmt:message key="us.pais" /></label>
                    <input name="cmb_pais" id="cmb_pais"
                           dojoType="dijit.form.FilteringSelect"
                           promptMessage="<fmt:message key="ge.necesita_valor" />"
                           invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                           value="ECU" store="paisStore" searchAttr="nombre" required="true"
                           onchange="cargar_ciudades" />
                    <div class="clearthefloats"></div>

                    <div dojoType="dojo.data.ItemFileReadStore" jsId="ciudadStore" url="/websae/F_mostrar_datos?tipo=mostrar_ciudades&id_pais=ECU"></div>
                    <label class="info_modificar"><fmt:message key="us.ciudad" /></label>
                    <input name="cmb_ciudad" id="cmb_ciudad"
                           dojoType="dijit.form.FilteringSelect"
                           invalidMessage="<fmt:message key="ge.solo_valores_lista" />"
                           promptMessage="<fmt:message key="ge.necesita_valor" />"
                           value="Guayaquil" store="ciudadStore" searchAttr="nombre" required="true" />
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.lugar" /></label>
                    <textarea id="txt_lugar" name="txt_lugar" cols="30" rows="2" maxlength="200" onkeyup="return ismaxlength(this)"></textarea>
                    <div class="clearthefloats"></div>
                </div>

                <div id="tab_info3" style="overflow:auto">
                    <label class="info_modificar"><fmt:message key="even.objetivo" /> (ES):</label>
                    <!--textarea id="txt_objetivo_es" name="txt_objetivo_es" cols="30" rows="1" maxlength="900" onkeyup="return ismaxlength(this)"></textarea-->
                    <textarea id="txt_objetivo_es" name="txt_objetivo_es"
                              dojoType="dijit.form.SimpleTextarea" value=" " trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('en','ps','txt_objetivo_en','txt_objetivo_es');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.objetivo" /> (EN):</label>
                    <textarea id="txt_objetivo_en" name="txt_objetivo_en"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','en','txt_objetivo_es','txt_objetivo_en');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.objetivo" /> (PT):</label>
                    <textarea id="txt_objetivo_pt" name="txt_objetivo_pt"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','pt','txt_objetivo_es','txt_objetivo_pt');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.descripcion" /> (ES):</label>
                    <textarea id="txt_descripcion_es" name="txt_descripcion_es"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('en','es','txt_descripcion_en','txt_descripcion_es');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.descripcion" /> (EN):</label>
                    <textarea id="txt_descripcion_en" name="txt_descripcion_en"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','en','txt_descripcion_es','txt_descripcion_en');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.descripcion" /> (PT):</label>
                    <textarea id="txt_descripcion_pt" name="txt_descripcion_pt"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','pt','txt_descripcion_es','txt_descripcion_pt');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                </div>

                <div id="tab_info4" style="overflow:auto">
                    <label class="info_modificar"><fmt:message key="even.dirigido" /> (ES):</label>
                    <textarea id="txt_dirigido_es" name="txt_dirigido_es"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('en','es','txt_dirigido_en','txt_dirigido_es');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.dirigido" /> (EN):</label>
                    <textarea id="txt_dirigido_en" name="txt_dirigido_en"
                              dojoType="dijit.form.SimpleTextarea" value=" "
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','en','txt_dirigido_es','txt_dirigido_en');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.dirigido" /> (PT):</label>
                    <textarea id="txt_dirigido_pt" name="txt_dirigido_pt"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','pt','txt_dirigido_es','txt_dirigido_pt');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>

                    <label class="info_modificar"><fmt:message key="even.descripcion_registro" /> (ES):</label>
                    <textarea id="txt_descripcion_registro_es" name="txt_descripcion_registro_es"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('en','es','txt_descripcion_registro_en','txt_descripcion_registro_es');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_espanol.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.descripcion_registro" /> (EN):</label>
                    <textarea id="txt_descripcion_registro_en" name="txt_descripcion_registro_en"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','en','txt_descripcion_registro_es','txt_descripcion_registro_en');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_ingles.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="even.descripcion_registro" /> (PT):</label>
                    <textarea id="txt_descripcion_registro_pt" name="txt_descripcion_registro_pt"
                              dojoType="dijit.form.SimpleTextarea" value=" "  trim="true"
                              maxlength="600" required="false"
                              invalidMessage="<fmt:message key="ge.necesita_valor" />"
                              style="float:left; width:145px; height:32px;" >
                    </textarea>
                    <span><a href="javascript:traduccion('es','pt','txt_descripcion_registro_es','txt_descripcion_registro_pt');" style="float:left; margin:3px 5px;">
                            <img src="/websae/images/ico_portugues.jpg" width="18px" alt="" />
                        </a></span>
                    <div class="clearthefloats"></div>
                </div>
                <div id="tab_info5" style="overflow:auto">
                    <label class="info_modificar"><fmt:message key="ge.correo_evento" />:</label>
                    <input type="text" id="txt_email" name="txt_email"
                           dojoType="dijit.form.ValidationTextBox" trim="true"
                           promptMessage="<fmt:message key="ge.necesita_valor" />"
                           invalidMessage="<fmt:message key="ge.formato_invalido" />"
                           trim="true" maxlength="50" regExp=".*@.*" />
                    <div class="clearthefloats"></div>
                    <label class="info_modificar"><fmt:message key="ge.incrustar" />:</label>
                    <textarea id="txt_agenda_general" name="txt_agenda_general" cols="30" rows="3" maxlength="900" onkeyup="return ismaxlength(this)"></textarea>
                    <div class="clearthefloats"></div>
                    <label class="info_modificar">Tutorial:</label>
                    <!--a href="/websae/archivo/tutorial_calendario.pdf" target="_blank" style="color: blue; float:left; margin-top:7px;" onclick="window.open(this.href, this.target, 'width=600, heigth=700, scrollbars=yes');">Ver...</a-->
                    <a href="/websae/archivo/tutorial_calendario.pdf" target="_blank" style="color: blue; float:left; margin-top:7px;">Ver...</a>
                    <div class="clearthefloats"></div>
                </div>
            </div>
            <div class="clearthefloats"></div>
        </div>

        <div id="div_status" style="margin-top:15px;" dojoType="dijit.layout.ContentPane">
            <span style="float: left; margin-left: 50px;"><fmt:message key="ge.estado" />:</span>
            <div style="float:left; width:300px;">
                <input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_vigente" value="V" checked="checked" /><fmt:message key="ge.vigente" /><!--/label-->
                <input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_pendiente" value="P" checked="checked" /><fmt:message key="ge.pendiente" /><!--/label-->
                <input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_realizado" value="R" /><fmt:message key="ge.realizado" />
                <input dojoType="dijit.form.RadioButton" type="radio" name="rb_status" id="rb_eliminado" value="E" /><fmt:message key="ge.eliminado" />
            </div>
        </div>
        <div class="clearthefloats"></div>
    </form>
    <div class="botones_administracion"><br />
        <button id="btn_registrar" dojoType="dijit.form.Button" onclick="accion_evento('registrar');" class="div_form" style="margin-left:50px;">
            <fmt:message key="ge.guardar" />
        </button>
        <button id="btn_modificar" dojoType="dijit.form.Button" onclick="accion_evento('modificar');" class="div_form" style="margin-left:50px;">
            <fmt:message key="ge.modificar" />
        </button>
        <button id="btn_cancelar" dojoType="dijit.form.Button" onclick="dijit.byId('div_evento').hide();" class="div_form">
            <fmt:message key="ge.cancelar" />
        </button>
        <button id="btn_cerrar" dojoType="dijit.form.Button" onclick="dijit.byId('div_evento').hide();" class="div_form" style="margin-left:80px;">
            <fmt:message key="ge.cerrar" />
        </button><br /><br />
    </div>
</div>
