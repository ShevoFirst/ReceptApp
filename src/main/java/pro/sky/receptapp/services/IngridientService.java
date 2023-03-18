package pro.sky.receptapp.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Objects;

@Service
public class IngridientService {
    private HashMap<Long, Ingridient> ingridientMap = new HashMap<>();
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
        System.out.println(ingridientMap);
        System.out.println(ingridientMap.isEmpty());
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
        saveToFile();
        return false;
    }
    private void saveToFile(){
        filesService.saveToJsonFile(ingridientMap,"ingridient.json");
    }
    private void readFromFile(){
        String json = filesService.readFromFile("ingridient.json");
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        HashMap map = (HashMap) jsonParser.parseMap(json);
        ingridientMap = Objects.requireNonNull(map);
    }
    @PostConstruct
    private void init() {
        readFromFile();
        id = Long.valueOf(ingridientMap.size());
    }
}
