package com.learnspring.websocket.controller;

import com.learnspring.websocket.dto.Message;
import com.learnspring.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    @Autowired
    private WebSocketService webSocketService;

    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(Message message) {
        this.webSocketService.notifyFrontend(message.getMessageContent());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
