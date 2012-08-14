DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ad_obtener_opciones_por_perfil` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_opciones_por_perfil`(id_perfil BIGINT(20))
BEGIN
     SELECT op.op_id_opcion, op.op_nombre_es, op.op_estado
     FROM AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
     WHERE pe.pe_id_perfil = id_perfil AND pe.pe_id_perfil = po.ref_perfil AND
           po.ref_opcion = op.op_id_opcion AND po.po_estado = 'V';
END $$

DELIMITER ;