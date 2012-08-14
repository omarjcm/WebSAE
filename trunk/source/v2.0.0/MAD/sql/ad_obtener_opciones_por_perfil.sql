CREATE  PROCEDURE ad_obtener_opciones_por_perfil
    @usuario varchar(8), 
    @clave varchar(8),
    @id_perfil decimal

AS

    SELECT op.op_id_opcion, op.op_nombre, op.op_abreviatura, op.op_estado 
    FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
    WHERE us.us_id_usuario = @usuario AND us.us_clave=@clave AND pe.pe_id_perfil = @id_perfil AND us.us_id_usuario = up.ref_usuario AND up.ref_perfil = pe.pe_id_perfil AND pe.pe_id_perfil = po.ref_perfil AND po.ref_opcion = op.op_id_opcion

RETURN
GO
