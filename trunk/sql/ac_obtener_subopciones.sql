DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ac_obtener_subopciones` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_obtener_subopciones`(id_opcion BIGINT(20), tipo CHAR(1))
BEGIN
     SELECT subop.op_id_opcion, subop.op_nombre_es, subop.op_nombre_en,
            subop.op_nombre_pt, subop.op_url, subop.op_url_usuario, subop.op_estado
     FROM AC_Opcion op, AC_Asignar_Opcion ao, AC_Opcion subop
     WHERE ao.ref_opcion = op.op_id_opcion AND ao.ref_subopcion = subop.op_id_opcion AND
           op.op_id_opcion = id_opcion AND subop.op_estado = 'V' AND subop.op_tipo = tipo
     ORDER BY op_id_opcion;
END $$

DELIMITER ;