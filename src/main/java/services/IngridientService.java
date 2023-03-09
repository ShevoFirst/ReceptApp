package services;

import org.springframework.stereotype.Service;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngridientService {
    private final Map<Long, Ingridient> ingridientMap = new HashMap<>();
    private Long id = 0L;
    public void addIngridient(Ingridient ingridient){
        ingridientMap.put(id,ingridient);
        id++;
    }
    public Ingridient getIngridient(Long id){
        return ingridientMap.get(id);
    }

    public Ingridient editIngridient(long id , Ingridient ingridient){
        if (ingridient == null) {
            return null;
        }
        ingridientMap.put(id,ingridient);
        return ingridient;
    }
    public boolean deleteIngridient(long id) {
        if (ingridientMap.containsKey(id)) {
            ingridientMap.remove(id);
            return true;
        }
        return false;
    }
}
