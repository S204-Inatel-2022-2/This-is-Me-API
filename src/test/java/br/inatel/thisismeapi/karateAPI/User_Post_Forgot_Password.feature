Feature: Post API Forgot Password

  Background:
    * url 'http://localhost:8080'
    * headers Accept = 'application/json'

  Scenario: Post User Forgot Password (Success)
    Given path '/user/reset/forgot-password'
    #email válido para receber o código
    And param email = "gakira1217@hempyl.com"
    When method post
    Then status 200
    And print response


  Scenario: Post User Forgot Password (Bad Request)
    Given path '/user/reset/forgot-password'
    When method post
    Then status 400
    And print response


  Scenario: Post User Forgot Password With Numbers (Unauthorized)
    Given path '/user/reset/forgot-password'
    And param email = 123456789
    When method post
    Then status 401
    And print response


  Scenario: Post User Forgot Password With Valid Email (Unauthorized)
    Given path '/user/reset/forgot-password'
    And param email = "testeTest@falseEmail.com"
    When method post
    Then status 401
    And print response


    # Ocorreu quando faltou a senha do banco ou do email
#  Scenario: Post User Forgot Password (Internal Server Error)
#    Given path '/user/reset/forgot-password'
#    And param email = "testeOutros@test.com"
#    When method post
#    Then status 500
#    And print response
