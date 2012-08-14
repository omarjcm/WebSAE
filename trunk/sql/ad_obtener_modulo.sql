DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ad_obtener_modulo` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_modulo`(id_subopcion BIGINT(20))
BEGIN
     DECLARE id BIGINT(20);

     SELECT op.op_id_opcion INTO id
     FROM AC_Opcion subop, AC_Asignar_Opcion ao, AC_Opcion op
     WHERE subop.op_id_opcion = id_subopcion AND subop.op_id_opcion = ao.ref_subopcion AND
           ao.ref_opcion = op.op_id_opcion;

     IF id <=> NULL THEN
         SELECT * FROM AC_Opcion WHERE op_id_opcion = id_subopcion;
     ELSE
          CALL ad_obtener_modulo( id );
     END IF;
END $$

DELIMITER ;