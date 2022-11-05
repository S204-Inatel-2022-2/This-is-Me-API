Feature: Post API Character

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Post Character, set clothes (Success)
    Given path '/user/login'
    And request {"email": "testeOutros@test.com", "password": "12345"}
    When method post
    Then status 200
    And print response

    Given path '/character/set-clothes'
    And param number = 15
    When method post
    Then status 200
    And print response


  Scenario: Post Character, set clothes (Bad Request)
    Given path '/user/login'
    And request {"email": "testeOutros@test.com", "password": "12345"}
    When method post
    Then status 200
    And print response

    Given path '/character/set-clothes'
    And param number = "a"
    When method post
    Then status 400
    And print response


  Scenario: Post Character, set clothes (Unauthorized)
    Given path '/character/set-clothes'
    And param number = 15
    When method post
    Then status 401
    And print response
