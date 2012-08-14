DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_conferencista_evento` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_conferencista_evento`(tipo VARCHAR(10), id_usuario BIGINT(20), id_evento BIGINT(20), OUT mensaje VARCHAR(20))
BEGIN
     DECLARE id_usuario_perfil BIGINT(20);
     DECLARE id BIGINT(20);

     SELECT up_id_usuario_perfil INTO id_usuario_perfil FROM AC_Usuario_Perfil WHERE ref_usuario = id_usuario AND ref_perfil = 7;

     IF tipo = 'registrar' THEN
        IF id_usuario_perfil <=> NULL THEN
           SET mensaje = 'ERROR:registrar';
        ELSE
           SELECT ce_id_conferencista_evento INTO id FROM AE_Conferencista_Evento WHERE ref_usuario_perfil = id_usuario_perfil AND ref_evento = id_evento;
           IF id <=> NULL THEN
              INSERT INTO AE_Conferencista_Evento (ref_usuario_perfil, ref_evento) VALUES (id_usuario_perfil, id_evento);
              SET mensaje = 'OK:registrar';
           ELSE
              SET mensaje = 'ERROR:repetido';
           END IF;
        END IF;
     ELSEIF tipo = 'eliminar' THEN
        IF id_usuario_perfil <=> NULL OR id_evento <=> NULL THEN
           SET mensaje = 'ERROR:eliminar';
        ELSE
           DELETE FROM AE_Conferencista_Evento WHERE ref_evento = id_evento AND ref_usuario_perfil = id_usuario_perfil;
           SET mensaje = 'OK:eliminar';
        END IF;
     END IF;
END $$

DELIMITER ;