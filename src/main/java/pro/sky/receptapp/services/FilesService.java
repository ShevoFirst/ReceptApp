package pro.sky.receptapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(dataFilePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean cleanFile(){
        try {
            Files.deleteIfExists(Path.of(dataFilePath));
            Files.createFile(Path.of(dataFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
