DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_evento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_evento`(
     tipo VARCHAR(10),
     id_evento BIGINT(20),
     nombre_es VARCHAR(500),
     nombre_en VARCHAR(500),
     nombre_pt VARCHAR(500),
     lugar VARCHAR(300),
     objetivo_es TEXT,
     objetivo_en TEXT,
     objetivo_pt TEXT,
     descripcion_es TEXT,
     descripcion_en TEXT,
     descripcion_pt TEXT,
     dirigido_es TEXT,
     dirigido_en TEXT,
     dirigido_pt TEXT,
     fecha_inicio DATE,
     fecha_fin DATE,
     slogan_es VARCHAR(500),
     slogan_en VARCHAR(500),
     slogan_pt VARCHAR(500),
     imagen VARCHAR(100),
     email VARCHAR(50),
     agenda_general VARCHAR(50),
     estado CHAR(1),
     descripcion_registro_es TEXT,
     descripcion_registro_en TEXT,
     descripcion_registro_pt TEXT,
     estado_registro CHAR(1),
     id_tipo_evento BIGINT(20),
     id_ciudad BIGINT(20),
     OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN
        SELECT ev_id_evento INTO id FROM AE_Evento
        WHERE ev_nombre_es = nombre_es OR ev_nombre_en = nombre_en OR ev_nombre_pt = nombre_pt;

        IF id <=> NULL THEN
           INSERT INTO AE_Evento (ev_nombre_es, ev_nombre_en, ev_nombre_pt,
                                  ev_lugar,
                                  ev_objetivo_es, ev_objetivo_en, ev_objetivo_pt,
                                  ev_descripcion_es, ev_descripcion_en, ev_descripcion_pt,
                                  ev_dirigido_es, ev_dirigido_en, ev_dirigido_pt,
                                  ev_fecha_inicio, ev_fecha_fin,
                                  ev_slogan_es, ev_slogan_en, ev_slogan_pt,
                                  ev_imagen, ev_email, ev_agenda_general, ev_estado,
                                  ev_descripcion_registro_es, ev_descripcion_registro_en, ev_descripcion_registro_pt,
                                  ev_estado_registro, ref_tipo_evento, ref_ciudad, ref_faq)
                          VALUES (nombre_es, nombre_en, nombre_pt,
                                  lugar,
                                  objetivo_es, objetivo_en, objetivo_pt,
                                  descripcion_es, descripcion_en, descripcion_pt,
                                  dirigido_es, dirigido_en, dirigido_pt,
                                  fecha_inicio, fecha_fin,
                                  slogan_es, slogan_en, slogan_pt,
                                  imagen, email, agenda_general, estado,
                                  descripcion_registro_es, descripcion_registro_en, descripcion_registro_pt,
                                  estado_registro, id_tipo_evento, id_ciudad, NULL);
                                  
           SELECT ev_id_evento INTO id FROM AE_Evento WHERE ev_nombre_es = nombre_es OR ev_nombre_en = nombre_en OR ev_nombre_pt = nombre_pt;
           SET mensaje = 'OK:registro';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        UPDATE AE_Evento SET ev_nombre_es = nombre_es, ev_nombre_en = nombre_en, ev_nombre_pt = nombre_pt,
                             ev_lugar = lugar,
                             ev_objetivo_es = objetivo_es, ev_objetivo_en = objetivo_en, ev_objetivo_pt = objetivo_pt,
                             ev_descripcion_es = descripcion_es, ev_descripcion_en = descripcion_en, ev_descripcion_pt = descripcion_pt,
                             ev_dirigido_es = dirigido_es, ev_dirigido_en = dirigido_en, ev_dirigido_pt = dirigido_pt,
                             ev_fecha_inicio = fecha_inicio, ev_fecha_fin = fecha_fin,
                             ev_slogan_es = slogan_es, ev_slogan_en = slogan_en, ev_slogan_pt = slogan_pt,
                             ev_imagen = imagen, ev_email = email, ev_agenda_general = agenda_general, ev_estado = estado,
                             ev_descripcion_registro_es = descripcion_registro_es, ev_descripcion_registro_en = descripcion_registro_en, ev_descripcion_registro_pt = descripcion_registro_pt,
                             ev_estado_registro = estado_registro, ref_tipo_evento = id_tipo_evento, ref_ciudad = id_ciudad
        WHERE ev_id_evento = id_evento;
        
        SET mensaje = 'OK:modificar';
     END IF;
END $$

DELIMITER ;