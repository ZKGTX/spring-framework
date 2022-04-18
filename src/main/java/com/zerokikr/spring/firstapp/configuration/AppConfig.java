package com.zerokikr.spring.firstapp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zerokikr.spring.firstapp") // все бины конфигурация ищет в указанном пакете

public class AppConfig {
}

