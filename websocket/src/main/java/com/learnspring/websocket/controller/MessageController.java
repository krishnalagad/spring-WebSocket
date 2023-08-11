package com.learnspring.websocket.controller;

import com.learnspring.websocket.dto.Message;
import com.learnspring.websocket.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseEntity<ResponseMessage> getMessage(final Message message) throws InterruptedException {
        System.out.println(message.getMessageContent());
        Thread.sleep(1000);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
                HtmlUtils.htmlEscape(message.getMessageContent())));
    }
}
