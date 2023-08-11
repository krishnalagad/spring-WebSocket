package com.learnspring.websocket.service;

import com.learnspring.websocket.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void notifyFrontend(String message) {
        ResponseMessage response = new ResponseMessage(message);

        this.simpMessagingTemplate.convertAndSend("/topic/messages", response);
    }
}
