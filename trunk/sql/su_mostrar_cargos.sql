DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_cargos` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_cargos`()
BEGIN
     SELECT * FROM SU_Cargo WHERE ca_estado = 'V';
END $$

DELIMITER ;