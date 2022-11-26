package br.inatel.thisismeapi.cucumber.steps;

import br.inatel.thisismeapi.cucumber.config.RestAssuredExtension;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewAccountSteps {

    private static ResponseOptions<Response> response;

    @Dado("que o usuário preencheu o formulário de cadastro com os dados válidos")
    public void queOUsuárioPreencheuOFormulárioDeCadastroComOsDadosVálidos() {
    }

    @Dado("que o usuário não está logado")
    public void queOUsuárioNãoEstáLogado() {

    }

    @Então("o usuário deve receber uma mensagem de erro")
    public void oUsuárioDeveReceberUmaMensagemDeErro() {


    }

    @E("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int status) {
        assertEquals(status, response.getStatusCode());
    }

    @Quando("o usuario acessar a rota helloUser")
    public void oUsuarioAcessarARotaHelloUser() {
        response = RestAssuredExtension.GetOps("/helloUser");
    }

}
