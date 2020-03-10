-- version 1.0 --

-- users table
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `LASTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `EMAILADDRESS` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `PASSWORD` varchar(32) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAILADDRESS` (`EMAILADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- admins table
CREATE TABLE `admins` (
  `USERID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`USERID`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- customers table
CREATE TABLE `customers` (
  `USERID` int(11) NOT NULL AUTO_INCREMENT,
  `PHONENUMBER` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`USERID`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;


-- addresses table
CREATE TABLE `addresses` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERID` int(11) NOT NULL,
  `ADDRESS_TITLE` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `ADDRESS_DETAIL` varchar(250) COLLATE utf8_turkish_ci NOT NULL,
  `CITY` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `DISTRICT` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `POSTCODE` varchar(15) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMERID` (`CUSTOMERID`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`CUSTOMERID`) REFERENCES `customers` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- orders table
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMERID` (`CUSTOMERID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CUSTOMERID`) REFERENCES `customers` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;


-- orderitems table
CREATE TABLE `orderitems` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERID` int(11) NOT NULL,
  `PRODUCTID` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `TOTALPRICE` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDERID` (`ORDERID`),
  KEY `PRODUCTID` (`PRODUCTID`),
  CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`ORDERID`) REFERENCES `orders` (`ID`),
  CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`PRODUCTID`) REFERENCES `products` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- categories table 
CREATE TABLE `categories` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;


-- products table
CREATE TABLE `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORYID` int(11) NOT NULL,
  `PRODUCT_NAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `CODE` char(6) COLLATE utf8_turkish_ci NOT NULL,
  `PRICE` double NOT NULL,
  `STOCK_AMOUNT` int(11) NOT NULL,
  `IMAGE` varchar(400) COLLATE utf8_turkish_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `CATEGORYID` (`CATEGORYID`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`CATEGORYID`) REFERENCES `categories` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;



-- version 2.0 --

-- users table
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `LASTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `EMAILADDRESS` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `PASSWORD` varchar(32) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAILADDRESS` (`EMAILADDRESS`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- customers table
CREATE TABLE `customers` (
  `USERID` int(11) NOT NULL,
  `PHONENUMBER` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`USERID`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- admins table
CREATE TABLE `admins` (
  `USERID` int(11) NOT NULL,
  PRIMARY KEY (`USERID`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- addresses table
CREATE TABLE `addresses` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERID` int(11) NOT NULL,
  `ADDRESS_TITLE` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `ADDRESS_DETAIL` varchar(250) COLLATE utf8_turkish_ci NOT NULL,
  `CITY` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `DISTRICT` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `POSTCODE` varchar(15) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMERID` (`CUSTOMERID`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`CUSTOMERID`) REFERENCES `customers` (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- orders table
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `COMPLETEFLAG` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMERID` (`CUSTOMERID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CUSTOMERID`) REFERENCES `customers` (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- orderitems table
CREATE TABLE `orderitems` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERID` int(11) NOT NULL,
  `PRODUCTID` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `TOTALPRICE` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDERID` (`ORDERID`),
  KEY `PRODUCTID` (`PRODUCTID`),
  CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`ORDERID`) REFERENCES `orders` (`ID`),
  CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`PRODUCTID`) REFERENCES `products` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- products table
CREATE TABLE `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORYID` int(11) NOT NULL,
  `PRODUCT_NAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `CODE` char(6) COLLATE utf8_turkish_ci NOT NULL,
  `PRICE` double NOT NULL,
  `STOCK_AMOUNT` int(11) NOT NULL,
  `IMAGE` varchar(400) COLLATE utf8_turkish_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `CATEGORYID` (`CATEGORYID`),
  CONSTRAINT `CATEGORYID` FOREIGN KEY (`CATEGORYID`) REFERENCES `categories` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- categories table
CREATE TABLE `categories` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `PLATFORMID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLATFORMID` (`PLATFORMID`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`PLATFORMID`) REFERENCES `platforms` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- platforms table
CREATE TABLE `platforms` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLATFORM_NAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PLATFORM_NAME` (`PLATFORM_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- bills table
CREATE TABLE `bills` (
  `ORDERID` int(11) NOT NULL,
  `PAYMENTOPTION` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `FIRSTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `LASTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `EMAILADDRESS` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `PHONENUMBER` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `ADDRESS_TITLE` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `ADDRESS_DETAIL` varchar(250) COLLATE utf8_turkish_ci NOT NULL,
  `CITY` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `DISTRICT` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `POSTCODE` varchar(15) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`ORDERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- billitems table
CREATE TABLE `billitems` (
  `BILLID` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `TOTALPRICE` double NOT NULL,
  `PRODUCTNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `CATEGORYNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `PLATFORMNAME` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `CODE` char(6) COLLATE utf8_turkish_ci NOT NULL,
  KEY `BILLID` (`BILLID`),
  CONSTRAINT `billitems_ibfk_1` FOREIGN KEY (`BILLID`) REFERENCES `bills` (`ORDERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;
