DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_administrar_cargo` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_cargo`(tipo VARCHAR(10), id_cargo BIGINT(20), nombre VARCHAR(50), estado CHAR(1), OUT mensaje VARCHAR(50), OUT id_objeto BIGINT(20))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT ca_id_cargo INTO id FROM SU_Cargo WHERE ca_nombre = nombre;

        IF id <=> NULL THEN
           INSERT INTO SU_Cargo (ca_nombre, ca_estado) VALUES (nombre, estado);
           SELECT ca_id_cargo INTO id_objeto FROM SU_Cargo WHERE ca_nombre = nombre;
           
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT ca_id_cargo INTO id FROM SU_Cargo WHERE ca_nombre = nombre AND ca_id_cargo <> id_cargo;
           IF id <=> NULL THEN
              UPDATE SU_Cargo SET ca_nombre = nombre, ca_estado = estado WHERE ca_id_cargo = id_cargo;
              SET mensaje = 'OK:modificar';
           ELSE
              SET mensaje = 'ERROR:repetido';
           END IF;

     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(ca_id_cargo) INTO contador FROM SU_Cargo ca, SU_Empresa_Usuario eu, SU_Tipo_Empresa_Cargo te WHERE ( ca.ca_id_cargo = eu.ref_cargo OR ca.ca_id_cargo = te.ref_cargo ) AND ca.ca_id_cargo = id_cargo;

        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM SU_Cargo WHERE ca_id_cargo = id_cargo;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;