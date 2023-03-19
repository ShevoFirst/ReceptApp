package pro.sky.receptapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.services.IngridientService;

@RestController
@RequestMapping("/ingridient")
@Tag(name = "Ингридиенты", description = "CRUD операции и другие эндпоинты с транзакциями")
public class IngridientController {
    private final IngridientService ingridientServise;

    public IngridientController(IngridientService ingredientService) {
        this.ingridientServise = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "создание ингридиента"
    )
    public ResponseEntity createIngridient(@RequestBody Ingridient ingridient){
        ingridientServise.addIngridient(ingridient);
        return ResponseEntity.ok(ingridient);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "получение ингридиента"
    )
    public ResponseEntity getIngridient(@PathVariable long id){
        Ingridient ingridient = ingridientServise.getIngridient(id);
        if (ingridient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }


    @PutMapping("{id}")
    @Operation(
            summary = "редактирование ингридиента"
    )
    public ResponseEntity editIngridient(@PathVariable long id,@RequestBody Ingridient ingridient){
        ingridientServise.editIngridient(id,ingridient);
        return ResponseEntity.ok(ingridient);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "удаление ингридиента"
    )
    public ResponseEntity deleteIngridient(@PathVariable long id){
        ingridientServise.deleteIngridient(id);
        return ResponseEntity.ok(id);
    }
}
