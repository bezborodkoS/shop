package com.example.shop.configuration;

import org.springframework.context.annotation.Configuration;

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
