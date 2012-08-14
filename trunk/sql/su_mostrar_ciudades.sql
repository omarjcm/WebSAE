DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_mostrar_ciudades` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_ciudades`(id_pais VARCHAR(3))
BEGIN
     SELECT ci.ci_id_ciudad, ci.ci_nombre, ci.ci_distrito, ci.ci_estado
     FROM SU_Pais pa, SU_Ciudad ci
     WHERE pa.pa_id_pais = id_pais AND pa.pa_id_pais = ci.ref_pais AND ci.ci_estado = 'V';
END $$

DELIMITER ;