DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ac_obtener_opciones` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_obtener_opciones`(id_perfil BIGINT(20), tipo CHAR(1))
BEGIN
     SELECT op.op_id_opcion, op.op_nombre_es, op.op_nombre_en, op.op_nombre_pt, op.op_url, op.op_url_usuario, op.op_estado
     FROM AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
     WHERE pe.pe_id_perfil = po.ref_perfil AND po.ref_opcion = op.op_id_opcion AND
           pe.pe_id_perfil = id_perfil AND op.op_estado = 'V' AND op.op_tipo = tipo
     GROUP BY op_nombre_es
     ORDER BY op_id_opcion;
END $$

DELIMITER ;