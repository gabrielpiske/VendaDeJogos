CREATE SCHEMA vendajogos;
USE vendajogos;

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `enderececo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `jogo` (
  `idjogo` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  `dataLancamento` varchar(45) NOT NULL,
  `classificacaoIndicativa` int NOT NULL,
  `imagem` longblob,
  PRIMARY KEY (`idjogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `carrinho` (
  `idcarrinho` INT NOT NULL AUTO_INCREMENT,
  `valorTotal` DOUBLE NOT NULL,
  PRIMARY KEY (`idcarrinho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `carrinho_jogo` (
  `idcarrinho` INT NOT NULL,
  `idjogo` INT NOT NULL,
  PRIMARY KEY (`idcarrinho`, `idjogo`),
  FOREIGN KEY (`idcarrinho`) REFERENCES `carrinho` (`idcarrinho`) ON DELETE CASCADE,
  FOREIGN KEY (`idjogo`) REFERENCES `jogo` (`idjogo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;