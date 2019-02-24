-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 23-Fev-2019 às 00:17
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
  `preco` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `evento`
--

INSERT INTO `evento` (`idEvento`, `idLocatario`, `idConvidado`, `nome`, `setor`, `dataInicio`, `dataFim`, `status`, `preco`) VALUES
(1, 19, NULL, 'Exposição', 'Teatro Margarida Schivasappa', '2019-02-22 16:00:00', '2019-02-23 09:00:00', 'Agendado', 'Entrada Franca'),
(2, 20, NULL, 'Distribuição de Merendas', 'Pátio da Fundação', '2019-02-25 12:00:00', '2019-02-26 19:00:00', 'Confirmado', '10,00');

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
(5, '33784309070'),
(16, '49754855080'),
(17, '01431461229');

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
(19, NULL, 5, 'João Maria', '(32)32323-2323', 'joaomaria@email.com', '59132-370', 'Rua da PiraÃºna', 'PajuÃ§ara'),
(20, 3, NULL, 'yuuyuyuutyuyrurt', '(51)46641-7644', 'ewqewqq@email.com', '40230-510', 'Travessa dos Contentes', 'FederaÃ§Ã£o'),
(21, NULL, 16, 'Carlos', '(91)98311-1111', 'carlos@gmail.com', '96410-350', 'Rua Onze', 'SÃ£o Martins'),
(22, NULL, 17, 'Teste do Teste', '(00)00099-9999', 'teste1@email.com.br', '66093-330', 'Jardim Tapajos', 'Marco');

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
  MODIFY `idEvento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `fisico`
--
ALTER TABLE `fisico`
  MODIFY `idFisico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `juridico`
--
ALTER TABLE `juridico`
  MODIFY `idJuridico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `locatario`
--
ALTER TABLE `locatario`
  MODIFY `idLocatario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

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
