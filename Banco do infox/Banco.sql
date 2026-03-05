/* ===========================================================
   CRIAÇÃO DO BANCO DE DADOS
   =========================================================== */
CREATE DATABASE dbinfo;
USE dbinfo;

/* ===========================================================
   CRIAÇÃO DAS TABELAS
   =========================================================== */
-- Tabela de usuários
CREATE TABLE tbusuarios (
    iduser INT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    fone VARCHAR(15),
    login VARCHAR(15) NOT NULL UNIQUE,
    senha VARCHAR(15) NOT NULL,
    perfil VARCHAR(20) NOT NULL
);

-- Tabela de clientes
CREATE TABLE tbclientes (
    idcli INT PRIMARY KEY AUTO_INCREMENT,
    nomecli VARCHAR(50) NOT NULL,
    endcli VARCHAR(100),
    fonecli VARCHAR(50) NOT NULL,
    emailcli VARCHAR(50)
);

-- Tabela de ordens de serviço
CREATE TABLE tbos (
    os INT PRIMARY KEY AUTO_INCREMENT,
    data_os TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    equipamento VARCHAR(150) NOT NULL,
    defeito VARCHAR(150) NOT NULL,
    servico VARCHAR(150),
    tecnico VARCHAR(3),
    valor DECIMAL(10,2),
    idcli INT NOT NULL,
    FOREIGN KEY (idcli) REFERENCES tbclientes(idcli)
);

/* ===========================================================
   INSERÇÃO DE DADOS
   =========================================================== */
-- Usuários
INSERT INTO tbusuarios (iduser, usuario, fone, login, senha, perfil) 
VALUES 
    (1, 'Anderson Orleans', '48-99999-9999', 'andersonorleans', '123456', 'admin'),
    (2, 'Administrador', '48-99999-9988', 'admin', 'admin', 'admin'),
    (3, 'Aluno IFSC', '48-99999-9977', 'alunoifsc', '123456', 'user');

-- Clientes
INSERT INTO tbclientes (nomecli, endcli, fonecli, emailcli) 
VALUES 
    ('João Arthur', 'Ribeirão da Ilha, 40', '99999-9999', 'joao@gmail.com');

-- Ordens de serviço
INSERT INTO tbos (equipamento, defeito, servico, tecnico, valor, idcli) 
VALUES 
    ('Notebook', 'Não liga', 'Troca da fonte', 'Zé', 87.50, 1);

/* ===========================================================
   CONSULTAS
   =========================================================== */
-- Consulta de ordens de serviço com dados do cliente
SELECT 
    O.os,
    O.equipamento,
    O.defeito,
    O.servico,
    O.valor,
    C.nomecli,
    C.fonecli
FROM tbos AS O
INNER JOIN tbclientes AS C ON O.idcli = C.idcli;

-- Consulta geral
SELECT * FROM tbusuarios;
SELECT * FROM tbclientes;
SELECT * FROM tbos;

-- Consultas específicas
SELECT * FROM tbclientes WHERE nomecli LIKE 'J%';
SELECT * FROM tbusuarios WHERE login='admin' AND senha='admin';

/* ===========================================================
   ALTERAÇÕES DE TABELA
   =========================================================== */
-- Caso a coluna perfil ainda não exista
-- ALTER TABLE tbusuarios ADD COLUMN perfil VARCHAR(20) NOT NULL;

-- Atualizações de perfil (já foram inseridas na criação de usuários)
-- UPDATE tbusuarios SET perfil='admin' WHERE iduser=2;
-- UPDATE tbusuarios SET perfil='admin' WHERE iduser=1;
-- UPDATE tbusuarios SET perfil='user' WHERE iduser=3;

/* ===========================================================
   DESCRIÇÃO DAS TABELAS
   =========================================================== */
DESCRIBE tbusuarios;
DESCRIBE tbclientes;
DESCRIBE tbos;