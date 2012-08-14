DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_empresas_por_tipo_empresa` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_empresas_por_tipo_empresa`(id_tipo_empresa BIGINT(20))
BEGIN
     SELECT * FROM `su_empresa` em, `su_tipo_empresa` te
     WHERE em.ref_tipo_empresa = te.te_id_tipo_empresa AND
           te.te_id_tipo_empresa = id_tipo_empresa AND em.em_estado = 'V';
END $$

DELIMITER ;