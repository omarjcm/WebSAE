DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_buscar_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_buscar_usuario`(email VARCHAR(50), OUT id_usuario BIGINT(20))
BEGIN
     SELECT us_id_usuario INTO id_usuario FROM AC_Usuario WHERE us_email = email;
     
     SELECT id_usuario;
END $$

DELIMITER ;