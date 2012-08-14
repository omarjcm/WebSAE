-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema websagi
--

CREATE DATABASE IF NOT EXISTS websagi;
USE websagi;

--
-- Temporary table structure for view `v_usuario`
--
DROP TABLE IF EXISTS `v_usuario`;
DROP VIEW IF EXISTS `v_usuario`;
CREATE TABLE `v_usuario` (
  `us_id_usuario` bigint(20),
  `us_nombre` varchar(100),
  `us_apellido` varchar(100),
  `us_email` varchar(50),
  `us_clave` varchar(15),
  `us_genero` char(1),
  `us_fecha_nacimiento` date,
  `us_direccion` varchar(100),
  `us_telefono` varchar(20),
  `us_celular` varchar(20),
  `us_actividad` char(1),
  `us_hoja_vida` varchar(200),
  `us_foto` varchar(50),
  `us_estado` char(1),
  `us_linkedin` varchar(100),
  `ref_titulo` bigint(20),
  `ref_ciudad` bigint(20),
  `pa_id_pais` varchar(3),
  `pa_nombre` varchar(30),
  `pa_continente` varchar(45),
  `pa_region` varchar(45),
  `pa_estado` varchar(1),
  `eu_id_empresa_usuario` bigint(20),
  `eu_telefono` varchar(16),
  `eu_fax` varchar(16),
  `eu_estado` char(1),
  `ref_cargo` bigint(20),
  `ref_empresa` bigint(20),
  `ci_id_ciudad` int(10) unsigned,
  `ci_estado` varchar(1),
  `ci_nombre` varchar(30),
  `ci_distrito` varchar(45),
  `ci_id_pais` varchar(3),
  `em_id_empresa` bigint(20),
  `em_nombre` varchar(100),
  `em_direccion` varchar(300),
  `em_telefono` varchar(16),
  `em_codigo_postal` varchar(10),
  `em_fax` varchar(16),
  `em_logo` varchar(100),
  `em_web` varchar(50),
  `em_siglas` varchar(100),
  `em_estado` char(1),
  `em_id_ciudad` bigint(20),
  `em_id_tipo_empresa` bigint(20),
  `te_id_tipo_empresa` bigint(20),
  `te_nombre` varchar(50),
  `te_descripcion` varchar(100),
  `te_estado` char(11)
);

--
-- Definition of table `aa_archivo`
--

DROP TABLE IF EXISTS `aa_archivo`;
CREATE TABLE `aa_archivo` (
  `ar_id_archivo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ar_archivo` varchar(100) NOT NULL,
  `ar_estado` char(1) NOT NULL,
  `ref_articulo` bigint(20) NOT NULL,
  PRIMARY KEY (`ar_id_archivo`),
  KEY `FK_aa_archivo_1` (`ref_articulo`),
  CONSTRAINT `FK_aa_archivo_1` FOREIGN KEY (`ref_articulo`) REFERENCES `aa_articulo` (`ar_id_articulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aa_archivo`
--

/*!40000 ALTER TABLE `aa_archivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `aa_archivo` ENABLE KEYS */;


--
-- Definition of table `aa_articulo`
--

DROP TABLE IF EXISTS `aa_articulo`;
CREATE TABLE `aa_articulo` (
  `ar_id_articulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ar_titulo` text NOT NULL,
  `ar_resumen` text NOT NULL,
  `ar_palabras_claves` varchar(400) DEFAULT NULL,
  `ar_fecha_publicacion` date NOT NULL,
  `ar_comentario_articulo` text,
  `ar_permiso_descarga` binary(1) NOT NULL,
  `ar_estado` char(1) NOT NULL,
  PRIMARY KEY (`ar_id_articulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aa_articulo`
--

/*!40000 ALTER TABLE `aa_articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `aa_articulo` ENABLE KEYS */;


--
-- Definition of table `aa_autor_articulo`
--

DROP TABLE IF EXISTS `aa_autor_articulo`;
CREATE TABLE `aa_autor_articulo` (
  `aa_id_articulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `aa_autor_principal` binary(1) NOT NULL,
  `aa_estado` char(1) NOT NULL,
  `ref_usuario_perfil` bigint(20) NOT NULL,
  `ref_articulo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`aa_id_articulo`),
  KEY `FK_aa_autor_articulo_1` (`ref_usuario_perfil`),
  KEY `FK_aa_autor_articulo_2` (`ref_articulo`),
  CONSTRAINT `FK_aa_autor_articulo_1` FOREIGN KEY (`ref_usuario_perfil`) REFERENCES `ac_usuario_perfil` (`up_id_usuario_perfil`),
  CONSTRAINT `FK_aa_autor_articulo_2` FOREIGN KEY (`ref_articulo`) REFERENCES `aa_articulo` (`ar_id_articulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aa_autor_articulo`
--

/*!40000 ALTER TABLE `aa_autor_articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `aa_autor_articulo` ENABLE KEYS */;


--
-- Definition of table `ac_asignar_opcion`
--

DROP TABLE IF EXISTS `ac_asignar_opcion`;
CREATE TABLE `ac_asignar_opcion` (
  `ao_id_asignar_opcion` bigint(20) NOT NULL AUTO_INCREMENT,
  `ao_estado` char(1) NOT NULL,
  `ref_opcion` bigint(20) NOT NULL,
  `ref_subopcion` bigint(20) NOT NULL,
  PRIMARY KEY (`ao_id_asignar_opcion`),
  KEY `FK_ac_asignar_opcion_1` (`ref_opcion`),
  KEY `FK_ac_asignar_opcion_2` (`ref_subopcion`),
  CONSTRAINT `FK_ac_asignar_opcion_1` FOREIGN KEY (`ref_opcion`) REFERENCES `ac_opcion` (`op_id_opcion`),
  CONSTRAINT `FK_ac_asignar_opcion_2` FOREIGN KEY (`ref_subopcion`) REFERENCES `ac_opcion` (`op_id_opcion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ac_asignar_opcion`
--

/*!40000 ALTER TABLE `ac_asignar_opcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ac_asignar_opcion` ENABLE KEYS */;


--
-- Definition of table `ac_opcion`
--

DROP TABLE IF EXISTS `ac_opcion`;
CREATE TABLE `ac_opcion` (
  `op_id_opcion` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_nombre_es` varchar(50) DEFAULT NULL,
  `op_nombre_en` varchar(50) DEFAULT NULL,
  `op_nombre_pt` varchar(50) NOT NULL,
  `op_url` varchar(100) DEFAULT NULL,
  `op_estado` char(1) NOT NULL,
  PRIMARY KEY (`op_id_opcion`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ac_opcion`
--

/*!40000 ALTER TABLE `ac_opcion` DISABLE KEYS */;
INSERT INTO `ac_opcion` (`op_id_opcion`,`op_nombre_es`,`op_nombre_en`,`op_nombre_pt`,`op_url`,`op_estado`) VALUES 
 (1,'Suscribirse','Subscribe','Assine','/websae/suscripcion/index.jsp','V'),
 (2,'Eventos por realizar','Incoming Events','Eventos por Marca','#','V'),
 (3,'Eventos pasados','Pass Events','Últimos Eventos','#','V'),
 (4,'Publicaciones','Publications','Publicaes','#','V'),
 (5,'Por Áreas','By Areas','Por Áreas','#','V'),
 (6,'Buscar Publicación','Publication Search','Publicao Pesquisa','#','V'),
 (7,'Perfil del Usuario','User Profiles','Perfis do Usuário','#','V'),
 (8,'Modificar su Cuenta','Modify Account','Modificar Contas','/websae/usuario/modificar_cuenta/index.jsp','V'),
 (9,'Modificar Contraseña','Modify Password','Modificar Senha','/websae/usuario/modificar_clave/index.jsp','V'),
 (10,'Evaluar Trabajos','Evaluate Works','Avalie Obras','#','V'),
 (11,'Administración','Administration','Administrao','#','V');
/*!40000 ALTER TABLE `ac_opcion` ENABLE KEYS */;


--
-- Definition of table `ac_perfil`
--

DROP TABLE IF EXISTS `ac_perfil`;
CREATE TABLE `ac_perfil` (
  `pe_id_perfil` bigint(20) NOT NULL AUTO_INCREMENT,
  `pe_nombre` varchar(50) NOT NULL,
  `pe_estado` char(1) NOT NULL,
  PRIMARY KEY (`pe_id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ac_perfil`
--

/*!40000 ALTER TABLE `ac_perfil` DISABLE KEYS */;
INSERT INTO `ac_perfil` (`pe_id_perfil`,`pe_nombre`,`pe_estado`) VALUES 
 (1,'Administrador General','V'),
 (2,'Administrador','V'),
 (3,'Suscriptor','V'),
 (4,'Revisor','V'),
 (5,'Autor','V'),
 (6,'Visitante','V'),
 (7,'Conferencista Invitado','V');
/*!40000 ALTER TABLE `ac_perfil` ENABLE KEYS */;


--
-- Definition of table `ac_perfil_opcion`
--

DROP TABLE IF EXISTS `ac_perfil_opcion`;
CREATE TABLE `ac_perfil_opcion` (
  `po_id_perfil_opcion` bigint(20) NOT NULL AUTO_INCREMENT,
  `po_estado` char(1) NOT NULL,
  `ref_perfil` bigint(20) NOT NULL,
  `ref_opcion` bigint(20) NOT NULL,
  PRIMARY KEY (`po_id_perfil_opcion`),
  KEY `FK_ac_perfil_opcion_1` (`ref_perfil`),
  KEY `FK_ac_perfil_opcion_2` (`ref_opcion`),
  CONSTRAINT `FK_ac_perfil_opcion_1` FOREIGN KEY (`ref_perfil`) REFERENCES `ac_perfil` (`pe_id_perfil`),
  CONSTRAINT `FK_ac_perfil_opcion_2` FOREIGN KEY (`ref_opcion`) REFERENCES `ac_opcion` (`op_id_opcion`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ac_perfil_opcion`
--

/*!40000 ALTER TABLE `ac_perfil_opcion` DISABLE KEYS */;
INSERT INTO `ac_perfil_opcion` (`po_id_perfil_opcion`,`po_estado`,`ref_perfil`,`ref_opcion`) VALUES 
 (1,'V',6,1),
 (2,'V',6,2),
 (3,'V',6,3),
 (4,'V',6,4),
 (5,'V',3,7),
 (7,'V',3,2),
 (8,'V',3,3),
 (9,'V',3,4),
 (15,'V',4,7),
 (16,'V',4,2),
 (17,'V',4,3),
 (18,'V',4,4),
 (19,'V',4,10);
/*!40000 ALTER TABLE `ac_perfil_opcion` ENABLE KEYS */;


--
-- Definition of table `ac_usuario`
--

DROP TABLE IF EXISTS `ac_usuario`;
CREATE TABLE `ac_usuario` (
  `us_id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `us_nombre` varchar(50) DEFAULT NULL,
  `us_apellido` varchar(50) DEFAULT NULL,
  `us_email` varchar(50) DEFAULT NULL,
  `us_clave` varchar(15) DEFAULT NULL,
  `us_genero` char(1) DEFAULT NULL,
  `us_fecha_nacimiento` date DEFAULT NULL,
  `us_direccion` varchar(100) DEFAULT NULL,
  `us_telefono` varchar(20) DEFAULT NULL,
  `us_celular` varchar(20) DEFAULT NULL,
  `us_actividad` char(1) DEFAULT NULL COMMENT 'Si estudia o trabaja',
  `us_hoja_vida` varchar(50) DEFAULT NULL,
  `us_foto` varchar(50) DEFAULT NULL,
  `us_estado` char(1) NOT NULL,
  `us_linkedin` varchar(100) DEFAULT NULL,
  `ref_titulo` bigint(20) NOT NULL,
  `ref_ciudad` bigint(20) NOT NULL,
  PRIMARY KEY (`us_id_usuario`),
  KEY `FK_ac_usuario_1` (`ref_titulo`),
  KEY `FK_ac_usuario_2` (`ref_ciudad`),
  CONSTRAINT `FK_ac_usuario_1` FOREIGN KEY (`ref_titulo`) REFERENCES `su_titulo` (`ti_id_titulo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ac_usuario`
--

/*!40000 ALTER TABLE `ac_usuario` DISABLE KEYS */;
INSERT INTO `ac_usuario` (`us_id_usuario`,`us_nombre`,`us_apellido`,`us_email`,`us_clave`,`us_genero`,`us_fecha_nacimiento`,`us_direccion`,`us_telefono`,`us_celular`,`us_actividad`,`us_hoja_vida`,`us_foto`,`us_estado`,`us_linkedin`,`ref_titulo`,`ref_ciudad`) VALUES 
 (1,'Guillermo Omar','Pizarro Vásquez','omarjcm@gmail.com','regina','m','1981-11-06','La A entre la 42 y la 43','2845844','092985295',NULL,NULL,NULL,'V',NULL,1,593),
 (2,'Rafael','Rivadeneria','rafariva@gmail.com','rafariva','m','1981-11-06',NULL,NULL,NULL,NULL,NULL,NULL,'V',NULL,1,593),
 (3,'Viviana','Quevedo','viviquevedo_29@hotmail.com','viviquevedo','f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'V',NULL,1,593),
 (4,'Vivi','Quevedo','vivianaquevedo@yahoo.com','viviquevedo','f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'V',NULL,1,593),
 (5,'Omar','Pizarro','omarjcm@hotmail.com','omarjcm','m',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'V',NULL,1,593),
 (6,'Dario','Pizarro','gpizarro@fiec.espol.edu.ec','gpizarro','m',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'V',NULL,1,593);
/*!40000 ALTER TABLE `ac_usuario` ENABLE KEYS */;


--
-- Definition of table `ac_usuario_perfil`
--

DROP TABLE IF EXISTS `ac_usuario_perfil`;
CREATE TABLE `ac_usuario_perfil` (
  `up_id_usuario_perfil` bigint(20) NOT NULL AUTO_INCREMENT,
  `up_estado` char(1) NOT NULL,
  `ref_usuario` bigint(20) NOT NULL,
  `ref_perfil` bigint(20) NOT NULL,
  PRIMARY KEY (`up_id_usuario_perfil`),
  KEY `FK_ac_usuario_perfil_2` (`ref_perfil`),
  KEY `FK_ac_usuario_perfil_1` (`ref_usuario`),
  CONSTRAINT `FK_ac_usuario_perfil_1` FOREIGN KEY (`ref_usuario`) REFERENCES `ac_usuario` (`us_id_usuario`),
  CONSTRAINT `FK_ac_usuario_perfil_2` FOREIGN KEY (`ref_perfil`) REFERENCES `ac_perfil` (`pe_id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ac_usuario_perfil`
--

/*!40000 ALTER TABLE `ac_usuario_perfil` DISABLE KEYS */;
INSERT INTO `ac_usuario_perfil` (`up_id_usuario_perfil`,`up_estado`,`ref_usuario`,`ref_perfil`) VALUES 
 (1,'V',1,3),
 (2,'V',1,4);
/*!40000 ALTER TABLE `ac_usuario_perfil` ENABLE KEYS */;


--
-- Definition of table `gi_grupo_investigacion`
--

DROP TABLE IF EXISTS `gi_grupo_investigacion`;
CREATE TABLE `gi_grupo_investigacion` (
  `gi_id_grupo_investigacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `gi_nombre` varchar(100) NOT NULL,
  `gi_objetivo` text NOT NULL,
  `gi_logo` varchar(100) DEFAULT NULL,
  `gi_web` varchar(100) DEFAULT NULL,
  `gi_telefono` varchar(20) DEFAULT NULL,
  `gi_estado` char(1) NOT NULL,
  PRIMARY KEY (`gi_id_grupo_investigacion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gi_grupo_investigacion`
--

/*!40000 ALTER TABLE `gi_grupo_investigacion` DISABLE KEYS */;
INSERT INTO `gi_grupo_investigacion` (`gi_id_grupo_investigacion`,`gi_nombre`,`gi_objetivo`,`gi_logo`,`gi_web`,`gi_telefono`,`gi_estado`) VALUES 
 (1,'Grupo de Investigación de Ingeniería de Software','Aplicación de MERODE en el desarrollo de software en el Ecuador. Plan de Métricas para Empresas de Software.',NULL,'http://www.vlir8.espol.edu.ec/AppVlir8/',NULL,'V'),
 (2,'Comunidad de Software Libre - KOKOA','Somos un grupo de educadores y estudiantes  de la ESPOL interesados en promover en nuestra universidad y en la sociedad el uso y distribución del software libre. Con la firme convicción de que el software debe ser un recurso libre y accesible para todos, nuestra misión es dar a conocer al mundo sus beneficios incentivando su uso a nivel personal, académico y profesional a través de diferentes tipos de eventos. \r\n \r\nLas charlas, exposiciones, talleres, fiestas de instalación que organizamos ayudan a nuestros visitantes a interesarse y familiarizarse con el maravilloso mundo del software libre. Además de esto brindamos a nuestros miembros la oportunidad de manifestar sus dudas, comentarios y opiniones a través de foros interactivos, a los que pueden acceder a través de este portal.',NULL,'http://www.kokoa.espol.edu.ec/',NULL,'V'),
 (3,'TAWS','Contribuir a la formación de jóvenes investigadores, precursores en el desarrollo de aplicaciones web, fomentando el uso de nuevas tecnologías informáticas.',NULL,'http://blog.espol.edu.ec/taws/',NULL,'V');
/*!40000 ALTER TABLE `gi_grupo_investigacion` ENABLE KEYS */;


--
-- Definition of table `su_cargo`
--

DROP TABLE IF EXISTS `su_cargo`;
CREATE TABLE `su_cargo` (
  `ca_id_cargo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ca_nombre` varchar(50) NOT NULL,
  `ca_estado` char(1) NOT NULL,
  PRIMARY KEY (`ca_id_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `su_cargo`
--

/*!40000 ALTER TABLE `su_cargo` DISABLE KEYS */;
INSERT INTO `su_cargo` (`ca_id_cargo`,`ca_nombre`,`ca_estado`) VALUES 
 (1,'Gerente General','V'),
 (3,'Gerente Financiero','V'),
 (4,'Jefe de Sistemas','V'),
 (5,'Estudiante','V'),
 (6,'Docente','V'),
 (7,'Administrativo','V'),
 (8,'Rector','V'),
 (10,'OTRO CARGO','V');
/*!40000 ALTER TABLE `su_cargo` ENABLE KEYS */;


--
-- Definition of table `su_empresa`
--

DROP TABLE IF EXISTS `su_empresa`;
CREATE TABLE `su_empresa` (
  `em_id_empresa` bigint(20) NOT NULL AUTO_INCREMENT,
  `em_nombre` varchar(50) NOT NULL,
  `em_direccion` varchar(60) DEFAULT NULL,
  `em_telefono` varchar(16) DEFAULT NULL,
  `em_codigo_postal` varchar(10) DEFAULT NULL,
  `em_fax` varchar(16) DEFAULT NULL,
  `em_logo` varchar(100) DEFAULT NULL,
  `em_web` varchar(50) DEFAULT NULL,
  `em_siglas` varchar(100) DEFAULT NULL,
  `em_estado` char(1) NOT NULL,
  `ref_ciudad` bigint(20) NOT NULL,
  `ref_tipo_empresa` bigint(20) NOT NULL,
  PRIMARY KEY (`em_id_empresa`),
  KEY `FK_su_empresa_1` (`ref_tipo_empresa`),
  KEY `FK_su_empresa_2` (`ref_ciudad`),
  CONSTRAINT `FK_su_empresa_1` FOREIGN KEY (`ref_tipo_empresa`) REFERENCES `su_tipo_empresa` (`te_id_tipo_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `su_empresa`
--

/*!40000 ALTER TABLE `su_empresa` DISABLE KEYS */;
INSERT INTO `su_empresa` (`em_id_empresa`,`em_nombre`,`em_direccion`,`em_telefono`,`em_codigo_postal`,`em_fax`,`em_logo`,`em_web`,`em_siglas`,`em_estado`,`ref_ciudad`,`ref_tipo_empresa`) VALUES 
 (177,'Escuela Superior Politécnica del Litoral','',NULL,NULL,NULL,NULL,NULL,NULL,'V',593,1);
/*!40000 ALTER TABLE `su_empresa` ENABLE KEYS */;


--
-- Definition of table `su_empresa_usuario`
--

DROP TABLE IF EXISTS `su_empresa_usuario`;
CREATE TABLE `su_empresa_usuario` (
  `eu_id_empresa_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `eu_telefono` varchar(16) DEFAULT NULL,
  `eu_fax` varchar(16) DEFAULT NULL,
  `eu_estado` char(1) NOT NULL,
  `ref_cargo` bigint(20) NOT NULL,
  `ref_empresa` bigint(20) NOT NULL,
  `ref_usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`eu_id_empresa_usuario`),
  KEY `FK_su_empresa_usuario_1` (`ref_cargo`),
  KEY `FK_su_empresa_usuario_2` (`ref_empresa`),
  KEY `FK_su_empresa_usuario_3` (`ref_usuario`),
  CONSTRAINT `FK_su_empresa_usuario_1` FOREIGN KEY (`ref_cargo`) REFERENCES `su_cargo` (`ca_id_cargo`),
  CONSTRAINT `FK_su_empresa_usuario_2` FOREIGN KEY (`ref_empresa`) REFERENCES `su_empresa` (`em_id_empresa`),
  CONSTRAINT `FK_su_empresa_usuario_3` FOREIGN KEY (`ref_usuario`) REFERENCES `ac_usuario` (`us_id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `su_empresa_usuario`
--

/*!40000 ALTER TABLE `su_empresa_usuario` DISABLE KEYS */;
INSERT INTO `su_empresa_usuario` (`eu_id_empresa_usuario`,`eu_telefono`,`eu_fax`,`eu_estado`,`ref_cargo`,`ref_empresa`,`ref_usuario`) VALUES 
 (1,'',NULL,'V',5,177,1),
 (2,NULL,NULL,'V',5,177,2),
 (3,NULL,NULL,'V',5,177,3),
 (4,NULL,NULL,'V',5,177,4),
 (5,NULL,NULL,'V',5,177,5),
 (6,NULL,NULL,'V',5,177,6);
/*!40000 ALTER TABLE `su_empresa_usuario` ENABLE KEYS */;


--
-- Definition of table `su_tipo_empresa`
--

DROP TABLE IF EXISTS `su_tipo_empresa`;
CREATE TABLE `su_tipo_empresa` (
  `te_id_tipo_empresa` bigint(20) NOT NULL AUTO_INCREMENT,
  `te_nombre` varchar(50) NOT NULL,
  `te_descripcion` varchar(100) DEFAULT NULL,
  `te_estado` char(11) NOT NULL,
  PRIMARY KEY (`te_id_tipo_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `su_tipo_empresa`
--

/*!40000 ALTER TABLE `su_tipo_empresa` DISABLE KEYS */;
INSERT INTO `su_tipo_empresa` (`te_id_tipo_empresa`,`te_nombre`,`te_descripcion`,`te_estado`) VALUES 
 (1,'Universidad',NULL,'V'),
 (2,'Empresa Privada',NULL,'V'),
 (3,'Empresa del Estado',NULL,'V');
/*!40000 ALTER TABLE `su_tipo_empresa` ENABLE KEYS */;


--
-- Definition of table `su_tipo_empresa_cargo`
--

DROP TABLE IF EXISTS `su_tipo_empresa_cargo`;
CREATE TABLE `su_tipo_empresa_cargo` (
  `te_id_tipo_empresa_cargo` bigint(20) NOT NULL AUTO_INCREMENT,
  `te_estado` char(1) NOT NULL,
  `ref_tipo_empresa` bigint(20) NOT NULL,
  `ref_cargo` bigint(20) NOT NULL,
  PRIMARY KEY (`te_id_tipo_empresa_cargo`),
  KEY `FK_su_tipo_empresa_cargo_1` (`ref_tipo_empresa`),
  KEY `FK_su_tipo_empresa_cargo_2` (`ref_cargo`),
  CONSTRAINT `FK_su_tipo_empresa_cargo_1` FOREIGN KEY (`ref_tipo_empresa`) REFERENCES `su_tipo_empresa` (`te_id_tipo_empresa`),
  CONSTRAINT `FK_su_tipo_empresa_cargo_2` FOREIGN KEY (`ref_cargo`) REFERENCES `su_cargo` (`ca_id_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `su_tipo_empresa_cargo`
--

/*!40000 ALTER TABLE `su_tipo_empresa_cargo` DISABLE KEYS */;
INSERT INTO `su_tipo_empresa_cargo` (`te_id_tipo_empresa_cargo`,`te_estado`,`ref_tipo_empresa`,`ref_cargo`) VALUES 
 (11,'V',2,1),
 (12,'V',2,3),
 (13,'V',2,4),
 (14,'V',1,5),
 (15,'V',1,6),
 (16,'V',1,7),
 (17,'V',1,8),
 (18,'V',1,10),
 (19,'V',2,10);
/*!40000 ALTER TABLE `su_tipo_empresa_cargo` ENABLE KEYS */;


--
-- Definition of table `su_titulo`
--

DROP TABLE IF EXISTS `su_titulo`;
CREATE TABLE `su_titulo` (
  `ti_id_titulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ti_nombre` varchar(50) DEFAULT NULL,
  `ti_abreviatura` varchar(10) DEFAULT NULL,
  `ti_estado` char(1) NOT NULL,
  PRIMARY KEY (`ti_id_titulo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `su_titulo`
--

/*!40000 ALTER TABLE `su_titulo` DISABLE KEYS */;
INSERT INTO `su_titulo` (`ti_id_titulo`,`ti_nombre`,`ti_abreviatura`,`ti_estado`) VALUES 
 (1,'Ing. Computación','Ing.','V'),
 (2,'Ing. Telecomunicaciones','Ing.','V'),
 (3,'Ing. Robótica','Ing.','V'),
 (4,'Analista de Sistemas','Anal.','V'),
 (5,'Philosophical Doctor','PhD.','V'),
 (6,'Master in Sciences','MSc.','V'),
 (7,'OTRO','otro','V');
/*!40000 ALTER TABLE `su_titulo` ENABLE KEYS */;


--
-- Definition of procedure `ac_obtener_opciones`
--

DROP PROCEDURE IF EXISTS `ac_obtener_opciones`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_obtener_opciones`(id_perfil BIGINT(20))
BEGIN
     SELECT op.op_id_opcion, op.op_nombre_es, op.op_nombre_en, op.op_nombre_pt, op.op_url, op.op_estado
     FROM AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
     WHERE pe.pe_id_perfil = po.ref_perfil AND po.ref_opcion = op.op_id_opcion AND
           pe.pe_id_perfil = id_perfil AND op.op_estado = 'V'
     GROUP BY op_nombre_es
     ORDER BY op_id_opcion;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ac_obtener_opciones_por_usuario`
--

DROP PROCEDURE IF EXISTS `ac_obtener_opciones_por_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_obtener_opciones_por_usuario`(email VARCHAR(50))
BEGIN
     SELECT op.op_id_opcion, op.op_nombre_es, op.op_nombre_en, op.op_nombre_pt, op.op_url, op.op_estado
     FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
     WHERE us.us_id_usuario = up.ref_usuario AND up.ref_perfil = pe.pe_id_perfil AND
           pe.pe_id_perfil = po.ref_perfil AND po.ref_opcion = op.op_id_opcion AND
           us.us_email = email AND op.op_estado = 'V'
     GROUP BY op_nombre_es
     ORDER BY op_id_opcion;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ac_obtener_subopciones`
--

DROP PROCEDURE IF EXISTS `ac_obtener_subopciones`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ac_obtener_subopciones`(id_opcion BIGINT(20))
BEGIN
     SELECT subop.op_id_opcion, subop.op_nombre_es, subop.op_nombre_en,
            subop.op_nombre_pt, subop.op_url, subop.op_estado
     FROM AC_Opcion op, AC_Asignar_Opcion ao, AC_Opcion subop
     WHERE ao.ref_opcion = op.op_id_opcion AND ao.ref_subopcion = subop.op_id_opcion AND
           op.op_id_opcion = id_opcion AND subop.op_estado = 'V'
     ORDER BY op_id_opcion;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ad_obtener_modulo`
--

DROP PROCEDURE IF EXISTS `ad_obtener_modulo`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_modulo`(id_subopcion BIGINT(20))
BEGIN
     DECLARE id BIGINT(20);

     SELECT op.op_id_opcion INTO id
     FROM AC_Opcion subop, AC_Asignar_Opcion ao, AC_Opcion op
     WHERE subop.op_id_opcion = id_subopcion AND subop.op_id_opcion = ao.ref_subopcion AND
           ao.ref_opcion = op.op_id_opcion;

     IF id <=> NULL THEN
         SELECT * FROM AC_Opcion WHERE op_id_opcion = id_subopcion;
     ELSE
          CALL ad_obtener_modulo( id );
     END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ad_obtener_opciones_por_perfil`
--

DROP PROCEDURE IF EXISTS `ad_obtener_opciones_por_perfil`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_opciones_por_perfil`(id_perfil BIGINT(20))
BEGIN
     SELECT op.op_id_opcion, op.op_nombre_es, op.op_estado
     FROM AC_Perfil pe, AC_Perfil_Opcion po, AC_Opcion op
     WHERE pe.pe_id_perfil = id_perfil AND pe.pe_id_perfil = po.ref_perfil AND
           po.ref_opcion = op.op_id_opcion AND po.po_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ad_obtener_perfiles_por_usuario`
--

DROP PROCEDURE IF EXISTS `ad_obtener_perfiles_por_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_perfiles_por_usuario`(email VARCHAR(50), clave VARCHAR(15))
BEGIN
    SELECT pe.pe_id_perfil, pe.pe_nombre, pe.pe_estado
    FROM AC_Usuario us, AC_Usuario_Perfil up, AC_Perfil pe
    WHERE us.us_email = email AND us.us_clave = clave AND up.up_estado = 'V' AND
          us.us_id_usuario = up.ref_usuario AND up.ref_perfil = pe.pe_id_perfil;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ad_obtener_usuario`
--

DROP PROCEDURE IF EXISTS `ad_obtener_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_obtener_usuario`(email VARCHAR(50), clave VARCHAR(15))
BEGIN
     SELECT *
     FROM AC_Usuario us, SU_Empresa_Usuario eu, SU_Ciudad ci, SU_Pais pa,
          SU_Empresa em, SU_Tipo_Empresa te
     WHERE us_email = email AND us_clave = clave AND us.us_id_usuario = eu.ref_usuario AND
           pa.pa_id_pais = ci.ref_pais AND us.ref_ciudad = ci.ci_id_ciudad AND
           eu.ref_empresa = em.em_id_empresa AND em.ref_tipo_empresa = te.te_id_tipo_empresa;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `ad_validar_usuario`
--

DROP PROCEDURE IF EXISTS `ad_validar_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ad_validar_usuario`(email VARCHAR(50), clave VARCHAR(15), OUT centinela BINARY)
BEGIN
     IF EXISTS( SELECT * FROM AC_Usuario WHERE us_email = email AND us_clave = clave ) THEN
        SET centinela = 1;
     ELSE
        SET centinela = 0;
     END IF;
     
     SELECT centinela;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_administrar_empresa`
--

DROP PROCEDURE IF EXISTS `su_administrar_empresa`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_empresa`(tipo VARCHAR(20), id_empresa BIGINT(20), nombre VARCHAR(50), direccion VARCHAR(60), telefono VARCHAR(16), codigo_postal VARCHAR(10), fax VARCHAR(16), logo VARCHAR(100), web VARCHAR(50), siglas VARCHAR(100), estado CHAR(1), id_ciudad BIGINT(20), id_tipo_empresa BIGINT(20), OUT mensaje VARCHAR(50), OUT id_objeto BIGINT(20))
BEGIN
     IF tipo = 'registrar' THEN
        CALL `su_buscar_empresa`(nombre, id_ciudad, id_tipo_empresa, id_objeto);
        IF id_objeto <> NULL THEN
           SET mensaje = 'ERROR:empresa repetida';
        ELSE
           INSERT INTO `su_empresa` (em_nombre, em_direccion, em_telefono, em_codigo_postal, em_fax, em_logo, em_web, em_siglas, em_estado, ref_ciudad, ref_tipo_empresa)
           VALUES ( nombre, direccion, telefono, codigo_postal, fax, logo, web, siglas, estado, id_ciudad, id_tipo_empresa );
           
           SET mensaje = 'OK:registro';
           CALL `su_buscar_empresa`(nombre, id_ciudad, id_tipo_empresa, id_objeto);
        END IF;
     END IF;
     
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_administrar_usuario`
--

DROP PROCEDURE IF EXISTS `su_administrar_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_administrar_usuario`(tipo VARCHAR(20), nombre VARCHAR(50), apellido VARCHAR(50), email VARCHAR(45), clave VARCHAR(15), genero CHAR(1), fecha_nacimiento DATETIME, direccion VARCHAR(100), telefono VARCHAR(20), celular VARCHAR(20), actividad CHAR(1), hoja_vida VARCHAR(50), foto VARCHAR(50), estado CHAR(1), id_titulo BIGINT(20), id_ciudad BIGINT(20), id_empresa BIGINT(20), id_cargo BIGINT(20), telefono_oficina VARCHAR(16), OUT mensaje VARCHAR(50))
BEGIN
     DECLARE id_usuario BIGINT(20);

     IF tipo = 'registrar' THEN
        CALL su_buscar_usuario(email, id_usuario);
        
        IF id_usuario <=> NULL THEN
          INSERT INTO AC_Usuario(us_nombre, us_apellido, us_email, us_clave,
                                 us_genero, us_fecha_nacimiento,
                                 us_direccion, us_telefono, us_celular, us_actividad,
                                 us_hoja_vida, us_foto, us_estado, ref_titulo, ref_ciudad)
                         VALUES (nombre, apellido, email, clave,
                                 genero, fecha_nacimiento,
                                 direccion, telefono, celular, actividad,
                                 hoja_vida, foto, estado, id_titulo, id_ciudad);

          CALL su_buscar_usuario(email, id_usuario);
          INSERT INTO SU_Empresa_Usuario (eu_estado, eu_telefono, ref_cargo, ref_empresa, ref_usuario)
          VALUES ('V', telefono_oficina, id_cargo, id_empresa, id_usuario);
          
          INSERT INTO AC_Usuario_Perfil (up_estado, ref_usuario, ref_perfil)
          VALUES ('V', id_usuario, 3);

          SET mensaje = 'OK:registro';
        ELSE
          SET mensaje = 'ERROR:email repetido';
        END IF;
     END IF;
     
     IF tipo = 'modificar' THEN
     
        UPDATE AC_Usuario
        SET us_nombre = nombre, us_apellido = apellido, us_genero = genero,
            us_fecha_nacimiento = fecha_nacimiento, us_direccion = direccion,
            us_telefono = telefono, us_celular = celular, us_actividad = actividad,
            us_hoja_vida = hoja_vida, us_foto = foto, us_estado = estado,
            ref_titulo = id_titulo, ref_ciudad = id_ciudad
        WHERE us_email = email;
        
        UPDATE SU_Empresa_Usuario
        SET ref_cargo = id_cargo, ref_empresa = id_empresa, eu_telefono = telefono_oficina
        WHERE ref_usuario = (SELECT us_id_usuario FROM AC_Usuario WHERE us_email = email);
        
        SET mensaje = 'OK:modificar';
     END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_buscar_empresa`
--

DROP PROCEDURE IF EXISTS `su_buscar_empresa`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_buscar_empresa`(nombre VARCHAR(50), id_ciudad BIGINT(20), id_tipo_empresa BIGINT(20), OUT id_empresa BIGINT(20))
BEGIN
     SELECT em_id_empresa INTO id_empresa FROM SU_Empresa
     WHERE em_nombre = nombre AND ref_ciudad = id_ciudad AND ref_tipo_empresa = id_tipo_empresa;

     SELECT id_empresa;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_buscar_usuario`
--

DROP PROCEDURE IF EXISTS `su_buscar_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_buscar_usuario`(email VARCHAR(50), OUT id_usuario BIGINT(20))
BEGIN
     SELECT us_id_usuario INTO id_usuario FROM AC_Usuario WHERE us_email = email;
     
     SELECT id_usuario;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_crear_cuenta`
--

DROP PROCEDURE IF EXISTS `su_crear_cuenta`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_crear_cuenta`(usuario VARCHAR(50), nombre VARCHAR(50), apellido VARCHAR(50), email VARCHAR(45), clave VARCHAR(10), genero CHAR(1), fecha_nacimiento DATETIME, direccion VARCHAR(100), telefono VARCHAR(20), celular VARCHAR(20), actividad CHAR(1), hoja_vida VARCHAR(50), foto VARCHAR(50), estado CHAR(1), id_titulo BIGINT(20), id_ciudad BIGINT(20))
    COMMENT 'Permite la creacion de una cuenta en WebSAE de un Usuario'
BEGIN

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_modificar_clave`
--

DROP PROCEDURE IF EXISTS `su_modificar_clave`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_modificar_clave`(clave_antigua VARCHAR(15), clave_actual VARCHAR(15), email VARCHAR(50))
BEGIN
     UPDATE AC_Usuario
     SET us_clave = clave_actual
     WHERE us_clave = clave_antigua AND us_email = email;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_cargos`
--

DROP PROCEDURE IF EXISTS `su_mostrar_cargos`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_cargos`()
BEGIN
     SELECT * FROM SU_Cargo WHERE ca_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_cargos_por_tipo_empresa`
--

DROP PROCEDURE IF EXISTS `su_mostrar_cargos_por_tipo_empresa`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_cargos_por_tipo_empresa`(id_tipo_empresa BIGINT(20))
BEGIN
     SELECT ca.ca_id_cargo, ca.ca_nombre, ca.ca_estado
     FROM `su_cargo` ca, `su_tipo_empresa` te, `su_tipo_empresa_cargo` tec
     WHERE ca.ca_id_cargo = tec.ref_cargo AND tec.ref_tipo_empresa = te.te_id_tipo_empresa AND
           te.te_id_tipo_empresa = id_tipo_empresa AND tec.te_estado = 'V' AND ca.ca_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_ciudades`
--

DROP PROCEDURE IF EXISTS `su_mostrar_ciudades`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_ciudades`(id_pais VARCHAR(3))
BEGIN
     SELECT ci.ci_id_ciudad, ci.ci_nombre, ci.ci_distrito, ci.ci_estado
     FROM SU_Pais pa, SU_Ciudad ci
     WHERE pa.pa_id_pais = id_pais AND pa.pa_id_pais = ci.ref_pais AND ci.ci_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_empresas_por_tipo_empresa`
--

DROP PROCEDURE IF EXISTS `su_mostrar_empresas_por_tipo_empresa`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_empresas_por_tipo_empresa`(id_tipo_empresa BIGINT(20))
BEGIN
     SELECT * FROM `su_empresa` em, `su_tipo_empresa` te
     WHERE em.ref_tipo_empresa = te.te_id_tipo_empresa AND
           te.te_id_tipo_empresa = id_tipo_empresa AND em.em_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_paises`
--

DROP PROCEDURE IF EXISTS `su_mostrar_paises`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_paises`()
BEGIN
     SELECT * FROM `su_pais` WHERE pa_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_tipos_empresas`
--

DROP PROCEDURE IF EXISTS `su_mostrar_tipos_empresas`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_tipos_empresas`()
BEGIN
     SELECT * FROM SU_Tipo_Empresa WHERE te_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_mostrar_titulos`
--

DROP PROCEDURE IF EXISTS `su_mostrar_titulos`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_mostrar_titulos`()
BEGIN
     SELECT * FROM `su_titulo` WHERE ti_estado = 'V';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `su_obtener_usuario`
--

DROP PROCEDURE IF EXISTS `su_obtener_usuario`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `su_obtener_usuario`(email VARCHAR(50))
BEGIN
     SELECT * FROM AC_Usuario WHERE us_email = email;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of view `v_usuario`
--

DROP TABLE IF EXISTS `v_usuario`;
DROP VIEW IF EXISTS `v_usuario`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `websagi`.`v_usuario` AS select `us`.`us_id_usuario` AS `us_id_usuario`,`us`.`us_nombre` AS `us_nombre`,`us`.`us_apellido` AS `us_apellido`,`us`.`us_email` AS `us_email`,`us`.`us_clave` AS `us_clave`,`us`.`us_genero` AS `us_genero`,`us`.`us_fecha_nacimiento` AS `us_fecha_nacimiento`,`us`.`us_direccion` AS `us_direccion`,`us`.`us_telefono` AS `us_telefono`,`us`.`us_celular` AS `us_celular`,`us`.`us_actividad` AS `us_actividad`,`us`.`us_hoja_vida` AS `us_hoja_vida`,`us`.`us_foto` AS `us_foto`,`us`.`us_estado` AS `us_estado`,`us`.`us_linkedin` AS `us_linkedin`,`us`.`ref_titulo` AS `ref_titulo`,`us`.`ref_ciudad` AS `ref_ciudad`,`pa`.`pa_id_pais` AS `pa_id_pais`,`pa`.`pa_nombre` AS `pa_nombre`,`pa`.`pa_continente` AS `pa_continente`,`pa`.`pa_region` AS `pa_region`,`pa`.`pa_estado` AS `pa_estado`,`eu`.`eu_id_empresa_usuario` AS `eu_id_empresa_usuario`,`eu`.`eu_telefono` AS `eu_telefono`,`eu`.`eu_fax` AS `eu_fax`,`eu`.`eu_estado` AS `eu_estado`,`eu`.`ref_cargo` AS `ref_cargo`,`eu`.`ref_empresa` AS `ref_empresa`,`ci`.`ci_id_ciudad` AS `ci_id_ciudad`,`ci`.`ci_estado` AS `ci_estado`,`ci`.`ci_nombre` AS `ci_nombre`,`ci`.`ci_distrito` AS `ci_distrito`,`ci`.`ref_pais` AS `ci_id_pais`,`em`.`em_id_empresa` AS `em_id_empresa`,`em`.`em_nombre` AS `em_nombre`,`em`.`em_direccion` AS `em_direccion`,`em`.`em_telefono` AS `em_telefono`,`em`.`em_codigo_postal` AS `em_codigo_postal`,`em`.`em_fax` AS `em_fax`,`em`.`em_logo` AS `em_logo`,`em`.`em_web` AS `em_web`,`em`.`em_siglas` AS `em_siglas`,`em`.`em_estado` AS `em_estado`,`em`.`ref_ciudad` AS `em_id_ciudad`,`em`.`ref_tipo_empresa` AS `em_id_tipo_empresa`,`te`.`te_id_tipo_empresa` AS `te_id_tipo_empresa`,`te`.`te_nombre` AS `te_nombre`,`te`.`te_descripcion` AS `te_descripcion`,`te`.`te_estado` AS `te_estado` from (((((`websae`.`ac_usuario` `us` join `websae`.`su_empresa_usuario` `eu`) join `websae`.`su_ciudad` `ci`) join `websae`.`su_pais` `pa`) join `websae`.`su_empresa` `em`) join `websae`.`su_tipo_empresa` `te`) where ((`us`.`us_id_usuario` = `eu`.`ref_usuario`) and (`pa`.`pa_id_pais` = `ci`.`ref_pais`) and (`us`.`ref_ciudad` = `ci`.`ci_id_ciudad`) and (`eu`.`ref_empresa` = `em`.`em_id_empresa`) and (`em`.`ref_tipo_empresa` = `te`.`te_id_tipo_empresa`));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
