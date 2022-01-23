package cz.csas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CsasDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsasDemoApplication.class, args);
    }

}
