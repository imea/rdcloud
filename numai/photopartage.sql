-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 30 Novembre 2015 à 19:26
-- Version du serveur :  5.6.24
-- Version de PHP :  5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `photopartage`
--
CREATE DATABASE IF NOT EXISTS `photopartage` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `photopartage`;

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `USER_EMAIL` varchar(255) NOT NULL,
  `PHOTO_ID` int(255) NOT NULL,
  `COMMENT_CREATED_DATE` datetime DEFAULT NULL,
  `COMMENT_TEXT` text,
  `COMMENT_ID` int(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `comments`
--



-- --------------------------------------------------------

--
-- Structure de la table `friends`
--

CREATE TABLE IF NOT EXISTS `friends` (
  `FRIEND_EMAIL` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `friends`
--



-- --------------------------------------------------------

--
-- Structure de la table `pages`
--

CREATE TABLE IF NOT EXISTS `pages` (
  `ID` int(255) NOT NULL,
  `SECTION` varchar(255) NOT NULL,
  `TITLE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pages`
--

INSERT INTO `pages` (`ID`, `SECTION`, `TITLE`) VALUES
(1, 'welcome', 'Accueil'),
(2, 'sign_in', 'Authentifiez-vous'),
(3, 'sign_up', 'Inscription'),
(4, 'user_area', 'Espace membre '),
(5, 'log_out', 'Déconnexion');

-- --------------------------------------------------------

--
-- Structure de la table `photos`
--

CREATE TABLE IF NOT EXISTS `photos` (
  `PHOTO_ID` int(255) NOT NULL,
  `USER_EMAIL` varchar(255) NOT NULL,
  `IMAGE_PATH` varchar(255) NOT NULL,
  `IMAGE_SIZE` bigint(32) DEFAULT NULL,
  `DATE_UPLOADED` datetime DEFAULT NULL,
  `TAG` varchar(255) NOT NULL,
  `BLOB` longblob NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `photos`
--


-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `USER_EMAIL` varchar(255) NOT NULL,
  `USER_PASSWORD` varchar(255) NOT NULL,
  `USER_FIRST_NAME` varchar(255) DEFAULT NULL,
  `USER_LAST_NAME` varchar(255) DEFAULT NULL,
  `USER_CREATED_DATE` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`USER_EMAIL`, `USER_PASSWORD`, `USER_FIRST_NAME`, `USER_LAST_NAME`, `USER_CREATED_DATE`) VALUES
('a@a', '356a192b7913b04c54574d18c28d46e6395428ab', 'a', 'a', '2015-11-28 16:06:48');

-- --------------------------------------------------------

--
-- Structure de la table `users_friends`
--

CREATE TABLE IF NOT EXISTS `users_friends` (
  `USER_EMAIL` varchar(255) NOT NULL,
  `FRIEND_EMAIL` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users_friends`
--


--
-- Index pour les tables exportées
--

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`COMMENT_ID`), ADD KEY `I_FK_COMMENTS_USERS` (`USER_EMAIL`), ADD KEY `I_FK_COMMENTS_PHOTOS` (`PHOTO_ID`);

--
-- Index pour la table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`FRIEND_EMAIL`);

--
-- Index pour la table `pages`
--
ALTER TABLE `pages`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `photos`
--
ALTER TABLE `photos`
  ADD PRIMARY KEY (`PHOTO_ID`), ADD KEY `I_FK_PHOTOS_USERS` (`USER_EMAIL`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USER_EMAIL`);

--
-- Index pour la table `users_friends`
--
ALTER TABLE `users_friends`
  ADD PRIMARY KEY (`FRIEND_EMAIL`,`USER_EMAIL`), ADD KEY `I_FK_USERS_FRIENDS_FRIENDS` (`FRIEND_EMAIL`), ADD KEY `I_FK_USERS_FRIENDS_USERS` (`USER_EMAIL`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `comments`
--
ALTER TABLE `comments`
  MODIFY `COMMENT_ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `photos`
--
ALTER TABLE `photos`
  MODIFY `PHOTO_ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=88;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`USER_EMAIL`) REFERENCES `users` (`USER_EMAIL`),
ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`PHOTO_ID`) REFERENCES `photos` (`PHOTO_ID`);

--
-- Contraintes pour la table `photos`
--
ALTER TABLE `photos`
ADD CONSTRAINT `photos_ibfk_1` FOREIGN KEY (`USER_EMAIL`) REFERENCES `users` (`USER_EMAIL`);

--
-- Contraintes pour la table `users_friends`
--
ALTER TABLE `users_friends`
ADD CONSTRAINT `users_friends_ibfk_1` FOREIGN KEY (`FRIEND_EMAIL`) REFERENCES `friends` (`FRIEND_EMAIL`),
ADD CONSTRAINT `users_friends_ibfk_2` FOREIGN KEY (`USER_EMAIL`) REFERENCES `users` (`USER_EMAIL`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
