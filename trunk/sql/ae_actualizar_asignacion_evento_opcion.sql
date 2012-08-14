DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_actualizar_asignacion_evento_opcion` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_actualizar_asignacion_evento_opcion`(id_evento BIGINT(20), id_opcion BIGINT(20), estado CHAR(1))
BEGIN
    DECLARE id BIGINT(20);
    
    SELECT eo_id_evento_opcion INTO id FROM AE_Evento_Opcion WHERE ref_evento = id_evento AND ref_opcion = id_opcion;
    IF id <=> NULL THEN
        INSERT INTO AE_Evento_Opcion (eo_estado, ref_evento, ref_opcion) VALUES (estado, id_evento, id_opcion);
    END IF;
    UPDATE AE_Evento_Opcion SET eo_estado = estado WHERE eo_id_evento_opcion = id;
END $$

DELIMITER ;