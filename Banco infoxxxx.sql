create database dbinfox;

use dbinfox;

create table tbusuarios (
iduser int primary key,
usuario varchar (50) not null,
fone varchar (15),
login varchar (15) not null unique,
senha varchar (15) not null
);

create table tbclientes (
idcli int primary key auto_increment,
nomecli varchar (50) not null,
endcli varchar (100),
fonecli varchar (50) not null,
emailcli varchar (50)
);

create table tbos (
os int primary key auto_increment,
data_os timestamp default current_timestamp,
equipamento varchar (150) not null,
defeito varchar (150) not null,
servico varchar (150),
tecnico varchar (3),
valor decimal (10,2),
idcli int not null,
foreign key (idcli) references tbclientes (idcli)
);

/* --------------------------------------------------------------------------------------------------------------------------------------------------- */

insert into tbusuarios (iduser, usuario, fone, login, senha) values (1, 'Anderson Orleans', '48-99999-9999', 'andersonorleans', '123456');

insert into tbusuarios (iduser, usuario, fone, login, senha) values (2, 'Administrador', '48-99999-9988', 'admin', 'admin');

insert into tbusuarios (iduser, usuario, fone, login, senha) values (3, 'Aluno IFSC', '48-99999-9977', 'alunoifsc', '123456');

insert into tbclientes (nomecli, endcli, fonecli, emailcli) values ('João Arthur', 'Ribeirão da Ilha, 40', '99999-9999', 'joao@gmail.com');

insert into tbos (equipamento, defeito, servico, tecnico, valor, idcli) values ('Notebook', 'Não liga', 'Troca da fonte', 'Zé', 87.50, 1);

/* --------------------------------------------------------------------------------------------------------------------------------------------------- */

select 
O.os,equipamento,defeito,servico,valor,
C.nomecli,fonecli
from tbos as O
inner join tbclientes as C
on (O.idcli = C.idcli);

/* --------------------------------------------------------------------------------------------------------------------------------------------------- */

describe tbclientes;
describe tbos;
describe tbusuarios;

describe tbos;

/* --------------------------------------------------------------------------------------------------------------------------------------------------- */

select * from tbusuarios;
select * from tbclientes;
select * from tbos;

select * from tbclientes;

select idcli, nomecli,fonecli from tbclientes where nomecli like 'a%';

-- A linha abaixo cria um aplido aos campos da tabela:
select idcli as Id, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like 'a%';

/* --------------------------------------------------------------------------------------------------------------------------------------------------- */

alter table tbos add tipo varchar(15) not null after data_os;

alter table tbos add situacao varchar(20) not null after tipo;

ALTER TABLE tbos MODIFY tecnico VARCHAR(150);

select * from tbos;





