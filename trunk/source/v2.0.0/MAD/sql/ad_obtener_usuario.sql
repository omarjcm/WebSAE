CREATE  PROCEDURE ad_obtener_usuario
@usuario varchar(8), 
@clave varchar(8)

AS

SELECT * FROM AC_Usuario WHERE us_id_usuario = @usuario AND us_clave=@clave

RETURN
GO
