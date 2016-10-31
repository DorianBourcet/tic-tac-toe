-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 31 Octobre 2016 à 15:18
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tictactoe`
--

-- --------------------------------------------------------

--
-- Structure de la table `grille_jeu`
--

CREATE TABLE `grille_jeu` (
  `id` int(11) NOT NULL,
  `id_partie` int(11) NOT NULL,
  `11` tinyint(1) NOT NULL,
  `12` tinyint(1) NOT NULL,
  `13` tinyint(1) NOT NULL,
  `21` tinyint(1) NOT NULL,
  `22` tinyint(1) NOT NULL,
  `23` tinyint(1) NOT NULL,
  `31` tinyint(1) NOT NULL,
  `32` tinyint(1) NOT NULL,
  `33` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `pseudo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

CREATE TABLE `partie` (
  `id` int(11) NOT NULL,
  `joueur1` varchar(30) NOT NULL,
  `joueur2` varchar(30) NOT NULL,
  `couleur_joueur1` varchar(1) NOT NULL,
  `terminee` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `grille_jeu`
--
ALTER TABLE `grille_jeu`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `partie`
--
ALTER TABLE `partie`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `grille_jeu`
--
ALTER TABLE `grille_jeu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `partie`
--
ALTER TABLE `partie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
