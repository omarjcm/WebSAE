CREATE  PROCEDURE ad_validar_usuario
    @usuario varchar(8), 
    @clave varchar(8), 
    @centinela bit OUTPUT 

AS

IF EXISTS(SELECT * FROM AC_Usuario WHERE us_id_usuario = @usuario AND us_clave=@clave)
    SET @centinela = 1
ELSE
    SET @centinela = 0

RETURN
GO
