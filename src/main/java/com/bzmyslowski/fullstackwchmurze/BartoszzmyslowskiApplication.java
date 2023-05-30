package com.bzmyslowski.fullstackwchmurze;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
public class BartoszzmyslowskiApplication {

    //inicjalizacja loggera
    private static final Logger logger = LoggerFactory.getLogger(BartoszzmyslowskiApplication.class);

    public static void main(String[] args) {
        //informacja o tym, że server z aplikacją się uruchamia
        logger.info("Server application is starting...");
        //uruchomienie aplikacji
        SpringApplication.run(BartoszzmyslowskiApplication.class, args);
        //informacja z datą i godziną uruchomienia aplikacji / servera
        logger.info("Server started at {}", LocalDateTime.now());
        //wyświetlenie mojego imienia i nazwiska
        logger.info("Author: Bartosz Zmysłowski");
    }

    @Component
    public static class ServerPortLogger implements ApplicationListener<WebServerInitializedEvent> {
        @Override
        public void onApplicationEvent(WebServerInitializedEvent event) {
            //pobranie portu, na którym nasłuchuje aplikacja
            int serverPort = event.getWebServer().getPort();
            //wyświetlenie loga z informacją dotyczącą portu TCP, na którym aplikacja nasłuchuje zapytań klienta
            logger.info("Server is listening on port: " + serverPort);
        }
    }


}
