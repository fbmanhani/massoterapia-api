package br.edu.ifsp.manhani.massoterapia;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.EnableFirebaseRealtimeDatabase;

import lombok.extern.slf4j.Slf4j;

@EnableFirebaseRealtimeDatabase
@SpringBootApplication
@Slf4j
public class MassoterapiaApiApplication {

    public static final String SERVER_PORT = "server.port";
    public static final String SERVER_CONTEXT_PATH = "server.servlet.contextPath";

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MassoterapiaApiApplication.class, args);
        try {
            Environment env = ctx.getEnvironment();
            String contextPath = env.getProperty("server.servlet.context-path");
            if (StringUtils.isBlank(contextPath)) {
                contextPath = "";
            }
            // @formatter:off
            log.info("\n\n *** \n\n" + 
                    "\tAplicação {} iniciada com sucesso!\n" + 
                    "\tDisponível nos endereços:\n" + 
                    "\tLocal: http://localhost:{}\n" + 
                    "\tExterno: http://{}:{}\n" + 
                    "\tSwagger Url: http://{}:{}\n" + 
                    "\tLocal Swagger Url: http://localhost:{}\n" + 
                    "\n *** \n\n",
                    env.getProperty("spring.application.name"),
                    env.getProperty(SERVER_PORT) + contextPath,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty(SERVER_PORT) + contextPath,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty(SERVER_PORT) + contextPath + "/swagger-ui.html",
                    env.getProperty(SERVER_PORT) + contextPath + "/swagger-ui.html");
            // @formatter:on
        } catch (UnknownHostException e) {
            log.error("Falha ao executar aplicacao: {}", e);
            ctx.close();
        }
    }

}
