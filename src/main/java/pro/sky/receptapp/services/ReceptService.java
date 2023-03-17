package pro.sky.receptapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pro.sky.receptapp.model.Recept;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReceptService {
    private Map<Long, Recept> receptMap = new HashMap<>();
    private Long id = 0L;

    private final FilesService filesService;

    public ReceptService(FilesService filesService) {
        this.filesService = filesService;
    }

    public void addRecept(Recept recept){
        receptMap.put(id,recept);
        id++;
        saveToFile();
    };
    public Recept getRecept(Long id){
        return receptMap.get(id);
    };
    public Recept editRecept(long id , Recept recept){
        if (recept == null) {
            return null;
        }
        receptMap.put(id,recept);
        saveToFile();
        return recept;
    }
    public boolean deleteRecept(long id){
        if (receptMap.containsKey(id)){
            receptMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }
    private void saveToFile(){
        filesService.saveToJsonFile(receptMap,"recept.json");
    }
    private void readFromFile(){
      String json = filesService.readFromFile("recept.json");
        try {
            receptMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recept>>() {});
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
