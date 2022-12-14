<h1 align="center">:point_up: CheckPoint</h1>
<h1 align="center">Clinica Odontologica.</h1>
<h2 align="center">Projeto integrador de BeckEnd DH -API de reserva de consultas </h2>

<br>
<br>
<div align="center">
<img style="display: block; margin: auto;" alt="versão" src="https://img.shields.io/badge/Vers%C3%A3o-1.0.0-blue?style=plastic&logo=exercism">
<img style="display: block; margin: auto;" alt="progresso" src="https://img.shields.io/badge/progresso-100%25-green?style=plastic&logo=lastpass">

</div>


<br>
<br>
<p align="center">
 <a href="#">Alan Menezes</a> • 
 <a href="#">Ettore Muniz</a> •
 <a href="#">Thiago Dorea Alves</a> • 
</p>

<br>
<br>

<h3 align="center">:bookmark_tabs: Documentação: <a href="https://documenter.getpostman.com/view/10174062/2s8YzUyhFP"> https://documenter.getpostman.com/view/10174062/2s8YzUyhFP </a>  </h3>
<br>
<br>
```
I M P O R T A N T E
 
Para utilizar todas as funções necessário utilizar o login e enviar o token em cada requisição.
- Login:  master 
- Senha:  123456 

* Ao se criar um paciente o login será o RG
* Ao se criar um Dentista o login será a MATRICULA 
```

### :closed_book: Bibliotecas utilizadas
- Java 17
- Spring Boot
- Spring Security
- Maven

### :clipboard: Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
1. [Maven](https://maven.apache.org/download.cgi)
2. [Java 17](https://www.oracle.com/java/technologies/downloads/)


### :pushpin: Resumo:
Este projeto consiste em uma API para um consultorio Odontologico que permita administrar a reserva/marcação
de consultas:

- <strong> Administração de dados odontológicos:</strong> Adicionar e modificar os dados dos dentistas. Registrar nome, sobrenome e matrícula de cadastro.
- <strong> Administração de pacientes: </strong> Registrar, modificar e excluir pacientes. De cada um se armazenam: nome, sobrenome, endereço, RG, data de cadastro.
- <strong> Login: </strong> Validar a entrada no sistema por meio de um login com nome de usuário e senha. Permitir que qualquer pessoa logada registre uma consulta, mas apenas aqueles que têm uma função de administração pode gerenciar dentistas e pacientes.
- <strong> Registrar consulta: </strong> Deve ser possível permitir que um paciente seja atribuído a uma consulta com um dentista em uma determinada data e hora.