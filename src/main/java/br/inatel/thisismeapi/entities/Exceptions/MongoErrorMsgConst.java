package br.inatel.thisismeapi.entities.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoErrorMsgConst {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoErrorMsgConst.class);

    private final static String E11000 = "Já Existe uma conta cadastrada com esse e-mail!";

    public static String getMsgByCode(String code) {
        LOGGER.info("m=getMsgByCode, mongoCode={}", code);

        if (code.equals("E11000"))
            return E11000;
        return "Erro " + code + " desconhecido!";
    }
}
