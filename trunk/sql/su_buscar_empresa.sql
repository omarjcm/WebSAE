DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_buscar_empresa` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_buscar_empresa`(nombre VARCHAR(100), id_ciudad BIGINT(20), id_tipo_empresa BIGINT(20), OUT id_empresa BIGINT(20))
BEGIN
     SELECT em_id_empresa INTO id_empresa FROM SU_Empresa WHERE em_nombre = nombre AND ref_ciudad = id_ciudad AND ref_tipo_empresa = id_tipo_empresa;
     SELECT id_empresa;
END $$

DELIMITER ;