DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_subcategoria_evento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_subcategoria_evento`(
    tipo VARCHAR(10),
    id_subcategoria_evento BIGINT(20),
    porcentaje_descuento FLOAT(9,2),
    id_subcategoria BIGINT(20),
    id_evento BIGINT(20),
    OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN

        SELECT se_id_subcategoria_evento INTO id FROM AE_Subcategoria_Evento WHERE ref_subcategoria = id_subcategoria AND ref_evento = id_evento;
        IF id <=> NULL THEN
           INSERT INTO AE_Subcategoria_Evento (se_porcentaje_descuento, ref_subcategoria, ref_evento) VALUES (porcentaje_descuento, id_subcategoria, id_evento);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;

     ELSEIF tipo = 'modificar' THEN
        SELECT se_id_subcategoria_evento INTO id FROM AE_Subcategoria_Evento WHERE se_porcentaje_descuento = porcentaje_descuento AND ref_subcategoria = id_subcategoria AND ref_evento = id_evento AND se_id_subcategoria_evento <> id_subcategoria_evento;
        IF id <=> NULL THEN
           UPDATE AE_Subcategoria_Evento SET se_porcentaje_descuento = porcentaje_descuento, ref_subcategoria = id_subcategoria, ref_evento = id_evento WHERE se_id_subcategoria_evento = id_subcategoria_evento;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'eliminar' THEN
        DELETE FROM AE_Subcategoria_Evento WHERE se_id_subcategoria_evento = id_subcategoria_evento;
        SET mensaje = 'OK:eliminar';
     END IF;
END $$

DELIMITER ;