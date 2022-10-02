[![Java CI with Gradle](https://github.com/S204-Inatel-2022-2/This-is-Me-API/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/S204-Inatel-2022-2/This-is-Me-API/actions/workflows/gradle.yml)

<h1 align="center">This is Me (API)</h1>
<h3 align="center">:building_construction: Em Desenvolvimento :building_construction:</h3>

## ❓ Sobre o projeto
O objetivo da aplicação é proporcionar um ambiente onde o usuário terá um personagem representativo, onde o mesmo terá níveis e habilidades como há em jogos, possibilitando o usuário criar tarefas onde ganhará XP ao conclui-las. Essas tarefas pode ou não conter datas e períodos para conclui-las. O aplicativo deve ser fácil de utilizar e ajudar o usuário a gerenciar o seu tempo. :shipit:

## :hammer_and_wrench: Executando Localmente
- Baixe e instale [Java 17]()
- Baixe e instale [MongoDB Community Server](https://www.mongodb.com/try/download/community) (Pode utilizar um container do docker igualmente)
- Recomendado que utilize o [Intellj](https://www.jetbrains.com/pt-br/idea/)
### Como executar com Intellj
- Clone o projeto e importe para seu Intellj
- Dentro de ``src/main/java/br/inatel/thisismeapi`` clique com botão direito sobre ``TimeApplication`` e selecione a opção ``Modify Run Configuration...``
- Verifique se está utilizando o JDK 17
- Dentro de VM Options copie ``-Dspring.profiles.active=dev`` e cole para rodar com configurações de dev (Utiliza de um banco local, necessario o MongoDB)
- Ou dentro de VM Options copie ``-Dspring.profiles.active=qa`` e cole para rodar com configurações de qa, utiliza do cluster de qa para realizar test.
- Verifique nas documentações privadas as chaves de acesso ao banco para configurar as variaveis de ambiente na sua IDE como (PASSWORD_MONGODB_QA e as PRIVATE_KEYS)
- Com os passos acima concluido execute o TimeApplication (caso utilize outra IDE deve-se seguir os passos necessarios para configurar as variaveis de ambiente de sua ide)
- Com a aplicação rodando abra o seu navegador e acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para acessar a interface do swagger e ver os endpoints da aplicação.
- Ao acessar irá pedir um login e senha utilize o seu login e senha cadastrado, se não possuir solicite um login e senha a um admin.

## 💻 Tecnologias
As seguintes tecnologias foram utilizadas na construção do projeto:

- **[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)**
- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[Spring Security](https://spring.io/projects/spring-security)**
- **[Junit](https://junit.org/junit5/docs/current/user-guide/)**
- **[Jacoco](https://www.eclemma.org/jacoco/)**
- **[MongoDB](https://www.mongodb.com/)**

## :iphone: Links Apps
- [Flutter](https://github.com/S204-Inatel-2022-2/This-is-Me-App)
- [Heroku Server](https://timeapibyredfoxghs.herokuapp.com/swagger-ui/index.html)

## :card_index_dividers: Documentações
- [Confluence](https://redandmoon.atlassian.net/wiki/spaces/NW/pages/1736713/This+is+Me+App)
