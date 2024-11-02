DROP DATABASE IF EXISTS `db_dolcedelizia`;

CREATE DATABASE `db_dolcedelizia`;

USE `db_dolcedelizia`;

--
-- Table structure for table `cajera`
--

DROP TABLE IF EXISTS `cajera`;

CREATE TABLE `cajera` (
  `CodUsuario` int NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(45) NOT NULL,
  `Contrasenia` varchar(45) NOT NULL,
  PRIMARY KEY (`CodUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cajera` VALUES (1,'CAJERA','123456');

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `CodCliente` int NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(200) NOT NULL,
  `Numero_Ruc` varchar(11) NOT NULL,
  `Numero_Dni` varchar(8) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Direccion` varchar(200) NOT NULL,
  PRIMARY KEY (`CodCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cliente` VALUES (1,'Jaser Vasquez Espinoza','10123456781','12345678','999999999','Lima 123 - Perú');

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `CodProducto` int NOT NULL AUTO_INCREMENT,
  `Cantidad` int NOT NULL,
  `Descripcion` varchar(200) NOT NULL,
  `Precio` decimal(15,2) NOT NULL,
  `Imagen` varchar(45) NOT NULL,
  PRIMARY KEY (`CodProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `producto` VALUES (1,100,'Helado de Fresa',2.00,'fresa.jpeg'),(2,100,'Helado de Vainilla',1.50,'vainilla.png'),(3,100,'Helado de Chocolate',3.50,'chocolate.png'),(4,100,'Helado de Lucuma',2.50,'lucuma.png'),(5,100,'Helado Tricolor',2.00,'tricolor.png'),(6,100,'Helado de Menta',1.20,'menta.jpeg');

--
-- Table structure for table `sistema`
--

DROP TABLE IF EXISTS `sistema`;

CREATE TABLE `sistema` (
  `CodSistema` int NOT NULL AUTO_INCREMENT,
  `CodProducto` int NOT NULL,
  `Descripcion` varchar(200) NOT NULL,
  `Cantidad` int NOT NULL,
  `Precio` decimal(15,2) NOT NULL,
  `Total` decimal(15,2) NOT NULL,
  PRIMARY KEY (`CodSistema`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;