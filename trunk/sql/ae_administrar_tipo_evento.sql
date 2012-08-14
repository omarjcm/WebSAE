DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_tipo_evento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_tipo_evento`(tipo VARCHAR(10), id_tipo_evento BIGINT(20), nombre VARCHAR(100), estado CHAR(1), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT te_id_tipo_evento INTO id FROM `ae_tipo_evento` WHERE te_nombre = nombre;

        IF id <=> NULL THEN
           INSERT INTO `ae_tipo_evento` (te_nombre, te_estado) VALUES (nombre, estado);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT te_id_tipo_evento INTO id FROM AE_Tipo_Evento WHERE te_nombre = nombre AND te_id_tipo_evento <> id_tipo_evento;
        IF id <=> NULL THEN
           UPDATE AE_Tipo_Evento SET te_nombre = nombre, te_estado = estado WHERE te_id_tipo_evento = id_tipo_evento;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(te_id_tipo_evento) INTO contador FROM AE_Tipo_Evento te, AE_Evento ev WHERE te.te_id_tipo_evento = ev.ref_tipo_evento AND te.te_id_tipo_evento = id_tipo_evento;
        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM AE_Tipo_Evento WHERE te_id_tipo_evento = id_tipo_evento;
              SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;