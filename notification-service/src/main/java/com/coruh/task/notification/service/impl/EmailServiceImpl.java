package com.coruh.task.notification.service.impl;

import com.coruh.task.notification.model.EmailEvent;
import com.coruh.task.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    @KafkaListener(topics = "${app.topic.notification}")
    public void sendEmail(EmailEvent emailEvent) {
        log.info("Email event received, {}",emailEvent);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("harundoguscoruh@gmail.com");
        message.setTo(emailEvent.getTo());
        message.setSubject(emailEvent.getSubject());
        message.setText(emailEvent.getBody());
        emailSender.send(message);
    }
}
