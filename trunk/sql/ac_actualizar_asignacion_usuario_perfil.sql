DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ac_actualizar_asignacion_usuario_perfil` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_actualizar_asignacion_usuario_perfil`(email VARCHAR(50), id_perfil BIGINT(20), estado CHAR(1))
BEGIN
    DECLARE id BIGINT(20);
    DECLARE id_usuario BIGINT(20);

    SELECT us_id_usuario INTO id_usuario FROM AC_Usuario WHERE us_email = email;
    SELECT up_id_usuario_perfil INTO id FROM AC_Usuario_Perfil WHERE ref_perfil = id_perfil AND ref_usuario = id_usuario;
    IF id <=> NULL THEN
        IF estado = 'V' THEN
           INSERT INTO AC_Usuario_Perfil (up_estado, ref_usuario, ref_perfil) VALUES (estado, id_usuario, id_perfil);
        END IF;
    END IF;
    UPDATE AC_Usuario_Perfil SET up_estado = estado WHERE up_id_usuario_perfil = id;
END $$

DELIMITER ;