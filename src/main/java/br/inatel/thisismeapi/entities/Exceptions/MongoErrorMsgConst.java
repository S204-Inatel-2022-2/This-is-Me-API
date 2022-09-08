package br.inatel.thisismeapi.entities.Exceptions;

public class MongoErrorMsgConst {
    private static String E11000 = "Já Existe uma conta cadastrada com esse e-mail";

    public static String getMsgByCode(String code) {
        if (code.equals("E11000"))
            return E11000;
        return "Erro " + code + " desconhecido";
    }
}
