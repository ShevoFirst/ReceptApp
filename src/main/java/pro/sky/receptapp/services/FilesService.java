package pro.sky.receptapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FilesService {
    @Value("${path.to.file.folder}")
    private String dataFilePath;

    public void saveToJsonFile(Object object, String fileName) {
        Path path = Path.of(dataFilePath, fileName);
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            Files.createDirectories(path.getParent());
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.writeString(path, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getDataFile(String fileName){
        return new File(dataFilePath+"/"+fileName);
    }
    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(dataFilePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean cleanFile(){
        try {
            Files.deleteIfExists(Path.of(dataFilePath));
            Files.createFile(Path.of(dataFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public String convertToTextFormat(List<Recept> recipes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            Recept recipe = recipes.get(i);
            sb.append("Рецепт ").append(i + 1).append(": ").append(recipe.getName()).append(System.lineSeparator());
            sb.append("Время приготовления: ").append(recipe.getTime()).append(" минут.").append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append("Ингредиенты:").append(System.lineSeparator());
            for (Ingridient ingredient : recipe.getIngridients()) {
                sb.append(ingredient.getName()).append(" - ").append(ingredient.getUnit()).append(" г.").append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
            sb.append("Инструкция приготовления:").append(System.lineSeparator());
            for (int j = 0; j < recipe.getSteps().size(); j++) {
                sb.append(j + 1).append(": ").append(recipe.getSteps().get(j)).append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}

