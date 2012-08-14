DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_material` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_material`(tipo VARCHAR(10), id_material BIGINT(20), precio FLOAT(9,2), descripcion VARCHAR(200), cantidad_entregar INTEGER(11), estado CHAR(1), id_tipo_material BIGINT(20), id_evento BIGINT(20), OUT mensaje VARCHAR(20))
BEGIN
    DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN
        SELECT ma_id_material INTO id FROM AE_Material WHERE ref_evento = id_evento AND ref_tipo_material = id_tipo_material;
        IF id <=> NULL THEN
           INSERT INTO AE_Material (ma_precio, ma_descripcion, ma_cantidad_entregar, ma_estado, ref_tipo_material, ref_evento)
                            VALUES (precio, descripcion, cantidad_entregar, estado, id_tipo_material, id_evento);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT ma_id_material INTO id FROM AE_Material WHERE ref_evento = id_evento AND ref_tipo_material = id_tipo_material AND ma_id_material <> id_material;
        IF id <=> NULL THEN
           UPDATE AE_Material SET ma_precio = precio, ma_descripcion = descripcion, ma_cantidad_entregar = cantidad_entregar,
                                  ma_estado = estado, ref_tipo_material = id_tipo_material, ref_evento = id_evento
           WHERE ma_id_material = id_material;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        DELETE FROM AE_Material WHERE ma_id_material = id_material;
        SET mensaje = 'OK:eliminar';
     END IF;
END $$

DELIMITER ;