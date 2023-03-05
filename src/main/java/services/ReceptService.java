package services;

import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;

import java.util.HashMap;
import java.util.Map;

public class ReceptService {
    private final Map<Long, Recept> receptMap = new HashMap<>();
    private Long id = 0L;
    public void addRecept(Recept recept){
        receptMap.put(id,recept);
        id++;
    };
    public Recept getRecept(Long id){
        return receptMap.get(id);
    };
}
