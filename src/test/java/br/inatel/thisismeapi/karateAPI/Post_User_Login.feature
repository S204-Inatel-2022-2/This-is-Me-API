Feature: Post API Login

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Post Login (Success)
    Given path '/user/login'
    And request {"email": "testeOutros@test.com", "password": "12345"}
    When method post
    Then status 200
    And print response

#  Scenario: Post Login (Bad Request)
#    Given path '/user/login'
##    And request {"email": "", "password": ""}
#    When method post
#    Then status 400
#    And print response

  Scenario: Post Login (Unauthorized)
    Given path '/user/login'
    And request {"email": "user@user.com", "password": "123"}
    When method post
    Then status 401
    And print response



  Scenario: Post Login For Testing New Password (Success)
    Given path '/user/login'
    And request {"email": "gakira1217@hempyl.com", "password": "654321"}
    When method post
    Then status 200
    And print response
