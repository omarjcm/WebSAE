DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ce_administrar_seccion` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ce_administrar_seccion`(
     tipo VARCHAR(10),
     id_seccion BIGINT(20),
     titulo VARCHAR(500),
     descripcion VARCHAR(500),
     oculta BOOLEAN,
     id_evaluacion BIGINT(20),
     OUT mensaje VARCHAR(50)
)
BEGIN
     DECLARE id BIGINT(20);
     DECLARE indicador INTEGER(11);

     IF tipo = 'registrar' THEN
         SELECT se_id_seccion INTO id FROM CE_Seccion WHERE se_nombre = titulo AND se_descripcion = descripcion AND se_oculta = oculta AND ref_evaluacion = id_evaluacion;
         IF id <=> NULL THEN
            INSERT INTO CE_Seccion (se_nombre, se_descripcion, se_oculta, ref_evaluacion) VALUES (titulo, descripcion, oculta, id_evaluacion);
            SET mensaje = 'OK:registrar';
         ELSE
            SET mensaje = 'ERROR:repetido';
         END IF;
     ELSEIF tipo = 'modificar' THEN
         SELECT se_id_seccion INTO id FROM CE_Seccion WHERE se_nombre = titulo AND se_descripcion = descripcion AND se_oculta = oculta AND ref_evaluacion = id_evaluacion AND se_id_seccion <> id_seccion;
         IF id <=> NULL THEN
            UPDATE CE_Seccion SET se_nombre = titulo, se_descripcion = descripcion, se_oculta = oculta WHERE se_id_seccion = id_seccion;
            SET mensaje = 'OK:modificar';
         ELSE
            SET mensaje = 'ERROR:repetido';
         END IF;
     ELSEIF tipo = 'eliminar' THEN
         SELECT COUNT(*) INTO indicador FROM CE_Seccion se, CE_Pregunta pr WHERE se.se_id_seccion = pr.ref_seccion;
         IF indicador > 0 THEN
            SET mensaje = 'ERROR:asignado';
         ELSE
            DELETE FROM CE_Seccion WHERE se_id_seccion = id_seccion;
         END IF;
     END IF;

END $$

DELIMITER ;