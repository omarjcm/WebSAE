CREATE PROCEDURE ad_validar_perfil_usuario 
    @usuario VARCHAR(8),@clave VARCHAR(8),@id_perfil DECIMAL,@centinela BIT OUTPUT

AS

IF EXISTS 
    (SELECT * FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe 
    WHERE us.us_id_usuario=@usuario AND us.us_clave=@clave AND pe.pe_id_perfil=@id_perfil AND us.us_id_usuario=up.ref_usuario AND up.ref_perfil=pe.pe_id_perfil)
	SET @centinela = 1
ELSE
	SET @centinela = 0

RETURN
GO
