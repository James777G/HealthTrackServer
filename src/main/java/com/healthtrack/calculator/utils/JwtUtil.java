package com.healthtrack.calculator.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


/**
 * Json Web Token Utility Class
 * Provides JWT basic operations
 * <p>
 *     这里简单讲一下 JWT （Json Web Token） 的逻辑：
 *     1. 当用户带着他的用户名和密码成功登录之后，服务器会将用户的用户名加密成一串 Token，并将这个 Token 返还用户
 *     2. 之后每次用户发出请求都会带着这个 Token
 *     3. Token 之中藏有之前在服务器中设下的基于密钥的签名，当每次用户带着 Token 来访问服务器的时候，服务器会去检验签名
 *     是否被修改过，只有没有被修改过的签名才会通过验证
 *     4. 同样的呢，服务器也会将这个 Token 什么时候过期写在 Token 里面，所以每次用户带着 Token 来请求的时候，服务器会来
 *     检测 token 是否已经过期
 *     5. 服务器本身不存储任何 Token
 * </p>
 */
@Slf4j
public class JwtUtil {

    // Obtain JWT Secret Key from environment variables
    private static final String SECRET_KEY = System.getenv("JWT_SECRET_KEY");
    private static final long EXPIRATION_TIME = 900000; // 15 minutes in milliseconds


    /**
     * The generateToken method creates a JWT for the given username.
     * It sets an expiration time on the token and signs it with your secret key.
     * @param username that needs to be parsed
     * @return JWT
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * The getUsernameFromToken method retrieves the username from a given token.
     * @param token JWT in String format
     * @return username
     */
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * This method checks if the status of JWT
     * @param token JWT in String format
     * @return boolean indicating status
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.warn("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.warn("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty");
        }
        return false;
    }
}
