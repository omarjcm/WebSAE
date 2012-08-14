DELIMITER $$

DROP PROCEDURE IF EXISTS `websae`.`ce_administrar_tema` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ce_administrar_tema`(
     tipo VARCHAR(10),
     id_tema BIGINT(20),
     tema VARCHAR(500),
     id_convocatoria BIGINT(20),
     OUT mensaje VARCHAR(50)
)
BEGIN
     DECLARE indicador INTEGER(11);
     DECLARE id BIGINT(20);

     IF tipo = 'registrar' THEN
        SELECT te_id_tema INTO id FROM CE_Tema WHERE te_nombre = tema;
        IF id <=> NULL THEN
           INSERT INTO CE_Tema (te_nombre, ref_convocatoria) VALUES (tema, id_convocatoria);
           SET mensaje = 'OK:registrar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;

     ELSEIF tipo = 'modificar' THEN
        SELECT te_id_tema INTO id FROM CE_Tema WHERE ref_convocatoria = id_convocatoria AND te_nombre = tema AND te_id_tema <> id_tema;
        IF id <=> NULL THEN
           UPDATE CE_Tema SET te_nombre = tema WHERE te_id_tema = id_tema;
           SET mensaje = 'OK:modificar';
        ELSE
           SET mensaje = 'ERROR:repetido';
        END IF;

     ELSEIF tipo = 'eliminar' THEN
        SELECT COUNT(*) INTO indicador FROM CE_Tema te, CE_Tema_Articulo ta WHERE te.te_id_tema = ta.ref_tema;

        IF indicador > 0 THEN
            SET mensaje = 'ERROR:asignado';
        ELSE
            DELETE FROM CE_Tema WHERE te_id_tema = id_tema;
            SET mensaje = 'OK:eliminar';
        END IF;
     END IF;

END $$

DELIMITER ;