DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ce_administrar_alternativa` $$
CREATE PROCEDURE `websae`.`ce_administrar_alternativa` (
     tipo VARCHAR(10),
     id_alternativa BIGINT(20),
     nombre VARCHAR(500),
     id_pregunta BIGINT(20),
     OUT mensaje VARCHAR(50)
)
BEGIN
     DECLARE id BIGINT(20);
     DECLARE indicador INTEGER(11);
     
     IF tipo = 'registrar' THEN
         SELECT al_id_alternativa INTO id FROM CE_Alternativa WHERE al_nombre = nombre;
         IF id <=> NULL THEN
            INSERT INTO CE_Alternativa (al_nombre, ref_pregunta) VALUES (nombre, id_pregunta);
            SET mensaje = 'OK:registrar';
         ELSE
            SET mensaje = 'ERROR:repetido';
         END IF;
     ELSEIF tipo = 'modificar' THEN
         SELECT al_id_alternativa INTO id FROM CE_Alternativa WHERE al_nombre = nombre AND ref_pregunta = id_pregunta AND al_id_alternativa <> id_alternativa;
         IF id <=> NULL THEN
            UPDATE CE_Alternativa SET al_nombre = nombre WHERE al_id_alternativa = id_alternativa;
            SET mensaje = 'OK:modificar';
         ELSE
            SET mensaje = 'ERROR:repetido';
         END IF;
     ELSEIF tipo = 'eliminar' THEN
         DELETE FROM AE_Registro WHERE al_id_alternativa = id_alternativa;
     END IF;
END $$

DELIMITER ;