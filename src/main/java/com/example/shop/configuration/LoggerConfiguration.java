package com.example.shop.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class LoggerConfiguration {

//    @Bean
//    public LoggerContext configureLogging() {
//        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//        try {
//            JoranConfigurator configurator = new JoranConfigurator();
//            configurator.setContext(context);
//            context.reset();
//            configurator.doConfigure(new File("logback.xml"));
//            StatusPrinter.printInCaseOfErrorsOrWarnings(context);
//        } catch (Exception e) {
//            System.err.println("Ошибка загрузки конфигурации логирования: " + e.getMessage());
//        }
//        return context;
//    }

}
