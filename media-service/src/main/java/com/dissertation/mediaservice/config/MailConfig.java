package com.dissertation.mediaservice.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
public class MailConfig implements WebMvcConfigurer {

    @Value("mail.host")
    private String host;

    @Value("mail.port")
    private Integer port;

    @Value("mail.username")
    private String username;

    @Value("mail.password")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", Boolean.toString(true));
        props.put("mail.smtp.starttls.enable", Boolean.toString(true));
        props.put("mail.debug", Boolean.toString(true));

        return mailSender;
    }

    @Bean
    public VelocityEngine getVelocityEngine() {
        VelocityEngine velocityEngine;

        velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(props);
        return velocityEngine;
    }

}
