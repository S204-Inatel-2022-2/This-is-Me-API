Feature: Everything about Quest API

  Background:
    * url 'https://timeapibyredfoxghs.herokuapp.com'
    * headers Accept = 'application/json'
    * def createdEmail =
    """
      function CreateEmail() {
      let horas = new Date().getHours().toString();
      let minutos = new Date().getMinutes().toString();
      let seg = new Date().getSeconds().toString();
      let email = horas + minutos + seg + 'id@email.com';
      return {"register": {"email": email , "password": "12345", "verifyPassword": "12345", "characterName": "test123"}, "login": {"email": email , "password": "12345"}};
      }
    """

    ### Post Quest
  Scenario: Post Quest, Criar uma quest não estando logado (Unauthorized)
    Given path '/quest'
    And request
    """
    {
      "hexColor": "string",
      "name": "string",
      "desc": "string",
      "startDate": "2022-11-23",
      "endDate": "2022-11-23",
      "skill": {
        "name": "Java",
        "percentage": 0
      },
      "week": [
        {
          "startTime": "00:00",
          "endTime": "00:00",
          "dayOfWeekCustom": "SUNDAY"
        }
      ]
    }
    """
    When method post
    Then status 401
    And print response


  Scenario: Post Quest, Criar usuário, Logar com usuário criado, Criar uma quest com todos os dados inválidos, Deletar usuário criado (Bad Request)
    Given path '/user/register'
    And def email = createdEmail()
    And request email.register
    When method post
    Then status 201
    And print response

    Given path '/user/login'
    And request email.login
    When method post
    Then status 200
    And print response

    Given path '/quest'
    And request
    """
    {
      "hexColor": "string",
      "name": "string",
      "desc": "string",
      "startDate": "2022-11-23",
      "endDate": "2022-11-23",
      "skill": {
        "name": "Java",
        "percentage": 0
      },
      "week": [
        {
          "startTime": "00:00",
          "endTime": "00:00",
          "dayOfWeekCustom": "SUNDAY"
        }
      ]
    }
    """
    When method post
    Then status 400
    And print response

    Given path '/user/login'
    And request {"email": "admin@admin.com", "password": "admin456"}
    When method post
    Then status 200
    And print response

    Given path '/admin/delete-user-by-email'
    And param email = email.login.email
    When method delete
    Then status 204
    And print response


  Scenario: Post Quest, Criar usuário, Logar com usuário criado, Criar uma quest com todos os dados inválidos, Deletar usuário criado (Bad Request)
    Given path '/user/register'
    And def email = createdEmail()
    And request email.register
    When method post
    Then status 201
    And print response

    Given path '/user/login'
    And request email.login
    When method post
    Then status 200
    And print response

    Given path '/quest'
    And request
    """
    {
      "hexColor": "string",
      "name": "string",
      "desc": "string",
      "startDate": "2022-11-23",
      "endDate": "2022-11-23",
      "skill": {
        "name": "Java",
        "percentage": 0
      },
      "week": [
        {
          "startTime": "00:00",
          "endTime": "00:00",
          "dayOfWeekCustom": "SUNDAY"
        }
      ]
    }
    """
    When method post
    Then status 400
    And print response

    Given path '/user/login'
    And request {"email": "admin@admin.com", "password": "admin456"}
    When method post
    Then status 200
    And print response

    Given path '/admin/delete-user-by-email'
    And param email = email.login.email
    When method delete
    Then status 204
    And print response

