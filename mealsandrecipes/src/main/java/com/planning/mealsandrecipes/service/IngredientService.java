import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    // Save an ingredient to the repository.
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Retrieve an ingredient by its ID.
    public Ingredient get(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    // Retrieve a list of all ingredients.
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    // Delete an ingredient by its ID.
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
