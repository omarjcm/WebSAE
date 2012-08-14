DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_administrar_tipo_empresa` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_tipo_empresa`(tipo VARCHAR(10), id_tipo_empresa BIGINT(20), nombre VARCHAR(100), estado CHAR(1), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);

     IF tipo = 'registrar' THEN
        SELECT te_id_tipo_empresa INTO id FROM SU_Tipo_Empresa WHERE te_nombre = nombre;
        IF id <=> NULL THEN
           INSERT INTO SU_Tipo_Empresa (te_nombre, te_estado) VALUES (nombre, estado);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'modificar' THEN
        SELECT te_id_tipo_empresa INTO id FROM SU_Tipo_Empresa WHERE te_nombre = nombre AND te_id_tipo_empresa <> id_tipo_empresa;
        IF id <=> NULL THEN
           UPDATE SU_Tipo_Empresa SET te_nombre = nombre, te_estado = estado WHERE te_id_tipo_empresa = id_tipo_empresa;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(te_id_tipo_empresa) INTO contador FROM SU_Tipo_Empresa te, SU_Empresa em, SU_Tipo_Empresa_Cargo tec WHERE te.te_id_tipo_empresa = id_tipo_empresa AND (em.ref_tipo_empresa = te.te_id_tipo_empresa OR te.te_id_tipo_empresa = tec.ref_tipo_empresa);
        IF contador > 0 THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM SU_Tipo_Empresa WHERE te_id_tipo_empresa = id_tipo_empresa;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;