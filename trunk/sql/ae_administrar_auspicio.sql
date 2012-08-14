DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_auspicio` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_auspicio`(tipo VARCHAR(10), id_auspicio BIGINT(20), monto FLOAT(9,2), descripcion TEXT, id_evento BIGINT(20), id_empresa BIGINT(20), OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN
        SELECT au_id_auspicio INTO id FROM AE_Auspicio WHERE ref_evento = id_evento AND ref_empresa = id_empresa;
        IF id <=> NULL THEN
           INSERT INTO AE_Auspicio (au_monto, au_descripcion, ref_evento, ref_empresa) VALUES (monto, descripcion, id_evento, id_empresa);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'modificar' THEN
        UPDATE AE_Auspicio SET au_monto = monto, au_descripcion = descripcion, ref_evento = id_evento, ref_empresa = id_empresa WHERE au_id_auspicio = id_auspicio;
        SET mensaje = 'OK:modificar';
     ELSEIF tipo = 'eliminar' THEN
        DELETE FROM AE_Auspicio WHERE au_id_auspicio = id_auspicio;
        SET mensaje = 'OK:eliminar';
     END IF;
END $$

DELIMITER ;