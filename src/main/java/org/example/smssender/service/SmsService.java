package org.example.smssender.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.trial_number}")
    private String fromNumber;

    @PostConstruct
    public void setup() {
        Twilio.init(accountSid, authToken);
    }

    public String sendSms(String phoneNumber, String smsMessage) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(fromNumber),
                    smsMessage).create();


            log.info("SMS sent! Status: {}", message.getStatus());

            return message.getStatus().toString();
        } catch (Exception e) {
            log.error("SMS sending failed: {}", e.getMessage());
            return "FAILED: " + e.getMessage();
        }
    }
}