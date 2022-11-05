Feature: Get API User

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Get User Hello (Success)
    Given path '/user/login'
    And request {"email": "testeOutros@test.com", "password": "12345"}
    When method post
    Then status 200
    And print response

    Given path '/user/helloUser'
    When method get
    Then status 200
    And print response


  Scenario: Get User Hello (Unauthorized)
    Given path '/user/helloUser'
    When method get
    Then status 401
    And print response
