package pro.sky.receptapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingridient {
    private String name;
    private int count;
    private String unit;
}
