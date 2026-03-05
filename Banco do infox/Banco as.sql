use dbinfox;

describe tbusuarios;

describe tbclientes;

select * from tbusuarios;

select * from tbclientes;

select * from tbclientes where nomecli like 'J%';

select * from tbusuarios where login='admin' and senha='admin';

alter table tbusuarios add column perfil varchar (20) not null;

update tbusuarios set perfil='admin' where iduser=2;
update tbusuarios set perfil='admin' where iduser=1;
update tbusuarios set perfil='user' where iduser=3;







