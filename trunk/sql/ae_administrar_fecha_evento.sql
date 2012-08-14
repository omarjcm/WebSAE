DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_fecha_evento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_fecha_evento`(tipo VARCHAR(10), id_fecha_evento BIGINT(20), fecha DATE, id_evento BIGINT(20), OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN
        SELECT fe_id_fecha_evento INTO id FROM AE_Fecha_Evento WHERE ref_evento = id_evento AND fe_fecha = fecha;
        IF id <=> NULL THEN
           INSERT INTO AE_Fecha_Evento (fe_fecha, ref_evento) VALUES (fecha, id_evento);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'modificar' THEN
        SELECT fe_id_fecha_evento INTO id FROM AE_Fecha_Evento WHERE fe_fecha = fecha AND ref_evento = id_evento AND fe_id_fecha_evento <> id_fecha_evento;
        IF id <=> NULL THEN
           UPDATE AE_Fecha_Evento SET fe_fecha = fecha WHERE fe_id_fecha_evento = id_fecha_evento;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'eliminar' THEN
        DELETE FROM AE_Actividad WHERE ref_fecha_evento = id_fecha_evento;
        DELETE FROM AE_Fecha_Evento WHERE fe_id_fecha_evento = id_fecha_evento;
        SET mensaje = 'OK:eliminar';
     END IF;
END $$

DELIMITER ;