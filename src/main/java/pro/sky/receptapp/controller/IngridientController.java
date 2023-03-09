package pro.sky.receptapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;
import services.IngridientService;

@RestController
@RequestMapping("/ingridient")
public class IngridientController {
    private IngridientService ingridientServise;

    @PostMapping
    public ResponseEntity createIngridient(@PathVariable Ingridient ingridient){
        ingridientServise.addIngridient(ingridient);
        return ResponseEntity.ok(ingridient);
    }

    @GetMapping("{id}")
    public ResponseEntity getIngridient(@PathVariable long id){
        Ingridient ingridient = ingridientServise.getIngridient(id);
        if (ingridient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }


    @PutMapping("{id}")
    public ResponseEntity editIngridient(@PathVariable long id,@RequestBody Ingridient ingridient){
        ingridientServise.editIngridient(id,ingridient);
        return ResponseEntity.ok(ingridient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteIngridient(@PathVariable long id){
        ingridientServise.deleteIngridient(id);
        return ResponseEntity.ok(id);
    }
}
