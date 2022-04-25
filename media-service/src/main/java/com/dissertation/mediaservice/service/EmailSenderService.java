package com.dissertation.mediaservice.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSenderService {

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ResourceLoader resourceLoader;
}
