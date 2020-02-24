create database sgvet;

use sgvet

create table cliente(
	cpf varchar(15) not null primary key,
	rg varchar(14) not null,
	nome varchar(35) not null,
	datanasc  date not null,
	sexo varchar(13) not null,
	fone1 varchar(15) not null,
	fone2 varchar(15),
	email varchar(40) not null,
	estado varchar(20) not null,
	cidade varchar(30) not null,
	endereco varchar(30) not null,
	bairro varchar(20) not null,
	numero varchar(5) not null,
	complemento varchar(40) not null,
	cep varchar(9) not null
	);

create table animal(
	cpfcliente varchar(15) not null,
	nome varchar(15) not null,
	familia varchar(15) not null,
	raca varchar(15) not null,
	especie varchar(15) not null,
	foreign key(cpfcliente) references cliente(cpf),
	primary key(cpfcliente,nome)
	);

create table funcionario(
	cpf varchar(14) not null primary key,
	rg varchar(15) not null,
	nome varchar(35) not null,
	datanasc  date not null,
	sexo varchar(20) not null,
	fone1 varchar(15) not null,
	fone2 varchar(15),
	email varchar(40) not null,
	estado varchar(20) not null,
	cidade varchar(30) not null,
	endereco varchar(30) not null,
	bairro varchar(20) not null,
	numero varchar(5) not null,
	complemento varchar(40) not null,
	cep varchar(9) not null,
	crmv varchar(20),
	id bigint not null,
	idsenha varchar(20) not null
	); 

create table procedimento(
	codproc serial not null primary key,
	cpfmedico varchar(14) not null,
	nomeanimal varchar(15) not null,
	cpfclianimal varchar(14) not null,
	data date not null,
	descricao varchar(500),
	numleito smallint
	);
