package com.coruh.task.notification.service;

import com.coruh.task.notification.model.EmailEvent;

public interface EmailService {
    void sendEmail(EmailEvent emailEvent);
}
