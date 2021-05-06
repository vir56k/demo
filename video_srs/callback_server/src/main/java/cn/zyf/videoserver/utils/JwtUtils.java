//package com.vir56k.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Date;
//
//public class JwtUtils {
//    private static final String SECRET = "yunF09271619!@";
//    private static final long EXPIRE = 1000 * 60 * 60 * 12;  //过期时间，12小时
//
//    /**
//     * 构建一个 token
//     * 传入 userID
//     *
//     * @param userID
//     * @return
//     */
//    public static String sign(String userID) {
//        try {
//            Date now = new Date();
//            long expMillis = now.getTime() + EXPIRE;
//            Date expDate = new Date(expMillis);
//
//            Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
//            String token = JWT.create()
//                    .withIssuer("auth0")
//                    .withJWTId(userID)
//                    .withIssuedAt(now)
//                    .withExpiresAt(expDate)
//                    .sign(algorithmHS);
//            return token;
//        } catch (JWTCreationException exception) {
//            //Invalid Signing configuration / Couldn't convert Claims.
//            return null;
//        }
//    }
//
//    /**
//     * 解析 token
//     * 返回  是否有效
//     * @param token
//     * @return
//     */
//    public static boolean verify(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SECRET);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("auth0")
//                    .build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);
//            String userID = jwt.getId();
//            return userID != null && !"".equals(userID);
//        } catch (JWTVerificationException exception) {
//            //Invalid signature/claims
//            return false;
//        }
//    }
//
//    /**
//     * 解析 token
//     * 返回  userid
//     * @param token
//     * @return
//     */
//    public static String decode(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SECRET);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("auth0")
//                    .build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);
//            return jwt.getId();
//        } catch (JWTVerificationException exception) {
//            //Invalid signature/claims
//            return null;
//        }
//    }
//}
