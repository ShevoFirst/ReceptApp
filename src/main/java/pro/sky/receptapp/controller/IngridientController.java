package pro.sky.receptapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.receptapp.model.Ingridient;
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
    public ResponseEntity getIngridient(@PathVariable long ingridientId){
        Ingridient ingridient = ingridientServise.getIngridient(ingridientId);
        if (ingridient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }
}
