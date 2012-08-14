CREATE  PROCEDURE ad_obtener_perfiles_por_usuario
    @usuario varchar(8), 
    @clave varchar(8)

AS

    SELECT pe.pe_id_perfil, pe.pe_nombre, pe.pe_estado, pr.pr_id_proceso, pr.pr_nombre, pr.pr_estado
    FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe, AC_Proceso pr
    WHERE us.us_id_usuario = @usuario AND us.us_clave=@clave AND  
           us.us_id_usuario = up.ref_usuario AND up.ref_perfil = pe.pe_id_perfil AND pe.ref_proceso = pr.pr_id_proceso;

RETURN
GO
