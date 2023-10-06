package com.example.rest;

import java.io.*;
import java.util.Objects;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {

        FileInputStream serviceAccount =
                null;
        FirebaseOptions options;
        try {
            serviceAccount = new FileInputStream("serviceAccount.json");


            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FirebaseApp.initializeApp(options);


        SpringApplication.run(RestApplication.class, args);

    }

}
