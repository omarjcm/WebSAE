DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_paises` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_paises`()
BEGIN
     SELECT * FROM `su_pais` WHERE pa_estado = 'V';
END $$

DELIMITER ;