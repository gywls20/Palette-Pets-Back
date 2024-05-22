package com.palette.palettepetsback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PalettePetsBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalettePetsBackApplication.class, args);
    }

}
