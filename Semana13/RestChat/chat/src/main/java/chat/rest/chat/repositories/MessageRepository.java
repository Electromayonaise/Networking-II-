package chat.rest.chat.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import chat.rest.chat.model.Message;


import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    
    
}
