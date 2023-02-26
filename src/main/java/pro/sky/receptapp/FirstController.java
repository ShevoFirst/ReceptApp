package pro.sky.receptapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class FirstController {
    @GetMapping()
    public String startApp(){
        return "Приложение запущенно";
    }
    @GetMapping("/info")
    public String projectInfo(){//
        return "Шевченко Дмитрий, Budget App, 27.02.2023, проект для сайта рецептов";
    }
}