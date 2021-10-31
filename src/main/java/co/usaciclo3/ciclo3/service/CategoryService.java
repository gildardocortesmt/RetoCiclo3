package co.usaciclo3.ciclo3.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usaciclo3.ciclo3.model.Category;
import co.usaciclo3.ciclo3.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category k){
        if(k.getId()==null){
            return categoryRepository.save(k);
        }else{
            //Valida si el Id existe, si es vacío no existe y lo guarda
            Optional<Category> kaux=categoryRepository.getCategory(k.getId());
            if (kaux.isEmpty()){
                return categoryRepository.save(k);
            }else{
                return k;
            }
        }
    }

    public Category update(Category k){
            if(k.getId()!=null){
                Optional<Category> e=categoryRepository.getCategory(k.getId());
                if(!e.isEmpty()){
                    if(k.getName()!=null){
                        e.get().setName(k.getName());
                    }
                    if(k.getDescription() !=null){
                        e.get().setDescription(k.getDescription());
                    }
                    
                    categoryRepository.save(e.get());
                    return e.get();
                }else{
                    return k;
                }
            }else{
                return k;
            }   
    }
    
    public boolean deleteCategory(int id){
        Boolean aBoolean=getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
