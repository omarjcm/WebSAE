DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_tipos_empresas` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_tipos_empresas`()
BEGIN
     SELECT * FROM SU_Tipo_Empresa WHERE te_estado = 'V';
END $$

DELIMITER ;