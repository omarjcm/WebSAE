DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_cargos_por_tipo_empresa` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_cargos_por_tipo_empresa`(id_tipo_empresa BIGINT(20))
BEGIN
     SELECT ca.ca_id_cargo, ca.ca_nombre, ca.ca_estado
     FROM `su_cargo` ca, `su_tipo_empresa` te, `su_tipo_empresa_cargo` tec
     WHERE ca.ca_id_cargo = tec.ref_cargo AND tec.ref_tipo_empresa = te.te_id_tipo_empresa AND
           te.te_id_tipo_empresa = id_tipo_empresa AND tec.te_estado = 'V' AND ca.ca_estado = 'V';
END $$

DELIMITER ;