create database sportsocial;

use sportsocial;

CREATE TABLE jogador(
  idjogador INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  nmrCamisa INT NOT NULL,
  idade int not null,
  idTime int,
  nmrPunicoes int,
  PRIMARY KEY (idjogador));
  
CREATE TABLE `time`(
  idTime INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  qntJogadores INT NOT NULL,
  idEsporte int,
  vitorias int,
  derrotas int,
  empates int,
  idCampeonato int,
  idLiga int,
  receita double,
  PRIMARY KEY (idTime));

CREATE TABLE `esporte` (
  `idEsporte` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `duracao` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEsporte`));
  
  CREATE TABLE `partida` (
  `idPartida` INT NOT NULL AUTO_INCREMENT,
  `data` VARCHAR(45) NOT NULL,
  `horario` VARCHAR(45) NOT NULL,
  idTime int,
  idTime2 int,
  idEsporte int,
  idLocal int,
  pontosTime1 int,
  pontosTime2 int,
  idCampeonato int,
  idLiga int,
  PRIMARY KEY (`idPartida`));
  
  CREATE TABLE administrador (
  idAdministrador INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  senha int not null,
  PRIMARY KEY (idAdministrador));

CREATE TABLE `local` (
  `idLocal` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  tipo varchar(45) not null,
  PRIMARY KEY (`idLocal`));
  
  CREATE TABLE `campeonato` (
  `idCampeonato` INT NOT NULL AUTO_INCREMENT,
  nome varchar(45) not null,
  `medalhas` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCampeonato`));
  
  CREATE TABLE `liga` (
  `idLiga` INT NOT NULL AUTO_INCREMENT,
  nome varchar(45) not null,
  `medalhas` int not null,
  `Troféu` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLiga`));
  
ALTER TABLE jogador ADD CONSTRAINT fk_time FOREIGN KEY (idTime) REFERENCES `time`(idTime); 
ALTER TABLE `time` ADD CONSTRAINT fk_ligaTime FOREIGN KEY (idLiga) REFERENCES `liga`(idLiga);
ALTER TABLE `time` ADD CONSTRAINT fk_esporteTime FOREIGN KEY (idEsporte) REFERENCES `esporte`(idEsporte);
ALTER TABLE `time` ADD CONSTRAINT fk_campeonatoTime FOREIGN KEY (idCampeonato) REFERENCES `campeonato`(idCampeonato);
ALTER TABLE `partida` ADD CONSTRAINT `fk_ligaPartida` FOREIGN KEY (idLiga) REFERENCES `liga`(idLiga);
ALTER TABLE `partida` ADD CONSTRAINT `fk_localPartida` FOREIGN KEY (idLocal) REFERENCES `local`(idLocal); 
ALTER TABLE `partida` ADD CONSTRAINT `fk_esporte` FOREIGN KEY (`idEsporte`) REFERENCES `esporte`(`idEsporte`);
ALTER TABLE `partida` ADD CONSTRAINT `fk_CampeonatoPartida` FOREIGN KEY (idCampeonato) REFERENCES `campeonato`(idCampeonato);
ALTER TABLE `partida` ADD CONSTRAINT fk_time1 FOREIGN KEY (idTime) REFERENCES `time`(idTime); 
ALTER TABLE `partida` ADD CONSTRAINT fk_time2 FOREIGN KEY (idTime2) REFERENCES `time`(idTime); 



#criando o administrador
INSERT INTO `sportsocial`.`administrador` (`nome`, `senha`) VALUES ('admin', '1234');

#criando os esportes 
INSERT INTO `sportsocial`.`esporte` (`nome`, `duracao`, `descricao`) VALUES ('futsal', '10:00', 'futsal');
INSERT INTO `sportsocial`.`esporte` (`nome`, `duracao`, `descricao`) VALUES ('volei', '15:00', 'volei');

#criando os times 
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time1', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time2', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time3', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time4', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time5', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time6', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time7', '12', '2');
INSERT INTO `sportsocial`.`time` (`nome`, `qntJogadores`, `idEsporte`) VALUES ('time8', '12', '2');

#criando os jogadores

INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog1', '1', '19', '1');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog2', '2', '19', '1');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog3', '3', '19', '1');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog4', '4', '19', '1');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog5', '5', '19', '1');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog6', '6', '19', '1');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog7', '7', '19', '2');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog8', '8', '19', '2');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog9', '9', '19', '2');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog10', '10', '19', '2');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog11', '11', '19', '2');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog12', '12', '19', '2');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog13', '13', '19', '3');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog14', '14', '19', '3');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog15', '15', '19', '3');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog16', '16', '19', '3');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog17', '17', '19', '3');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog18', '18', '19', '3');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog19', '19', '19', '4');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog20', '20', '19', '4');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog21', '21', '19', '4');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog22', '22', '19', '4');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog23', '23', '19', '4');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog24', '24', '19', '4');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog25', '25', '19', '5');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog26', '26', '19', '5');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog27', '27', '19', '5');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog28', '28', '19', '5');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog29', '29', '19', '5');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog30', '30', '19', '5');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog31', '31', '19', '6');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog32', '32', '19', '6');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog33', '33', '19', '6');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog34', '34', '19', '6');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog35', '35', '19', '6');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog36', '36', '19', '6');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog37', '37', '19', '7');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog38', '38', '19', '7');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog39', '39', '19', '7');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog40', '40', '19', '7');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog41', '41', '19', '7');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog42', '42', '19', '7');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog43', '43', '19', '8');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog44', '44', '19', '8');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog45', '45', '19', '8');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog46', '46', '19', '8');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog47', '47', '19', '8');
INSERT INTO `sportsocial`.`jogador` (`nome`, `nmrCamisa`, `idade`, `idTime`) VALUES ('jog48', '48', '19', '8');
  