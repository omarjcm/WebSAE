DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ad_obtener_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_usuario`(email VARCHAR(50), clave VARCHAR(15))
BEGIN
     SELECT *
     FROM AC_Usuario us, SU_Empresa_Usuario eu, SU_Ciudad ci, SU_Pais pa,
          SU_Empresa em, SU_Tipo_Empresa te
     WHERE us_email = email AND us_clave = clave AND us.us_id_usuario = eu.ref_usuario AND
           pa.pa_id_pais = ci.ref_pais AND us.ref_ciudad = ci.ci_id_ciudad AND
           eu.ref_empresa = em.em_id_empresa AND em.ref_tipo_empresa = te.te_id_tipo_empresa;
END $$

DELIMITER ;