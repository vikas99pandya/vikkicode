package com.mycomp.exception;


import java.nio.charset.Charset;
import java.util.Base64;

public class TestMain {

    public static void main(String []args){



        String auth="user:password";

        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        System.out.println("hi.."+authHeader);

    }


}
