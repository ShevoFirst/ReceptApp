package pro.sky.receptapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.receptapp.model.Ingridient;
import pro.sky.receptapp.model.Recept;
import services.ReceptService;

@RestController
@RequestMapping("/recept")
public class ReceptController {
    private ReceptService receptService;
    @PostMapping
    public ResponseEntity createRecept(@PathVariable Recept recept){
        receptService.addRecept(recept);
        return ResponseEntity.ok(recept);
    }

    @GetMapping("{id}")
    public ResponseEntity getRecept(@PathVariable long receptId){
        Recept recept = receptService.getRecept(receptId);
        if (recept == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recept);
    }
}