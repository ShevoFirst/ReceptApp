package pro.sky.receptapp.controller; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.receptapp.model.Recept;
import services.ReceptService;

@RestController
@RequestMapping("/recept")
public class ReceptController {
    private ReceptService receptService;


    @PostMapping
    public ResponseEntity createRecept(@RequestBody Recept recept) {
        receptService.addRecept(recept);
        return ResponseEntity.ok(recept);
    }

    @GetMapping("{id}")
    public ResponseEntity getRecept(@PathVariable long id){
        Recept recept = receptService.getRecept(id);
        if (recept == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recept);
    }

    @PutMapping("{id}")
    public ResponseEntity editRecept(@PathVariable long id,@RequestBody Recept recept){
        receptService.editRecept(id,recept);
        return ResponseEntity.ok(recept);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRecept(@PathVariable long id){
        receptService.deleteRecept(id);
        return ResponseEntity.ok(id);
    }
}