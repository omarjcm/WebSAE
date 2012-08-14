DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_asignar_tipo_empresa_cargo` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_asignar_tipo_empresa_cargo`(
     estado CHAR(1),
     id_tipo_empresa BIGINT(20),
     id_cargo BIGINT(20))
BEGIN
     DECLARE id BIGINT(20);
     DECLARE contador INTEGER(11);
     DECLARE max_id BIGINT(20);

     SELECT te_id_tipo_empresa_cargo INTO id FROM  SU_Tipo_Empresa_Cargo WHERE ref_tipo_empresa = id_tipo_empresa AND ref_cargo = id_cargo;

     IF id <=> NULL THEN
          IF id_cargo = -1 THEN
              SELECT max(ca_id_cargo) INTO max_id FROM su_cargo;
              INSERT INTO SU_Tipo_Empresa_Cargo (te_estado, ref_tipo_empresa, ref_cargo) VALUES (estado, id_tipo_empresa, max_id);
          ELSE
              INSERT INTO SU_Tipo_Empresa_Cargo (te_estado, ref_tipo_empresa, ref_cargo) VALUES (estado, id_tipo_empresa, id_cargo);
          END IF;
          SELECT te_id_tipo_empresa_cargo INTO id FROM  SU_Tipo_Empresa_Cargo WHERE ref_tipo_empresa = id_tipo_empresa AND ref_cargo = id_cargo;
     END IF;

     SELECT COUNT(*) INTO contador
     FROM SU_Tipo_Empresa_Cargo tec, SU_Cargo ca, SU_Tipo_Empresa te, SU_Empresa em, SU_Empresa_usuario eu
     WHERE (tec.ref_tipo_empresa = id_tipo_empresa AND tec.ref_cargo = id_cargo) AND
           (ca.ca_id_cargo = tec.ref_cargo AND te.te_id_tipo_empresa = tec.ref_tipo_empresa AND
           te.te_id_tipo_empresa = em.ref_tipo_empresa AND eu.ref_cargo = ca.ca_id_cargo AND eu.ref_empresa = em.em_id_empresa);

     IF estado = 'E' AND contador = 0 THEN
          DELETE FROM SU_Tipo_Empresa_Cargo WHERE te_id_tipo_empresa_cargo = id;
     END IF;
END $$

DELIMITER ;