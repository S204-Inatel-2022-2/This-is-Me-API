package br.inatel.thisismeapi.classestotest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Date;

@ActiveProfiles("dev")
public final class JwtUtilToTest {


    public static String generateTokenResetPassword(String email, String code, String key, int exp){

        return JWT.create()
                .withClaim("email", email)
                .withClaim("number", Integer.valueOf(code))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(exp)))
                .sign(Algorithm.HMAC256(key));
    }

}
