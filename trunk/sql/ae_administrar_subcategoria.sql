DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_subcategoria` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_subcategoria`(
     tipo VARCHAR(10),
     id_subcategoria BIGINT(20),
     nombre CHAR(200),
     estado CHAR(1),
     id_categoria BIGINT(20),
     OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE indicador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT su_id_subcategoria INTO id FROM AE_Subcategoria WHERE su_nombre = nombre AND ref_categoria = id_categoria;
        IF id <=> NULL THEN
           INSERT INTO AE_Subcategoria (su_nombre, su_estado, ref_categoria) VALUES (nombre, estado, id_categoria);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT su_id_subcategoria INTO id FROM AE_Subcategoria WHERE su_nombre = nombre AND ref_categoria = id_categoria AND su_id_subcategoria <> id_subcategoria;
        IF id <=> NULL THEN
           UPDATE AE_Subcategoria SET su_nombre = nombre, su_estado = estado, ref_categoria = id_categoria WHERE su_id_subcategoria = id_subcategoria;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(*) INTO indicador FROM AE_Subcategoria su, AE_Subcategoria_Evento se WHERE su.su_id_subcategoria = se.ref_subcategoria AND su.su_id_subcategoria = id_subcategoria;
        IF indicador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM AE_Subcategoria WHERE su_id_subcategoria = id_subcategoria;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;