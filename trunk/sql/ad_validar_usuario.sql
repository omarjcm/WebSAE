DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ad_validar_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_validar_usuario`(email VARCHAR(50), clave VARCHAR(15), OUT centinela BINARY)
BEGIN
     IF EXISTS( SELECT * FROM AC_Usuario WHERE us_email = email AND us_clave = clave ) THEN
        SET centinela = 1;
     ELSE
        SET centinela = 0;
     END IF;
     
     SELECT centinela;
END $$

DELIMITER ;