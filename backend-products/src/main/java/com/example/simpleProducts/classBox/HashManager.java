package com.example.simpleProducts.classBox;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
En MySQL se puede cifrar igualmente mediante SHA2.
Esta clase ha quedado en desuso, y la parte de cifrado se realiza en la base de datos mediante un trigger.
*/
public class HashManager {

    private static final String ALGORITHM = "SHA-256";
    private static final String ENCODE = "UTF-8";


public static String encrypt(String psswrd) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    byte[] msg = psswrd.getBytes(ENCODE);

    MessageDigest dgt = MessageDigest.getInstance(ALGORITHM);
    dgt.reset();
    dgt.update(msg);

    return String.format("%064x", new BigInteger(1, dgt.digest()));

}

public static Boolean validatePassword(String a, String b){

        if (a.equals(b)){
            return true;
        }
        return false;

}



}
