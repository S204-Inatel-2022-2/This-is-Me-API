# language: pt

Funcionalidade: Teste de cadastro de usuário

#      * Eu como usuário quero cadastrar um novo usuário no sistema
#      * Para que eu possa ter acesso ao sistema
#      * E para que eu possa ter acesso aos dados do sistema

#  Cenário: Cadastro de usuário com sucesso
#    Dado que o usuário preencheu o formulário de cadastro com os dados válidos
#      | characterName | email          | password | verifyPassword |
#      | João          | joao@gmail.com | 123456   | 123456         |
#    Quando o usuário clicar no botão de cadastro e enviar o formulário
#    Então o usuário deve ser cadastrado com sucesso
#    E o status da resposta deve ser 200

  Cenário: checando rota de hello user não autorizado
    Dado que o usuário não está logado
    Quando o usuario acessar a rota helloUser
    Então o usuário deve receber uma mensagem de erro
    E o status da resposta deve ser 401
