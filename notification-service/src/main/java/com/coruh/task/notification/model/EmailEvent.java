package com.coruh.task.notification.model;

import lombok.Data;

@Data
public class EmailEvent {
    private String to;
    private String subject;
    private String body;
    private String emailType;
}
