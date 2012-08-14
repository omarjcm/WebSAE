#@(#) script.ddl
DROP DATABASE IF EXISTS websae;
CREATE DATABASE IF NOT EXISTS websae;
USE websae;

CREATE TABLE Titulo
(
	id_titulo INTEGER NOT NULL PRIMARY KEY,
	titulo String NOT NULL,
	abreviatura String NOT NULL,
	#indicador de estado
	estado int NOT NULL
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
	id_tipo_material Integer NOT NULL PRIMARY KEY,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tipo_Evento
(
	id_tipo_evento INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (50) NOT NULL,
	#indicador de estado
	estado INT NOT NULL
);

CREATE TABLE Tipo_Empresa
(
	id_tipo_empresa Integer NOT NULL,
	nombre String NOT NULL,
	descripcion String NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tema_Articulo
(
	id_tema_articulo Integer NOT NULL,
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

CREATE TABLE Seccion
(
	id_seccion Integer NOT NULL,
	titulo String NOT NULL,
	descripcion String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Rol
(
	id_rol INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (50) NOT NULL,
	descripcion CHAR (100) NULL,
	#indicador de estado
	estado INT NOT NULL
);

CREATE TABLE Respuesta
(
	id_respuesta Integer NOT NULL,
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

CREATE TABLE Pais
(
	id_pais INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (50) NOT NULL,
	continente ENUM ('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') NOT NULL,
	#indicador de estado
	estado INT NOT NULL
);

CREATE TABLE Grupo_Investigacion
(
	id_grupo_investigacion INTEGER NOT NULL PRIMARY KEY,
	nombre_grupo CHAR (50) NOT NULL,
	objetivo_grupo TEXT NOT NULL,
	logo_direccion CHAR (100) NULL,
	pagina_web CHAR (100) NULL,
	telefono CHAR (16) NULL,
	#indicador de estado
	estado INT NOT NULL
);

CREATE TABLE Evaluador_Articulo
(
	id_evaluador_articulo Integer NOT NULL,
	#indicador de estado
	estado int
);

CREATE TABLE Evaluacion_Tema_Articulo
(
	id_evaluacion_tema_articulo Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Evaluacion
(
	id_evaluacion Integer NOT NULL,
	mensaje String NOT NULL,
	descripcion String NOT NULL,
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

CREATE TABLE Convocatoria
(
	#indicador de estado
	estado int NOT NULL,
	id_convocatoria Integer NOT NULL,
	descripcion String NOT NULL,
	ruta_formato String NOT NULL,
	fecha_max_presentacion_art String NOT NULL,
	fecha_max_evaluacion_art String NOT NULL,
	fecha_max_aceptacion_art String NOT NULL,
	fecha_max_correccion_art String NOT NULL
);

CREATE TABLE Conferencista_Evento_Articulo
(
	id_conferencista_evento_articulo Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Conferencista_Evento
(
	id_conferencista_evento Integer NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Ciudad
(
	id_ciudad Integer NOT NULL,
	nombre String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Categoria
(
	id_categoria INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (80) NOT NULL,
	#indicador de estado
	estado INTEGER NOT NULL
);

CREATE TABLE Cargo
(
	id_cargo Integer NOT NULL,
	cargo String NOT NULL,
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

CREATE TABLE Auspicio
(
	id_auspicio Integer NOT NULL,
	monto float NULL,
	descripcion String NULL,
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

CREATE TABLE Alternativa
(
	id_alternativa Integer NOT NULL,
	titulo String NOT NULL,
	#indicador de estado
	estado int NOT NULL
);

CREATE TABLE Tipo_Empresa_Cargo
(
	id_tipo_empresa_cargo Integer NOT NULL,
	#indicador de estado
	estado int NULL
);

CREATE TABLE Opcion_Menu
(
	id_opcion_menu INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (30) NULL,
	link CHAR (100) NULL
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
	id_usuario INTEGER NOT NULL PRIMARY KEY,
	#N�mero de c�dula para personas de Ecuador o n�mero de pasaporte para extranjeros.
	identificacion CHAR (16) NOT NULL,
	#Debido a que puede ser CI (Nacional), CI (Extranjera) o No. de pasaporte.
	tipo_identificacion CHAR NOT NULL,
	#Nombre del usuario
	nombre CHAR (50) NOT NULL,
	#Apellido del usuario
	apellido CHAR (50) NOT NULL,
	#Email del usuario
	correo CHAR (50) NOT NULL,
	#Clave del usuario
	clave CHAR (15) NOT NULL,
	#fecha (dd,mm,aaaa) en que naci� el usuario
	fecha_nacimiento DATE NULL,
	#Direcci�n de domicilio del usuario
	direccion CHAR (50) NULL,
	#sexo del usuario (masculino o femenino)
	genero CHAR NOT NULL,
	#Tel�fono convencional del usuario
	telefono CHAR (20) NULL,
	#N�mero de tel�fono celular del usuario
	celular CHAR (16) NULL,
	#si el usuario estudia o trabaja.
	actividad CHAR NULL,
	#Informaci�n acerca del perfil profesional que ha llevado el usuario.
	hoja_vida TEXT NULL,
	#Ruta a la imagen de la foto del usuario.
	foto_direccion CHAR (20) NULL,
	#indicador de estado
	estado INT NOT NULL,
	ref_titulo INTEGER NULL,
	FOREIGN KEY(ref_titulo) REFERENCES Titulo (id_titulo)
);

CREATE TABLE Subcategoria
(
	id_subcategoria INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (50) NOT NULL,
	#indicador de estado
	estado INT NOT NULL,
	ref_categoria INTEGER NOT NULL,
	FOREIGN KEY(ref_categoria) REFERENCES Categoria (id_categoria)
);

CREATE TABLE Evento
(
	id_evento INTEGER NOT NULL PRIMARY KEY,
	nombre CHAR (250) NOT NULL,
	lugar CHAR (100) NOT NULL,
	objetivo TEXT NOT NULL,
	descripcion TEXT NOT NULL,
	dirigido TEXT NOT NULL,
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE NOT NULL,
	slogan CHAR (100) NOT NULL,
	direccion_grafico CHAR (50) NULL,
	descripcion_registro TEXT NULL,
	#indicador de estado
	estado INT NOT NULL,
	ref_tipo_evento INTEGER NULL,
	FOREIGN KEY(ref_tipo_evento) REFERENCES Tipo_Evento (id_tipo_evento)
);

CREATE TABLE Submenu
(
	id_submenu INTEGER NOT NULL PRIMARY KEY,
	ref_opcion_menu INTEGER NULL,
	ref_submenu INTEGER NULL,
	FOREIGN KEY(ref_submenu) REFERENCES Opcion_Menu (id_opcion_menu),
	FOREIGN KEY(ref_opcion_menu) REFERENCES Opcion_Menu (id_opcion_menu)
);

CREATE TABLE Menu
(
	id_menu INTEGER NOT NULL PRIMARY KEY,
	ref_opcion_men