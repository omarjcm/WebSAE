DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_tipo_material` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_tipo_material`(tipo VARCHAR(10), id_tipo_material BIGINT(20), nombre VARCHAR(100), estado CHAR(1), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT tm_id_tipo_material INTO id FROM AE_Tipo_Material WHERE tm_nombre = nombre;

        IF id <=> NULL THEN
           INSERT INTO AE_Tipo_Material (tm_nombre, tm_estado) VALUES (nombre, estado);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT tm_id_tipo_material INTO id FROM AE_Tipo_Material WHERE tm_nombre = nombre AND tm_id_tipo_material <> id_tipo_material;
        IF id <=> NULL THEN
           UPDATE AE_Tipo_Material SET tm_nombre = nombre, tm_estado = estado WHERE tm_id_tipo_material = id_tipo_material;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(tm_id_tipo_material) INTO contador FROM AE_Tipo_Material tm, AE_Material ma WHERE tm.tm_id_tipo_material = ma.ref_tipo_material AND tm.tm_id_tipo_material = id_tipo_material;
        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM AE_Tipo_Material WHERE tm_id_tipo_material = id_tipo_material;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;