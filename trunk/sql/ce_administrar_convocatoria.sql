DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ce_administrar_convocatoria` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ce_administrar_convocatoria`(
     tipo VARCHAR(10),
     descripcion_es TEXT,
     descripcion_en TEXT,
     descripcion_pt TEXT,
     ruta_formato VARCHAR(100),
     fecha_max_presentacion_resumen DATE,
     fecha_max_presentacion DATE,
     fecha_max_evaluacion DATE,
     fecha_max_aceptacion DATE,
     fecha_max_correccion DATE,
     estado CHAR(1),
     id_evento BIGINT(20),
     OUT mensaje VARCHAR(50),
     OUT id_objeto BIGINT(20)
)
BEGIN
     DECLARE id BIGINT(20);

     SELECT co_id_convocatoria INTO id FROM CE_Convocatoria WHERE ref_evento = id_evento;
     IF id <=> NULL THEN
        SET tipo='registrar';
     ELSE
        SET tipo='modificar';
     END IF;

     IF tipo = 'registrar' THEN
        INSERT INTO CE_Convocatoria (co_descripcion_es, co_descripcion_en, co_descripcion_pt, co_ruta_formato,
                                     co_fecha_max_presentacion_resumen, co_fecha_max_presentacion,
                                     co_fecha_max_evaluacion, co_fecha_max_aceptacion, co_fecha_max_correccion,
                                     co_estado, ref_evento)
                             VALUES (descripcion_es, descripcion_en, descripcion_pt, ruta_formato,
                                     fecha_max_presentacion_resumen, fecha_max_presentacion,
                                     fecha_max_evaluacion, fecha_max_aceptacion, fecha_max_correccion,
                                     estado, id_evento);
        SET mensaje = 'OK:registrar';
     END IF;

     IF tipo = 'modificar' THEN
        UPDATE CE_Convocatoria SET co_descripcion_es = descripcion_es, co_descripcion_en = descripcion_en,
                                   co_descripcion_pt = descripcion_pt, co_ruta_formato = ruta_formato,
                                   co_fecha_max_presentacion_resumen = fecha_max_presentacion_resumen,
                                   co_fecha_max_presentacion = fecha_max_presentacion,
                                   co_fecha_max_evaluacion = fecha_max_evaluacion,
                                   co_fecha_max_aceptacion = fecha_max_aceptacion,
                                   co_fecha_max_correccion = fecha_max_correccion,
                                   co_estado = co_estado
        WHERE ref_evento = id_evento;
        SET mensaje = 'OK:modificar';
     END IF;

     SELECT co_id_convocatoria INTO id_objeto FROM CE_Convocatoria WHERE ref_evento = id_evento;
END $$

DELIMITER ;