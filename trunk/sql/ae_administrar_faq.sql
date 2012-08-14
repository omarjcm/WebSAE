DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ae_administrar_faq` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ae_administrar_faq`(tipo VARCHAR(10), id_evento BIGINT(20), texto TEXT)
BEGIN
     DECLARE id BIGINT(20);
     
     IF tipo = 'actualizar' THEN
          SELECT ref_faq INTO id FROM AE_Evento WHERE ev_id_evento = id_evento;
          IF id <=> NULL THEN
             SELECT MAX(fa_id_faq) INTO id FROM AE_Faq;
             IF id <=> NULL THEN
                SET id = 1;
             ELSE
                SET id = id + 1;
             END IF;
             
             INSERT INTO AE_Faq (fa_id_faq, fa_texto) VALUES (id, texto);
             UPDATE AE_Evento SET ref_faq = id WHERE ev_id_evento = id_evento;
          END IF;
          UPDATE AE_Faq SET fa_texto = texto WHERE fa_id_faq = id;
     END IF;

     IF tipo = 'eliminar' THEN
        SELECT ref_faq INTO id FROM AE_Evento WHERE ev_id_evento = id_evento;
        DELETE FROM AE_Faq WHERE fa_id_faq = id;
        UPDATE AE_Evento SET ref_faq = NULL WHERE ev_id_evento = id_evento;
     END IF;
END $$

DELIMITER ;