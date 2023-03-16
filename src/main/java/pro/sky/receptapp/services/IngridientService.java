package pro.sky.receptapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngridientService {
    private HashMap<Long,Ingridient> ingridientMap = new HashMap<>();
    private final FilesService filesService;
    private Long id = 0L;

    public IngridientService(FilesService filesService) {
        this.filesService = filesService;
    }

    public void addIngridient(Ingridient ingridient){
        ingridientMap.put(id,ingridient);
        id++;
        saveToFile();
    }
    public Ingridient getIngridient(Long id){
        System.out.println(ingridientMap.get(0));
        return ingridientMap.get(id);
    }

    public Ingridient editIngridient(long id , Ingridient ingridient){
        if (ingridient == null) {
            return null;
        }
        ingridientMap.put(id,ingridient);
        saveToFile();
        return ingridient;
    }
    public boolean deleteIngridient(long id) {
        if (ingridientMap.containsKey(id)) {
            ingridientMap.remove(id);
            return true;
        }
        return false;
    }
    private void saveToFile(){
        filesService.saveToJsonFile(ingridientMap,"ingridient");
    }
    private void readFromFile(){
        String json = filesService.readFromFile("ingridient.json");
        System.out.println(json);
        try {
            ingridientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingridient>>() {
            });
            System.out.println(ingridientMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct
    private void init() {
        try {
            readFromFile();
        }catch (Exception e){

        }
    }
}
