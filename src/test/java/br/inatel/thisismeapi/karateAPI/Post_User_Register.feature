Feature: Post API Register

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'
    * def createdEmail =
    """
      function CreateEmail() {
      let horas = new Date().getHours().toString();
      let minutos = new Date().getMinutes().toString();
      let seg = new Date().getSeconds().toString();
      let email = horas + minutos + seg + 'Id@email.com';
      return {"email": email , "password": "12345", "verifyPassword": "12345", "characterName": "test123"};
      }
    """

  Scenario: Post User, Registrando usuário com todas as informações válidas e preenchidas de forma Automática
    Given path '/user/register'
    And request createdEmail()
    When method post
    Then status 201
    And print response


  Scenario: Post User, Registrando usuário com a senha inferior a 5 caracteres (Bad Request)
    Given path '/user/register'
    And request {"email": "testSenha1@test.com", "password": "1234", "verifyPassword": "1234", "characterName": ""}
    When method post
    Then status 400
    And print response


  Scenario: Post User, Registrando usuário com a senha superior a 30 caracteres (Bad Request)
    Given path '/user/register'
    And request {"email": "testesteste", "password": "0123456789012345678901234567890123456789", "verifyPassword": "0123456789012345678901234567890123456789", "characterName": ""}
    When method post
    Then status 400
    And print response


  Scenario: Post User, Registrando usuário com email inválido, senhas incompatíveis e characterName em branco (Bad Request)
    Given path '/user/register'
    And request {"email": "testesteste", "password": "123456789", "verifyPassword": "123456", "characterName": ""}
    When method post
    Then status 400
    And print response


  Scenario: Post User, Registrando usuário com duplicidade de emails (Conflict)
    Given path '/user/register'
    And def email = createdEmail()
    And request email
    When method post
    Then status 201
    And print response

    Given path '/user/register'
    And request email
    When method post
    Then status 409
    And print response


#   Utilizado apenas quando reset o banco e precisa de um email para realizar os outros testes.
#  Scenario: Post User, registrando apenas o email que será utilizada na maioria dos testes
#    Given path '/user/register'
#    And request {"email": "testeOutros@test.com", "password": "12345", "verifyPassword": "12345", "characterName": "littleTest"}
#    When method post
#    Then status 201
#    And print response
#
#  Scenario: Post User, registrando apenas o email que será utilizada na maioria dos testes
#    Given path '/user/register'
#    And request {"email": "gakira1217@hempyl.com", "password": "12345", "verifyPassword": "12345", "characterName": "littleTest"}
#    When method post
#    Then status 201
#    And print response
