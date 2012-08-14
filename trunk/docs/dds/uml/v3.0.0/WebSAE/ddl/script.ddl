#@(#) script.ddl
CREATE DATABASE IF NOT EXISTS websae;
USE websae;

CREATE TABLE Faq
(
	id_faq Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Evaluacion_Tema_Articulo
(
	id_evaluacion_tema_articulo Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Autor_Articulo
(
	id_autor_articulo Integer NULL,
	autor_principal Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Material_Registro
(
	id_material_registro Integer NOT NULL,
	cantidad_adicional int NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Conferencista_Evento_Articulo
(
	id_conferencista_evento_articulo Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Respuesta
(
	id_respuesta Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Subcategoria_Evento
(
	id_subcategoria_evento Integer NOT NULL,
	porcentaje_descuento float NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Organizador
(
	id_organizador Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Conferencista_Evento
(
	id_conferencista_evento Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Rol_Usuario
(
	id_rol_usuario Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Evaluador_Articulo
(
	id_evaluador_articulo Integer NOT NULL,
	#indicador de estado
	estado int
);

CREATE TABLE Subevento_Evento
(
	id_subevento_evento Integer NOT NULL PRIMARY KEY,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tema_Articulo
(
	id_tema_articulo Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tipo_Empresa_Cargo
(
	id_tipo_empresa_cargo Integer NOT NULL,
	#indicador de estado
	estado int NULL
);

CREATE TABLE Rol
(
	id_rol Integer NOT NULL,
	nombre String NOT NULL,
	descripcion String NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Convocatoria
(
	id_convocatoria Integer NOT NULL PRIMARY KEY,
	descripcion String NOT NULL,
	ruta_formato String NOT NULL,
	fecha_max_presentacion_art String NOT NULL,
	fecha_max_evaluacion_art String NOT NULL,
	fecha_max_aceptacion_art String NOT NULL,
	fecha_max_correccion_art String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tipo_Evento
(
	id_tipo_evento Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Alternativa
(
	id_alternativa Integer NOT NULL PRIMARY KEY,
	titulo String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Cargo
(
	id_cargo Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Empresa_Usuario
(
	id_empresa_usuario Integer NOT NULL,
	telefono_oficina String NULL,
	fax_oficina String NULL,
	#indicador de estado
	estado int
);

CREATE TABLE Evento
(
	id_evento Integer NOT NULL PRIMARY KEY,
	nombre String NOT NULL,
	lugar String NOT NULL,
	slogan String NOT NULL,
	objetivo String NOT NULL,
	descripcion String NOT NULL,
	dirigido String NOT NULL,
	fecha_inicio String NOT NULL,
	fecha_fin String NOT NULL,
	direccion_grafico String NULL,
	descripcion_registro String NULL,
	#indicador de estado
	estado int
);

CREATE TABLE Respuesta_Faq
(
	id_respuesta_faq Integer NOT NULL,
	respuesta String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Material
(
	id_material Integer NOT NULL,
	precio float NOT NULL,
	cantidad_entregar int NOT NULL,
	descripcion String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Categoria
(
	id_categoria Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Fecha_Evento
(
	#indicador de estado
	estado int,
	id_fecha_evento Integer NOT NULL,
	fecha String NOT NULL
);

CREATE TABLE Categoria_Evento
(
	id_categoria_evento Integer NOT NULL,
	precio float NOT NULL,
	fecha_inicio String NOT NULL,
	fecha_fin String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Pregunta
(
	id_pregunta Integer NOT NULL,
	titulo String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Auspicio
(
	id_auspicio Integer NOT NULL PRIMARY KEY,
	monto float NULL,
	descripcion String NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Registro
(
	id_registro Integer NOT NULL,
	fecha_registro String NOT NULL,
	valor_total_registro float NOT NULL,
	aprobar_descuento int NOT NULL,
	asistencia int NOT NULL,
	pagado int NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Actividad
(
	id_actividad Integer NOT NULL PRIMARY KEY,
	hora_fin String NOT NULL,
	hora_inicio String NOT NULL,
	actividad String NOT NULL,
	expositor String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tipo_Empresa
(
	id_tipo_empresa Integer NOT NULL,
	nombre String NOT NULL,
	descripcion String NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Grupo_Investigacion
(
	id_grupo_investigacion Integer NOT NULL,
	nombre_grupo String NOT NULL,
	objetivo_grupo String NOT NULL,
	logo_direccion String NULL,
	pagina_web String NULL,
	telefono String NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Pais
(
	id_pais Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Ciudad
(
	id_ciudad Integer NOT NULL PRIMARY KEY,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Pregunta_Faq
(
	id_pregunta_faq Integer NOT NULL,
	pregunta String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Empresa
(
	id_empresa Integer NOT NULL,
	nombre String NOT NULL,
	direccion String NULL,
	telefono String NULL,
	codigo_postal String NULL,
	fax String NULL,
	logo_direccion String NULL,
	pagina_web String NULL,
	siglas String NULL,
	#indicador de estado
	estado int
);

CREATE TABLE Tipo_Pregunta
(
	id_tipo_pregunta Integer NOT NULL,
	titulo String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tipo_Material
(
	id_tipo_material Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Seccion
(
	id_seccion Integer NOT NULL,
	titulo String NOT NULL,
	descripcion String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Titulo
(
	id_titulo Integer NOT NULL,
	titulo String NOT NULL,
	abreviatura String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tema
(
	id_tema Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Articulo
(
	id_articulo Integer NOT NULL,
	titulo String NOT NULL,
	resumen String NOT NULL,
	palabras_claves String NULL,
	direccion_articulo String NOT NULL,
	fecha_publicacion String NOT NULL,
	descarga String NULL,
	comentario_autor String NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Subcategoria
(
	id_subcategoria Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Evaluacion
(
	id_evaluacion Integer NOT NULL PRIMARY KEY,
	mensaje String NOT NULL,
	descripcion String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

#<html>
#  <head>
#    
#  </head>
#  <body>
#    <p>
#      Persona que se suscribe al portal y se obtiene de ella sus datos, se 
#      relaciona con <b>Rol_Usuario</b> para asignarle roles dentro del 
#      sistema, con <b>Empresa_Usuario</b> para indicar la empresa o 
#      universidad en la cual labora o estudia, y con <b>Registro</b> para 
#      tener constancia del registro a los eventos que realicen los Grupos de 
#      Investigaci&#243;n.
#    </p>
#  </body>
#</html>
CREATE TABLE Usuario
(
	#identificador del objeto Usuario dentro del portal
	id_usuario Integer NOT NULL,
	#N�mero de c�dula para personas de Ecuador o n�mero de pasaporte para extranjeros.
	identificacion String NOT NULL,
	#Debido a que puede ser CI (Nacional), CI (Extranjera) o No. de pasaporte.
	tipo_identificacion char NOT NULL,
	#Nombre del usuario
	nombre String NOT NULL,
	#Apellido del usuario
	apellido String NOT NULL,
	#Email del usuario
	correo String NOT NULL,
	#Clave del usuario
	clave String NOT NULL,
	#sexo del usuario (masculino o femenino)
	genero char NOT NULL,
	#fecha (dd,mm,aaaa) en que naci� el usuario
	fecha_nacimiento String NULL,
	#Direcci�n de domicilio del usuario
	direccion String NULL,
	#Tel�fono convencional del usuario
	telefono String NULL,
	#N�mero de tel�fono celular del usuario
	celular String NULL,
	#si el usuario estudia o trabaja.
	actividad char NULL,
	#Informaci�n acerca del perfil profesional que ha llevado el usuario.
	hoja_vida String,
	#Ruta a la imagen de la foto del usuario.
	foto_direccion String,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Opcion_Menu
(
	id_opcion_menu INTEGER NOT NULL PRIMARY KEY,
	#indicador de estado
	estado int
);

CREATE TABLE Menu
(
	id_menu INTEGER NOT NULL,
	#indicador de estado
	estado int
);

CREATE TABLE Submenu
(
	id_submenu INTEGER NOT NULL PRIMARY KEY,
	#indicador de estado
	estado int
);

CREATE TABLE Menu_Evento
(
	id_menu_evento INTEGER NOT NULL PRIMARY KEY,
	#indicador de estado
	estado NULL,
	ref_opcion_menu INTEGER NULL,
	FOREIGN KEY(ref_opcion_menu) REFERENCES Opcion_Menu (id_opcion_menu)
);
