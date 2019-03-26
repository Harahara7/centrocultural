-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 26-Mar-2019 às 23:05
-- Versão do servidor: 10.1.37-MariaDB
-- versão do PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `centro_cultural_bd`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `convidado`
--

CREATE TABLE `convidado` (
  `idConvidado` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `evento`
--

CREATE TABLE `evento` (
  `idEvento` int(11) NOT NULL,
  `idLocatario` int(11) NOT NULL,
  `idConvidado` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `setor` varchar(100) DEFAULT NULL,
  `dataInicio` datetime DEFAULT NULL,
  `dataFim` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `preco` varchar(200) DEFAULT NULL,
  `descricao` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `evento`
--

INSERT INTO `evento` (`idEvento`, `idLocatario`, `idConvidado`, `nome`, `setor`, `dataInicio`, `dataFim`, `status`, `preco`, `descricao`) VALUES
(2, 20, NULL, 'Distribuição de Merendas', 'Pátio da Fundação', '2019-03-02 19:14:00', '2019-03-04 19:14:00', 'Encerrado', '10,00', ''),
(29, 22, NULL, 'Gincana', 'Pátio da Fundação', '2019-03-16 09:00:00', '2019-03-16 19:00:00', 'Confirmado', 'Um quilo de alimento não perecível', '30x Objetos'),
(30, 22, NULL, 'teste', 'Casa das Artes', '2019-03-20 17:21:00', '2019-03-20 20:21:00', 'Agendado', 'wwwwww', '1x Objeto'),
(32, 23, NULL, 'Evento teste', 'Pátio da Fundação', '2019-03-22 18:34:00', '2019-03-23 18:34:00', 'Agendado', 'nada', '1x Datashow\r\n1x Mesa\r\n1x Extensão elétrica'),
(33, 24, NULL, 'Distribuição de Merendas', 'Pátio da Fundação', '2019-03-24 09:30:00', '2019-03-24 12:00:00', 'Confirmado', 'Entrada Franca', 'Ação social para distribuição de lanches');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fisico`
--

CREATE TABLE `fisico` (
  `idFisico` int(11) NOT NULL,
  `cpf` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `fisico`
--

INSERT INTO `fisico` (`idFisico`, `cpf`) VALUES
(17, '01431461229'),
(18, '01431461229'),
(19, '66322115774');

-- --------------------------------------------------------

--
-- Estrutura da tabela `juridico`
--

CREATE TABLE `juridico` (
  `idJuridico` int(11) NOT NULL,
  `cnpj` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `juridico`
--

INSERT INTO `juridico` (`idJuridico`, `cnpj`) VALUES
(3, '19329007000180');

-- --------------------------------------------------------

--
-- Estrutura da tabela `locatario`
--

CREATE TABLE `locatario` (
  `idLocatario` int(11) NOT NULL,
  `idJuridico` int(11) DEFAULT NULL,
  `idFisico` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `logradouro` varchar(150) DEFAULT NULL,
  `bairro` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `locatario`
--

INSERT INTO `locatario` (`idLocatario`, `idJuridico`, `idFisico`, `nome`, `contato`, `email`, `cep`, `logradouro`, `bairro`) VALUES
(20, 3, NULL, 'yuuyuyuutyuyrurt', '(51)46641-7644', 'ewqewqq@email.com', '40230-510', 'Travessa dos Contentes', 'FederaÃ§Ã£o'),
(22, NULL, 17, 'Teste', '(91)98346-2716', 'teste@gmail.com', '24210-206', 'Praia SÃ£o Domingos', 'SÃ£o Domingos'),
(23, NULL, 18, 'Teste do Teste', '(95)73985-7395', 'aaa@gmail.com', '66093-330', 'Jardim TapajÃ³s', 'Marco'),
(24, NULL, 19, 'Locatário Teste', '(59)48317-8571', 'locatario@gmail.com', '69900-064', 'Rua Benjamin Constant', 'Centro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `usuario`, `senha`) VALUES
(1, 'funcionario', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `convidado`
--
ALTER TABLE `convidado`
  ADD PRIMARY KEY (`idConvidado`);

--
-- Indexes for table `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`idEvento`),
  ADD KEY `fk_evento_locatario1_idx` (`idLocatario`),
  ADD KEY `fk_evento_convidado1_idx` (`idConvidado`);

--
-- Indexes for table `fisico`
--
ALTER TABLE `fisico`
  ADD PRIMARY KEY (`idFisico`);

--
-- Indexes for table `juridico`
--
ALTER TABLE `juridico`
  ADD PRIMARY KEY (`idJuridico`);

--
-- Indexes for table `locatario`
--
ALTER TABLE `locatario`
  ADD PRIMARY KEY (`idLocatario`),
  ADD KEY `fk_locatario_juridico1_idx` (`idJuridico`),
  ADD KEY `fk_locatario_fisico1_idx` (`idFisico`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `convidado`
--
ALTER TABLE `convidado`
  MODIFY `idConvidado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evento`
--
ALTER TABLE `evento`
  MODIFY `idEvento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `fisico`
--
ALTER TABLE `fisico`
  MODIFY `idFisico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `juridico`
--
ALTER TABLE `juridico`
  MODIFY `idJuridico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `locatario`
--
ALTER TABLE `locatario`
  MODIFY `idLocatario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `fk_evento_convidado1` FOREIGN KEY (`idConvidado`) REFERENCES `convidado` (`idConvidado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_evento_locatario1` FOREIGN KEY (`idLocatario`) REFERENCES `locatario` (`idLocatario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `locatario`
--
ALTER TABLE `locatario`
  ADD CONSTRAINT `fk_locatario_fisico1` FOREIGN KEY (`idFisico`) REFERENCES `fisico` (`idFisico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_locatario_juridico1` FOREIGN KEY (`idJuridico`) REFERENCES `juridico` (`idJuridico`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
