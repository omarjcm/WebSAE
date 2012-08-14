DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_modificar_clave` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_modificar_clave`(clave_antigua VARCHAR(15), clave_actual VARCHAR(15), email VARCHAR(50))
BEGIN
     UPDATE AC_Usuario
     SET us_clave = clave_actual
     WHERE us_clave = clave_antigua AND us_email = email;
END $$

DELIMITER ;