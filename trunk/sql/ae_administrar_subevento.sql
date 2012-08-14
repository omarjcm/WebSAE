DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_subevento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_subevento`(
     tipo VARCHAR(20),
     id_subevento BIGINT(20),
     nombre_es VARCHAR(500),
     nombre_en VARCHAR(500),
     nombre_pt VARCHAR(500),
     descripcion_es TEXT,
     descripcion_en TEXT,
     descripcion_pt TEXT,
     cupo_maximo INTEGER(11),
     cupo_disponible INTEGER(11),
     id_tipo_evento BIGINT(20),
     id_evento BIGINT(20),
     OUT mensaje VARCHAR(20) )
BEGIN
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN
        INSERT INTO AE_Evento (ev_nombre_es, ev_nombre_en, ev_nombre_pt, ev_descripcion_es, ev_descripcion_en, ev_descripcion_pt, ev_cupo_maximo, ev_cupo_disponible, ref_tipo_evento) VALUES (nombre_es, nombre_en, nombre_pt, descripcion_es, descripcion_en, descripcion_pt, cupo_maximo, cupo_maximo, id_tipo_evento);
        SELECT ev_id_evento INTO id FROM AE_Evento ev WHERE ev.ev_nombre_es = nombre_es AND ev_nombre_en = nombre_en AND ev_nombre_pt = nombre_pt AND ev_descripcion_es = descripcion_es AND ev_descripcion_en = descripcion_en AND ev_descripcion_pt = descripcion_pt AND ref_tipo_evento = id_tipo_evento;
        INSERT INTO AE_Asignar_Evento (ae_estado, ref_evento, ref_subevento) VALUES ('V', id_evento, id);
        SET mensaje = 'OK:registrar';
     ELSEIF tipo = 'modificar' THEN
        UPDATE AE_Evento SET ev_nombre_es = nombre_es, ev_nombre_en = nombre_en, ev_nombre_pt = nombre_pt, ev_descripcion_es = descripcion_es, ev_descripcion_en = descripcion_en, ev_descripcion_pt = descripcion_pt, ev_cupo_maximo = cupo_maximo, ref_tipo_evento = id_tipo_evento WHERE ev_id_evento = id_subevento;
        SET mensaje = 'OK:modificar';
     ELSEIF tipo = 'eliminar' THEN

        SET autocommit=0;

        /* PASO 1: Procesamiento de la eliminación de los datos de la agenda de un subevento. */
        DELETE FROM AE_Actividad WHERE ref_fecha_evento IN (SELECT fe_id_fecha_evento FROM AE_Fecha_Evento WHERE ref_evento = id_subevento);
        DELETE FROM AE_Fecha_Evento WHERE ref_evento = id_subevento;
        /* PASO 2: Procesamiento de la eliminación de los materiales de un subevento. */
        DELETE FROM AE_Material WHERE ref_evento = id_subevento;
        /* PASO 3: Procesamiento de la eliminación de los precios de un subevento. */
        DELETE FROM AE_Categoria_Evento WHERE ref_evento = id_subevento;
        /* PASO 4: Procesamiento de la eliminación de los descuentos de un subevento. */
        DELETE FROM AE_Subcategoria_Evento WHERE ref_evento = id_subevento;
        /* PASO 5: Procesamiento de la eliminación de la eliminacion de un subevento a un evento. */
        DELETE FROM AE_Asignar_Evento WHERE ref_evento = id_evento AND ref_subevento = id_subevento;
        /* PASO 6: Eliminación del Evento. */
        DELETE FROM AE_Evento WHERE ev_id_evento = id_subevento;

        SET mensaje = 'OK:eliminar';

        COMMIT;
     END IF;
END $$

DELIMITER ;