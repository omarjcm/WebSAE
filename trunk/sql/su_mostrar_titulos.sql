DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_titulos` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_titulos`()
BEGIN
     SELECT * FROM `su_titulo` WHERE ti_estado = 'V';
END $$

DELIMITER ;