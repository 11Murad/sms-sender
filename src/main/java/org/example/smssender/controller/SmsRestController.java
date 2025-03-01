package org.example.smssender.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smssender.model.SmsSendRequest;
import org.example.smssender.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SmsRestController {
    @Autowired
    SmsService smsService;

    @PostMapping("processSms")
    public String processSms(@RequestBody SmsSendRequest sendRequest) {

        log.info("processSms started sendRequest: {}", sendRequest.toString());
        return smsService.sendSms(sendRequest.getPhoneNumber(), sendRequest.getMessage());
    }
}
