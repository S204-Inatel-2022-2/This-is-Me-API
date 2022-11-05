Feature: Post API Reset Verify Code

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Post User Forgot Password Verify Code (Success)
    Given path '/user/reset/verify-code-reset'
    And request {"email": "gakira1217@hempyl.com", "number": 1067457}
    When method post
    Then status 200
    And print response
    And print responseCookies
    And print responseCookies['token_reset']['value']


  Scenario: Post User Reset Password Verify Code (Bad Request)
    Given path '/user/reset/verify-code-reset'
    When method post
    Then status 400
    And print response


  Scenario: Post User Reset Password Verify Code Invalid Email (Unauthorized)
    Given path '/user/reset/verify-code-reset'
    And request {"email": "string", "number": 0}
    When method post
    Then status 401
    And print response


  Scenario: Post User Reset Password Verify Code Without Code (Unauthorized)
    Given path '/user/reset/verify-code-reset'
    And request {"email": "testeOutros@test.com", "number": 0}
    When method post
    Then status 401
    And print response


  # Descobrir como fazer
#  Scenario: Post User Reset Password Verify Code (Internal Server Error)
#    Given path '/user/reset/verify-code-reset'
#    And request {"email": "gakira1217@hempyl.com", "number": 547774}
#    When method post
#    Then status 500
#    And print response
