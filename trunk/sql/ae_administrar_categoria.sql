DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_categoria` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_categoria`(tipo VARCHAR(10), id_categoria BIGINT(20), nombre VARCHAR(100), estado CHAR(1), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT ca_id_categoria INTO id FROM `ae_categoria` WHERE ca_nombre = nombre;
        IF id <=> NULL THEN
           INSERT INTO `ae_categoria` (ca_nombre, ca_estado) VALUES (nombre, estado);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT ca_id_categoria INTO id FROM AE_Categoria WHERE ca_nombre = nombre AND ca_id_categoria <> id_categoria;
        IF id <=> NULL THEN
           UPDATE AE_Categoria SET ca_nombre = nombre, ca_estado = estado WHERE ca_id_categoria = id_categoria;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(ca.ca_id_categoria) INTO contador FROM AE_Categoria ca, AE_Subcategoria su, AE_Categoria_Evento ce WHERE ca.ca_id_categoria = id_categoria AND (ca.ca_id_categoria = su.ref_categoria OR ca.ca_id_categoria = ce.ref_categoria);
        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM AE_Categoria WHERE ca_id_categoria = id_categoria;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;