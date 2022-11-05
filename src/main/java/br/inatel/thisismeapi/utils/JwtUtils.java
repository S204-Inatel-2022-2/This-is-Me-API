package br.inatel.thisismeapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.util.Date;

public final class JwtUtils {

    public static String createJwtResetTokenWith(String email, Integer number, long expiresAt, String privateKey) {

        return JWT.create()
                .withClaim("email", email)
                .withClaim("number", number)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(expiresAt)))
                .sign(Algorithm.HMAC256(privateKey));
    }

    public static DecodedJWT decodedJWT(String jwt, String privateKey) {
        return JWT.require(Algorithm.HMAC256(privateKey))
                .build()
                .verify(jwt);
    }
}
