package co.usaciclo3.ciclo3.repository.crud;
import org.springframework.data.repository.CrudRepository;

import co.usaciclo3.ciclo3.model.Category;
public interface CategoryCrudRepository extends CrudRepository <Category,Integer> {
    
}
