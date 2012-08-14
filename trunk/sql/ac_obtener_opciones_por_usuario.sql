DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ac_obtener_opciones_por_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_obtener_opciones_por_usuario`(email VARCHAR(50), tipo CHAR(1))
BEGIN
     SELECT op.op_id_opcion, op.op_nombre_es, op.op_nombre_en, op.op_nombre_pt, op.op_url, op.op_url_usuario, op.op_estado
     FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
     WHERE us.us_id_usuario = up.ref_usuario AND up.ref_perfil = pe.pe_id_perfil AND up.up_estado = 'V' AND
           pe.pe_id_perfil = po.ref_perfil AND po.ref_opcion = op.op_id_opcion AND
           us.us_email = email AND op.op_estado = 'V' AND op.op_tipo = tipo
     GROUP BY op_nombre_es
     ORDER BY op_id_opcion;
END $$

DELIMITER ;