package pro.sky.receptapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Recept {
    private String name;
    private int time;
    private List<String> steps;
    private List<Ingridient> ingridients;
}
