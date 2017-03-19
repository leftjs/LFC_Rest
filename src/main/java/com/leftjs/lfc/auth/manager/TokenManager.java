package com.leftjs.lfc.auth.manager;

import com.leftjs.lfc.auth.model.TokenModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by jason on 2017/3/12.
 */
public class TokenManager {
    private static int expired = 72; // h
    private static String secret = "jasonwebtokensecret";


    //Sample method to construct a JWT
    public static TokenModel createToken(String subject) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + expired * 3600 * 1000;
        Date exp = new Date(expMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(exp);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return new TokenModel(builder.compact(), exp.getTime());
    }



    public static String getSubject(String token){


        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();
        if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
            // 已过期
            return "";

        }

        return claims.getSubject();
    }


}
