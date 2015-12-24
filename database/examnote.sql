-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 23, 2015 at 11:29 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `examnote`
--
CREATE DATABASE IF NOT EXISTS `examnote` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `examnote`;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `sex` char(1) NOT NULL,
  `emailAddress` varchar(100) NOT NULL,
  `createdUser` datetime NOT NULL,
  `lastLogin` datetime NOT NULL,
  `ipAddress` varchar(100) NOT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `firstname`, `lastname`, `dateOfBirth`, `sex`, `emailAddress`, `createdUser`, `lastLogin`, `ipAddress`, `status`) VALUES
(8, 'admin', '¦ù9§Èç‚x“ùqÔäH-‰', NULL, '', '0000-00-00', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', ''),
(9, 'developersamim', 'n\Zâ(Àz$B±E«¶ïä ', NULL, '', '0000-00-00', '', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', ''),
(10, 'adsf', 'O6â ýrÀ,Šu¾•ír´Y', 'asdf', 'adsf', '0018-09-24', 'M', 'adsf', '2015-12-20 15:03:08', '2015-12-20 15:03:08', '10.1.4.234', 'A'),
(11, 'adsf', '‘ÍÆý’`JˆÜÀhBüj¹f', 'asdf', 'adsf', '2003-03-11', 'M', 'asdf', '2015-12-20 15:09:02', '2015-12-20 15:09:02', '10.1.4.234', 'A'),
(12, 'puspa', '£ë/;KŠ·¾ÉÔe5¦¤', 'puspa', 'tripathi', '2000-05-08', 'F', 'puspa@gmail.com', '2015-12-21 22:24:40', '2015-12-21 22:24:40', '192.168.0.5', 'A');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
