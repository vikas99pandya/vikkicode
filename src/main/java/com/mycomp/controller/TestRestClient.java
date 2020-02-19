package com.mycomp.controller;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;

public class TestRestClient {
    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier(){

                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        if (hostname.equals("localhost")) {
                            return true;
                        }
                        return false;
                    }
                });
    }

    public static void main(String[] args) throws Exception{

        URL url = new URL("https://localhost:8443/mydata");
        trustCert();
        HttpsURLConnection  conn = (HttpsURLConnection) url.openConnection();
        if(conn!=null) {

//            System.out.println("Response Code : " + conn.getResponseCode());
//            System.out.println("Cipher Suite : " + conn.getCipherSuite());
//            System.out.println("\n");
//            Certificate[] certs = conn.getServerCertificates();
//            for (Certificate cert : certs) {
//                System.out.println("Cert Type : " + cert.getType());
//                System.out.println("Cert Hash Code : " + cert.hashCode());
//                System.out.println("Cert Public Key Algorithm : "
//                        + cert.getPublicKey().getAlgorithm());
//                System.out.println("Cert Public Key Format : "
//                        + cert.getPublicKey().getFormat());
//                System.out.println("\n");
//            }


            BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));

                String input;

                while ((input = br.readLine()) != null) {
                    System.out.println(input);
                }
                br.close();

            }
        }



    private static void trustCert() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }


}
