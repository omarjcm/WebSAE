DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ad_obtener_perfiles_por_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_perfiles_por_usuario`(email VARCHAR(50), clave VARCHAR(15))
BEGIN
    SELECT pe.pe_id_perfil, pe.pe_nombre, pe.pe_estado
    FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe
    WHERE us.us_email = email AND us.us_clave = clave AND up.up_estado = 'V' AND
          us.us_id_usuario = up.ref_usuario AND up.ref_perfil = pe.pe_id_perfil;
END $$

DELIMITER ;