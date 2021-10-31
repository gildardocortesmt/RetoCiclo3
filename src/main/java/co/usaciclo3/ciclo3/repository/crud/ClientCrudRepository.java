package co.usaciclo3.ciclo3.repository.crud;
import org.springframework.data.repository.CrudRepository;

import co.usaciclo3.ciclo3.model.Client;

public interface ClientCrudRepository extends CrudRepository <Client,Integer> {
    
}
