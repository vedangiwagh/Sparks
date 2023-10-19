import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient get(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
