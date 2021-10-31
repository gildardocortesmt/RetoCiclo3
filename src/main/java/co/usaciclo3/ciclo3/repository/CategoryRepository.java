package co.usaciclo3.ciclo3.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.usaciclo3.ciclo3.model.Category;
import co.usaciclo3.ciclo3.repository.crud.CategoryCrudRepository;

@Repository
public class CategoryRepository {
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;
    public List<Category> getAll(){
        return (List<Category>) categoryCrudRepository.findAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryCrudRepository.findById(id);
    }

    public Category save(Category k){
        return categoryCrudRepository.save(k);
    }

    public void delete(Category category){
        categoryCrudRepository.delete(category);
    }

}
