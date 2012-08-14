DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`gi_administrar_grupo_investigacion` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `gi_administrar_grupo_investigacion`(
     tipo VARCHAR(10),
     id_grupo_investigacion BIGINT(20),
     nombre VARCHAR(300),
     objetivo TEXT,
     logo VARCHAR(300),
     web VARCHAR(300),
     telefono VARCHAR(20),
     OUT mensaje VARCHAR(20))

BEGIN
     DECLARE id BIGINT(20);
     DECLARE indicador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT gi_id_grupo_investigacion INTO id FROM GI_Grupo_Investigacion WHERE gi_nombre = nombre;
        IF id <=> NULL THEN
           INSERT INTO GI_Grupo_Investigacion (gi_nombre, gi_objetivo, gi_logo, gi_web, gi_telefono) VALUES (nombre, objetivo, logo, web, telefono);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'modificar' THEN
        SELECT gi_id_grupo_investigacion INTO id FROM GI_Grupo_Investigacion WHERE gi_nombre = nombre AND gi_id_grupo_investigacion <> id_grupo_investigacion;
        IF id <=> NULL THEN
           UPDATE GI_Grupo_Investigacion SET gi_nombre = nombre, gi_objetivo = objetivo, gi_logo = logo, gi_web = web, gi_telefono = telefono WHERE gi_id_grupo_investigacion = id_grupo_investigacion;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     ELSEIF tipo = 'eliminar' THEN
        SELECT COUNT(*) INTO indicador FROM GI_Grupo_Investigacion gi, AE_Organizador org WHERE gi.gi_id_grupo_investigacion = id_grupo_investigacion AND gi.gi_id_grupo_investigacion = org.ref_grupo_investigacion;
        IF indicador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM GI_Grupo_Investigacion WHERE gi_id_grupo_investigacion = id_grupo_investigacion;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;