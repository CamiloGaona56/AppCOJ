-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.24-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para appcoj
CREATE DATABASE IF NOT EXISTS `appcoj` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `appcoj`;

-- Volcando estructura para tabla appcoj.abono
CREATE TABLE IF NOT EXISTS `abono` (
  `id_abono` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `abono` double DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `medio` varchar(200) DEFAULT NULL,
  `observacion` text DEFAULT NULL,
  `estado` int(11) DEFAULT 1,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_abono`) USING BTREE,
  KEY `FK_PacienteAbono` (`id_paciente`),
  CONSTRAINT `FK_PacienteAbono` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.abono: ~6 rows (aproximadamente)
DELETE FROM `abono`;
INSERT INTO `abono` (`id_abono`, `id_paciente`, `fecha`, `abono`, `saldo`, `medio`, `observacion`, `estado`, `fecha_registro`) VALUES
	(1, 1, '2024-11-19', 50000, 0, 'Nequi', 'Pago completo', 1, '2024-11-20 02:24:59'),
	(2, 1, '2024-11-19', 0, 0, 'DaviPlata', 'test1', 1, '2024-11-20 02:32:54'),
	(3, 1, '2024-11-18', 0, 0, 'Tarjeta', 'test', 0, '2024-11-20 02:34:44'),
	(4, 1, '2024-11-19', 3000, 0, 'DaviPlata', 'test', 0, '2024-11-20 02:35:08'),
	(5, 1, '2024-11-19', 0, 0, 'Nequi', 'test', 0, '2024-11-20 02:38:30'),
	(6, 1, '2024-11-19', 50000, 40000, 'Nequi', 'aaaa', 1, '2024-11-20 03:01:06');

-- Volcando estructura para tabla appcoj.configuracion
CREATE TABLE IF NOT EXISTS `configuracion` (
  `id_configuracion` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(200) DEFAULT NULL,
  `parametro` varchar(500) DEFAULT NULL,
  `valor` text DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_configuracion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.configuracion: ~4 rows (aproximadamente)
DELETE FROM `configuracion`;
INSERT INTO `configuracion` (`id_configuracion`, `categoria`, `parametro`, `valor`, `estado`) VALUES
	(1, 'Tipo_documento', 'CC', 'Cedula Ciudana', 1),
	(2, 'Tipo_documento', 'CE', 'Cedula Extrajera', 1),
	(3, 'Tipo_documento', 'TI', 'Tarjeta Indentidad', 1),
	(4, 'Dientes', 'Numero de dientes', '[11][12][13][14][15][16][17][18][21][22][23][24][25][26][27][28][29][31][32][33][34][35][36][37][38][41][42][43][44][45][45][46][47][48][48][51][52][53][54][55][61][62][63][64][65][71][72][73][74][75][81][82][83][84][85]', 1);

-- Volcando estructura para tabla appcoj.paciente
CREATE TABLE IF NOT EXISTS `paciente` (
  `id_paciente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `documento` int(11) DEFAULT NULL,
  `celular` varchar(200) DEFAULT NULL,
  `fecha` date NOT NULL,
  `recomendacion` varchar(200) DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT 0,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_paciente`),
  UNIQUE KEY `documento` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.paciente: ~3 rows (aproximadamente)
DELETE FROM `paciente`;
INSERT INTO `paciente` (`id_paciente`, `nombre`, `tipo_documento`, `documento`, `celular`, `fecha`, `recomendacion`, `estado`, `fecha_registro`) VALUES
	(1, 'Camilo Gaona', 'CC', 1012462411, '312571674441', '2024-09-12', 'Hja', 1, '2024-09-12 10:11:12'),
	(2, 'Laura Bohorquez', 'CC', 4534534, '3125716748', '2024-09-17', 'Familiar Bentacourd', 1, '2024-09-18 01:40:24'),
	(3, 'Sandra Betacourd', 'CC', 1234567, '32115744784', '2024-09-17', 'Familiar Bentacourd', 1, '2024-09-18 01:40:44'),
	(4, 'aaa', 'CC', 453453453, '32115744784', '2024-09-17', 'Familiar Bentacourd', 1, '2024-09-18 01:46:50'),
	(6, 'Juan David Bohorquez', 'CC', 100417128, '312571648', '2024-10-05', 'Familiar - tia', 0, '2024-10-05 23:46:36');

-- Volcando estructura para tabla appcoj.procedimiento
CREATE TABLE IF NOT EXISTS `procedimiento` (
  `id_procedimiento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_procedimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.procedimiento: ~3 rows (aproximadamente)
DELETE FROM `procedimiento`;
INSERT INTO `procedimiento` (`id_procedimiento`, `nombre`, `estado`, `fecha_registro`) VALUES
	(1, 'Implantes Dentales', 1, '2024-09-05 23:54:33'),
	(2, 'Extracción de las Muelas del Juicio', 1, '2024-09-05 23:58:02'),
	(3, 'Elevación de Seno Maxilar', 1, '2024-09-06 00:02:32');

-- Volcando estructura para tabla appcoj.proyeccion
CREATE TABLE IF NOT EXISTS `proyeccion` (
  `id_proyeccion` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `numero_diente` int(200) DEFAULT NULL,
  `id_procedimiento` int(11) DEFAULT NULL,
  `presupuesto_actual` double DEFAULT NULL,
  `presupuesto_inicial` double DEFAULT NULL,
  `observacion` text DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  `fecha_modificacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_proyeccion`),
  KEY `FK_paciente` (`id_paciente`),
  KEY `FK_usuario` (`id_usuario`),
  KEY `FK_procedimiento` (`id_procedimiento`),
  CONSTRAINT `FK_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_procedimiento` FOREIGN KEY (`id_procedimiento`) REFERENCES `procedimiento` (`id_procedimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.proyeccion: ~9 rows (aproximadamente)
DELETE FROM `proyeccion`;
INSERT INTO `proyeccion` (`id_proyeccion`, `id_paciente`, `id_usuario`, `numero_diente`, `id_procedimiento`, `presupuesto_actual`, `presupuesto_inicial`, `observacion`, `estado`, `fecha_registro`, `fecha_modificacion`) VALUES
	(1, 1, 1, 0, 1, 150000, 160000, 'test 1', 2, '2024-10-12 21:26:20', '2024-11-20 03:01:50'),
	(2, 1, 1, 15, 1, 350000, 300000, NULL, 2, '2024-10-12 21:26:20', '2024-11-20 03:01:50'),
	(3, 1, 1, 11, 1, 87777, NULL, NULL, 1, '2024-10-12 22:15:39', '2024-10-20 18:22:14'),
	(4, 1, 1, 28, 2, 25555, NULL, NULL, 1, '2024-10-12 22:18:14', '2024-10-20 18:22:13'),
	(5, 1, 1, 11, 2, 655555, NULL, NULL, 1, '2024-10-12 22:18:14', '2024-10-20 18:22:13'),
	(6, 1, 1, 13, 2, 100000, 50000, NULL, 1, '2024-10-12 22:36:13', '2024-10-16 23:41:12'),
	(7, 1, 1, 1, 2, 6000, NULL, NULL, 1, '2024-10-12 22:36:13', '2024-10-16 23:41:13'),
	(8, 3, 1, 0, 1, 30000, 25000, NULL, 2, '2024-10-20 21:31:48', '2024-10-20 21:31:56'),
	(9, 1, 1, 0, 3, 10, NULL, 'null', 1, '2024-10-30 02:12:56', '2024-10-30 02:12:56'),
	(10, 6, 1, 0, 1, 50000, NULL, 'Test', 1, '2024-10-30 02:13:41', '2024-10-30 02:13:41'),
	(11, 2, 1, 1, 1, 25000, NULL, 'tst', 1, '2024-11-20 02:32:04', '2024-11-20 02:32:04');

-- Volcando estructura para tabla appcoj.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `estado` int(11) DEFAULT 1,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.rol: ~0 rows (aproximadamente)
DELETE FROM `rol`;
INSERT INTO `rol` (`id_rol`, `nombre`, `estado`, `fecha_registro`) VALUES
	(1, 'Administrador', 1, '2024-08-31 15:11:03'),
	(2, 'Gestor', 1, '2024-09-02 01:23:46');

-- Volcando estructura para tabla appcoj.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `apellido` varchar(200) DEFAULT NULL,
  `documento` int(11) DEFAULT NULL,
  `correo` varchar(200) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `contrasena` varchar(500) DEFAULT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `documento` (`documento`),
  KEY `FK_rol` (`id_rol`),
  CONSTRAINT `FK_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla appcoj.usuario: ~1 rows (aproximadamente)
DELETE FROM `usuario`;
INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `documento`, `correo`, `estado`, `contrasena`, `id_rol`, `fecha_registro`) VALUES
	(1, 'Camilo', 'Gaona', 1012462411, 'camilogaona65@gmail.com', 1, '2025', 2, '2024-08-31 17:26:12');

-- Volcando estructura para procedimiento appcoj.Abono_c_ConsultarAbono
DELIMITER //
CREATE PROCEDURE `Abono_c_ConsultarAbono`(
	IN `IdPc` INT
)
SELECT a.id_abono,a.id_paciente,a.fecha,a.abono,a.saldo,a.medio,a.observacion,a.estado,a.fecha_registro
FROM abono a
WHERE a.id_paciente = IdPc AND a.estado = 1//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Abono_c_ConsultarAbonoId
DELIMITER //
CREATE PROCEDURE `Abono_c_ConsultarAbonoId`(
	IN `IdAb` INT
)
SELECT a.id_abono,a.id_paciente,a.fecha,a.abono,a.saldo,a.medio,a.observacion,a.estado,a.fecha_registro
FROM abono a
WHERE a.id_abono = IdAb//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Abono_m_CambiarEstado
DELIMITER //
CREATE PROCEDURE `Abono_m_CambiarEstado`(
	IN `IdAb` INT,
	IN `Etd` INT

)
UPDATE abono a
SET a.estado = Etd
WHERE a.id_abono = IdAb//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Abono_m_ModificarAbono
DELIMITER //
CREATE PROCEDURE `Abono_m_ModificarAbono`(
	IN `IdAb` INT,
	IN `Fch` DATE,
	IN `Abo` DOUBLE,
	IN `Sld` DOUBLE,
	IN `Mdo` VARCHAR(200),
	IN `Obs` TEXT
)
UPDATE abono a
SET a.fecha = Fch,a.abono = Abo,a.saldo = Sld,a.medio = Mdo,a.observacion = Obs
WHERE a.id_abono = IdAb//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Abono_r_RegistrarAbono
DELIMITER //
CREATE PROCEDURE `Abono_r_RegistrarAbono`(
	IN `IdPc` INT,
	IN `Fch` DATE,
	IN `Abo` DOUBLE,
	IN `Sld` DOUBLE,
	IN `Mdo` VARCHAR(200),
	IN `Obs` TEXT
)
INSERT INTO abono (id_paciente,fecha,abono,saldo,medio,observacion)
VALUE(IdPc,Fch,Abo,Sld,Mdo,Obs)//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Configuracion_c_ConsultarCategoria
DELIMITER //
CREATE PROCEDURE `Configuracion_c_ConsultarCategoria`(
	IN `Cgc` TEXT
)
SELECT c.id_configuracion,c.categoria,c.parametro,c.valor,c.estado
FROM configuracion c
WHERE c.categoria = Cgc//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_c_ConsultarPaciente
DELIMITER //
CREATE PROCEDURE `Paciente_c_ConsultarPaciente`()
SELECT p.id_paciente,p.tipo_documento,p.documento,p.nombre,p.celular,p.fecha,p.recomendacion,p.estado,p.fecha_registro
FROM paciente p
ORDER BY estado desc, nombre ASC//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_c_ConsultarPacienteDocumento
DELIMITER //
CREATE PROCEDURE `Paciente_c_ConsultarPacienteDocumento`(
	IN `Dcm` INT
)
SELECT p.id_paciente,p.tipo_documento,p.documento,p.nombre,p.celular,p.fecha,p.recomendacion,p.estado,p.fecha_registro
FROM paciente p
WHERE p.documento = Dcm//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_c_ConsultarPacienteEstado
DELIMITER //
CREATE PROCEDURE `Paciente_c_ConsultarPacienteEstado`(
	IN `est` INT
)
SELECT p.id_paciente,p.tipo_documento,p.documento,p.nombre,p.celular,p.fecha,p.recomendacion,p.estado,p.fecha_registro
FROM paciente p
WHERE p.estado = est//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_c_ConsultarPacienteId
DELIMITER //
CREATE PROCEDURE `Paciente_c_ConsultarPacienteId`(
	IN `idP` INT
)
SELECT p.id_paciente,p.tipo_documento,p.documento,p.nombre,p.celular,p.fecha,p.recomendacion,p.estado,p.fecha_registro
FROM paciente p
WHERE p.id_paciente = IdP//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_m_ModificarEstadoPaciente
DELIMITER //
CREATE PROCEDURE `Paciente_m_ModificarEstadoPaciente`(idP INT, etd INT)
UPDATE paciente p
SET p.estado = etd
WHERE p.id_paciente = idP//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_m_ModificarPaciente
DELIMITER //
CREATE PROCEDURE `Paciente_m_ModificarPaciente`(idP INT,nbr VARCHAR(200), tdc VARCHAR(100),dcm INT, cll VARCHAR(200), rmd VARCHAR(200), fcH DATE)
UPDATE paciente u
SET nombre = nbr,tipo_documento = tdc,documento = dcm,u.celular = cll,recomendacion = rmd, u.fecha = fch
WHERE u.id_paciente = idP//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Paciente_r_RegistrarPaciente
DELIMITER //
CREATE PROCEDURE `Paciente_r_RegistrarPaciente`(
	IN `nbr` VARCHAR(200),
	IN `tdc` VARCHAR(100),
	IN `dcm` INT,
	IN `cll` VARCHAR(200),
	IN `rcm` VARCHAR(200),
	IN `fch` DATE
)
INSERT INTO paciente (nombre,tipo_documento,documento,celular,recomendacion,fecha,estado,fecha_registro)
VALUES (nbr,tdc,dcm,cll,rcm,fch,1,NOW())//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Procedimiento_c_ConsultaProcedimiento
DELIMITER //
CREATE PROCEDURE `Procedimiento_c_ConsultaProcedimiento`()
SELECT p.id_procedimiento,p.nombre,p.estado,p.fecha_registro
FROM procedimiento p//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Procedimiento_c_ConsultaProcedimientoActivo
DELIMITER //
CREATE PROCEDURE `Procedimiento_c_ConsultaProcedimientoActivo`()
SELECT p.id_procedimiento,p.nombre,p.estado,p.fecha_registro
FROM procedimiento p
WHERE p.estado = 1//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Procedimiento_c_ConsultaProcedimientoId
DELIMITER //
CREATE PROCEDURE `Procedimiento_c_ConsultaProcedimientoId`(Ipd INT)
SELECT p.id_procedimiento,p.nombre,p.estado,p.fecha_registro
FROM procedimiento p
WHERE p.id_procedimiento = Ipd//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Procedimiento_M_ModificarEstadoProcedimiento
DELIMITER //
CREATE PROCEDURE `Procedimiento_M_ModificarEstadoProcedimiento`(Ipd INT, etd INT)
UPDATE procedimiento p
SET p.estado = etd
WHERE p.id_procedimiento = Ipd//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Procedimiento_M_ModificarProcedimiento
DELIMITER //
CREATE PROCEDURE `Procedimiento_M_ModificarProcedimiento`(Ipd INT, nbr VARCHAR(500))
UPDATE procedimiento p 
SET p.nombre = nbr 
WHERE p.id_procedimiento = Ipd//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Procedimiento_r_RegistrarProcedimiento
DELIMITER //
CREATE PROCEDURE `Procedimiento_r_RegistrarProcedimiento`(
	IN `nbr` VARCHAR(500)
)
INSERT INTO procedimiento (nombre,estado,fecha_registro)
VALUES (nbr,1, NOW())//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_c_ConsultaProyeccionxPaciente
DELIMITER //
CREATE PROCEDURE `Proyeccion_c_ConsultaProyeccionxPaciente`(
	IN `IdP` int
)
SELECT p.id_proyeccion,p.id_paciente,p.id_usuario,CONCAT(u.nombre,' ',u.apellido) AS 'Nombre Usuario',p.numero_diente,p.id_procedimiento, pd.nombre,
			p.presupuesto_inicial,p.presupuesto_actual,p.estado,p.fecha_registro,p.observacion
FROM proyeccion p
INNER JOIN procedimiento pd ON pd.id_procedimiento = p.id_procedimiento
INNER JOIN usuario u ON p.id_usuario = u.id_usuario
WHERE p.id_paciente = IdP AND p.estado > 0
ORDER BY p.numero_diente ASC//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_c_ConsultaProyeccionxPacientexIdProyeccion
DELIMITER //
CREATE PROCEDURE `Proyeccion_c_ConsultaProyeccionxPacientexIdProyeccion`(
	IN `IdPy` INT
)
SELECT p.id_proyeccion,p.id_paciente,p.id_usuario,CONCAT(u.nombre,' ',u.apellido) AS 'Nombre Usuario',p.numero_diente,p.id_procedimiento, pd.nombre,
			p.presupuesto_inicial,p.presupuesto_actual,p.estado,p.fecha_registro,p.observacion
FROM proyeccion p
INNER JOIN procedimiento pd ON pd.id_procedimiento = p.id_procedimiento
INNER JOIN usuario u ON p.id_usuario = u.id_usuario
WHERE p.id_proyeccion = IdPy//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_c_ConsultarTotalPresupuesto
DELIMITER //
CREATE PROCEDURE `Proyeccion_c_ConsultarTotalPresupuesto`(
	IN `IdP` INT
)
SELECT p.id_paciente, COALESCE(SUM(p.presupuesto_actual), 0) AS 'Total Presupuesto'
FROM proyeccion p
WHERE p.id_paciente = IdP AND p.estado > 0
GROUP BY p.id_paciente//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_m_ModificarEstadoRevisarEliminar
DELIMITER //
CREATE PROCEDURE `Proyeccion_m_ModificarEstadoRevisarEliminar`(
	IN `IdPy` INT,
	IN `Etd` INT
)
UPDATE proyeccion p
SET p.estado = Etd
WHERE p.id_proyeccion = IdPy//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_m_ModificarProyeccionId
DELIMITER //
CREATE PROCEDURE `Proyeccion_m_ModificarProyeccionId`(
	IN `IdPy` INT,
	IN `Ndt` INT,
	IN `Ipd` INT,
	IN `Pac` DOUBLE,
	IN `Obs` TEXT
)
UPDATE proyeccion p
SET p.numero_diente = Ndt, p.id_procedimiento = Ipd, p.presupuesto_actual = Pac, p.observacion = Obs
WHERE p.id_proyeccion = IdPy//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_m_ModificarProyeccionIdPresupuestoInicial
DELIMITER //
CREATE PROCEDURE `Proyeccion_m_ModificarProyeccionIdPresupuestoInicial`(IdPy INT, Pic DOUBLE)
UPDATE proyeccion p
SET p.presupuesto_inicial = Pic
WHERE p.id_proyeccion = IdPy//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Proyeccion_r_RegistrarProyeccion
DELIMITER //
CREATE PROCEDURE `Proyeccion_r_RegistrarProyeccion`(
	IN `IdP` INT,
	IN `IdU` INT,
	IN `Ndt` INT,
	IN `IdPc` INT,
	IN `Pac` DOUBLE,
	IN `Obs` TEXT
)
INSERT INTO proyeccion (id_paciente,id_usuario,numero_diente,id_procedimiento,presupuesto_actual,observacion,estado)
VALUES (IdP,IdU,Ndt,IdPc,Pac,Obs,1)//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Rol_c_ConsultarRol
DELIMITER //
CREATE PROCEDURE `Rol_c_ConsultarRol`()
SELECT r.id_rol,r.nombre,r.estado,r.fecha_registro
FROM rol r
WHERE r.estado = 1//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Rol_c_ConsultarRolGeneral
DELIMITER //
CREATE PROCEDURE `Rol_c_ConsultarRolGeneral`()
SELECT r.id_rol,r.nombre,r.estado,r.fecha_registro
FROM rol r//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Rol_c_ConsultarRolId
DELIMITER //
CREATE PROCEDURE `Rol_c_ConsultarRolId`(
	IN `Irl` INT
)
SELECT r.id_rol,r.nombre,r.estado,r.fecha_registro
FROM rol r
WHERE r.id_rol = Irl//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Rol_m_ModificarEstado
DELIMITER //
CREATE PROCEDURE `Rol_m_ModificarEstado`(Irl INT, etd INT)
UPDATE rol r
SET r.estado = etd
WHERE r.id_rol = Irl//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Rol_m_ModificarRol
DELIMITER //
CREATE PROCEDURE `Rol_m_ModificarRol`(
	IN `Irl` INT,
	IN `nbr` VARCHAR(200)
)
UPDATE rol r
SET r.nombre = nbr
WHERE r.id_rol = Irl//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Rol_r_RegistrarRol
DELIMITER //
CREATE PROCEDURE `Rol_r_RegistrarRol`(nbr VARCHAR(200))
INSERT INTO rol (nombre,estado,fecha_registro)
VALUES (nbr, 1, NOW())//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_c_ConsultarUsuario
DELIMITER //
CREATE PROCEDURE `Usu_c_ConsultarUsuario`()
SELECT u.id_usuario,u.nombre,u.apellido,u.documento,u.correo,u.estado,u.id_rol,r.nombre,u.contrasena,u.fecha_registro
FROM usuario u
INNER JOIN rol r ON u.id_rol = r.id_rol//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_c_ConsultarUsuarioId
DELIMITER //
CREATE PROCEDURE `Usu_c_ConsultarUsuarioId`(
	IN `idU` INT
)
SELECT u.id_usuario,u.nombre,u.apellido,u.documento,u.correo,u.estado,u.id_rol,r.nombre,u.contrasena,u.fecha_registro
FROM usuario u
INNER JOIN rol r ON u.id_rol = r.id_rol
WHERE u.id_usuario = idU//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_c_ConsultaUsuarioSesion
DELIMITER //
CREATE PROCEDURE `Usu_c_ConsultaUsuarioSesion`(
	IN `dct` INT,
	IN `cts` VARCHAR(500)
)
SELECT u.id_usuario,u.nombre,u.apellido,u.documento,u.correo,u.estado,u.id_rol,r.nombre,u.contrasena,u.fecha_registro, CONCAT(u.nombre,' ',u.apellido) AS 'Nombre Completo'
FROM usuario u
INNER JOIN rol r ON u.id_rol = r.id_rol
WHERE u.documento = dct AND u.contrasena = cts//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_m_CambiarEstadoUsurio
DELIMITER //
CREATE PROCEDURE `Usu_m_CambiarEstadoUsurio`(idU INT, etd INT)
UPDATE usuario u
SET u.estado = etd
WHERE u.id_usuario = idU//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_m_RestablecerContrasena
DELIMITER //
CREATE PROCEDURE `Usu_m_RestablecerContrasena`(
	IN `idU` INT
)
UPDATE usuario u
SET u.contrasena = YEAR(NOW())
WHERE u.id_usuario = idU//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_m_RestablecerContrasenaSession
DELIMITER //
CREATE PROCEDURE `Usu_m_RestablecerContrasenaSession`(
	IN `dcm` INT,
		IN `cts` VARCHAR(500)
)
UPDATE usuario u
SET u.contrasena = cts
WHERE u.documento = dcm//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_r_RegistrarUsuario
DELIMITER //
CREATE PROCEDURE `Usu_r_RegistrarUsuario`(
	IN `nbr` VARCHAR(200),
	IN `apl` VARCHAR(200),
	IN `dcm` INT,
	IN `cro` VARCHAR(200),
	IN `idr` INT
)
INSERT INTO usuario (nombre,apellido,documento,correo,estado,id_rol,contrasena,fecha_registro)
VALUES (nbr, apl,dcm, cro,1,idr,'2024',NOW())//
DELIMITER ;

-- Volcando estructura para procedimiento appcoj.Usu_u_ModificarUsuario
DELIMITER //
CREATE PROCEDURE `Usu_u_ModificarUsuario`(
	IN `idU` INT,
	IN `nbr` VARCHAR(200),
	IN `apl` VARCHAR(200),
	IN `dcm` INT,
	IN `cro` VARCHAR(200),
	IN `idr` INT,
	IN `etd` INT
)
UPDATE usuario u
SET u.nombre = nbr, u.apellido = apl, u.documento = dcm, u.correo = cro, u.id_rol = idr, u.estado = etd
WHERE u.id_usuario = idU//
DELIMITER ;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
