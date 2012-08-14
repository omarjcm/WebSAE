DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_actividad` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_actividad`(
     tipo VARCHAR(10),
     id_actividad BIGINT(20),
     hora_inicio VARCHAR(10),
     hora_fin VARCHAR(10),
     actividad TEXT,
     id_fecha_evento BIGINT(20),
     OUT mensaje VARCHAR(20))
BEGIN
     DECLARE indicador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT COUNT(*) INTO indicador FROM AE_Actividad
        WHERE ref_fecha_evento = id_fecha_evento AND
            ((hora_inicio >= ac_hora_inicio AND hora_inicio < ac_hora_fin) OR
             (hora_fin > ac_hora_inicio AND hora_fin <= ac_hora_fin) OR
             (hora_inicio < ac_hora_inicio AND hora_fin > ac_hora_fin));

        IF indicador > 0 THEN
           SET mensaje = 'ERROR:fuera-rango';
        ELSE
           INSERT INTO AE_Actividad (ac_hora_inicio, ac_hora_fin, ac_actividad, ref_fecha_evento) VALUES (hora_inicio, hora_fin, actividad, id_fecha_evento);
           SET mensaje = 'OK:registrar';
        END IF;

     ELSEIF tipo = 'modificar' THEN
        SELECT COUNT(*) INTO indicador FROM AE_Actividad
        WHERE ref_fecha_evento = id_fecha_evento AND ac_id_actividad <> id_actividad AND
            ((hora_inicio >= ac_hora_inicio AND hora_inicio < ac_hora_fin) OR
             (hora_fin > ac_hora_inicio AND hora_fin <= ac_hora_fin) OR
             (hora_inicio < ac_hora_inicio AND hora_fin > ac_hora_fin));

        IF indicador > 0 THEN
           SET mensaje = 'ERROR:fuera-rango';
        ELSE
           UPDATE AE_Actividad SET ac_hora_inicio = hora_inicio, ac_hora_fin = hora_fin, ac_actividad = actividad, ref_fecha_evento = id_fecha_evento WHERE ac_id_actividad = id_actividad;
           SET mensaje = 'OK:modificar';
        END IF;

     ELSEIF tipo = 'eliminar' THEN
        DELETE FROM AE_Actividad WHERE ac_id_actividad = id_actividad;
        SET mensaje = 'OK:eliminar';
     END IF;
END $$

DELIMITER ;