DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_categoria_evento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_categoria_evento`(
    tipo VARCHAR(10),
    id_categoria_evento BIGINT(20),
    precio FLOAT(9,2),
    fecha_inicio DATE,
    fecha_fin DATE,
    id_categoria BIGINT(20),
    id_evento BIGINT(20),
    OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE indicador INTEGER(11);

     IF tipo = 'registrar' THEN

        SELECT COUNT(*) INTO indicador FROM AE_Categoria_Evento
        WHERE ref_evento = id_evento AND ref_categoria = id_categoria AND
            ((fecha_inicio > ce_fecha_inicio AND fecha_inicio < ce_fecha_fin) OR
             (fecha_fin > ce_fecha_inicio AND fecha_fin < ce_fecha_fin) OR
             (fecha_inicio < ce_fecha_inicio AND fecha_fin > ce_fecha_fin) OR
             (fecha_fin = ce_fecha_inicio OR fecha_inicio = ce_fecha_fin));

        IF indicador > 0 THEN
           SET mensaje = 'ERROR:fuera-rango';
        ELSE
           SELECT ce_id_categoria_evento INTO id FROM AE_Categoria_Evento WHERE ce_precio = precio AND ce_fecha_inicio = fecha_inicio AND ce_fecha_fin = fecha_fin AND ref_categoria = id_categoria AND ref_evento = id_evento;
           IF id <=> NULL THEN
             INSERT INTO AE_Categoria_Evento (ce_precio, ce_fecha_inicio, ce_fecha_fin, ref_categoria, ref_evento) VALUES (precio, fecha_inicio, fecha_fin, id_categoria, id_evento);
             SET mensaje = 'OK:registrar';
           ELSE
             SET mensaje = 'ERROR:repetido';
           END IF;
        END IF;

     ELSEIF tipo = 'modificar' THEN

        SELECT COUNT(*) INTO indicador FROM AE_Categoria_Evento
        WHERE ref_evento = id_evento AND ref_categoria = id_categoria AND ce_id_categoria_evento <> id_categoria_evento AND
            ((fecha_inicio > ce_fecha_inicio AND fecha_inicio < ce_fecha_fin) OR
             (fecha_fin > ce_fecha_inicio AND fecha_fin < ce_fecha_fin) OR
             (fecha_inicio < ce_fecha_inicio AND fecha_fin > ce_fecha_fin) OR
             (fecha_fin = ce_fecha_inicio OR fecha_inicio = ce_fecha_fin));

        IF indicador > 0 THEN
           SET mensaje = 'ERROR:fuera-rango';
        ELSE
           SELECT ce_id_categoria_evento INTO id FROM AE_Categoria_Evento WHERE ce_precio = precio AND ce_fecha_inicio = fecha_inicio AND ce_fecha_fin = fecha_fin AND ref_categoria = id_categoria AND ref_evento = id_evento AND ce_id_categoria_evento <> id_categoria_evento;
           IF id <=> NULL THEN
             UPDATE AE_Categoria_Evento SET ce_precio = precio, ce_fecha_inicio = fecha_inicio, ce_fecha_fin = fecha_fin, ref_categoria = id_categoria, ref_evento = id_evento WHERE ce_id_categoria_evento = id_categoria_evento;
             SET mensaje = 'OK:modificar';
           ELSE
             SET mensaje = 'ERROR:repetido';
           END IF;
        END IF;

     ELSEIF tipo = 'eliminar' THEN
        DELETE FROM AE_Categoria_Evento WHERE ce_id_categoria_evento = id_categoria_evento;
        SET mensaje = 'OK:eliminar';
     END IF;

END $$

DELIMITER ;