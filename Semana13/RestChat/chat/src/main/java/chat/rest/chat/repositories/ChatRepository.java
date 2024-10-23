package chat.rest.chat.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import chat.rest.chat.model.Chat;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    Chat findBySenderAndTo(String sender, String to);
    
}
