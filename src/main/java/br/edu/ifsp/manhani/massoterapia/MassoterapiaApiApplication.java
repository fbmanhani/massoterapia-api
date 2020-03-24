package br.edu.ifsp.manhani.massoterapia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.EnableFirebaseRealtimeDatabase;

@EnableFirebaseRealtimeDatabase
@SpringBootApplication
@ComponentScan("br.edu.ifsp.manhani.massoterapia")
public class MassoterapiaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MassoterapiaApiApplication.class, args);
    }

}
