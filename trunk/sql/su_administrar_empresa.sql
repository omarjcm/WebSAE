DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_administrar_empresa` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_empresa`(tipo VARCHAR(20), id_empresa BIGINT(20), nombre VARCHAR(100), direccion VARCHAR(300), telefono VARCHAR(16), codigo_postal VARCHAR(10), fax VARCHAR(16), logo VARCHAR(100), web VARCHAR(50), siglas VARCHAR(100), estado CHAR(1), id_ciudad BIGINT(20), id_tipo_empresa BIGINT(20), OUT mensaje VARCHAR(50), OUT id_objeto BIGINT(20))
BEGIN
     DECLARE contador INTEGER(11);

     IF tipo = 'registrar' THEN
        CALL `su_buscar_empresa`(nombre, id_ciudad, id_tipo_empresa, id_objeto);
       
        IF id_objeto <=> NULL THEN
           INSERT INTO `su_empresa` (em_nombre, em_direccion, em_telefono, em_codigo_postal, em_fax, em_logo, em_web, em_siglas, em_estado, ref_ciudad, ref_tipo_empresa)
           VALUES ( nombre, direccion, telefono, codigo_postal, fax, logo, web, siglas, estado, id_ciudad, id_tipo_empresa );

           SET mensaje = 'OK:registro';
           CALL `su_buscar_empresa`(nombre, id_ciudad, id_tipo_empresa, id_objeto);
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;
     END IF;
    
     IF tipo = 'modificar' THEN
        SELECT em_id_empresa INTO id_objeto FROM SU_Empresa WHERE em_nombre = nombre AND ref_tipo_empresa = id_tipo_empresa AND ref_ciudad = id_ciudad AND em_id_empresa <> id_empresa;
        SELECT COUNT(em.em_id_empresa) INTO contador FROM SU_Empresa em, SU_Empresa_Usuario eu WHERE em.em_id_empresa = eu.ref_empresa AND em.em_id_empresa = id_empresa;

        IF estado = 'E' AND (contador > 0) THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           IF id_objeto <=> NULL THEN
              UPDATE SU_Empresa SET em_nombre = nombre, em_direccion = direccion,
                                    em_telefono = telefono, em_codigo_postal = codigo_postal,
                                    em_fax = fax, em_logo = logo, em_web = web, em_siglas = siglas,
                                    em_estado = estado, ref_ciudad = id_ciudad, ref_tipo_empresa = id_tipo_empresa
              WHERE em_id_empresa = id_empresa;
              SET mensaje = 'OK:modificar';
           ELSE
              SET mensaje = 'ERROR:repetido';
           END IF;
        END IF;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT COUNT(em.em_id_empresa) INTO contador FROM SU_Empresa em, SU_Empresa_Usuario eu WHERE em.em_id_empresa = eu.ref_empresa AND em.em_id_empresa = id_empresa;

        IF (contador > 0) THEN
           SET mensaje = 'ERROR:asignado';
        ELSE
           DELETE FROM SU_Empresa WHERE em_id_empresa = id_empresa;
           SET mensaje = 'OK:eliminar';
        END IF;
    END IF;

END $$

DELIMITER ;