Feature: Get API Character

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Get Login+Character (Success)
    Given path '/user/login'
    And request {"email": "testeOutros@test.com", "password": "12345"}
    When method post
    Then status 200
    And print response

    Given path '/character/get-character'
    When method get
    Then status 200
    And print response


  Scenario: Get Character (Unauthorized)
    Given path '/character/get-character'
    When method get
    Then status 401
    And print response



  # Como fazer um Get que resulte um em Bad Request?
#  Scenario: Get Character Bad Request
#    Given path '/user/login'
#    And request {"email": "testeOutros@test.com", "password": "12345"}
#    When method post
#    Then status 200
#    And print response
#
#    Given path '/character/get-character'
#    When method get
#    Then status 400
#    And print response
