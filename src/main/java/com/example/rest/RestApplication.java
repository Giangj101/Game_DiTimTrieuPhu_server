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
            serviceAccount = new FileInputStream("D:\\Code\\Android Game\\Game_DiTimTrieuPhu_server\\src\\main\\resources\\service.json");


            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://ditimtrieuphu-bbd1e-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FirebaseApp.initializeApp(options);


        SpringApplication.run(RestApplication.class, args);

    }

}
