package co.usaciclo3.ciclo3.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.usaciclo3.ciclo3.model.Message;
import co.usaciclo3.ciclo3.repository.crud.MessageCrudRepository;

@Repository
public class MessageRepository {
    @Autowired
    private MessageCrudRepository messageCrudRepository;
    
    public List<Message> getAll(){
        return (List<Message>) messageCrudRepository.findAll();
    }

    public Optional<Message> getMessage(int id){
        return messageCrudRepository.findById(id);
    }

    public Message save(Message m){
        return messageCrudRepository.save(m);
    }
    
    public void delete(Message message){
        messageCrudRepository.delete(message);
    }
}
