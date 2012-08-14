DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_organizador` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_organizador`(
    tipo VARCHAR(10),
    id_organizador BIGINT(20),
    id_grupo_investigacion BIGINT(20),
    id_empresa BIGINT(20),
    tipo_organizador CHAR(1),
    id_evento BIGINT(20),
    OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id BIGINT(20);

     SELECT or_id_organizador INTO id FROM AE_Organizador WHERE (ref_grupo_investigacion = id_grupo_investigacion OR ref_empresa = id_empresa) AND ref_evento = id_evento;
     IF tipo = 'registrar' THEN
        IF id <=> NULL THEN
           INSERT INTO AE_Organizador (or_tipo, ref_grupo_investigacion, ref_empresa, ref_evento) VALUES (tipo_organizador, id_grupo_investigacion, id_empresa, id_evento);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'eliminar' THEN
        DELETE FROM AE_Organizador WHERE or_id_organizador = id_organizador;
        SET mensaje = 'OK:eliminar';
     END IF;
END $$

DELIMITER ;