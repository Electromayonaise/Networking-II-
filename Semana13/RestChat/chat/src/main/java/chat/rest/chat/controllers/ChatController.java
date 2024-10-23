package chat.rest.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import chat.rest.chat.model.Message;
import chat.rest.chat.model.Chat;
import chat.rest.chat.services.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody Message message, @ModelAttribute Chat chat){
        chatService.SendMessage(message, chat.getTo());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getChat(@ModelAttribute Chat chat) {
        return ResponseEntity.ok(chatService.getChat(chat.getSender(), chat.getTo()));
    }
    
}
