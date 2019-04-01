# Host: localhost  (Version: 5.5.53)
# Date: 2018-09-09 10:55:30
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "answer"
#

CREATE TABLE `answer` (
  `aid` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

#
# Structure for table "problem"
#

CREATE TABLE `problem` (
  `pid` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `sum` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Structure for table "requery"
#

CREATE TABLE `requery` (
  `rId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT '',
  PRIMARY KEY (`rId`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

#
# Structure for table "softs"
#

CREATE TABLE `softs` (
  `soid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT 'without',
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`soid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Structure for table "student"
#

CREATE TABLE `student` (
  `sid` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `major` varchar(25) DEFAULT NULL,
  `address` varchar(25) DEFAULT NULL,
  `will` varchar(10) NOT NULL,
  `tel` varchar(25) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "tb_answer"
#

CREATE TABLE `tb_answer` (
  `anid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL DEFAULT '',
  `arid` int(11) unsigned DEFAULT '0',
  `time` varchar(30) DEFAULT NULL,
  `uid` int(11) unsigned NOT NULL DEFAULT '0',
  `ansid` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`anid`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

#
# Structure for table "tb_arcticle"
#

CREATE TABLE `tb_arcticle` (
  `arid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT '',
  `content` varchar(255) NOT NULL DEFAULT '',
  `uid` varchar(30) NOT NULL DEFAULT '',
  `time` varchar(30) DEFAULT NULL,
  `apploud` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`arid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Structure for table "tb_user"
#

CREATE TABLE `tb_user` (
  `uid` varchar(30) NOT NULL DEFAULT '',
  `pwd` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(30) DEFAULT '',
  `disc` varchar(100) DEFAULT NULL,
  `ico` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
