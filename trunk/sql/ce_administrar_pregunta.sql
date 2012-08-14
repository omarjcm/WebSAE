DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ce_administrar_pregunta` $$
CREATE PROCEDURE `websae`.`ce_administrar_pregunta` (
     tipo VARCHAR(10),
     id_pregunta BIGINT(20),
     nombre TEXT,
     id_seccion BIGINT(20),
     id_tipo_pregunta BIGINT(20),
     OUT mensaje VARCHAR(50)
)
BEGIN
     DECLARE indicador INTEGER(11);
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN

        SELECT pr_id_pregunta INTO id FROM CE_Pregunta WHERE ref_seccion = id_seccion AND ref_tipo_pregunta = tema;
        IF id <=> NULL THEN
           INSERT INTO CE_Pregunta (pr_nombre, ref_seccion, ref_) VALUES (tema, id_convocatoria);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;

     ELSEIF tipo = 'modificar' THEN

     ELSEIF tipo = 'eliminar' THEN

     END IF;

END $$

DELIMITER ;