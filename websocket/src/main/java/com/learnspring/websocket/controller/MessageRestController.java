package com.learnspring.websocket.controller;

import com.learnspring.websocket.dto.Message;
import com.learnspring.websocket.service.WebSocketService;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    @Autowired
    private WebSocketService webSocketService;

    private Logger logger = LoggerFactory.getLogger(MessageRestController.class);

    @PostMapping("/send-message")
    @Hidden
    public ResponseEntity<?> sendMessage(Message message) {
        this.webSocketService.notifyToFrontend(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/test")
    public String testApi() {
        return "This is testing API";
    }

    @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public Message getContent(@RequestBody Message message) {
        this.logger.info("Message object received: {}", message);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
}
