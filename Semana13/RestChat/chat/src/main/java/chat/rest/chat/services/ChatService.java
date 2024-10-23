package chat.rest.chat.services;
import chat.rest.chat.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chat.rest.chat.repositories.MessageRepository;
import chat.rest.chat.model.Message;
import chat.rest.chat.model.Chat;
import org.springframework.messaging.simp.SimpMessagingTemplate;


@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

    public void SendMessage(Message message, String to){
        Chat chat = chatRepository.findBySenderAndTo(message.getSender(), to);
        if(chat == null){
            chat = Chat.builder()
                .sender(message.getSender())
                .to(to)
                .build();
            chatRepository.save(chat);
        }
        message.setChat(chat);
        messageRepository.save(message);
        // notify the user
        simpleMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }


    public Chat getChat(String sender, String to){
        Chat chat = chatRepository.findBySenderAndTo(sender, to);
        if(chat != null){
            return chat;
        }
        else {
            return Chat.builder()
                .sender(sender)
                .to(to)
                .build();
        }

    }

    
}
