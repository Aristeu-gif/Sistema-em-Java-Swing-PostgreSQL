CREATE TABLE public.leilao
(
  id serial NOT NULL UNIQUE,
  item varchar(200) NOT NULL,
  precoinicial double precision NOT NULL,
  situacao varchar(100) DEFAULT NULL,
  CONSTRAINT pkleilao PRIMARY KEY (id)
)

CREATE TABLE public.pessoa
(
  id serial NOT NULL UNIQUE,
  cpf varchar(15) NOT NULL UNIQUE,
  nome varchar(200),
  sexo varchar(2) ,
  dataNascimento date NOT NULL, 
  CONSTRAINT pkpessoa PRIMARY KEY (id)
)

CREATE TABLE public.lance
(
  id serial NOT NULL UNIQUE,
  idLeilao serial NOT NULL ,
  idPessoa serial NOT NULL,
  valorDoLance double precision NOT NULL,
  FOREIGN KEY (idLeilao) REFERENCES leilao (id),
  FOREIGN KEY (idPessoa) REFERENCES pessoa (id),
  CONSTRAINT pklance PRIMARY KEY (id)
)
