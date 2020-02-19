package com.mycomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"etoegang._1_13.service_catalog","oasis.names.tc.saml._2_0","org.w3", "com.mycomp"})
public class MyProjApp {

    public static void main(String[] args) {
        SpringApplication.run(MyProjApp.class, args);
    }

}
