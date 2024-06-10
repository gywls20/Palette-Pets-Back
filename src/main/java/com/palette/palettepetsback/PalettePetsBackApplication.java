package com.palette.palettepetsback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;


@SpringBootApplication
public class PalettePetsBackApplication {

    public static void main(String[] args) {
        System.out.println("UUID : "+UUID.randomUUID());
        SpringApplication.run(PalettePetsBackApplication.class, args);
    }

}
