package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/api/messages")
    public List<String> getMessages() {
        try {
            return messageService.getMessages();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error fetching messages");
        }
    }

}
