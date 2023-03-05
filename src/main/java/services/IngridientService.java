package services;

import org.springframework.stereotype.Service;
import pro.sky.receptapp.model.Ingridient;

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
}
