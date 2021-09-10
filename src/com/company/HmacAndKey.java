package com.company;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HmacAndKey {

    SecureRandom random = new SecureRandom();

    public String key(){
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return stringBuilder(bytes);
    }

    public String stringBuilder(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes){
            stringBuilder.append(String.format("%02X", b));
        }
        return stringBuilder.toString();
    }

    public String hmac(int x) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] bytes = new byte[16];
        Mac signer = Mac.getInstance("HmacSHA3-224");
        SecretKeySpec keySpec = new SecretKeySpec(bytes, "HmacSHA3-224");
        signer.init(keySpec);

        String messageStr = "" + x;
        byte[] digest = signer.doFinal(messageStr.getBytes(StandardCharsets.UTF_8));
        return stringBuilder(digest);
    }
}
