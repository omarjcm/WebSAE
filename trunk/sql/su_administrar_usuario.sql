DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`su_administrar_usuario` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_usuario`(tipo VARCHAR(20), nombre VARCHAR(100), apellido VARCHAR(100), email VARCHAR(50), clave VARCHAR(15), genero CHAR(1), fecha_nacimiento DATETIME, direccion VARCHAR(100), telefono VARCHAR(20), celular VARCHAR(20), actividad CHAR(1), hoja_vida VARCHAR(50), foto VARCHAR(50), estado CHAR(1), id_titulo BIGINT(20), id_ciudad BIGINT(20), id_empresa BIGINT(20), id_cargo BIGINT(20), telefono_oficina VARCHAR(16), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id_usuario BIGINT(20);

     IF tipo = 'registrar' THEN
        CALL su_buscar_usuario(email, id_usuario);
        
        IF id_usuario <=> NULL THEN
          INSERT INTO AC_Usuario(us_nombre, us_apellido, us_email, us_clave,
                                 us_genero, us_fecha_nacimiento,
                                 us_direccion, us_telefono, us_celular, us_actividad,
                                 us_hoja_vida, us_foto, us_estado, ref_titulo, ref_ciudad)
                         VALUES (nombre, apellido, email, clave,
                                 genero, fecha_nacimiento,
                                 direccion, telefono, celular, actividad,
                                 hoja_vida, foto, estado, id_titulo, id_ciudad);

          CALL su_buscar_usuario(email, id_usuario);
          INSERT INTO SU_Empresa_Usuario (eu_estado, eu_telefono, ref_cargo, ref_empresa, ref_usuario)
          VALUES ('V', telefono_oficina, id_cargo, id_empresa, id_usuario);
          
          INSERT INTO AC_Usuario_Perfil (up_estado, ref_usuario, ref_perfil)
          VALUES ('V', id_usuario, 3);

          SET mensaje = 'OK:registro';
        ELSE
          SET mensaje = 'ERROR:email repetido';
        END IF;
     END IF;
     
     IF tipo = 'modificar' THEN
     
        UPDATE AC_Usuario
        SET us_nombre = nombre, us_apellido = apellido, us_genero = genero,
            us_fecha_nacimiento = fecha_nacimiento, us_direccion = direccion,
            us_telefono = telefono, us_celular = celular, us_actividad = actividad,
            us_hoja_vida = hoja_vida, us_foto = foto, us_estado = estado,
            ref_titulo = id_titulo, ref_ciudad = id_ciudad
        WHERE us_email = email;
        
        UPDATE SU_Empresa_Usuario
        SET ref_cargo = id_cargo, ref_empresa = id_empresa, eu_telefono = telefono_oficina
        WHERE ref_usuario = (SELECT us_id_usuario FROM AC_Usuario WHERE us_email = email);
        
        SET mensaje = 'OK:modificar';
     END IF;
END $$

DELIMITER ;