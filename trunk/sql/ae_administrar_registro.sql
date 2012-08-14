DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_registro` $$
CREATE PROCEDURE `websae`.`ae_administrar_registro` (
     tipo VARCHAR(10),
     id_registro BIGINT(20),
     fecha DATETIME,
     valor_abonado FLOAT,
     valor_total FLOAT,
     aprobar_descuento BINARY,
     asistencia BINARY,
     pagado BINARY,
     estado CHAR(1),
     id_categoria_evento BIGINT(20),
     id_subcategoria_evento BIGINT(20),
     id_evento BIGINT(20),
     id_usuario BIGINT(20),
     OUT mensaje VARCHAR(50)
)
BEGIN
     DECLARE id BIGINT(20);
     DECLARE indicador INTEGER(11);

     SELECT re_id_registro INTO id FROM AE_Registro WHERE ref_evento = id_evento AND ref_usuario = id_usuario;
     IF id <=> NULL THEN
        SET tipo='registrar';
     ELSE
        SET tipo='modificar';
     END IF;

     IF tipo = 'registrar' THEN
         IF id <=> NULL THEN
            INSERT INTO AE_Registro (re_fecha, re_valor_abonado, re_valor_total, re_aprobar_descuento, re_asistencia, re_pagado, re_estado, ref_categoria_evento, ref_subcategoria_evento, ref_evento, ref_usuario)
                             VALUES (fecha, valor_abonado, valor_total, aprobar_descuento, asistencia, pagado, estado, id_categoria_evento, id_subcategoria_evento, id_evento, id_usuario);
            SET mensaje = 'OK:registrar';
         ELSE
            SET mensaje = 'ERROR:repetido';
         END IF;
     ELSEIF tipo = 'modificar' THEN
         UPDATE AE_Registro SET re_fecha = fecha, re_valor_abonado = valor_abonado, re_valor_total = valor_total, re_aprobar_descuento = aprobar_descuento, re_asistencia = asistencia, re_pagado = pagado, re_estado = estado, ref_categoria_evento = id_categoria_evento, ref_subcategoria_evento = id_subcategoria_evento, ref_evento = id_evento, ref_usuario = id_usuario WHERE re_id_registro = id;
         SET mensaje = 'OK:modificar';
     ELSEIF tipo = 'eliminar' THEN
         SELECT COUNT(*) INTO indicador FROM CE_Seccion se, CE_Pregunta pr WHERE se.se_id_seccion = pr.ref_seccion;
         IF indicador > 0 THEN
            SET mensaje = 'ERROR:asignado';
         ELSE
            DELETE FROM AE_Registro WHERE se.se_id_seccion = pr.ref_seccion;
         END IF;
     END IF;

END $$

DELIMITER ;