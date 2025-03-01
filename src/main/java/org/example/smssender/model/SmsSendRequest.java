package org.example.smssender.model;

import lombok.Data;

@Data
public class SmsSendRequest {
    private String phoneNumber;
    private String message;
}
