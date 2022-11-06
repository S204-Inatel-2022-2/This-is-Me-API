Feature: Post API Reset Password

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Post User Reset Password (Success)
    Given path '/user/reset/verify-code-reset'
    And request {"email": "gakira1217@hempyl.com", "number": 1067457}
    When method post
    Then status 200
    And def token = responseCookies['token_reset']['value']

    Given path '/user/reset/reset-password'
    And header Authorization = token
    And request {"password": "654321", "passwordVerify": "654321"}
    When method post
    Then status 200
    And print response


  Scenario: Post User Reset Password (Bad Request)
    Given path '/user/reset/reset-password'
    When method post
    Then status 400
    And print response


  Scenario: Post User Reset Password (Unauthorized)
    Given path '/user/reset/reset-password'
    And request {"password": "987654", "passwordVerify": "987654"}
    When method post
    Then status 401
    And print response

#  Scenario: Post User Reset Password (Internal Server Error)
#    Given path '/user/reset/reset-password'
#    And param email = "testeOutros@test.com"
#    When method post
#    Then status 500
#    And print response
