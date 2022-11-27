INSERT INTO dentista(nome, sobrenome, matricula) VALUES('Marcela','Mirella Laís da Costa','0000001');
INSERT INTO dentista(nome, sobrenome, matricula)  VALUES('Alícia','Giovanna da Conceição','0000002');
INSERT INTO dentista(nome, sobrenome, matricula)  VALUES('Marcela','Mirella Laís da Costa','0000003');
INSERT INTO dentista(nome, sobrenome, matricula)  VALUES('Vanessa','Benedita Porto','0000004');

INSERT INTO endereco(cep,logradouro,numero,bairro,localidade,uf) VALUES('01444-080','Rua Padre Jacob Saliba','128','Jardim Paulistano','São Paulo','SP');

INSERT INTO paciente(nome, sobrenome, rg, data_cadastro,endereco_id) VALUES('Elza','Aurora Pinto','383959408','2020-04-07',1);
INSERT INTO paciente(nome, sobrenome, rg, data_cadastro,endereco_id) VALUES('Calebe','Samuel Luan Almada','252713333','2020-04-01',null);
INSERT INTO paciente(nome, sobrenome, rg, data_cadastro,endereco_id) VALUES('Analu','Jennifer Mariana Corte Real','277384904','2020-04-29',null);
INSERT INTO paciente(nome, sobrenome, rg, data_cadastro,endereco_id) VALUES('Benedito','Julio Brito','236214214','2020-10-06',null);
INSERT INTO paciente(nome, sobrenome, rg, data_cadastro,endereco_id) VALUES('Isabella','Francisca Araújo','419234664','2020-11-06',null);

INSERT INTO consulta(paciente_id, dentista_id, dh_consulta) VALUES(1,2, '2022-12-29 11:00');
INSERT INTO consulta(paciente_id, dentista_id, dh_consulta) VALUES(2,1, '2022-12-29 09:00');
INSERT INTO consulta(paciente_id, dentista_id, dh_consulta) VALUES(3,3, '2022-12-29 10:00');

INSERT INTO usuario(username, password, is_admin) VALUES('Ettore', '1234', false);
INSERT INTO usuario(username, password, is_admin) VALUES('Pedro', '1234', true);