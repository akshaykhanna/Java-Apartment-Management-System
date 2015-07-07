-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2015 at 09:45 PM
-- Server version: 5.5.36
-- PHP Version: 5.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `appartment`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `admin` varchar(10) NOT NULL,
  `pass` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin`, `pass`) VALUES
('username', 'password'),
('akshay', 'pass');

-- --------------------------------------------------------

--
-- Table structure for table `info`
--

CREATE TABLE IF NOT EXISTS `info` (
  `flatno` varchar(5) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` bigint(25) DEFAULT NULL,
  PRIMARY KEY (`flatno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `info`
--

INSERT INTO `info` (`flatno`, `name`, `phone`) VALUES
('37C', 'Akshay', 8860110036),
('40C', 'Piyush', 9464173315),
('6D', 'Rohan', 9464173316),
('8D', 'Ashok', 9464173314);

-- --------------------------------------------------------

--
-- Table structure for table `servcost`
--

CREATE TABLE IF NOT EXISTS `servcost` (
  `serv` varchar(2) NOT NULL,
  `price` int(10) NOT NULL,
  PRIMARY KEY (`serv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servcost`
--

INSERT INTO `servcost` (`serv`, `price`) VALUES
('B', 800),
('C', 1200),
('G', 1000),
('M', 600),
('S', 500);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE IF NOT EXISTS `services` (
  `flatno` varchar(5) NOT NULL,
  `serv` varchar(2) NOT NULL,
  PRIMARY KEY (`flatno`,`serv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`flatno`, `serv`) VALUES
('37C', 'G'),
('37C', 'M'),
('37C', 'S'),
('40C', 'B'),
('40C', 'C'),
('40C', 'G'),
('40C', 'M'),
('40C', 'S'),
('6D', 'G'),
('6D', 'M'),
('8D', 'C'),
('8D', 'G'),
('8D', 'M');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
