DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ce_administrar_evaluacion` $$
CREATE PROCEDURE `websae`.`ce_administrar_evaluacion` (
     tipo VARCHAR(10),
     mensaje_evaluacion TEXT,
     descripcion TEXT,
     estado CHAR(1),
     id_evento BIGINT(20),
     OUT mensaje VARCHAR(50)
)
BEGIN
     DECLARE id BIGINT(20);

     SELECT co_id_convocatoria INTO id FROM CE_Convocatoria WHERE ref_evento = id_evento;

     IF tipo = 'registrar' THEN

         IF id IS NOT NULL THEN
             INSERT INTO CE_Evaluacion (ev_mensaje, ev_descripcion, ev_estado, ref_convocatoria) VALUES (mensaje_evaluacion, descripcion, estado, id);
             SET mensaje = 'OK:registrar';
         END IF;

     ELSEIF tipo = 'modificar' THEN

         IF id IS NOT NULL THEN
             UPDATE CE_Evaluacion SET ev_mensaje = mensaje_evaluacion, ev_descripcion = descripcion, ev_estado = estado WHERE ref_convocatoria = id;
             SET mensaje = 'OK:modificar';
         END IF;

     END IF;

END $$

DELIMITER ;