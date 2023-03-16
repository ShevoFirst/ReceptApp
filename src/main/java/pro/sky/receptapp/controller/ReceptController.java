package pro.sky.receptapp.controller; 

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.receptapp.model.Recept;
import services.ReceptService;

@RestController
@RequestMapping("/recept")
@Tag(name = "Рецепты", description = "CRUD операции и другие эндпоинты с рецептами")

public class ReceptController {
    private ReceptService receptService;


    @PostMapping
    @Operation(
            summary = "создание рецепта"
    )
    public ResponseEntity createRecept(@RequestBody Recept recept) {
        receptService.addRecept(recept);
        return ResponseEntity.ok(recept);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "получение рецепта"
    )
    public ResponseEntity getRecept(@PathVariable long id){
        Recept recept = receptService.getRecept(id);
        if (recept == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recept);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "редактирование рецепта"
    )
    public ResponseEntity editRecept(@PathVariable long id,@RequestBody Recept recept){
        receptService.editRecept(id,recept);
        return ResponseEntity.ok(recept);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "удаление рецепта"
    )
    public ResponseEntity deleteRecept(@PathVariable long id){
        receptService.deleteRecept(id);
        return ResponseEntity.ok(id);
    }
}