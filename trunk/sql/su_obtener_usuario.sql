DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_obtener_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_obtener_usuario`(email VARCHAR(50))
BEGIN
     SELECT * FROM AC_Usuario WHERE us_email = email;
END $$

DELIMITER ;