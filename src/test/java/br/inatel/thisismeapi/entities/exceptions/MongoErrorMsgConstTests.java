package br.inatel.thisismeapi.entities.exceptions;

import br.inatel.thisismeapi.entities.Exceptions.MongoErrorMsgConst;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MongoErrorMsgConstTests {

    @Test
    public void testNewInstanceOfMongoErrorMsgConstTest(){
        MongoErrorMsgConst mongoErrorMsgConst = new MongoErrorMsgConst();

        assertEquals(MongoErrorMsgConst.class, mongoErrorMsgConst.getClass());
    }

    @Test
    public void testErrorE11000(){
        String msg = MongoErrorMsgConst.getMsgByCode("E11000");

        assertEquals("Já Existe uma conta cadastrada com esse e-mail!", msg);
    }

    @Test
    public void testUnknownError(){
        String code = "EUnknown123";
        String expected = "Erro " + code + " desconhecido!";
        String actual = MongoErrorMsgConst.getMsgByCode("EUnknown123");

        assertEquals(expected, actual);
    }

}
