package pro.sky.receptapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.receptapp.model.Recept;
import pro.sky.receptapp.services.FilesService;
import pro.sky.receptapp.services.ReceptService;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/files")
@Tag(name = "Работа с файлами", description = "CRUD операции и другие эндпоинты с файлами")
public class FileController {
    private final FilesService filesService;
    private final ReceptService recipeService;

    public FileController(FilesService filesService, ReceptService recipeService) {
        this.filesService = filesService;
        this.recipeService = recipeService;
    }
    @GetMapping("/get/{fileName}")
    @Operation(
            summary = "получение файла"
    )
    public ResponseEntity<InputStreamResource> downloadDataFile(@PathVariable String fileName) throws FileNotFoundException {
        File file = filesService.getDataFile(fileName);
        if (file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
             return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+fileName)
                    .body(resource);
        }else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/import/{name}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file, @PathVariable String name){
        filesService.cleanFile();
        File dataFile = filesService.getDataFile(name);

        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(),fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/get/recept")
    @Operation(
            summary = "получение TXT файла рецептов"
    )
    public ResponseEntity<InputStreamResource> downloadReceptTXTFile() throws FileNotFoundException {
        File file = filesService.getDataFile("recept.json");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.txt\"")
                .body(resource);
    }

}
