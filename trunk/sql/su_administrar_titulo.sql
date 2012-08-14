DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_administrar_titulo` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_titulo`(tipo VARCHAR(10), id_titulo BIGINT(20), nombre VARCHAR(100), abreviatura VARCHAR(10), estado CHAR(1), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);
     
     IF tipo = 'registrar' THEN
        SELECT ti_id_titulo INTO id FROM SU_Titulo WHERE ti_nombre = nombre AND ti_abreviatura = abreviatura;

        IF id <=> NULL THEN
           INSERT INTO SU_Titulo (ti_nombre, ti_abreviatura, ti_estado) VALUES (nombre, abreviatura, estado);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT ti_id_titulo INTO id FROM SU_Titulo WHERE ti_nombre = nombre AND ti_abreviatura = abreviatura AND ti_id_titulo <> id_titulo;
        SELECT COUNT(ti_id_titulo) INTO contador FROM SU_Titulo ti, AC_Usuario us WHERE ti.ti_id_titulo = us.ref_titulo AND ti.ti_id_titulo = id_titulo;

        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           IF id <=> NULL THEN
              UPDATE SU_Titulo SET ti_nombre = nombre, ti_abreviatura = abreviatura WHERE ti_id_titulo = id_titulo;
              SET mensaje = 'OK:modificar';
           ELSE
              SET mensaje = 'ERROR:repetido';
           END IF;
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(ti_id_titulo) INTO contador FROM SU_Titulo ti, AC_Usuario us WHERE ti.ti_id_titulo = us.ref_titulo AND ti.ti_id_titulo = id_titulo;

        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM SU_Titulo WHERE ti_id_titulo = id_titulo;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;